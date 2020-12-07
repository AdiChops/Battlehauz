package models;

import interfaces.Battleable;
import models.Items.Item;

import java.util.Arrays;
import java.util.HashMap;

public class Player extends GameCharacter implements Battleable {

    private int coins;
    private HashMap<Item, Integer> Items;
    private int[] consumeableBoost = {0,0,0};
    private int[] equipableBoost = {0,0,0};

    public int getCoins() { return coins; }

    public Player(String name, int maxHealth, int xp) {
        super(name, maxHealth, xp);
    }

    @Override
    public String toString(){
        return"Full Summary\n ------------------------------ \n"+
                this.getName() + "\nYou are level " + this.calculateLevel() + "\n"
                + this.getXP() + "XP\n"
                + this.getCurrentHealth() + " health / " + this.getMaxHealth() + "\n"
                + this.getCoins() + " coins"
                + "Your available moves are " + Arrays.toString(getMoves());
    }

    @Override
    public boolean attackSuccessful() {
        return false;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public Move performTurn() {
        return null;
    }


}
