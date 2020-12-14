package models.gameCharacters.enemy;

import models.Move;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Ogre extends Enemy {
    private Queue<Integer> sequence = new LinkedList<>(Arrays.asList(0, 1, 2));

    public Ogre(String n, int level) {
        super(n, 1000, level);
        addMove(new Move("Bonk", generateDamage(), 0));
        addMove(new Move("Belly Tackle", generateDamage(), 0));
        addMove(new Move("Smelly Armpit", generateDamage(), 0));

    }

    /***
     * ogres have a fixed/predictable sequence of moves rather than randomized
     * @return index of move
     */
    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        return prob >= 20;

    }

    /***
     * returns a predictable queue of moves for ogres specifically
     * @return move index
     */
    public int generateMoveIndex() {
        int index = sequence.remove();
        sequence.add(index);
        return index;
    }

}
