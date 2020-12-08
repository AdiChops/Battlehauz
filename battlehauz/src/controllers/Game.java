package controllers;

import models.GameCharacter;
import models.Items.Item;
import models.Items.ItemGenerator;
import models.Player;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/* move starts with Player choosing move/item
 if move, then call performTurn(int index, boolean isMove), which calls:
    - chooseMove(int index): returns Move at specified index
    -

 */

//remember, a controller is there so that we don't assume where the user input is coming from.
//Straight from Lanthier's notes, we assume there is no System.out.println or scanner anywhere but here.
public class Game {

    private int totalRounds = 0;
    private List<Item> allItems = new ArrayList<>();

    public void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("How would you like to proceed");
        System.out.println("1. Enter BattleHauz\n" +
                "2. Enter the shop\n" +
                "3. View stats\n" +
                "4. View credits\n" +
                "5. Quit game");
        boolean exit = false;
        while (!exit) {
            String choiceS = input.nextLine();
            try {
                int choice = Integer.parseInt(choiceS);
                if (choice == 1){
                    if (totalRounds == 0){
                        try{
                            initializeObjects();
                        }catch (IOException e){
                            System.out.println("Couldn't find file");
                        }
                    }
                    exit = true;
                    //When writing actual functions for the game, remove exit = true;
                }else if (choice == 2){
                    exit = true;
                }else if (choice == 3){
                    displayStats();
                    exit = true;
                }else if (choice == 4){
                    displayCredits();
                    exit = true;
                }else if (choice == 5){
                    System.exit(0);
                }else{
                    throw new InputException("Invalid input. Please enter a number between 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a number");
            }
            catch (InputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void initializeObjects() throws IOException {
        createItems();
        System.out.println(allItems);
        createCharacters();
    }

    public void createItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        while (br.ready()){
            allItems.add(ItemGenerator.generateItems(br));
        }

    }

    public void createCharacters() {

    }

    public void goToShop(){
//        System.out.println("Your character enters the shop. Here, they see the shopkeeper: Dave.");
//        System.out.println(shop.welcomeUser(Player player)); //will return a quote but also initialize everything in there for now
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

    public void displayStats(){

    }

    public void displayCredits(){
        System.out.println("BattleHauz made with <3 by:\n" +
                "Aaditya Chopra\n" +
                "Elias Hawa \n" +
                "Veronica Yung\n" +
                "Zara Ali");
    }

}


class InputException extends Exception{
    public InputException(String s){
        super(s);
    }
}