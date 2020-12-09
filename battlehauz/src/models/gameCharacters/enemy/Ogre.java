package models.gameCharacters.enemy;

import models.Move;

import java.util.ArrayList;

public class Ogre extends Enemy {

    public Ogre(String n, int maxHealth, int mana, int level) {
        super(n, maxHealth, mana, level);
        addMove(new Move("Bonk", calculateDamage()));
        addMove(new Move("Belly Tackle", calculateDamage()));
        addMove(new Move("Smelly Armpit", calculateDamage()));

    }

    /***
     * ogres have a fixed/predictable sequence of moves rather than randomized
     * @return index of move
     */
    public int generateMoveIndex() {
        //method overload, same move pattern instead of randomized
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        //add the move name into this sequence array
        int index = sequence.remove(0);
        sequence.add(sequence.size() - 1, index);
        //immediately add to back of array

        return index;
    }

}
