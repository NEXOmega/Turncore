package fr.valentin.turncore.core;

import fr.valentin.turncore.games.BaseFormater;
import lombok.Getter;

import java.util.*;

public class Player {
    @Getter
    private final String name;
    private final HashMap<Game, Integer> wins = new HashMap<>();
    @Getter
    private final Queue<Pledge> pledges = new ArrayDeque<>();

    public Player(String name) {
        this.name = name;
    }

    public int getTotalWins() {
        int total = 0;
        for (int win : wins.values()) {
            total += win;
        }
        return total;
    }

    public void addWin(Game game) {
        wins.put(game, wins.getOrDefault(game, 0) + 1);
    }

    public void addPledge(Pledge pledge) {
        pledges.add(pledge);
    }

    public String getPledge(HashSet<Player> players) {
        Pledge pledge = pledges.poll();
        if(pledge == null) return null;
        return pledge.executePledge(new BaseFormater(players));
    }
}
