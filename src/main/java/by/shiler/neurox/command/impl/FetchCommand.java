package by.shiler.neurox.command.impl;

import by.shiler.neurox.command.ICommand;
import by.shiler.neurox.database.DatabaseHelper;
import by.shiler.neurox.event.EventBus;
import by.shiler.neurox.event.RouletteEvent;
import by.shiler.neurox.event.game.RouletteGameEvent;
import by.shiler.neurox.event.player.RoulettePlayerEvent;
import by.shiler.neurox.event.result.RouletteResultEvent;
import by.shiler.neurox.repository.Bet;
import by.shiler.neurox.repository.Game;
import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class FetchCommand implements ICommand {

    private int gamesToFetch;
    private DatabaseHelper databaseHelper;

    public FetchCommand(int gamesToFetch) {
        this.gamesToFetch = gamesToFetch;
        databaseHelper = new DatabaseHelper();
    }

    @Override
    public void execute() {
        EventBus.start();
        Observable<RouletteEvent> stopEvents = EventBus.events()
                .filter(FetchCommand::isResultEvent)
                .delaySubscription(50, TimeUnit.NANOSECONDS);
        Observable<Game> gameObservable = EventBus.events()
                .buffer(stopEvents)
                .skip(1)
                .map(FetchCommand::composeGame)
                .limit(gamesToFetch)
                .doOnCompleted(() -> {
                    databaseHelper.closeSession();
                    EventBus.stop();
                    System.out.println("fetched " + gamesToFetch + " games");
                    System.exit(0); // omg, dude
                });
        gameObservable.subscribe(databaseHelper::saveGame);

    }

    private static Game composeGame(List<RouletteEvent> list) {
        int roll = -1;
        if (isResultEvent(list.get(list.size() - 1))) {
            RouletteResultEvent resultEvent = (RouletteResultEvent) list.get(list.size() - 1);
            roll = resultEvent.getRoll();
        }
        Game game = new Game(roll);
        for (RouletteEvent rouletteEvent : list) {
            if (isPlayerEvent(rouletteEvent)) {
                RoulettePlayerEvent roulettePlayerEvent = (RoulettePlayerEvent) rouletteEvent;
                String username = roulettePlayerEvent.getUser().getUsername();
                String color = roulettePlayerEvent.getTeam().getColor();
                double amount = roulettePlayerEvent.getBet().getCurrent();
                int mode = roulettePlayerEvent.getTeam().getMode();
                Bet bet = new Bet(username, amount, color, mode, game);
                game.addBet(bet);
            }
        }
        return game;
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
