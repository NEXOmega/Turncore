package fr.valentin.turncore.core;

import java.util.ArrayList;

public record Pledge(String pledge, int weight) {
    public String executePledge(StringFormater formater) {
        return formater.format(pledge);
    }
}
