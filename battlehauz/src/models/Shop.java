package models;
import models.gameCharacters.Player;
import models.items.ConsumableItem;
import models.items.Item;
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
    private boolean potionBoostPurchased;
    private static final Random RND = new Random();

    public Shop() throws IOException{
        potionBoostsInShop = new ArrayList<>();
        consumableItemsInShop = new ArrayList<>();
        potionBoostPurchased = false;
        loadItems();
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

    private void generateMoves(){
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

    public void enterShop(Player inShop){
        userAtShop = inShop;
        generateMoves();
    }

    public String displaySummaryofMovesInShop(){
        StringBuffer buffer = new StringBuffer();
        int moveIndex = 1;
        for(Move m: currentMovesInShop){
            buffer.append(moveIndex+": "+m.getShopSummary()+"\n");
        }
        return buffer.toString();
    }

    public String displaySummaryofConsumableItemsInShop(){
        StringBuffer buffer = new StringBuffer();
        int moveIndex = 1;
        for(Item i: consumableItemsInShop){
            buffer.append(moveIndex+": "+ i.getShopSummary()+"\n");
        }
        return buffer.toString();
    }

    public String displaySummaryOfPotionBoostsInShop(){
        StringBuffer buffer = new StringBuffer();
        int moveIndex = 1;
        for(Item i: potionBoostsInShop){
            buffer.append(moveIndex+": "+ i.getShopSummary()+"\n");
        }
        return buffer.toString();
    }

    public String purchaseMoveAtIndex(int index){
        if (currentMovesInShop.get(index).getBuyingPrice() <= userAtShop.getCoins()){
            if (userAtShop.addMove(currentMovesInShop.get(index))) {
                userAtShop.setCoins(userAtShop.getCoins() - currentMovesInShop.get(index).getBuyingPrice());
                return "You purchased " + currentMovesInShop.get(index).getName() + " for " + currentMovesInShop.get(index).getBuyingPrice()+".";
            }else{
                return "You do not have enough space to add another move. \n" +
                        "Sell one of your existing moves to buy another.";
            }
        }
        return "Insufficient funds.";
    }

    public String purchaseConsumableItemAtIndex(int index){
        Item itemToAdd = consumableItemsInShop.get(index);
        if(itemToAdd.getBuyingPrice() <= userAtShop.getCoins()){
            userAtShop.addItem(itemToAdd);
            userAtShop.setCoins(userAtShop.getCoins() - itemToAdd.getBuyingPrice());
            return "You purchased " + itemToAdd.getName() + " for " + itemToAdd.getBuyingPrice()+".";
        }
        return "Insufficient funds.";
    }

    public String purchasePotionBoostAtIndex(int index){
        Item itemToAdd = potionBoostsInShop.get(index);
        if(itemToAdd.getBuyingPrice() <= userAtShop.getCoins()){
            userAtShop.addItem(itemToAdd);
            userAtShop.setCoins(userAtShop.getCoins()-itemToAdd.getBuyingPrice());
            return "You purchased " + itemToAdd.getName() + " for " + itemToAdd.getBuyingPrice()+".";
        }
        return "Insufficient funds.";
    }

    public String buyBackMove(int index) {
        ArrayList<Move> userMoves = userAtShop.getMoves();
        Move moveToBuy = userMoves.get(index);
        if (moveToBuy.isSellable()) {
            userAtShop.increaseCoins(userMoves.get(index).calculateSellingPrice());
            userAtShop.removeMove(index);
        }
        return "You sold the move "+moveToBuy.getName()+" for "+moveToBuy.calculateSellingPrice()+" coins.";
    }

    public String buyBackItem(int index){
        ArrayList<Item> userItems = userAtShop.getOwnedItemNames();
        Item itemToBuy = userItems.get(index);
        if(userAtShop.removeItem(itemToBuy)){
            return "You sold 1 of the item "+itemToBuy.getName()+" for "+itemToBuy.getSellingPrice()+" coins.";
        }
        return "You sold "+itemToBuy.getName()+". However, because you have 0 in quantity left, you got no coins added.";
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
}
