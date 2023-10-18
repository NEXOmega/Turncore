package fr.valentin.turncore.games;

import fr.valentin.turncore.core.Player;
import fr.valentin.turncore.core.StringFormater;

import java.util.ArrayList;
import java.util.HashSet;

public class BaseFormater extends StringFormater {
    HashSet<Player> players;

    public BaseFormater(HashSet<Player> players) {
        this.players = players;
    }

    @Override
    public String format(String string) {
        string = string.replaceAll("%all", String.join(", ", players.stream().map(Player::getName).toList()));
        string = string.replaceAll("%random", players.stream().skip((int) (Math.random() * players.size())).findFirst().get().getName());
        return string;
    }
}
