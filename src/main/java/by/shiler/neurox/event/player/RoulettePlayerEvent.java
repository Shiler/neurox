package by.shiler.neurox.event.player;

import by.shiler.neurox.event.RouletteEvent;

import java.util.Objects;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class RoulettePlayerEvent implements RouletteEvent {

    private Bet bet;
    private User user;
    private Team team;

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "RoulettePlayerEvent{" +
                "bet=" + bet +
                ", user=" + user +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoulettePlayerEvent that = (RoulettePlayerEvent) o;
        return Objects.equals(bet, that.bet) &&
                Objects.equals(user, that.user) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet, user, team);
    }
}
