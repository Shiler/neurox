package by.shiler.neurox.repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bet> bets;

    @Column(name = "roll_result")
    private int rollResult;

    public Game(int rollResult) {
        this.rollResult = rollResult;
        bets = new ArrayList<>();
    }

    public Game() {
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
