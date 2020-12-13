package models;

import models.gameCharacters.Player;
import models.items.ConsumableItem;
import models.items.Item;
import models.items.Potion;
import utilities.Colors;
import utilities.ItemGenerator;
import utilities.WordsHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
    private Player userAtShop;
    private final ArrayList<Item> consumableItemsInShop;
    private final ArrayList<Item> potionBoostsInShop;
    private ArrayList<Move> currentMovesInShop;
    private final ArrayList<String> shopkeeperDialogue;
    private boolean potionBoostPurchased;
    private static final Random RND = new Random();

    public Shop() throws IOException {
        potionBoostsInShop = new ArrayList<>();
        consumableItemsInShop = new ArrayList<>();
        shopkeeperDialogue = new ArrayList<>();
        potionBoostPurchased = false;
        loadItems();
        loadQuotes();
    }

    //********************[Getters and Setters]*********************

    public boolean isPotionBoostPurchased() {
        return potionBoostPurchased;
    }

    public void setPotionBoostPurchased(boolean potionBoostPurchased) {
        this.potionBoostPurchased = potionBoostPurchased;
    }

    //********************[End of Getters and Setters]*********************

    //********************[Loads Items and Shopkeeper Quotes From File]*********************

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
        while (br.ready()) {
            String quote = br.readLine();
            quote = quote.replaceAll("Dave:", Colors.GREEN_BOLD + "Dave:" + Colors.RESET); // colors
            quote = quote.replaceAll("You:", Colors.BLUE_BOLD + "You:" + Colors.RESET); // colors
            shopkeeperDialogue.add(quote);
        }
    }
    //********************[End Of Loading Items and Shopkeeper Quotes From File]*********************

    /**
     * clears any moves already in the shop and generates five moves.
     * damage of the moves is based off of the level of the player at the shop.
     * max times a player can use the move and XP a move gives is calculated off of level and damage.
     * the buying price is calculated off of the damage and XP a move gives.
     * adds all five moves one by one to the cleared currentMovesInShop list.
     */
    private void generateMoves() {
        currentMovesInShop = new ArrayList<>();
        int generatedMoveCount = 0;
        while (generatedMoveCount < 5) {
            String moveName = WordsHelper.generateMoveName();
            int damage = RND.nextInt(((500 * userAtShop.calculateLevel() - 50 * userAtShop.calculateLevel() + 1) + 50 * userAtShop.calculateLevel())) + 1;
            int maxTimes = (userAtShop.calculateLevel() * 1000) / damage;
            int xpBoost = RND.nextInt(((userAtShop.calculateLevel() * 1000) - damage) / 10);
            int buyingPrice = (damage / 15) * 5 + (xpBoost / 20); // 5 coins/50 damage + 1 coin per 20 XP Boosts
            currentMovesInShop.add(new Move(moveName, damage, xpBoost, maxTimes, buyingPrice));
            generatedMoveCount++;
        }
    }

    /**
     * assigns the attribute userAtShop to reflect the current player and generates moves for the shop
     * @param inShop The current player interacting with the Shop
     */
    public void enterShop(Player inShop) {
        userAtShop = inShop;
        generateMoves();
    }

    /**
     * Builds a new String that appends the player in shop's current coins
     * Appends the summary, including price, of all the moves (Move objects) currently available for purchase, proceeded by an index number
     * @return a String of Move objects in the class in summary format
     */
    public String displaySummaryOfMovesInShop() {
        StringBuilder builder = new StringBuilder();
        builder.append("Your coins: ").append(userAtShop.getCoins()).append("\n");
        int moveIndex = 1;
        for (Move m : currentMovesInShop) {
            builder.append(moveIndex).append(": ").append(m.getShopSummary()).append("\n");
            moveIndex++;
        }
        return builder.toString();
    }

    /**
     * Builds a new String that appends the player in shop's current coins
     * Appends the summary, including price, of all the consumable items (ConsumableItem objects) currently available for purchase, proceeded by an index number
     * @return a String of Item objects in the class in summary format
     */
    public String displaySummaryOfConsumableItemsInShop() {
        StringBuilder builder = new StringBuilder();
        builder.append("Your coins: ").append(userAtShop.getCoins()).append("\n");
        int consumableItemIndex = 1;
        for (Item i : consumableItemsInShop) {
            builder.append(consumableItemIndex).append(": ").append(i.getShopSummary()).append("\n");
            consumableItemIndex++;
        }
        return builder.toString();
    }

    /**
     * Builds a new String that appends the player in Shop's current coins
     * Appends the summary, including price, of all the potion boosts (Potion objects) currently available for purchase, proceeded by an index number
     * @return a String of Item objects in the class in summary format
     */
    public String displaySummaryOfPotionBoostsInShop() {
        StringBuilder builder = new StringBuilder();
        builder.append("Your coins: ").append(userAtShop.getCoins()).append("\n");
        int potionIndex = 1;
        for (Item i : potionBoostsInShop) {
            builder.append(potionIndex).append(": ").append(i.getShopSummary()).append("\n");
            potionIndex++;
        }
        return builder.toString();
    }


    /**
     * checks if the Player at the Shop has enough coins to purchase an indicated Move.
     * if true, then calls a Player method that tries to add the Move to their Move list, and returns False if they have too many moves.
     * if previous method returns true, removes the Move from the list in shop and decreases Player coins depending on price of the move.
     * @param index Takes an index representing a Move object in the currentMovesInShop list
     * @return a String representing success/failure in purchasing a Move
     */
    public String purchaseMoveAtIndex(int index) {
        Move moveToPurchase = currentMovesInShop.get(index);
        if (moveToPurchase.getBuyingPrice() <= userAtShop.getCoins()) {
            if (userAtShop.addMove(moveToPurchase)) {
                currentMovesInShop.remove(moveToPurchase);
                userAtShop.setCoins(userAtShop.getCoins() - moveToPurchase.getBuyingPrice());
                return "You purchased " + moveToPurchase.getName() + " for " + moveToPurchase.getBuyingPrice() + " coins.";
            } else {
                return "You do not have enough space to add another move. \n" +
                        "Sell one of your existing moves to buy another.";
            }
        }
        return "Insufficient funds.";
    }

    /**
     * checks if the Player at the Shop has enough coins to purchase an indicated Consumable Item.
     * if true, then calls a Player method that adds the item to their inventory and decreases Player coins depending on price of the item.
     * @param index Takes an index representing an Item object in the consumableItemsInShop list
     * @return a String representing success/failure in purchasing a consumable item
     */
    public String purchaseConsumableItemAtIndex(int index) {
        Item itemToPurchase = consumableItemsInShop.get(index);
        if (itemToPurchase.getBuyingPrice() <= userAtShop.getCoins()) {
            userAtShop.addItem(itemToPurchase);
            userAtShop.setCoins(userAtShop.getCoins() - itemToPurchase.getBuyingPrice());
            return "You purchased " + itemToPurchase.getName() + " for " + itemToPurchase.getBuyingPrice() + " coins.";
        }
        return "Insufficient funds.";
    }

    /**
     * checks if the Player at the Shop has enough coins to purchase an indicated Potion Item.
     * if true, then decreases Player coins depending on price of the item.
     * additionally sets potionBoostPurchased to true to indicate the user has already purchased a boost at the shop.
     * calls upon Player method that consumes the potion boost and applies its stats immediately.
     * @param index Takes an index representing an Item object in the potionBoostsInShop list
     * @return a String representing success/failure in purchasing a potion boost item
     */
    public String purchasePotionBoostAtIndex(int index) {
        Item itemToPurchase = potionBoostsInShop.get(index);
        if (itemToPurchase.getBuyingPrice() <= userAtShop.getCoins()) {
            potionBoostPurchased = true;
            userAtShop.setCoins(userAtShop.getCoins() - itemToPurchase.getBuyingPrice());
            return "You purchased " + itemToPurchase.getName() + " for " + itemToPurchase.getBuyingPrice() + " coins.\n" +
                    userAtShop.drinkPotion((Potion) itemToPurchase);
        }
        return "Insufficient funds.";
    }

    /**
     * gets moves list of the Player at the Shop and retrieves Move object indicated by parameter.
     * checks if the move is a sellable move
     * if true, then calls upon Player method that removes the Move object from their list of moves
     * also increases the Player's coins by the selling price attribute of the Move object
     * @param index takes an index representing a Move object in the Player interacting with the Shop's moves list.
     * @return a String representing success/failure in selling a Move object
     */
    public String buyBackMove(int index) {
        ArrayList<Move> userMoves = userAtShop.getMoves();
        Move moveToBuy = userMoves.get(index);
        if (moveToBuy.isSellable()) {
            userAtShop.removeMove(index);
            userAtShop.increaseCoins(moveToBuy.calculateSellingPrice());
            return "You sold the move " + moveToBuy.getName() + " for " + moveToBuy.calculateSellingPrice() + " coins.";
        }
        return "You're trying to sell a basic move. These can't be sold. Try selling a move you've purchased at the shop.";
    }

    /**
     * gets consumable items list of the Player at the Shop and retrieves Item object indicated by parameter.
     * calls upon Player method that removes one of the Item object in their list of items, or removes it from their list if they have 0 left.
     * increases the Player's coins by the selling price attribute of the object
     * @param index takes an index representing an Item object in the Player interacting with the Shop's consumable item list.
     * @return a String representing success in selling the object
     */
    public String buyBackItem(int index) {
        ArrayList<Item> userItems = userAtShop.getOwnedItemNames();
        Item itemToBuy = userItems.get(index);
        userAtShop.removeItem(itemToBuy);
        userAtShop.increaseCoins(itemToBuy.getSellingPrice());
        return "You sold 1 of the item " + itemToBuy.getName() + " for " + itemToBuy.getSellingPrice() + " coins.";
    }

    /**
     * calculates size of different inventory lists in the Shop class
     * @param i takes an integer representing what list's size the caller wants returned from the Shop class
     * @return the size of the indicated list in the Shop
     */
    public int getSizeOfShopInventory(int i) {
        if (i == 1) {
            return currentMovesInShop.size();
        }
        if (i == 2) {
            return consumableItemsInShop.size();
        }
        if (i == 3) {
            return potionBoostsInShop.size();
        } else {
            return 0;
        }
    }

    /**
     * uses random number generation to get a conversation from the shopkeeperDialogue list based off of quotes
     * @return a String of conversation from the perspective of the Shopkeeper and Player at the shop.
     */
    public String getRandomDialogue() {
        return shopkeeperDialogue.get(RND.nextInt(shopkeeperDialogue.size()));
    }
}
