package models.gameCharacters;

import interfaces.Battleable;
import models.items.*;
import models.Move;
import models.gameCharacters.enemy.Dragon;
import models.utilities.Turn;

import java.util.*;

public class Player extends GameCharacter implements Battleable {
    private int coins;
    private int XP;
    private final HashMap<Item, Integer> items;
    private final ArrayList<Item> ownedItemNames;
    private final double[] consumableBoost = {0,0};
    private final double[] potionBoost = {0,0,0};
    private boolean levelUpDetected = false;
    int initialLevel = 0;

    public Player(String name) {
        super(name);
        items = new HashMap<>();
        ownedItemNames = new ArrayList<>();
        this.XP = 1000;
        this.coins = 0;
        this.addMove(new Move("Taunt", 150, 100));
        this.addMove(new Move("Sucker Punch", 200, 50));
        this.addMove(new Move("Drop Kick", 250, 0));
    }

    //********************[Getters and Setters]*********************

    public int getXP() { return XP; }

    public void setCoins(int coins) { this.coins = coins; }

    public int getCoins() { return coins; }

    public void increaseCoins(int amount){ this.coins += amount; }

    public void increaseXP(int xpIncrease){ this.XP += xpIncrease; }

    public HashMap<Item, Integer> getItems() { return items; }

    public ArrayList<Item> getOwnedItemNames() { return ownedItemNames; }

