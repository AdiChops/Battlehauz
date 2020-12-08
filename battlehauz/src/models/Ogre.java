package models;

import interfaces.Battleable;

import java.util.ArrayList;

public class Ogre extends Enemy {
    Move move1 = new Move("Bonk", 100, 10, 900, 0);
    Move move2 = new Move("Belly Tackle", 150, 10, 900, 0);
    Move move3 = new Move("Dodge", 0, 0, 900, 0);


    public Ogre(String n, int baseHealth, int mana) {
        super(n, baseHealth, mana);
        addMove(move1);
        addMove(move2);
        addMove(move3);

    }

    public int generateMoveIndex() {
        //method overload, same move pattern instead of randomized
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        //add the move name into this sequence array
        int index = sequence.remove(0);
        sequence.add(sequence.size() - 1, index);
        //immediately add to back of array

        return index;
    }

    @Override
    public int calculateDamage(Move move) {
        return 0;
    }
}
