package controllers;

import models.Move;
import models.Shop;
import models.gameCharacters.Player;
import models.gameCharacters.enemy.Calcifer;
import models.gameCharacters.enemy.Dragon;
import models.gameCharacters.enemy.Enemy;
import models.gameCharacters.enemy.Ogre;
import models.items.Item;
import utilities.Colors;
import models.Turn;
import utilities.WordsHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GameController {

    private int currentFloor;
    private final Queue<Enemy> enemiesToFight;
    private Enemy currentEnemy;
    private Player gamePlayer;
    private final Shop shop;
    private boolean playersTurn = false;

    //*********************[Constructors and Initializers]***************************
    public GameController() throws IOException {
        shop = new Shop();
        enemiesToFight = new LinkedList<>();
        currentFloor = 1;
    }

    public String start(String name) {
        this.gamePlayer = new Player(name);
        return Colors.BOLD + "Welcome to the Battlehauz, " + name + "!" + Colors.RESET;
    }

    //**************************[Getter methods]***************************************

    public int playerCurrentLevel(){
        return gamePlayer.calculateLevel();
    }

    private int numberOfEnemies() {
        return currentFloor + 2;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean playerIsAlive() {
        return gamePlayer.isAlive();
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    /**
     * Determines if the current enemy is alive
     * @return True if current enemy is still alive, false otherwise
     */
    public boolean currentEnemyIsAlive() {
        return currentEnemy.isAlive();
    }

    /**
     * Determines if there are more enemies on the floor
     * @return True if current enemy is still alive, false otherwise
     */
    public boolean hasMoreEnemies() {
        return !enemiesToFight.isEmpty();
    }

    //******************************************************************************************

    //*********************************[Battles]************************************************

    /**
     * Method that runs when player enters a new floor of the Battlehauz
     * (resets their potionBoostPurchased and generates the enemy queue)
     */
    public void enterBattleFloor() {
        shop.setPotionBoostPurchased(false);
        generateEnemyQueue();
    }

    /**
     * Generates the queue of enemies that the player must fight on the current floor
     */
    private void generateEnemyQueue() {
        enemiesToFight.clear();
        int numEnemies = numberOfEnemies();
        Enemy nextEnemy;
        for (int i = 1; i <= numEnemies; i++) {
            if (i % 3 == 0) {
                nextEnemy = new Dragon(WordsHelper.generateEnemyName(), currentFloor);
            }// Dragon case
            else if (i % 3 == 2) {
                nextEnemy = new Calcifer(WordsHelper.generateEnemyName(), currentFloor);
            }//Calcifer case
            else {
                nextEnemy = new Ogre(WordsHelper.generateEnemyName(), currentFloor);
            }// Ogre case
            enemiesToFight.add(nextEnemy);
        }
    }

    /**
     * To have the enemy speak for miscellaneous reasons (determined on "mode")
     * @param mode determines what kind of quote should be generated
     * @return the String for the quote generated
     */
    public String enemyTalk(char mode) {
        return Colors.YELLOW_BOLD + currentEnemy.getName() + ": "+ Colors.RESET + currentEnemy.speak(mode);
    }

    /**
     * Method removes and returns the first enemy in the list for the battle to start bettween this enemy
     * and the player.
     * @return String that is displayed to user to determine the enemy that the player has to fight next
     */
    public String startBattle() {
        currentEnemy = enemiesToFight.remove();
        playerTurnStart();
        return "You have encountered the " + currentEnemy.toString() + "!";
    }

    /**
     * Method that sets playerTurn to true (called when the player's turn starts)
     */
    public void playerTurnStart() {
        playersTurn = true;
    }

    /**
     * Method that returns the string for the user's options while they are battling
     * @return String of options
     */
    public String displayPlayerOptions() {
        return """
                1. Attack
                2. Use Item
                """;
    }

    /**
     * Method for the player's available moves during battle
     * @return String for the player's options for the move
     */
    public String displayPlayersMoves() {
        ArrayList<Move> moves = gamePlayer.getMoves();
        StringBuilder buffer = new StringBuilder();
        int moveIndex = 1;
        for (Move m : moves) {
            buffer.append(moveIndex).append(": ").append(m).append("\n");
            moveIndex++;
        }
        return buffer.toString();
    }

    /**
     * Method to display the Player's item inventory
     * @return String representation of the player's item inventory
     */
    public String displayPlayerInventory() {
        if (gamePlayer.getOwnedItemNames().size() == 0) return "You have no items you can use!";
        ArrayList<Item> items = gamePlayer.getOwnedItemNames();
        HashMap<Item, Integer> itemsQuantity = gamePlayer.getItems();
        StringBuilder buffer = new StringBuilder();
        int itemIndex = 1;
        for (Item i : items) {
            buffer.append(itemIndex).append(": ").append(i.getShopSummary()).append(" | Quantity: ").append(itemsQuantity.get(i)).append("\n");
            itemIndex++;
        }
        return buffer.toString();
    }

    /**
     * Method to perform the player's turn
     * @param moveIndex the move that the user chose to use
     * @return String of Turn object for summary of the player's turn
     * @throws IndexOutOfBoundsException If the user chose an invalid move (move out of array bounds)
     * @throws InputException If the user enters an advanced move that they can't use anymore due to running out of uses
     */
    public String doPlayerTurn(int moveIndex) throws IndexOutOfBoundsException, InputException {
        try {
            moveIndex--;
            Turn currentTurn = gamePlayer.performTurn(moveIndex, currentEnemy);
            playerTurnEnd();
            return "You " + currentTurn.toStringMove();
        } catch (NullPointerException e) {
            throw new InputException("You can't use that move anymore, you ran out of uses!");
        }
    }

    /***
     * Uses the item at the index that the user selected
     * Applies item effect to Player class
     * @param itemIndex index of item selected
     * @return String that describes action
     */
    public String playerUseItem(int itemIndex) {
        Turn currentTurn = gamePlayer.useItem(itemIndex);
        playerTurnEnd();
        return "You " + currentTurn.toStringItem();
    }

    /**
     * Method that sets playersTurn = false. Called when player's turn ends and enemy's turn starts
     */
    public void playerTurnEnd() {
        playersTurn = false;
    }

    /**
     * Method to display the enemy's current status
     * @return String for the enemy's current status (their name and health)
     */
    public String displayEnemyStatus() {
        return currentEnemy.getName() + "'s " + currentEnemy.currentFighterStatus();
    }

    /**
     * Method to perform the enemy's turn
     * @return String of Turn object for summary of the enemy's turn
     */
    public String doEnemyTurn() {
        Turn currentTurn = currentEnemy.performTurn(currentEnemy.generateMoveIndex(), gamePlayer);
        playerTurnStart();
        return currentEnemy.getName() + " " + currentTurn.toStringMove();
    }

    /**
     * Method for the Player's and Enemy's status to display after a turn is performed
     * @return String representing the fighters' statuses (Player and Enemy)
     */
    public String displayCurrentFightersStatus() {
        return "Your " + gamePlayer.currentFighterStatus() + "\n" + displayEnemyStatus();
    }

    /**
     * Method that returns the quote that the enemy says when they lose the battle
     * @return string for quote that enemy says
     */
    public String enemyLoss() {
        return enemyTalk('L') ;
    }

    /**
     * Method that increases the player's coins based on how many enemies are remaining on the floor
     * @return the number of coins that the player received for defeating the enemy
     */
    private int increasePlayerCoins() {
        int coins;
        switch (enemiesToFight.size()) {
            case 0 -> coins = 25 * currentFloor;
            case 1 -> coins = 20 * currentFloor;
            case 2 -> coins = 15 * currentFloor;
            default -> coins = 10 * currentFloor;
        }
        gamePlayer.increaseCoins(coins);
        return coins;
    }

    /**
     * Method that increases the player's XP based on how many enemies are remaining on the floor
     * @return the number of XP that the player received for defeating the enemy
     */
    private int increasePlayerXP() {
        int xp;
        switch (enemiesToFight.size()) {
            case 0 -> xp = 300 * currentFloor;
            case 1 -> xp = 200 * currentFloor;
            case 2 -> xp = 150 * currentFloor;
            default -> xp = 100 * currentFloor;
        }
        gamePlayer.increaseXP(xp);
        return xp;
    }

    /**
     * Method that determines the coins and XP that a player got for defeating the enemy
     * @return String that summarizes the player's rewards (coins and XP)
     */
    public String displayPlayerRewards() {
        int coins = increasePlayerCoins();
        int xp = increasePlayerXP();
        return Colors.BOLD + "You got " + coins + " coins and " + xp + "XP!" + Colors.RESET;
    }

    /**
     * Method the shows the levelling up String if the user levelled up
     * @param initialLevel to pass into the Player displayLevelUp function
     * @return String the is returned from Player displayLevelUp function
     */
    public String displayLevelUp(int initialLevel){
        return gamePlayer.displayLevelUp(initialLevel);
    }

    /**
     * Method that increments the floor
     */
    public void nextFloor() {
        currentFloor++;
    }

    /**
     * Method that returns the string that gets displayed when a player loses
     * @return String for quotes/dialogues that get displayed
     */
    public String playerLoss(){
        String quote = WordsHelper.shopkeeperQuote();
        return Colors.BOLD + "You died! You are getting sent back to the main menu.\nGo to the shop to purchase consumable items, more powerful moves, and potion boosts.\n" + Colors.RESET + enemyTalk('W') + "\n" + quote;
    }

    /**
     * Method to restore user's health and attributes
     */
    public void restorePlayer() {
        gamePlayer.fullRestore();
    }

    /**
     * Method to reset player's moves to max uses
     */
    public void movesReset(){
        gamePlayer.resetMoves();
    }

    /**
     * Method to reset the floor to floor 1 when the player reenters the battlehauz after dying
     */
    public void resetFloor(){
        currentFloor = 1;
    }

    //******************************************************************************************************************

    //*************************************************[this is where the shop functions start]*************************************************

    /**
     * indicates to the Shop object what Player object is interacting with it by passing it into a function
     * @return String message that welcomes the user
     */
    public String enterShop() {
        shop.enterShop(gamePlayer);
        return "You enter the shop. Here, you see the shopkeeper: Dave. Welcome!";
    }

    /**
     * the main menu of the shop
     * @return a String that indicates what ways the user can interact with the Shop object
     */
    public String displayShopOptions() {
        return ("""
                1. Buy a move
                2. Buy a consumable item
                3. Buy a potion boost
                4. Sell a move back to the shop.
                5. Sell a consumable item back to the shop.
                6. Speak with shopkeeper
                7. Return back to the main menu.""");
    }

    /**
     * @return String that displays the Move objects in the shop for purchase in menu format
     */
    public String displayMovesInShop() {
        return shop.displaySummaryOfMovesInShop();
    }

    /***
     * @return String that displays all consumable available items in the shop in menu format
     */
    public String displayConsumableItemsInShop() {
        return shop.displaySummaryOfConsumableItemsInShop();
    }

    /***
     * @return String that displays all potions available in the shop in menu format
     */
    public String displayPotionBoostsInShop() {
        return shop.displaySummaryOfPotionBoostsInShop();
    }

    /***
     * In the shop, a potion can only be purchased once per round of the Battlehauz
     * @return Boolean, true if a potion has already been purchased, false otherwise
     */
    public boolean boostHasNotBeenPurchasedCheck() {
        return !shop.isPotionBoostPurchased();
    }

    /**
     * used to show user what moves they have in order to sell
     * @return String that displays all moves of the Player interacting with the shop in menu format
     */
    public String displayPlayersMovesForShop() {
        return gamePlayer.availableMoves();
    }

    /**
     * used to check the upper bound for valid user input during menu selection in the shop
     * @param i indicates the list accessed
     * @return the size of the list indicated by the parameter
     */
    public int getSizeOfDisplayedMenu(int i) {
        if (i <= 3) {
            return shop.getSizeOfShopInventory(i);
        } else if (i == 4) {
            return gamePlayer.getMoves().size();
        } else if (i == 5){
            return gamePlayer.getOwnedItemNames().size();
        }
        return 0;
    }

    /**
     * allows the user to try to buy a move in the shop by user input
     * when moves are displayed, the menu items start at 1 and not 0
     * @param index is a user inputted number based off of what was displayed on the menu of moves for purchase
     * @return a String representing success/failure of purchasing the move
     */
    public String buyMove(int index) {
        return shop.purchaseMoveAtIndex(index - 1);
    }

    /***
     * tries to buy consumable item at selected index
     * when consumable items are displayed, the menu items start at 1 and not 0
     * @param index number inputted by the user based off what was displayed on the menu of consumable items for purchase
     * @return String confirmation that the player purchased the item or has insufficient funds
     */
    public String buyConsumableItem(int index) {
        return shop.purchaseConsumableItemAtIndex(index - 1);
    }

    /***
     * tries to buy potion boost at selected index
     * when Potion items are displayed, the menu items start at 1 and not 0
     * @param index number inputted by the user based off what was displayed on the menu of potion boost items for purchase
     * @return String confirmation that the player purchased the potion or has insufficient funds
     */
    public String buyPotionBoost(int index) {
        return shop.purchasePotionBoostAtIndex(index - 1);
    }

    /**
     * tries to sell move in player's inventory back to shop
     * when user moves are displayed, the menu items start at 1 and not 0
     * @param index number inputted by the user based off of what was displayed on the menu of moves of the user
     * @return String confirmation that the player sold the move back and how many coins they recieved
     */
    public String sellMoveToShop(int index) {
        return shop.buyBackMove(index - 1);
    }

    /***
     * Sells consumable item in player's inventory back to shop
     * when items are displayed, the menu items start at 1 and not 0
     * @param index number inputted by the user based off of what was displayed on the menu of items of the user
     * @return String confirmation that the player sold the item back and how many coins they received
     */
    public String sellItemToShop(int index) {
        return shop.buyBackItem(index - 1);
    }

    /**
     * @return String of a random conversation between the Shopkeeper and Player at shop.
     */
    public String displayShopkeeperConversation(){
        return shop.getRandomDialogue();
    }

    //*************************************************[this is where the shop functions end]*************************************************

    //*************************************************[this is where the rules are located]*************************************************

    /**
     * summarizes the basic rules of the Battlehauz battles.
     * @return a String of rules.
     */
    public String displayRules() {
        return """

                The rules of the Battlehauz are simple. You fight an increasing number of enemies at each floor (one-by-one).
                You and the enemy take turns choosing a move (you also have the option to choose an item to help your next move instead of a move).
                You keep fighting enemies and moving up floors until you run out of health.
                (A more dark way of saying it: the only way to leave the Battlehauz is DEATH). Have fun :)
                """;
    }

    /**
     * summarizes all gameplay rules of Battlehauz.
     * @return a String of all of the rules.
     */
    public String displayGameplayRules(){
        return """
                BATTLEHAUZ
                Your objective is to get to the highest floor you can. There's an increasing amount of enemies per floor. 
                Choose to attack an enemy using a move or use a consumable item to help you in your next turn instead.
                The only way out is death!
                                
                PLAYER LEVEL
                Level up by increasing your XP. To do so, defeat enemies in the Battlehauz. Note that moves with less damage usually give more XP than others!
                Leveling up means you get access to more powerful moves in shop, get more max health, and upgrade your basic moves damage.
                                
                ENEMIES
                They will get more harder to beat as you go up. Each enemy type has three basic moves.
                    Ogres: Easy, predictable moves in a sequence.
                    Calcifer: Medium, unpredictable moves.
                    Dragon: Hard, takes only 50% damage of purchased moves. Find their weakness!
                                
                COINS
                You gain coins and XP upon enemy defeat and 100 coins per floor cleared.
                                
                SHOP
                Visit the shop every time you die to get new moves, buy consumable items, or buy potion boosts. 
                These will help you get stronger and get higher in your next run of the Battlehauz.
                                
                MOVES
                Basic moves: Start off with three moves that can’t be sold. The damage of these moves increase as you level up.
                Buying: Buy new moves at the shop. Have a limited amount of uses in the Battlehauz that reset per entry.
                Selling: You can sell the moves you buy. Selling price depreciates the more you’ve used them.
                                
                CONSUMABLE ITEMS
                These items can be consumed instead of taking a move turn.
                	Offensive consumable: next move gives you more damage.
                	Defensive consumable: next enemy move deals less damage to you.
                	Regeneration consumable: you’ll heal next move for a certain amount of health.
                Buying: Purchase these from the shop. You can buy as many as you want.
                Selling: if you didn’t use them in battle, you can always resell for half the price you bought it.
                                
                POTION BOOSTS
                Can only purchase one before you enter the Battlehauz. Consumed immediately when bought. 
                They have an effect that lasts your entire run of the Battlehauz without having to sacrifice a turn.
                	Offensive potion boost: deal extra damage every move until you die.
                	Defensive potion boost: take less damage until you die.
                	Regeneration potion boost: heal every turn for a certain amount until you die.           
                """;
    }

    //*************************************************[this is where the rules end]*************************************************

    public String displayStats() {
        return gamePlayer.toString();
    }

    public String credits() {
        return """
                BattleHauz made with <3 by:
                Aaditya Chopra
                Elias Hawa\s
                Veronica Yung
                Zara Ali""";
    }
}