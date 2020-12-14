package models.gameCharacters;

import interfaces.Battleable;
import models.items.*;
import models.Move;
import models.gameCharacters.enemy.Dragon;
import utilities.Colors;
import models.Turn;

import java.util.*;

public class Player extends GameCharacter implements Battleable {
    private int coins;
    private int XP;
    private final HashMap<Item, Integer> items;
    private final ArrayList<Item> ownedItemNames;
    private final double[] consumableBoost = {0, 0};
    private final double[] potionBoost = {0, 0, 0};

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

    public int getXP() {
        return XP;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void increaseCoins(int amount) {
        this.coins += amount;
    }

    public void increaseXP(int xpIncrease) {
        this.XP += xpIncrease;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public ArrayList<Item> getOwnedItemNames() {
        return ownedItemNames;
    }

    //**********************************************************

    @Override
    public boolean attackSuccessful() {
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
        for (int healPoints = 0; healPoints < (int) totalHealing; healPoints++) {
            currentHealth += 1;
            if (currentHealth == maxHealth) break;
        }
        if (this.currentHealth < 0) this.currentHealth = 0;
    }

    public int calculateDamage(Move move) {
        return (move.getBaseDamage() + (int) (move.getBaseDamage() * (consumableBoost[0] + potionBoost[0])));
    }

    public int calculateLevel() {
        return XP / 1000;
    }

    public int calculateMaxHealth() {
        return maxHealth * calculateLevel();
    }

    /***
     * Gets the move that the player selected from moveIndex
     * Updates move (increases uses and depreciation cost)
     * Increases character xp
     * Calls takeDamage method from opponent class
     * Damage is calculated using the following formula
     * damageDealt = (baseDamage + (baseDamage * consumable attack damage boost))
     * Dragon enemies take 50% less damage from advanced/sellable moves
     * @param moveIndex Index of move selected show in view
     * @param opponent GameCharacter class of opponent
     * @return Turn summary
     */
    @Override
    public Turn performTurn(int moveIndex, GameCharacter opponent) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Move nextMove = this.chooseMove(moveIndex);
        if (!nextMove.canUse())
            throw new NullPointerException("");
        nextMove.updateMove();
        boolean s = attackSuccessful();
        if (s) {
            this.increaseXP(nextMove.getXPBoost());
            if (opponent instanceof Dragon && nextMove.isSellable()) {
                opponent.takeDamage((int) (0.5 * this.calculateDamage(nextMove)));
            } else {
                opponent.takeDamage(this.calculateDamage(nextMove));
            }
        }
        consumableBoost[0] = 0;
        return new Turn(nextMove, s);
    }

    public void removeMove(int moveIndex) {
        moves.remove(moveIndex);
    }

    public void resetMoves() {
        for (Move m : moves) {
            m.resetMove();
        }
    }

    //************************[Items]**************************

    /***
     * Checks the players item inventory (Hashmap) to see if they own that item already
     * If they do, then increase the quantity of that item by in the hashmap
     * otherwise, add that item to their inventory with a value of 1 item
     * @param itemToAdd The item being added to the players item inventory
     */
    public void addItem(Item itemToAdd) {
        boolean hasItem = false;
        for (Item i : items.keySet()) {
            if (i.getName().equals(itemToAdd.getName())) {
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
    public void removeItem(Item itemToRemove) {
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
    public Turn useItem(int itemIndex) {
        Item itemToUse = ownedItemNames.get(itemIndex - 1);
        if (itemToUse instanceof ConsumableOffensiveItem) {
            consumableBoost[0] = itemToUse.useItem();
        } else if (itemToUse instanceof ConsumableDefensiveItem) {
            consumableBoost[1] = itemToUse.useItem();
        } else if (itemToUse instanceof ConsumableHealingItem) {
            double totalHealing = maxHealth * itemToUse.useItem();
            for (int healPoints = 0; healPoints < (int) totalHealing; healPoints++) {
                currentHealth += 1;
                if (currentHealth == maxHealth) break;
            }
        }
        removeItem(itemToUse);
        return new Turn(itemToUse, true);
    }

    /***
     * Applies potion effect to player depending on what type of potion they drank
     * @param potion potion used by player
     * @return String confirmation that the player drank a potion
     */
    public String drinkPotion(Potion potion) {
        if (potion instanceof OffensivePotion) {
            potionBoost[0] = potion.useItem();
        } else if (potion instanceof DefensivePotion) {
            potionBoost[1] = potion.useItem();
        } else if (potion instanceof HealingPotion) {
            potionBoost[2] = potion.useItem();
        }
        return potion.toString();
    }

    //*********************************************************

    //***********************[Leveling up]********************

    /**
     * This function determines if the player has levelled up
     * @param initialLevel this is the player's initial level (before this function was called)
     * @return True if the player has levelled up, otherwise false
     */
    private boolean hasLevelledUp(int initialLevel) {
        return initialLevel < calculateLevel();
    }

    /**
     * This levels the player up (increases max health and base damage for basic moves)
     */
    private void levelUpPlayer() {
        setMaxHealth(calculateMaxHealth());
        getMoves().get(0).setBaseDamage(150 * calculateLevel());
        getMoves().get(1).setBaseDamage(200 * calculateLevel());
        getMoves().get(2).setBaseDamage(250 * calculateLevel());
    }

    /**
     * Display the player levelling up
     * @param initialLevel the player's initial level before the
     * @return string of level up summary, blank string otherwise
     */
    public String displayLevelUp(int initialLevel) {
        if (hasLevelledUp(initialLevel)) {
            levelUpPlayer();
            return Colors.BOLD + "You leveled up!\n" +
                    "Level " + initialLevel + " -->" + "Level " + calculateLevel() + "\n" +
                    "Move upgrade! " + getMoves().get(0).getName() + " : " + (getMoves().get(0).getBaseDamage() - (150 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(0).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(1).getName() + " : " + (getMoves().get(1).getBaseDamage() - (200 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(1).getBaseDamage() + "\n" +
                    "Move upgrade! " + getMoves().get(2).getName() + " : " + (getMoves().get(2).getBaseDamage() - (250 * (calculateLevel() - initialLevel))) + " --> " + getMoves().get(2).getBaseDamage() + Colors.RESET;
        }
        return "";
    }

    /**
     * Determines how much XP the player is away from levelling up
     * @return int of of the amount of XP available
     */
    private int calculateFromLevellingUp() {
        return ((this.calculateLevel() + 1) * 1000) - this.XP;
    }

    //**********************************************************

    //************[Resetting boosts & shop healing]*************

    /***
     * Resets all consumable item boosts back to 0
     * Normally done when the player dies
     */
    public void resetConsumableBoosts() {
        consumableBoost[0] = 0;
        consumableBoost[1] = 0;
    }

    /***
     * Resets all potion boosts back to 0
     * Normally done when the player dies
     */
    public void resetPermanentBoosts() {
        potionBoost[0] = 0;
        potionBoost[1] = 0;
        potionBoost[2] = 0;
    }

    /**
     * Restore the player's health and reset their
     */
    public void fullRestore() {
        resetConsumableBoosts();
        resetPermanentBoosts();
        setCurrentHealth(maxHealth);
    }

    //**********************************************************

    /**
     * Have the string representation of the player's available moves
     * @return String representation of all of the user's moves
     */
    public String availableMoves() {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < moves.size(); i++) {
            returnString.append(i + 1).append(": ").append(moves.get(i).getShopSummary()).append("\n");
        }
        return returnString.toString();
    }

    /***
     * Build String representation of all items that the user has
     * @return String representation of all items that the user has
     */
    private String availableItems() {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < ownedItemNames.size(); i++) {
            returnString.append(i + 1).append(": ").append(ownedItemNames.get(i).getShopSummary()).append("\n");
        }
        return returnString.toString();
    }

    /**
     * Player's full summary
     * @return String for Player's full summary
     */
    public String toString() {
        return "Full Summary\n------------------------------ \n" +
                this.getName() + "\nYou are at level " + this.calculateLevel() + "\n"
                + this.getXP() + "XP (you are " + calculateFromLevellingUp() + "XP from levelling up)\n"
                + this.getCurrentHealth() + " / " + this.getMaxHealth() + " health\n"
                + this.getCoins() + " coins\n"
                + "Your available moves are: \n" + availableMoves()
                + "\nYour available items are: \n" + availableItems();
    }


}
