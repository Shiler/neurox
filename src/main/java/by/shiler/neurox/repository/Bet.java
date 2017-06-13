package by.shiler.neurox.repository;

import javax.persistence.*;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String username;

    @Column
    private double amount;

    @Column
    private String color;

    @Column
    private int mode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    public Bet(String username, double amount, String color, int mode, Game game) {
        this.username = username;
        this.amount = amount;
        this.color = color;
        this.mode = mode;
        this.game = game;
    }

    public Bet() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", color='" + color + '\'' +
                ", mode=" + mode +
                ", game=" + game +
                '}';
    }
}
