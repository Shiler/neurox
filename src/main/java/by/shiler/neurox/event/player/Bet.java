package by.shiler.neurox.event.player;

import java.util.Objects;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class Bet {

    private double total;
    private double current;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "total=" + total +
                ", current=" + current +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Double.compare(bet.total, total) == 0 &&
                Double.compare(bet.current, current) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, current);
    }
}
