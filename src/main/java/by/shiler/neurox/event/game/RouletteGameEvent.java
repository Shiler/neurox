package by.shiler.neurox.event.game;

import by.shiler.neurox.event.RouletteEvent;

import java.util.Objects;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class RouletteGameEvent implements RouletteEvent {

    private int id;
    private int start;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "RouletteGameEvent{" +
                "id=" + id +
                ", start=" + start +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouletteGameEvent that = (RouletteGameEvent) o;
        return id == that.id &&
                start == that.start;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start);
    }
}