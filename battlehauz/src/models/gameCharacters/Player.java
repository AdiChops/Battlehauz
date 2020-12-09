package models.gameCharacters;

import interfaces.Battleable;
import models.Items.*;
import models.Move;
import models.gameCharacters.enemy.Dragon;
import models.gameCharacters.enemy.Enemy;
import models.utilities.Turn;

import java.util.*;

public class Player extends GameCharacter implements Battleable {
    private int coins;
    private int XP;
    private static HashMap<Item, Integer> items;
    private ArrayList<Item> ownedItemNames;
    private int[] consumeableBoost = {0,0};
    private int[] equipableBoost = {0,0};

    public Player(String name, int maxHealth) {
        super(name, maxHealth);
        items = new HashMap<>();
        ownedItemNames = new ArrayList<>();
        this.XP = 1000;
        this.coins = 0;
    }

    ////////////GETTER AND SETTER METHODS////////////////

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() { return coins; }

    public void increaseCoins(int amount){
        this.coins += amount;
    }

    public void increaseXP(int xpIncrease){
        this.XP += xpIncrease;
    }
//    public int calculateDamage(Move move){ return move.getBaseDamage();} // other factors will come in }

    public int calculateLevel() { return XP/1000;}

    public HashMap<Item, Integer> getItems() { return items; }

    /////////////////////////////////////////////////////

    @Override
    public boolean attackSuccessful() {
        // would depend on other factors
        Random rnd = new Random();
        return rnd.nextInt(100) > 5;
    }

    /***
     * Decreases player health using the following formula
     * totalDamage = (damageDealt - (damageDealt * consumable defensive boost))
     * Consumable defensive boost removes percentage of damageDealt from incoming damage
     * Damage gets applied to player
     * @param damage damage dealt by the attacker
     */
    @Override
    public void takeDamage(int damage) {
        // items would change this behaviour
        this.currentHealth -= (damage - (damage * consumeableBoost[2]));
    }

    public boolean removeItem(Item itemToRemove){
        try{
            items.remove(itemToRemove);
            ownedItemNames.remove(itemToRemove);
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

    /***
     * Checks the players item inventory (Hashmap) to see if they own that item already
     * If they do, then increase the quantity of that item by in the hashmap
     * otherwise, add that item to their inventory with a value of 1 item
     * @param itemToAdd The item being added to the players item inventory
     */
    public void addItem(Item itemToAdd){
        boolean hasItem = false;
        for (Item i: items.keySet()){
            if (i.getName().equals(itemToAdd.getName())){
                hasItem = true;
                items.replace(i, items.get(i) + 1);
                break;
            }
        }
        if (!hasItem) {
            items.put(itemToAdd, 1);
            ownedItemNames.add(itemToAdd);
        }
    }

    /***
     * Gets the item to use from the array list shown in view
     * Checks the type of the item, changes corresponding boost
     * If the item is a healing item, the healing is applied right away
     * @param itemIndex index of item selected by user show in view
     */
    public void useItem(int itemIndex){
        Item itemToUse = ownedItemNames.get(itemIndex - 1);
        if (itemToUse instanceof ConsumeableOffensiveItem){
            consumeableBoost[0] = itemToUse.useItem();
        }else if (itemToUse instanceof ConsumeableDefensiveItem){
            consumeableBoost[1] = itemToUse.useItem();
        }else if (itemToUse instanceof ConsumeableHealingItem){
            for (int healPoints = 0; healPoints < itemToUse.useItem(); healPoints++){
                currentHealth += 1;
                if (currentHealth == maxHealth) break;
            }
        }
        items.replace(itemToUse, items.get(itemToUse) - 1);
        if (items.get(itemToUse) == 0){
            removeItem(itemToUse);
        }
    }

    /***
     * Gets the move that the player selected from moveIndex
     * Updates move (increases uses and depreciation cost)
     * Increases character xp
     * Calls takeDamage method from opponent class
     * Damage is calculated using the following formula
     * damageDealt = (baseDamage + (baseDamage * consumable attack damage boost))
     * @param moveIndex Index of move selected show in view
     * @param opponent GameCharacter class of oponent
     * @return
     */
    @Override
    public Turn performTurn(int moveIndex, GameCharacter opponent) { // passing in opponent of type GameCharacter, as same method could be used for enemy
        try {
            Move nextMove = this.chooseMove(moveIndex);
            nextMove.updateMove();
            boolean s = attackSuccessful();
            this.increaseXP(nextMove.getXPBoost());
            if(s && !(opponent instanceof Dragon)){ // ogres and calcifers take normal damage from player
                opponent.takeDamage(this.calculateDamage(nextMove) + (this.calculateDamage(nextMove) * consumeableBoost[1]));
            }else{ // if its facing a dragon and move is successful and move is  advanced, deal 10% of player's move damage
                if(s && nextMove.isSellable()){
                    opponent.takeDamage((int)(0.5 * (this.calculateDamage(nextMove) + (this.calculateDamage(nextMove) * consumeableBoost[1]))));
                    // doesn't get XP for using advanced move
                }
                else{ // if its a basic move, go as normal
                    opponent.takeDamage(this.calculateDamage(nextMove) + (this.calculateDamage(nextMove) * consumeableBoost[1]));
                }
            }
            return new Turn(nextMove, s);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("That move selection was invalid. Please select a valid move or item.");
            return null;
        }
    }

    public String toString(){
        return"Full Summary\n ------------------------------ \n"+
                this.getName() + "\nYou are level " + this.calculateLevel() + "\n"
                + this.getXP() + "XP\n"
                + this.getCurrentHealth() + " health / " + this.getMaxHealth() + "\n"
                + this.getCoins() + " coins"
                + "Your available moves are " + Arrays.toString(moves.toArray());
    }

}
