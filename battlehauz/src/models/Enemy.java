package models;

import java.util.Random;
abstract class Enemy extends Character implements interfaces.Battleable {
    protected int mana;// will be used later to generate special move

    public Enemy(String n, int baseHealth, Move[] moves, int mana) {
        super(n, baseHealth, moves);
        this.mana = mana;
    }


    public String generateName(){
        //generates name for enemy
        return null;
    }

    public int chooseMove() {
        //randomly selects a move from moves array
        //returns the index
        int upperbound = moves.length;
        Random rand = new Random();
        int index = rand.nextInt(upperbound);
        return index;
    }

    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >=5){
                return true;
            }
            else{
                return false;
            }

        }

//    public void GiveDamage(enemyMove) {
//        if (AttackSuccessful()){
//            if (Player.getBaseHealth() >= enemyMove.getDamage()) {
//                Player.getBaseHealth() -= enemyMove.getDamage();
//            } else {
//                dies();
//            }
//        }
//
//    }

    public void takeDamage(Move playerMove) {
        //calls dies() if dead.
        if (Player.attackSuccessful() == true){
            if(this.getBaseHealth() >= playerMove.getDamage()){
                this.baseHealth -= playerMove.getDamage();
            }

        }

    }
    // abstract void draw()  this will be helpful for graphics, different images
}
