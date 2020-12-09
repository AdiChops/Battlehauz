package models.gameCharacters.enemy;

import models.Move;

import java.util.ArrayList;

public class Ogre extends Enemy {
    Move move1 = new Move("Bonk", calculateDamage());
    Move move2 = new Move("Belly Tackle", calculateDamage());
    Move move3 = new Move("Smelly Armpit", calculateDamage());


    public Ogre(String n, int mana, int level) {
        super(n, 1000, mana, level);
        addMove(move1);
        addMove(move2);
        addMove(move3);

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
