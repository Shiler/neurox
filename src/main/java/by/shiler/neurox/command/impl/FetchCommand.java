package by.shiler.neurox.command.impl;

import by.shiler.neurox.command.ICommand;
import by.shiler.neurox.event.EventBus;
import by.shiler.neurox.event.RouletteEvent;
import by.shiler.neurox.event.game.RouletteGameEvent;
import by.shiler.neurox.event.player.RoulettePlayerEvent;
import by.shiler.neurox.event.result.RouletteResultEvent;
import rx.Observable;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class FetchCommand implements ICommand {

    private int gamesToFetch;

    public FetchCommand(int gamesToFetch) {
        this.gamesToFetch = gamesToFetch;
    }

    @Override
    public void execute() {
        EventBus.start();
        Observable<RouletteEvent> stopEvents = EventBus.events()
                .filter(e -> e.getClass().equals(RouletteGameEvent.class));

        Observable gameObservable = EventBus.events()
                .buffer(stopEvents);
        gameObservable.subscribe(System.out::println);
    }

    private static boolean isGameEvent(RouletteEvent e) {
        return RouletteGameEvent.class.equals(e.getClass());
    }

    private static boolean isPlayerEvent(RouletteEvent e) {
        return RoulettePlayerEvent.class.equals(e.getClass());
    }

    private static boolean isResultEvent(RouletteEvent e) {
        return RouletteResultEvent.class.equals(e.getClass());
    }

}
