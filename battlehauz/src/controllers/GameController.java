package controllers;

import models.Items.Item;
import models.gameCharacters.Player;
import models.utilities.ItemGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/* move starts with Player choosing move/item
 if move, then call performTurn(int index, boolean isMove), which calls:
    - chooseMove(int index): returns Move at specified index
    -

 */

//remember, a controller is there so that we don't assume where the user input is coming from.
//Straight from Lanthier's notes, we assume there is no System.out.println or scanner anywhere but here.
public class GameController {

    private static int currentFloor = 0;
    private List<Item> allItems = new ArrayList<>();
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

    public void initializeObjects() throws IOException {
        System.out.println(allItems);
        createCharacters();
    }

    public void createCharacters() {

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

    public String displayStats(){
        return gamePlayer.toString();
    }


    public String credits(){
        return "\nBattleHauz made with <3 by:\n" +
                "Aaditya Chopra\n" +
                "Elias Hawa \n" +
                "Veronica Yung\n" +
                "Zara Ali";
    }

}


