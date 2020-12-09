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

    public Dragon(String name, int mana, int level) {
        super(name, 2000, mana, level);
        addMove(move1);
        addMove(move2);
        addMove(move3);
    }


    /***
     * randomly generates probability of hitting player, chance of missing is reduced to 5%
     * @return boolean that determines if the attack is successful
     */

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

