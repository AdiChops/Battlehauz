package interfaces;

import models.gameCharacters.GameCharacter;
import models.Turn;


public interface Battleable {
    /***
     * Method return true or false based on random int generation
     * dictates if an attack is successful or not
     * @return true if attack is successful, false otherwise
     */
    boolean attackSuccessful();

    /***
     * Method dictates how character performs a turn
     * @param moveIndex index of move selected
     * @param opponent opponent of whoever is fighting (enemy or player)
     * @return Turn summary
     */
    Turn performTurn(int moveIndex, GameCharacter opponent);
}
