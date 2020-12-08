package models.gameCharacters;

import interfaces.Battleable;
import models.Items.Item;
import models.Move;
import models.utilities.Turn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Player extends GameCharacter implements Battleable {

    private int XP;

    public Player(String name, int maxHealth) {
        super(name, maxHealth);
        this.XP = 1000;
        this.coins = 0;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    private int coins;
    private HashMap<Item, Integer> items;
    private int[] consumeableBoost = {0,0,0};
    private int[] equipableBoost = {0,0,0};

    public int getCoins() { return coins; }

    public void increaseCoins(int amount){
        this.coins += amount;
    }

    public void increaseXP(int xpIncrease){
        this.XP += xpIncrease;
    }
    public int calculateDamage(Move move){
        return move.getBaseDamage(); // other factors will come in
    }

    public int calculateLevel() { return XP/1000;}

    @Override
    public String toString(){
        return"Full Summary\n ------------------------------ \n"+
                this.getName() + "\nYou are level " + this.calculateLevel() + "\n"
                + this.getXP() + "XP\n"
                + this.getCurrentHealth() + " health / " + this.getMaxHealth() + "\n"
                + this.getCoins() + " coins"
                + "Your available moves are " + Arrays.toString(moves.toArray());
    }

    @Override
    public boolean attackSuccessful() {
        // would depend on other factors
        Random rnd = new Random();
        return rnd.nextInt(100) > 5;
    }

    @Override
    public void takeDamage(int damage) {
        // items would change this behaviour
        this.currentHealth -= damage;
    }

    public boolean removeItem(Item itemToRemove){
        try{
            items.remove(itemToRemove);
            return true;
        }
        catch(Exception e){ // TODO: make the exception catching more specific
            return false;
        }
    }

    public boolean removeMove(int moveIndex){
        try{
            moves.remove(moveIndex);
            return true;
        }
        catch (Exception e){ // TODO: make the exception catching more specific
            return false;
        }
    }

    @Override
    public Turn performTurn(int moveIndex, GameCharacter opponent) { // passing in opponent of type GameCharacter, as same method could be used for enemy
        try {
            Move nextMove = this.chooseMove(moveIndex);
            nextMove.updateMove();
            boolean s = attackSuccessful();
            if(s){
                opponent.takeDamage(this.calculateDamage(nextMove));
                this.increaseXP(nextMove.getXPBoost());
            }
            return new Turn(nextMove, s);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("That move selection was invalid. Please select a valid move or item.");
            return null;
        }
    }


}
