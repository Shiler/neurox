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

    static {
        try {
            String url = PropertiesLoader.load("application.properties").getProperty("ws.url");
            request = new Request.Builder().url(url).build();
            okHttpClient = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS)
                    .connectTimeout(0, TimeUnit.MILLISECONDS).pingInterval(25000, TimeUnit.MILLISECONDS).build();
        } catch (IOException e) {
            LOG.fatal(e);
        }
    }

    public static void start() {
        events = Observable.create(subscriber -> {
            okHttpClient.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    webSocket.send("2probe");
                    webSocket.send("5");
                    startPingPonger(webSocket);
                    LOG.info("WebSocket opened");
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {
                    subscriber.onNext(text);
                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    LOG.info("WebSocket is closing");
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                    LOG.error(t);
                    subscriber.onError(t);
                }

            });
        }).cast(String.class)
                .filter(s -> s.contains("\"roulette:"))
                .map(RouletteEventParser::parse);

    }

    public static Observable<RouletteEvent> events() {
        return events;
    }

    private static void startPingPonger(WebSocket webSocket) {
        Thread thread = new Thread(() -> {
            while (true) {
                webSocket.send("2");
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
