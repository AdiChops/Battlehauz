package models;

import models.Items.EquipableItem;
import models.Items.Item;
import models.gameCharacters.Player;
import models.utilities.ItemGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class Shop {
    private Player userAtShop;
    private ArrayList <Item> items;
    private Queue<String> quotes;
    private Move [] currentMovesInShop;
    private final String [] moveAdjectives = new String[]{"Blinding", "Gut", "Core", "Soul", "Full", "Top", "Rhythmic", "Awful", "Schemed", "Bottom"};
    private final String [] moveNames = new String[]{"Punch", "Kick", "Taunt", "Roll", "Chop", "Snap", "Cut", "Scald", "Slap", "Burn", "Roast", "Toast", "Wring", "Hit"};
    private static final Random RND = new Random();

    public Shop() throws IOException{
        items = new ArrayList<>();
        quotes = new LinkedList<>();
        loadItems();
    }

    public void loadItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        while (br.ready()){
            items.add(ItemGenerator.generateItems(br));
        }
        items.add(new EquipableItem("Ring of Regeneration", 800, 400));
        items.add(new EquipableItem("Ring of Offence", 800, 400));
        items.add(new EquipableItem("Ring of Defence", 800, 400));
        }

    //everytime the user enters the shop, generates 5 moves that will stay in the shop for the session.
    public void generateMoves(){
        //this is if we decide to do move generation
        //generate 5 moves based on userAtShop.
        //stores them in an array of 5. these are the 5 moves that will be in the shop during this current session.
        currentMovesInShop = new Move[5];
        for(int i = 0; i< 5; i++){
            String moveName = moveAdjectives[RND.nextInt(moveAdjectives.length)] + moveNames[RND.nextInt(moveNames.length)];
            int damage = RND.nextInt() * ((500*userAtShop.calculateLevel() - 50*userAtShop.calculateLevel() + 1) + 50*userAtShop.calculateLevel());
            int maxTimes = (userAtShop.calculateLevel()*1000)/damage;
            int xpBoost = RND.nextInt(((userAtShop.calculateLevel()*1000)-damage)/10);
            int buyingPrice = (damage / 15) * 5 + (xpBoost/20); // 5 coins/50 damage + 1 coin per 20 XP Boosts
            currentMovesInShop[i] = new Move(moveName, damage, xpBoost, maxTimes, buyingPrice);
        }
    }

    //everytime user enters shop.
    public void enterShop(Player inShop){
        userAtShop = inShop;
        generateMoves();
    }

    //a getter for the currentMovesInShop array to print out the (5) moves to console outside of the class.
    public Move[] getCurrentMovesInShop(){
        return currentMovesInShop;
    }

    //items are all the same except for equipable ones which are one time purchases.
    public ArrayList<Item> getItems(){
        return items;
    }

    //checks if user has enough coins
    //tries to add the move (duplicates, too many moves)
    //if move is successfully added, subtracts coins for the user and returns true
    //else, returns false
    public boolean purchaseMoveAtIndex(int index){
        if (currentMovesInShop[index].getBuyingPrice() < userAtShop.getCoins()){
            if (userAtShop.addMove(currentMovesInShop[index])) {
                userAtShop.setCoins(userAtShop.getCoins() - currentMovesInShop[index].getBuyingPrice());
                return true;
            }
        }
        return false;
    }

    //depends on how items will be displayed and rules for items
    //will discuss in a bit during meeting
    public boolean purchaseItemAtIndex(int index){
        if(items.get(index).getBuyingPrice() < userAtShop.getCoins()){
            userAtShop.addItem(items.get(index));
            userAtShop.setCoins(userAtShop.getCoins() - items.get(index).getBuyingPrice());
            if (items.get(index) instanceof EquipableItem){
                items.remove(index);
            }
            return true;
        }
        return false;
    }

    //should display all of the user's moves in the controller first through print statements
    //the user should be prompted to enter an index from their moves list through controller
    //this function will take the index of the move, check if it's a sellable move,
    //it will proceed to remove the move from the user and to add the coins to the user
    //sends boolean to controller for print statement
    // if it's false, it's a base move
    public boolean buyBackMove(int index) {
        ArrayList<Move> userMoves = userAtShop.getMoves();
        if (userMoves.get(index).isSellable()) {
            userAtShop.increaseCoins(userMoves.get(index).calculateSellingPrice());
            userAtShop.removeMove(index);
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
    public boolean buyBackItem(int index){
        HashMap<Item, Integer> userItems = userAtShop.getItems();
        return false;
        //having trouble with this one, will come back later.
    }


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
}
