package fr.valentin.turncore.core;

import fr.valentin.turncore.managers.PledgeManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Game {
    @Getter@Setter
    Party party;
    @Getter
    HashSet<Player> players = new LinkedHashSet<>();
    @Getter
    private ArrayList<Player> winners = new ArrayList<>();
    @Getter
    int turn = 0;
    @Getter
    private boolean started = false, paused = false, stopped = false;
    public void start() {
        started = true;
        startTurn(getCurrentPlayer());
    };
    public boolean nextTurn() {
        if(paused || stopped) return false;
        turn++;
        startTurn(getCurrentPlayer());
        return true;
    };
    public abstract void startTurn(Player player);
    public void stop() {
        stopped = true;
        for(Player player : winners) {
            player.addWin(this);
        }

        for(Player player : players) {
            if(!winners.contains(player)) {
                Pledge pledge = PledgeManager.getRandomWeightedPledge();
                if(pledge == null) continue;
                player.addPledge(pledge);
            }
        }
        doPledges();
        party.nextGame();
    };

    public abstract void doPledges();
    public void pause() {
        paused = true;
    };
    public void resume() {
        paused = false;
    };

    public Player getCurrentPlayer() {
        return (Player) players.toArray()[turn % players.size()];
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
}
