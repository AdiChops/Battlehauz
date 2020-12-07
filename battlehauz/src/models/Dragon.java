package models;

import java.util.Random;

public class Dragon extends Enemy{
    public Dragon(String name, int maxHealth, Move[] moves, int mana) {
        super(name, maxHealth, moves, mana);
    }

    //        public Dragon (String n, int baseHealth, Moves[] moves, int mana) {
//        super(n, baseHealth, moves, mana);
//    }


    @Override
    public Move performTurn() {
        return null;
    }
    @Override
    public int calculateDamage(Move playerMove){
        if (playerMove.getBaseMove()){//true
            int newDamage = (int) (playerMove.getBaseDamage()*0.1);
            return newDamage;
        }
        return playerMove.getBaseDamage(); // wanna make it 10X more effective or no penatly/
        }
    @Override
    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >= 5) {
            return true;
        } else {
            return false;
        }

    }


    }

