package by.shiler.neurox.command.impl;

import by.shiler.neurox.command.ICommand;
import by.shiler.neurox.entity.Bet;
import by.shiler.neurox.entity.Game;
import by.shiler.neurox.event.EventBus;
import by.shiler.neurox.event.RouletteEvent;
import by.shiler.neurox.event.game.RouletteGameEvent;
import by.shiler.neurox.event.player.RoulettePlayerEvent;
import by.shiler.neurox.event.result.RouletteResultEvent;
import rx.Observable;

import java.util.concurrent.TimeUnit;

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
        //Observable<RouletteEvent> stopEvents = EventBus.events()
        //        .filter(FetchCommand::isGameEvent);
        Observable<RouletteEvent> stopEvents = EventBus.events()
                .filter(FetchCommand::isResultEvent)
                .delaySubscription(1, TimeUnit.NANOSECONDS);
        Observable<Game> gameObservable = EventBus.events()
                .buffer(stopEvents).skip(1).map(list -> {
                    int id = -1;
                    int roll = -1;
                    if (isGameEvent(list.get(0))) {
                        RouletteGameEvent gameEvent = (RouletteGameEvent) list.get(0);
                        id = gameEvent.getId();
                    }
                    if (isResultEvent(list.get(list.size() - 1))) {
                        RouletteResultEvent resultEvent = (RouletteResultEvent) list.get(list.size() - 1);
                        roll = resultEvent.getRoll();
                    }
                    Game game = new Game(id, roll);
                    for (RouletteEvent rouletteEvent : list) {
                        if (isPlayerEvent(rouletteEvent)) {
                            RoulettePlayerEvent roulettePlayerEvent = (RoulettePlayerEvent) rouletteEvent;
                            String username = roulettePlayerEvent.getUser().getUsername();
                            String color = roulettePlayerEvent.getTeam().getColor();
                            double amount = roulettePlayerEvent.getBet().getCurrent();
                            int mode = roulettePlayerEvent.getTeam().getMode();
                            Bet bet = new Bet(username, amount, color, mode);
                            game.addBet(bet);
                        }
                    }
                    return game;
                }).take(gamesToFetch);
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
