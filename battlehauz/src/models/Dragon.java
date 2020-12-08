package models;

import java.util.Random;

public class Dragon extends Enemy {
    Move move1 = new Move(" Breath of Death", 800, 100, 900, 0);
    Move move2 = new Move("Dark Magic", 1000, 200, 900, 0);
    Move move3 = new Move("Dodge", 0, 0, 900, 0);

    public Dragon(String name, int maxHealth, int mana) {
        super(name, maxHealth, mana);
        addMove(move1);
        addMove(move2);
        addMove(move3);
    }




    @Override
    public int calculateDamage(Move playerMove) {
        if (playerMove.getBaseMove()) {//true
            int newDamage = (int) (playerMove.getBaseDamage() * 0.1);
            return newDamage;
        }
        return playerMove.getBaseDamage(); // wanna make it 10X more effective or no penatly/
    }

    @Override
    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >= 5) {
            return true;
        } else {
            return false;
        }

    }


}

