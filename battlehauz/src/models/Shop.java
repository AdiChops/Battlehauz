package models;
import models.items.ConsumableItem;
import models.utilities.WordsHelper;
//import models.items.EquipableItem;
import models.items.Item;
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
    private final ArrayList <Item> consumableItemsInShop;
    private final ArrayList<Item> potionsInShop;
    private ArrayList<Move> currentMovesInShop;
    private boolean potionPurchased = false;
    private static final Random RND = new Random();

    public Shop() throws IOException{
        potionsInShop = new ArrayList<>();
        consumableItemsInShop = new ArrayList<>();
        loadItems();
    }

    private void loadItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        while (br.ready()) {
            Item curItem = ItemGenerator.generateItems(br);
            if (curItem instanceof ConsumableItem) consumableItemsInShop.add(curItem);
            else potionsInShop.add(curItem);
        }
    }

    //everytime the user enters the shop, generates 5 moves that will stay in the shop for the session.
    private void generateMoves(){
        //generate 5 moves based on the player at the shop's level.
        //stores them in an array of 5. these are the 5 moves that will be in the shop during this current session.
        currentMovesInShop = new ArrayList<Move>();
        int generatedMoveCount = 0;
        while (generatedMoveCount < 5) {
            String moveName = WordsHelper.generateMoveName();
            int damage = RND.nextInt() * ((500*userAtShop.calculateLevel() - 50*userAtShop.calculateLevel() + 1) + 50*userAtShop.calculateLevel());
            int maxTimes = (userAtShop.calculateLevel()*1000)/damage;
            int xpBoost = RND.nextInt(((userAtShop.calculateLevel()*1000)-damage)/10);
            int buyingPrice = (damage / 15) * 5 + (xpBoost/20); // 5 coins/50 damage + 1 coin per 20 XP Boosts
            currentMovesInShop.add(new Move(moveName, damage, xpBoost, maxTimes, buyingPrice));
            generatedMoveCount++;
        }
    }

    //everytime user enters shop.
    public void enterShop(Player inShop){
        userAtShop = inShop;
        generateMoves();
    }

    //a getter for the currentMovesInShop array to print out the (5) moves to console outside of the class.
    public ArrayList<Move> getCurrentMovesInShop(){
        return currentMovesInShop;
    }

    public ArrayList<Item> getConsumableItemsInShop(){
        return consumableItemsInShop;
    }

    public String displaySummaryofMovesInShop(){
        StringBuffer buffer = new StringBuffer();
        int moveIndex = 1;
        for(Move m: currentMovesInShop){
            buffer.append(moveIndex+": "+m.shopSummary()+"\n");
        }
        return buffer.toString();
    }

    public String displaySummaryofItemsInShop(){
        StringBuffer buffer = new StringBuffer();
        int moveIndex = 1;
        for(Item i: consumableItemsInShop){
            buffer.append(moveIndex+": "+ i.getShopSummary()+"\n");
        }
        return buffer.toString();
    }

    //checks if user has enough coins
    //tries to add the move (duplicates, too many moves)
    //if move is successfully added, subtracts coins for the user and returns true
    //else, returns false
    public String purchaseMoveAtIndex(int index){
        if (currentMovesInShop.get(index).getBuyingPrice() < userAtShop.getCoins()){
            if (userAtShop.addMove(currentMovesInShop.get(index))) {
                userAtShop.setCoins(userAtShop.getCoins() - currentMovesInShop.get(index).getBuyingPrice());
                return "You purchased " + currentMovesInShop.get(index).getName() + " for " + currentMovesInShop.get(index).getBuyingPrice();
            }else{
                return "You do not have enough space to add another move \n" +
                        "Sell one of your existing moves to buy another";
            }
        }
        return "Insufficient funds";
    }

    //depends on how items will be displayed and rules for items
    //will discuss in a bit during meeting
    public String purchaseConsumableItemAtIndex(int index){
        if(consumableItemsInShop.get(index).getBuyingPrice() < userAtShop.getCoins()){
            userAtShop.addItem(consumableItemsInShop.get(index));
            userAtShop.setCoins(userAtShop.getCoins() - consumableItemsInShop.get(index).getBuyingPrice());
            return "You purchase " + consumableItemsInShop.get(index).getName() + " for " + consumableItemsInShop.get(index).getName();
        }
        return "Insufficient funds";
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
}
