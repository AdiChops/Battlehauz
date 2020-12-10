package controllers;

import models.Items.Item;
import models.gameCharacters.Player;
import models.gameCharacters.enemy.Calcifer;
import models.gameCharacters.enemy.Dragon;
import models.gameCharacters.enemy.Enemy;
import models.gameCharacters.enemy.Ogre;
import models.utilities.ItemGenerator;
import models.utilities.Turn;
import models.utilities.WordsGeneration;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/* move starts with Player choosing move/item
 if move, then call performTurn(int index, boolean isMove), which calls:
    - chooseMove(int index): returns Move at specified index
    -

 */

//remember, a controller is there so that we don't assume where the user input is coming from.
//Straight from Lanthier's notes, we assume there is no System.out.println or scanner anywhere but here.
public class GameController {

    private int currentFloor = 1;
    private List<Item> allItems = new ArrayList<>();
    private Queue<Enemy> enemiesToFight = new LinkedList<Enemy>();
    private Enemy currentEnemy;
    private Player gamePlayer;
    public void setGamePlayer(Player player){
        this.gamePlayer = player;
    }
    public Player getGamePlayer(){
        return this.gamePlayer;
    }

    public String start(String name) {
        this.gamePlayer = new Player(name);
        return "Welcome to the Battlehauz " + name + "!";
    }

    public String doPlayerTurn(int moveIndex) throws NumberFormatException{
        try {
            moveIndex--;
            Turn currentTurn = gamePlayer.performTurn(moveIndex, currentEnemy);
            return "You " + currentTurn.toString();
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new NumberFormatException();
        }
    }

    private int numberOfEnemies(){
        return currentFloor+2;
    }

    private void generateEnemyQueue(){
        int numEnemies = numberOfEnemies();
        Enemy nextEnemy;
        for(int i = 1; i <= numEnemies; i++){
            if(i%3 == 0){
                nextEnemy = new Dragon(WordsGeneration.generateEnemyName(),currentFloor);
            }// Dragon case
            else if(i%3 == 1){
                nextEnemy = new Calcifer(WordsGeneration.generateEnemyName(), currentFloor);
            }//Calcifer case
            else{
                nextEnemy = new Ogre(WordsGeneration.generateEnemyName(), currentFloor);
            }// Ogre case
            enemiesToFight.add(nextEnemy);
        }
    }

    public String startBattle(){
        currentEnemy = enemiesToFight.remove();
        return "\nYou have encountered the " + currentEnemy.toString() + "!";
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public void enterBattleFloor(){
        generateEnemyQueue();
    }

    public boolean playerIsAlive(){
        return gamePlayer.isAlive();
    }

    public boolean currentEnemyIsAlive() {
        return currentEnemy.isAlive();
    }

    public boolean hasMoreEnemies(){
        return !enemiesToFight.isEmpty();
    }

    public void goToShop(){
//        System.out.println("Your character enters the shop. Here, they see the shopkeeper: Dave.");
//        System.out.println(shop.enterShop(Player player)); //will return a quote but also initialize everything in there for now
//        System.out.println("1. Take a look at the moves offered in the shop.");
//        System.out.println("2. Take a look at the items they have.");
//        System.out.println("3. Sell a move back to the shop.");
//        System.out.println("4. Sell an item back to the shop.");
//        System.out.println("5. Return back to the main menu.");

//        OPTION ONE: BUY MOVE
//        System.out.println(player); //prints out the to string method with their stats including level and coins.
//        System.out.println("Get more powerful! Check out these moves. Or save your coins for later and come back for stronger ones...");
//        moves = shop.displayMoves() (returns five moves in an array list)
//        counter = 0;
//        for (Move m: moves):
//          counter++l
//          System.out.println(counter +”: “+m);
//        System.out.println(“Input the move number you would like to purchase. Put 6 to quit.”)
//        choice = in.nextLine()
//        if (choice != 6):
//          if (shop.purchaseMove(choice)) == true:
//              System.out.println(“Success! You have now inputted the move into your list of moves.);
//		    else:
//              System.out.println(“Failed to purchase. You have a duplicate of this move, too many moves, or not enough coins. Go sell a move before you attempt again.”)
//        Loop back to the options.
//
//
//        OPTION TWO: SELL MOVE
//        System.out.println(player); //prints out the to string method with their stats including level and coins.
//        System.out.println("Interested in the items, hmm? The shop offers many kinds: equipable ones, consumable ones..."); //more explanation to be added
//        items = shop.displayItems()
//        counter = 0;
//        for (Item m: items):
//          counter++
//          System.out.println(counter +”: “+m);
//        System.out.println(“Input the item number you would like to purchase. Put 6 to quit.”)
//        choice = in.nextLine()
//        if (choice != 6):
//          if (shop.purchaseItem(choice) == true):
//              System.out.println(“Success! You have now inputted the move into your list of items.);
//		  else:
//          depends on rules.
//        Loop back to options.
//
//
//        OPTION THREE: SELL MOVE
//        moves = player.getMoves() //returns moves array
//        counter = 0;
//        for (Move m: moves):
//          counter++;
//          System.out.println(counter +”: “+m);
//        System.out.println(“Input the move number you would like to sell. Put 6 to go back.”)
//        choice = in.nextLine()
//        if (choice != 6):
//          if ((shop.buyBackMove(choice)) == true):
//              System.out.println(“Success!” Here’s your new stats, including added coins from selling the coins and new move list: “);
//              System.out.println(player);
//		  else:
//              System.out.println(“You tried to sell a base move. These can’t be sold.”)
//        Loop back to options.
//
//
//        OPTION FOUR: SELL ITEM
//        items = player.getItems()
//        counter = 0;
//        for (Item m: items):
//          counter++
//          System.out.println(counter +”: “+m);
//        System.out.println(“Input the item number you would like to sell. Put 6 to quit.”)
//        choice = in.nextLine()
//        if (choice != 6):
//          if (shop.purchaseItem(choice) == true):
//              System.out.println(“Success! You have now inputted the item into your list of items.);
//		  else:
//          depends on rules.
//        Loop back to options.

//        OPTION FIVE: Return to menu
//        start()

    }

    public String displayRules(){
        return "\nThe rules of the Battlehauz are simple. You fight an increasing number of enemies at each floor (one-by-one).\n" +
                "You and the enemy take turns choosing a move (you also have the option to choose an item to help your next move instead of a move).\n" +
                "You keep fighting enemies and moving up floors until you run out of health\n" +
                "(or a more dark way of saying it, the only way to leave the Battlehauz is DEATH). Have fun :)\n";
    }

    public String displayPlayerShortSummary() {
        return gamePlayer.shortSummary();
    }

    public String displayerPlayerOptions(){
        return "1. Attack\n" +
                "2. Use Item";
    }

    public String displayEnemyStatus() {
        return currentEnemy.getName() + " is at " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth() + " health.";
    }

    public String displayStats(){
        return gamePlayer.toString();
    }

    public void nextFloor(){
        currentFloor++;
    }

    public String credits(){
        return "\nBattleHauz made with <3 by:\n" +
                "Aaditya Chopra\n" +
                "Elias Hawa \n" +
                "Veronica Yung\n" +
                "Zara Ali";
    }
}