    //**********************************************************

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
        this.currentHealth -= (damage - (damage * (consumableBoost[1] + potionBoost[1])));
        consumableBoost[1] = 0;
        double totalHealing = maxHealth * potionBoost[2];
        for (int healPoints = 0; healPoints < (int)totalHealing; healPoints++){
            currentHealth += 1;
            if (currentHealth == maxHealth) break;
        }
        if (this.currentHealth < 0) this.currentHealth = 0;
    }

    public int calculateDamage(Move move){ return (move.getBaseDamage() + (int)(move.getBaseDamage() * (consumableBoost[0] + potionBoost[0])));} // other factors will come in }

    public int calculateLevel() { return XP/1000;}

    public int calculateMaxHealth(){ return maxHealth * calculateLevel(); }

    /***
     * Gets the move that the player selected from moveIndex
     * Updates move (increases uses and depreciation cost)
     * Increases character xp
     * Calls takeDamage method from opponent class
     * Damage is calculated using the following formula
     * damageDealt = (baseDamage + (baseDamage * consumable attack damage boost))
     * @param moveIndex Index of move selected show in view
     * @param opponent GameCharacter class of oponent
     * @return Turn summary
     */
    @Override
    public Turn performTurn(int moveIndex, GameCharacter opponent) throws ArrayIndexOutOfBoundsException { // passing in opponent of type GameCharacter, as same method could be used for enemy
        Move nextMove = this.chooseMove(moveIndex);
        nextMove.updateMove();
        boolean s = attackSuccessful();
        initialLevel = calculateLevel();
        if (s) {
            this.increaseXP(nextMove.getXPBoost());
            detectLevelUp();
            if (opponent instanceof Dragon && nextMove.isSellable()) {
                //Dragon enemies take 50% less damage from advanced moves
                opponent.takeDamage((int) (0.5 * this.calculateDamage(nextMove)));
            } else {
                opponent.takeDamage(this.calculateDamage(nextMove));
            }
        }
        consumableBoost[0] = 0;
        return new Turn(nextMove, s);
    }

    public void removeMove(int moveIndex){
        try{
            moves.remove(moveIndex);
        }
        catch (Exception e){ // TODO: make the exception catching more specific
        }
    }

    //************************[Items]**************************

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
     * Removes 1 of passed in item from user's inventory
     * If the player has quantity 0 of the removed item, the item is entirely removed from the user's inventory
     * @param itemToRemove item to remove
     */
    public void removeItem(Item itemToRemove){
        items.replace(itemToRemove, items.get(itemToRemove) - 1);
        if (items.get(itemToRemove) == 0) {
            items.remove(itemToRemove);
            ownedItemNames.remove(itemToRemove);
        }
    }

    /***
     * Gets the item to use from the array list shown in view
     * Checks the type of the item, changes corresponding boost
     * If the item is a healing item, the healing is applied right away
     * @param itemIndex index of item selected by user show in view
     */
    public Turn useItem(int itemIndex){
        Item itemToUse = ownedItemNames.get(itemIndex - 1);
        if (itemToUse instanceof ConsumableOffensiveItem){
            consumableBoost[0] = itemToUse.useItem();
        }else if (itemToUse instanceof ConsumableDefensiveItem){
            consumableBoost[1] = itemToUse.useItem();
        }else if (itemToUse instanceof ConsumableHealingItem){
            double totalHealing = maxHealth * itemToUse.useItem();
            for (int healPoints = 0; healPoints < (int)totalHealing; healPoints++){
                currentHealth += 1;
                if (currentHealth == maxHealth) break;
            }
        }
        removeItem(itemToUse);
        return new Turn(itemToUse, true);
    }

    public String drinkPotion(Potion potion){
        if (potion instanceof OffensivePotion){
            potionBoost[0] = potion.useItem();
        }else if (potion instanceof DefensivePotion){
            potionBoost[1] = potion.useItem();
        }else if (potion instanceof HealingPotion){
            potionBoost[2] = potion.useItem();
        }
        return potion.toString();
    }

    //*********************************************************

    //***********************[Leveling up]********************
    /***
     * Used to pass to the view if a level up has occured to the player to display a message
     * @return
     */
    public boolean levelUpHasBeenDetected(){
        if (levelUpDetected){
            levelUpDetected = false;
            return true;
        }
        return false;
    }

    public void detectLevelUp(){
        if (initialLevel < calculateLevel()){
            levelUpDetected = true;
        }
    }

    public void levelUpPlayer() {
        setMaxHealth(calculateMaxHealth());
        getMoves().get(0).setBaseDamage(150 * calculateLevel());
        getMoves().get(1).setBaseDamage(200 * calculateLevel());
        getMoves().get(2).setBaseDamage(250 * calculateLevel());
    }

    public String displayLevelUp() {
        if (initialLevel < calculateLevel()) {
            levelUpPlayer();
            return "You leveled up!\n" +
                    "Level " + initialLevel + " -->" + "Level " + calculateLevel() + "\n" +
                    "Move upgrade! " + getMoves().get(0).getName() + " : " + (getMoves().get(0).getBaseDamage() - (150 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(0).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(1).getName() + " : " + (getMoves().get(1).getBaseDamage() - (200 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(1).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(2).getName() + " : " + (getMoves().get(2).getBaseDamage() - (250 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(2).getBaseDamage();

        }
        return "";
    }

    public String displayLevelUp(int initialLevel) {
        if (initialLevel < calculateLevel()) {
            levelUpPlayer();
            return "You leveled up!\n" +
                    "Level " + initialLevel + " -->" + "Level " + calculateLevel() + "\n" +
                    "Move upgrade! " + getMoves().get(0).getName() + " : " + (getMoves().get(0).getBaseDamage() - (150 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(0).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(1).getName() + " : " + (getMoves().get(1).getBaseDamage() - (200 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(1).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(2).getName() + " : " + (getMoves().get(2).getBaseDamage() - (250 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(2).getBaseDamage();

        }
        return "";
    }

    //**********************************************************

    //************[Resetting boosts & shop healing]*************

    public void resetConsumableBoosts(){
        consumableBoost[0] = 0;
        consumableBoost[1] = 0;
    }

    public void resetPermanentBoosts(){
        potionBoost[0] = 0;
        potionBoost[1] = 0;
        potionBoost[2] = 0;
    }

    public void fullRestore(){
        resetConsumableBoosts();
        resetPermanentBoosts();
        setCurrentHealth(maxHealth);
    }

    //**********************************************************

    public String availableMoves(){
        StringBuilder returnString = new StringBuilder();
        for(int i = 0; i < moves.size(); i++){
            returnString.append(i + 1).append(": ").append(moves.get(i).toString()).append("\n");
        }
        return returnString.toString();
    }

    public String toString(){
        return"Full Summary\n------------------------------ \n"+
                this.getName() + "\nYou are at level " + this.calculateLevel() + "\n"
                + this.getXP() + "XP\n"
                + this.getCurrentHealth() + " health / " + this.getMaxHealth() + "\n"
                + this.getCoins() + " coins\n"
                + "Your available moves are \n" + availableMoves();
    }


}
