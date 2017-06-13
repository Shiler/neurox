package by.shiler.neurox.entity;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class Bet {

    private String username;
    private double amount;
    private String color;
    private int mode;

    public Bet(String username, double amount, String color, int mode) {
        this.username = username;
        this.amount = amount;
        this.color = color;
        this.mode = mode;
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

    @Override
    public String toString() {
        return "Bet{" +
                "username='" + username + '\'' +
                ", amount=" + amount +
                ", color='" + color + '\'' +
                ", mode=" + mode +
                '}';
    }
}
