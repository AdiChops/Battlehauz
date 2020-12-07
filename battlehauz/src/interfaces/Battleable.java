package interfaces;

import models.Move;


public interface Battleable {
    // this method returns true or false based on probability of the attack hitting opponent
    // 5% chance for Player to miss move, varying% chance depending on subclass of enemy
    boolean attackSuccessful();

    void takeDamage(int damage);

    Move performTurn();

//    int calculateDamage();
}
