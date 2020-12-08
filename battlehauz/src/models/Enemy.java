package models;

import interfaces.Battleable;

import java.util.Random;

public abstract class Enemy extends GameCharacter implements Battleable {
    protected int mana;// will be used later to generate special move

    public Enemy(String name, int maxHealth, int mana) {
        super(name, maxHealth);
        this.mana = mana;

    }


    public String generateName() {
        //generates name for enemy
        return null;
    }

    public int generateMoveIndex() {
        //randomly selects a move from moves array
        //returns the index
        // choseMove(generateMoveIndex)
        int upperbound = getMoves().length;
        Random rand = new Random();
        int index = rand.nextInt(upperbound);
        return index;
    }

    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >= 20) {
            return true;
        } else {
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

    public void takeDamage(int damage) {
        //calls dies() if dead.
        if (this.getCurrentHealth() >= damage) {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
        }

    }


//
    @Override
    public void performTurn(int moveIndex, GameCharacter opponent ) {
        Move nextMove = this.chooseMove(moveIndex);
        nextMove.updateMove();
        if(this.attackSuccessful()) {
            opponent.takeDamage(this.calculateDamage(nextMove));
        }
    }

    // abstract void draw()  this will be helpful for graphics, different images
}
