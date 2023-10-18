package fr.valentin.turncore;

import fr.valentin.turncore.core.Game;
import fr.valentin.turncore.core.Party;
import fr.valentin.turncore.core.Player;
import fr.valentin.turncore.core.Pledge;
import fr.valentin.turncore.games.GuessTheNumber;
import fr.valentin.turncore.managers.PledgeManager;

import java.util.Scanner;

public class Turncore {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Party party = new Party(Party.ChoosingMode.ROTATING);
        party.addGame(new GuessTheNumber());
        Player player1 = new Player("Valentin");
        Player player2 = new Player("Bob");
        Player player3 = new Player("John");
        party.addPlayer(player1);
        party.addPlayer(player2);
        party.addPlayer(player3);
        party.setOnGameEnd(() -> {
            for(Player player : party.getPlayers()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(player.getName() + " add a pledge:");
                Pledge pledge = new Pledge(scanner.nextLine(), 1);
                PledgeManager.addPledge(pledge);
            }
        });
        party.nextGame();
    }
}
