package by.shiler.neurox.event;

import by.shiler.neurox.parser.RouletteEventParser;
import by.shiler.neurox.util.PropertiesLoader;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgeny Yushkevich on 13.06.2017.
 */
public class EventBus {

    private final static Logger LOG = LogManager.getLogger(EventBus.class);

    private static Request request;
    private static OkHttpClient okHttpClient;
    private static Observable<RouletteEvent> events;
    private static boolean isRunning = false;

    static {
        try {
            String url = PropertiesLoader.load("application.properties").getProperty("ws.url");
            request = new Request.Builder().url(url).build();
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .connectTimeout(0, TimeUnit.MILLISECONDS)
                    .pingInterval(25000, TimeUnit.MILLISECONDS)
                    .build();
        } catch (IOException e) {
            LOG.fatal(e);
        }
    }

    public static void start() {
        isRunning = true;
        events = Observable.create(subscriber -> {
            PingPonger pingPonger = new PingPonger();
            okHttpClient.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    subscriber.onStart();
                    webSocket.send("2probe");
                    webSocket.send("5");
                    pingPonger.start(webSocket);
                    LOG.info("WebSocket opened");
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {
                    if (!isRunning) {
                        webSocket.close(1000, "Requested by user");
                    }
                    subscriber.onNext(text);
                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    pingPonger.stop();
                    subscriber.onCompleted();
                    subscriber.unsubscribe();
                    webSocket.cancel();
                    LOG.info("WebSocket is closing");
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                    pingPonger.stop();
                    LOG.error(t);
                    subscriber.onError(t);
                }

            });
        }).cast(String.class)
                .filter(s -> s.contains("\"roulette:"))
                .map(RouletteEventParser::parse);
    }

    public static void stop() {
        isRunning = false;
    }

    public static Observable<RouletteEvent> events() {
        return events;
    }

    private static class PingPonger {

        private boolean isRunning = false;
        private Thread thread;

        private void start(WebSocket webSocket) {
            isRunning = true;
            thread = new Thread(() -> {
                while (isRunning) {
                    webSocket.send("2");
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        LOG.info("Ping ponger stopped.");
                    }
                }
            });
            thread.start();
        }

        private void stop() {
            isRunning = false;
            thread.interrupt();
        }

    }

}
