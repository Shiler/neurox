package by.shiler.neurox.event.result;

import by.shiler.neurox.event.RouletteEvent;

import java.util.Objects;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class RouletteResultEvent implements RouletteEvent {

    private int id;
    private String hash;
    private int roll;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RouletteResultEvent{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", roll=" + roll +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouletteResultEvent that = (RouletteResultEvent) o;
        return id == that.id &&
                roll == that.roll &&
                state == that.state &&
                Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hash, roll, state);
    }
}
