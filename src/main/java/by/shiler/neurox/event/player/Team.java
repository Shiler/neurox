package by.shiler.neurox.event.player;

import java.util.Objects;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class Team {

    private String color;
    private int mode;

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
        return "Team{" +
                "color='" + color + '\'' +
                ", mode=" + mode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return mode == team.mode &&
                Objects.equals(color, team.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, mode);
    }
}
