package models.gameCharacters.enemy;

import models.Move;
import models.gameCharacters.GameCharacter;
import models.utilities.Turn;

import java.util.Random;

public class Dragon extends Enemy {
    Move move1 = new Move(" Breath of Death", calculateDamage());
    Move move2 = new Move("Dark Magic", calculateDamage());
    Move move3 = new Move("Claw", calculateDamage());
    // Item

    public Dragon(String name, int maxHealth, int mana, int level) {
        super(name, maxHealth, mana, level);
        addMove(move1);
        addMove(move2);
        addMove(move3);
    }

//
//    @Override
//    public int calculateDamage(Move playerMove) {
//        if (!playerMove.isSellable()) {//true
//            int newDamage = (int) (playerMove.getBaseDamage() * 0.1);
//            return newDamage;
//        }
//        return playerMove.getBaseDamage(); // wanna make it 10X more effective or no penalty
//    }

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

    public void takeDamage(int damage) {
        //calls dies() if dead.
        if (this.getCurrentHealth() >= damage) {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
        }

    }


}

