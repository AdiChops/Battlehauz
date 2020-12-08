package models;

import models.Items.Item;
import models.utilities.ItemGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Shop {
    private Player userAtShop;
    private ArrayList <Item> items;
    private Queue<String> quotes;
    private Move [] currentMovesInShop;

    public Shop(ArrayList<Item> items){
        this.items = items;
        quotes = new LinkedList<String>();
        loadMoves(); //or this would be void if we generate moves everytime
        loadQuotes();
    }

    //loads all possible moves on initialization of shop
    //this is if we have a hardcoded list
    //stores them in arraylist "moves"
    public void loadMoves(){}

    //loads all quotes on initialization of shop
    //stores them in a queue
    public void loadQuotes(){}

    public void generateMoves(){
        //this is if we decide to do move generation
        //generate 5 moves based on userAtShop.
        //stores them in an array of 5. these are the 5 moves that will be in the shop during this current session.
    }

    //everytime user enters shop.
    public void welcomeUser(Player inShop){
        userAtShop = inShop;
        //this.generateMoves()
        System.out.println("Your character enters the shop. Here, they see the shopkeeper: Dave.");
        this.generateQuote();
        this.displayOptions();
    }

    //this goes in controller
    public void displayOptions(){
        System.out.println("1. Take a look at the moves offered in the shop.");
        System.out.println("2. Take a look at the items they have.");
        System.out.println("3. Sell a move back to the shop.");
        System.out.println("4. Sell an item back to the shop.");
        System.out.println("5. Return back to the main menu.");
    }

    //displays all/a certain amount of moves to the user that they are allowed to purchase.
    //do we generate moves here in this class?
    //this would make sense. in that case, the method to generate moves would be called when welcomeUser() is called.
    public void displayMoves(){
        System.out.println(userAtShop); //prints out the to string method with their stats including level and coins.
        System.out.println("Get more powerful! Check out these moves. Or save your coins for later and come back " +
                "for stronger ones...");
        for (Move i : currentMovesInShop){
            System.out.println(i);
        }
        System.out.println("\nWould you like to purchase one?");
    }

    public void displayItems(){
        System.out.println(userAtShop); //prints out the to string method with their stats including level and coins.
        System.out.println("Interested in the items, hmm? The shop offers many kinds: " +
                "equipable ones, consumable ones..."); //more explanation to be added
        for (Item i: items){
            System.out.println(i);
        }
        System.out.println("\nWould you like to purchase one?");
    }

    //returns move object to be checked by player to see if they're allowed to purchase the move
    public boolean purchaseMoveAtIndex(int index){
        if (userAtShop.addMove(currentMovesInShop[index])){
            if (currentMovesInShop[index].getBuyingPrice() < userAtShop.getCoins()) {
                userAtShop.setCoins(userAtShop.getCoins() - currentMovesInShop[index].getBuyingPrice());
                return true;
            }
        }
        return false;
    }

    //depends on how items will be displayed and rules for items
    //will discuss in a bit during meeting
    public boolean purchaseItemAtIndex(int index){ return false;}

    //should display all of the user's moves in the controller first through print statements
    //the user should be prompted to enter an index from their moves list through controller
    //this function will take the index of the move, check if it's a sellable move,
    //it will proceed to remove the move from the user and to add the coins to the user
    //sends boolean to controller for print statement
    public boolean buyBackMove(int index) {
        ArrayList<Move> userMoves = userAtShop.getMoves();
        if (currentMovesInShop[index].isSellable()) {
            userAtShop.removeMove(index);
            userAtShop.increaseCoins(currentMovesInShop[index].calculateSellingPrice());
            return true;
        }
        return false;
    }

    //depends on rules
    //in controller, will show all items
    //in controller, will enter an index
    //this function will accept an index representing a move from their item list
    //how to proceed will depend on rules for items
    //returns a boolean on whether or not it's successful or not.
    //adds coins to user and removes item
    //sends boolean to controller for print statement
    public boolean buyBackItem(int index){return false;}


    //uses the queue of quotes to remove the first element and return it as a String
    //this is what the shopkeeper says to the player when they first enter.
    //if queue is empty, reloads the quotes and tries again.
    public void generateQuote(){
        String quote;
        while (true) {
            try {
                System.out.println(quotes.remove());
                return;
            } catch (NoSuchElementException e) {
                loadQuotes();
            }
        }
    }

    public void createItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        while (br.ready()){
            items.add(ItemGenerator.generateItems(br));
        }
    }
}
