package by.shiler.neurox;

import by.shiler.neurox.parser.RouletteEventParser;
import com.appunite.websocket.rx.RxWebSockets;
import com.appunite.websocket.rx.messages.RxEventStringMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Subscription;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        final Request request = new Request.Builder().get()
                .url("ws://ws1.csgobounty.com/socket.io/?EIO=3&transport=websocket")
                .build();
        final Subscription subscribe = new RxWebSockets(new OkHttpClient(), request)
                .webSocketObservable()
                .filter(event -> event.getClass().equals(RxEventStringMessage.class))
                .cast(RxEventStringMessage.class)
                .filter(rxEventStringMessage -> rxEventStringMessage.message().contains("\"roulette:"))
                .map(o -> RouletteEventParser.parse(o.message()))
                .subscribe(System.out::println);
        Thread.sleep(60000);
        subscribe.unsubscribe();
    }

}
