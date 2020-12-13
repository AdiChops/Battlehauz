package models.gameCharacters.enemy;

import models.Move;

import java.util.Random;

public class Dragon extends Enemy {

    public Dragon(String name, int level) {
        super(name, 2000, level);
        addMove(new Move("Breath of Death", generateDamage(), 0));
        addMove(new Move("Dark Magic", generateDamage(), 0));
        addMove(new Move("Claw", generateDamage(), 0));
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
        return prob >= 5;
    }

}

