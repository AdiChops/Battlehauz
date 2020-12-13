package models;
import models.gameCharacters.Player;
import models.items.ConsumableItem;
import models.items.Item;
import models.items.Potion;
import models.utilities.ItemGenerator;
import models.utilities.WordsHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
    private Player userAtShop;
    private final ArrayList <Item> consumableItemsInShop;
    private final ArrayList<Item> potionBoostsInShop;
    private ArrayList<Move> currentMovesInShop;
    private ArrayList<String> shopkeeperDialogue;
    private boolean potionBoostPurchased;
    private static final Random RND = new Random();

    public Shop() throws IOException{
        potionBoostsInShop = new ArrayList<>();
        consumableItemsInShop = new ArrayList<>();
        shopkeeperDialogue = new ArrayList<>();
        potionBoostPurchased = false;
        loadItems();
        loadQuotes();
    }

    public boolean isPotionBoostPurchased() {
        return potionBoostPurchased;
    }

    public void setPotionBoostPurchased(boolean potionBoostPurchased) {
        this.potionBoostPurchased = potionBoostPurchased;
    }

    private void loadItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        while (br.ready()) {
            Item curItem = ItemGenerator.generateItems(br);
            if (curItem instanceof ConsumableItem) consumableItemsInShop.add(curItem);
            else potionBoostsInShop.add(curItem);
        }
    }

    private void loadQuotes() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dialogueOptions.txt"));
        while(br.ready()){
            shopkeeperDialogue.add(br.readLine());
        }
    }

    private void generateMoves(){
        currentMovesInShop = new ArrayList<>();
        int generatedMoveCount = 0;
        while (generatedMoveCount < 5) {
            String moveName = WordsHelper.generateMoveName();
            int damage = RND.nextInt(((500*userAtShop.calculateLevel() - 50*userAtShop.calculateLevel() + 1) + 50*userAtShop.calculateLevel())) + 1;
            int maxTimes = (userAtShop.calculateLevel()*1000)/damage;
            int xpBoost = RND.nextInt(((userAtShop.calculateLevel()*1000)-damage)/10);
            int buyingPrice = (damage / 15) * 5 + (xpBoost/20); // 5 coins/50 damage + 1 coin per 20 XP Boosts
            currentMovesInShop.add(new Move(moveName, damage, xpBoost, maxTimes, buyingPrice));
            generatedMoveCount++;
        }
    }

    public void enterShop(Player inShop){
        userAtShop = inShop;
        generateMoves();
    }

    public String displaySummaryofMovesInShop(){
        StringBuilder buffer = new StringBuilder();
        int moveIndex = 1;
        for(Move m: currentMovesInShop){
            buffer.append(moveIndex).append(": ").append(m.getShopSummary()).append("\n");
            moveIndex++;
        }
        return buffer.toString();
    }

    public String displaySummaryofConsumableItemsInShop(){
        StringBuilder builder = new StringBuilder();
        int consumableItemIndex = 1;
        for(Item i: consumableItemsInShop){
            builder.append(consumableItemIndex).append(": ").append(i.getShopSummary()).append("\n");
            consumableItemIndex++;
        }
        return builder.toString();
    }

    public String displaySummaryOfPotionBoostsInShop(){
        StringBuilder builder = new StringBuilder();
        int potionIndex = 1;
        for(Item i: potionBoostsInShop){
            builder.append(potionIndex).append(": ").append(i.getShopSummary()).append("\n");
            potionIndex++;
        }
        return builder.toString();
    }

    public String purchaseMoveAtIndex(int index){
        Move moveToPurchase = currentMovesInShop.get(index);
        if (moveToPurchase.getBuyingPrice() <= userAtShop.getCoins()){
            if (userAtShop.addMove(moveToPurchase)) {
                currentMovesInShop.remove(moveToPurchase);
                userAtShop.setCoins(userAtShop.getCoins() - moveToPurchase.getBuyingPrice());
                return "You purchased " + moveToPurchase.getName() + " for " + moveToPurchase.getBuyingPrice()+" coins.";
            }else{
                return "You do not have enough space to add another move. \n" +
                        "Sell one of your existing moves to buy another.";
            }
        }
        return "Insufficient funds.";
    }

    public String purchaseConsumableItemAtIndex(int index){
        Item itemToPurchase = consumableItemsInShop.get(index);
        if(itemToPurchase.getBuyingPrice() <= userAtShop.getCoins()){
            userAtShop.addItem(itemToPurchase);
            userAtShop.setCoins(userAtShop.getCoins() - itemToPurchase.getBuyingPrice());
            return "You purchased " + itemToPurchase.getName() + " for " + itemToPurchase.getBuyingPrice()+" coins.";
        }
        return "Insufficient funds.";
    }

    public String purchasePotionBoostAtIndex(int index){
        Item itemToPurchase = potionBoostsInShop.get(index);
        if(itemToPurchase.getBuyingPrice() <= userAtShop.getCoins()){
            potionBoostPurchased = true;
            userAtShop.setCoins(userAtShop.getCoins()-itemToPurchase.getBuyingPrice());
            return "You purchased " + itemToPurchase.getName() + " for " + itemToPurchase.getBuyingPrice()+" coins.\n" +
                    userAtShop.drinkPotion((Potion) itemToPurchase);
        }
        return "Insufficient funds.";
    }

    public String buyBackMove(int index) {
        ArrayList<Move> userMoves = userAtShop.getMoves();
        Move moveToBuy = userMoves.get(index);
        if (moveToBuy.isSellable()) {
            userAtShop.removeMove(index);
            userAtShop.increaseCoins(userMoves.get(index).calculateSellingPrice());
        }
        return "You sold the move "+moveToBuy.getName()+" for "+moveToBuy.calculateSellingPrice()+" coins.";
    }

    public String buyBackItem(int index){
        ArrayList<Item> userItems = userAtShop.getOwnedItemNames();
        Item itemToBuy = userItems.get(index);
        userAtShop.removeItem(itemToBuy);
        userAtShop.increaseCoins(userAtShop.getOwnedItemNames().get(index).getSellingPrice());
        return "You sold 1 of the item "+itemToBuy.getName()+" for "+itemToBuy.getSellingPrice()+" coins.";
    }

    public int getSizeOfShopInventory(int i){
        if(i == 1){
            return currentMovesInShop.size();
        }
        if(i == 2){
            return consumableItemsInShop.size();
        }
        if(i == 3){
            return potionBoostsInShop.size();
        }
        else{
            return 0;
        }
    }

    public String getRandomDialogue(){
        return shopkeeperDialogue.get(RND.nextInt(shopkeeperDialogue.size()));
    }
}
