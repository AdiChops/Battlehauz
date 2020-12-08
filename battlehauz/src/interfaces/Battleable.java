package interfaces;

import models.Enemy;
import models.GameCharacter;
import models.Move;
import models.Player;
import models.utilities.Turn;


public interface Battleable {
    // this method returns true or false based on probability of the attack hitting opponent
    // 5% chance for Player to miss move, varying% chance depending on subclass of enemy
    boolean attackSuccessful();

//    void takeDamage(int damage);
    int calculateDamage(Move move);

    Turn performTurn(int moveIndex, GameCharacter opponent);

//    int calculateDamage();
}
