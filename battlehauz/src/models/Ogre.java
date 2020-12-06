package models;

import interfaces.Battleable;

import java.util.ArrayList;

public class Ogre extends Enemy {
        public Ogre(String n, int baseHealth, Moves[] moves, int mana) {
        super(n, baseHealth, moves, mana);
    }

    public int generateMoveIndex() {
        //method overload, same move pattern instead of randomized
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        //add the move name into this sequence array
        int index =  sequence.remove(0);
        sequence.add(sequence.size()-1, index);
        //immediately add to back of array

        return index;
    }

    @Override
    public Move useMove() {

    }
}
