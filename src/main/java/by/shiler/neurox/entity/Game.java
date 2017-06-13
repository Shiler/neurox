package by.shiler.neurox.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class Game {

    private int id;
    private List<Bet> bets;
    private int rollResult;

    public Game(int id, int rollResult) {
        this.id = id;
        this.rollResult = rollResult;
        bets = new ArrayList<>();
    }

    public void addBet(Bet bet) {
        bets.add(bet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public int getRollResult() {
        return rollResult;
    }

    public void setRollResult(int rollResult) {
        this.rollResult = rollResult;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", bets=" + bets +
                ", rollResult=" + rollResult +
                '}';
    }
}
