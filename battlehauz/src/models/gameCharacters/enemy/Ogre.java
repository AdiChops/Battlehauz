package models.gameCharacters.enemy;

import models.Move;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Ogre extends Enemy {
    Move move1 = new Move("Bonk", generateDamage(), 0);
    Move move2 = new Move("Belly Tackle", generateDamage(), 0);
    Move move3 = new Move("Smelly Armpit", generateDamage(), 0);


    public Ogre(String n, int level) {
        super(n, 1000, level);
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
        Queue<Integer> sequence = new LinkedList<>(Arrays.asList(1, 2, 3));
        //add the move name into this sequence array
        int index = sequence.remove();
        sequence.add(index);
        //immediately add to back of array
        return index;
    }

}
