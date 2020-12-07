package models;

import java.util.Random;

public class Calcifer extends Enemy {
    public Calcifer (String name, int maxHealth, Move[] moves, int mana) {
        super(name, maxHealth, moves, mana);
    }
    @Override
    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >= 10) {
            return true;
        } else {
            return false;
        }

    }
    //abstract draw method


}
