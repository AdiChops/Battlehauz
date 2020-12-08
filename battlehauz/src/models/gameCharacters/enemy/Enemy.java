package models.gameCharacters.enemy;

import interfaces.Battleable;
import models.Move;
import models.gameCharacters.GameCharacter;
import models.utilities.Turn;

import java.util.Random;

public abstract class Enemy extends GameCharacter implements Battleable {
    protected int mana;// will be used later to generate special move
    protected int level;

    public Enemy(String name, int maxHealth, int mana, int level) {
        super(name, maxHealth);
        this.mana = mana;
        this.level = level;

    }


    public String generateName() {
        return name;
    }

    public int generateMoveIndex() {
        //randomly selects a move from moves array
        //returns the index
        // choseMove(generateMoveIndex)
        int upperbound = getMoves().size();
        Random rand = new Random();
        return rand.nextInt(upperbound);
    }

    public boolean attackSuccessful() {
        int upperbound = 100;
        Random rand = new Random();
        int prob = rand.nextInt(upperbound);
        if (prob >= 20) {
            return true;
        }
        return false;

    }
    // calculate damage of enemy's move
    public int calculateDamage() {
        int max = 500 * level;
        int min = 50 * level;
        return (int) (Math.random()*(max-min)+min);

    }
    //required
    public int calculateDamage(Move move) {
        return 0;

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
    // how much of player's damage points
    public void takeDamage(int damage) {
        //calls dies() if dead.
        if (this.getCurrentHealth() >= damage) {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
        }

    }


    //
    @Override
    public Turn performTurn(int moveIndex, GameCharacter opponent) {
        Move nextMove = this.chooseMove(moveIndex);
        nextMove.updateMove();
        boolean s = this.attackSuccessful();
        if (s) {
            opponent.takeDamage(this.calculateDamage());
        }
        return new Turn(nextMove, s);
    }



    // abstract void draw()  this will be helpful for graphics, different images
}
