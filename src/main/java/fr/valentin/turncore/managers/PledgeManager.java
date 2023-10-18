package fr.valentin.turncore.managers;

import fr.valentin.turncore.core.Pledge;

import java.util.ArrayList;
import java.util.Arrays;

public class PledgeManager {
    static ArrayList<Pledge> pledges = new ArrayList<>(Arrays.asList(
            new Pledge("\"Lick %random hand\"", 1),
            new Pledge("\"Take a shot\"", 2),
            new Pledge("\"Hug %all\"", 3)
    ));

    public static void addPledge(Pledge pledge) {
        pledges.add(pledge);
    }

    public static void removePledge(Pledge pledge) {
        pledges.remove(pledge);
    }

    public static Pledge getRandomWeightedPledge() {
        int totalWeight = 0;
        for (Pledge pledge : pledges) {
            totalWeight += pledge.weight();
        }
        int random = (int) (Math.random() * totalWeight);
        for (Pledge pledge : pledges) {
            random -= pledge.weight();
            if (random <= 0) {
                return pledge;
            }
        }
        return null;
    }
}
