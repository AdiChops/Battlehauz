package interfaces;

import models.gameCharacters.GameCharacter;
import models.utilities.Turn;


public interface Battleable {
    /***
     * Method return true or false based on random int generation
     * dictates if an attack is successful or not
     * @return
     */
    boolean attackSuccessful();

    /***
     * Method dictates how character takes damage
     * @param damage amnt of damage character is taking
     */
    void takeDamage(int damage);

    /***
     * Method dictates how character performs a turn
     * @param moveIndex index of move selected
     * @param opponent
     * @return
     */
    Turn performTurn(int moveIndex, GameCharacter opponent);
}
