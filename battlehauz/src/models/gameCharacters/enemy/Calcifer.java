package models.gameCharacters.enemy;

import models.Move;

import java.util.Random;

public class Calcifer extends Enemy {

    public Calcifer(String name, int level) {
        super(name, 1200, level);
        addMove(new Move("Fireball", generateDamage(), 0));
        addMove(new Move("Lava Floor", generateDamage(), 0));
        addMove(new Move("Core Rage", generateDamage(), 0));

    }

    /***
     * randomly generates probability of hitting player, chance of missing is reduced to 10%
     *
     * @return boolean that determines if the attack is successful
     */
    @Override
    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        return prob >= 10;

    }


    //abstract draw method


}
