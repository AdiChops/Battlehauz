package models;

import interfaces.Battleable;
import models.Items.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Player extends GameCharacter implements Battleable {

    private int XP;
    private int coins;
    private HashMap<Item, Integer> items;
    private int[] consumeableBoost = {0,0,0};
    private int[] equipableBoost = {0,0,0};

    public int getCoins() { return coins; }

    public Player(String name, int maxHealth) {
        super(name, maxHealth);
        this.XP = 1000;
        this.coins = 0;
    }

    public void increaseCoins(int amount){
        this.coins += amount;
    }

    public void increaseXP(int xpIncrease){
        this.XP += xpIncrease;
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
        Random rnd = new Random();

        return false;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public Move performTurn(int index) {

    }


}
