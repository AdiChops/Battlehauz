package models.gameCharacters.enemy;

import models.Move;

import java.util.Random;

public class Calcifer extends Enemy {
    Move move1 = new Move("Fireball", 200, 50, 900, 0);
    Move move2 = new Move("Lava Floor", 250, 60, 900, 0);
    Move move3 = new Move("Dodge", 0, 0, 900, 0);

    public Calcifer(String name, int maxHealth, int mana) {
        super(name, maxHealth, mana);
        addMove(move1);
        addMove(move2);
        addMove(move3);

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

    @Override
    public int calculateDamage(Move move) {
        return 0;
    }

    //abstract draw method


}
