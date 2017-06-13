package by.shiler.neurox.event;

import by.shiler.neurox.parser.RouletteEventParser;
import by.shiler.neurox.util.PropertiesLoader;
import com.appunite.websocket.rx.RxWebSockets;
import com.appunite.websocket.rx.messages.RxEventStringMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class EventBus {

    private final static Logger LOG = LogManager.getLogger(EventBus.class);

    private static Request request = null;
    private static Observable<RouletteEvent> events;
    private static OkHttpClient okHttpClient;

    static {
        try {
            String url = PropertiesLoader.load("application.properties").getProperty("ws.url");
            request = new Request.Builder().get()
                    .url(url)
                    .build();
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(0, TimeUnit.MILLISECONDS)
                    .pingInterval(10, TimeUnit.SECONDS)
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .build();
        } catch (IOException e) {
            LOG.error(e);
        }

    }

    public static void start() {
        events = new RxWebSockets(okHttpClient, request)
                .webSocketObservable()
                .filter(event -> event.getClass().equals(RxEventStringMessage.class))
                .cast(RxEventStringMessage.class)
                .filter(rxEventStringMessage -> rxEventStringMessage.message().contains("\"roulette:"))
                .map(o -> RouletteEventParser.parse(o.message()));
    }

    public static Observable<RouletteEvent> events() {
        return events;
    }

}
