package fr.valentin.turncore.games;

import fr.valentin.turncore.core.Game;
import fr.valentin.turncore.core.Player;

import java.util.Scanner;

public class GuessTheNumber extends Game {
    int numberToGuess;
    @Override
    public void start() {
        numberToGuess = (int) (Math.random() * 100);
        System.out.println("Number to guess: " + numberToGuess);

        super.start();
    }

    @Override
    public void startTurn(Player player) {
        System.out.println(player.getName() + "'s turn!");
        try {
            Scanner scanner = new Scanner(System.in);
            int number = Integer.parseInt(scanner.nextLine());
            if (number == numberToGuess) {
                System.out.println(player.getName() + " won!");
                getWinners().add(player);
                stop();
            } else if (number > numberToGuess) {
                System.out.println(player.getName() + " is too high!");
            } else {
                System.out.println(player.getName() + " is too low!");
            }
            nextTurn();
        } catch (NumberFormatException e) {
            System.out.println(player.getName() + " is not a number!");
            startTurn(player);
        }
    }

    @Override
    public void doPledges() {
        for(Player player : getPlayers())
            if(!getWinners().contains(player))
                System.out.println(player.getName() + " need to do " + player.getPledge(getPlayers()));
    }
}
