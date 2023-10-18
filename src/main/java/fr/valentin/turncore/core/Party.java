package fr.valentin.turncore.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.function.Function;

public class Party {
    @Getter
    HashSet<Player> players = new LinkedHashSet<>();
    @Getter
    ArrayList<Game> games = new ArrayList<>();

    private final ChoosingMode choosingMode;
    @Getter@Setter
    private boolean removeAfterEnd = false;
    @Getter@Setter
    Runnable onGameEnd;
    int gameNumber = 0;

    public Party(ChoosingMode choosingMode) {
        this.choosingMode = choosingMode;
    }

    public void nextGame() {
        Game game = choosingMode.function.apply(games);
        for(Player player : players) {
            game.addPlayer(player);
        }
        if(removeAfterEnd) games.remove(game);
        if(onGameEnd != null && gameNumber++>0) onGameEnd.run();
        game.start();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addGame(Game game) {
        game.setParty(this);
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public enum ChoosingMode {
        RANDOM(games -> {
            int random = (int) (Math.random() * games.size());
            return games.get(random);
        }),
        ROTATING(games -> games.get(0));

        final Function<ArrayList<Game>, Game> function;

        ChoosingMode(Function<ArrayList<Game>, Game> function) {
            this.function = function;
        }
    }
}
