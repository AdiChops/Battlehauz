package models.gameCharacters.enemy;

import models.Move;

import java.util.Random;

public class Calcifer extends Enemy {


    public Calcifer(String name, int maxHealth, int mana, int level) {
        super(name, maxHealth, mana, level);
        addMove(new Move("Fireball", calculateDamage()));
        addMove(new Move("Lava Floor", calculateDamage()));
        addMove(new Move("Core Rage", calculateDamage()));

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
