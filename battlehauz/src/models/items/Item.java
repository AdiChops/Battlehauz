package models.items;

public abstract class Item {
    protected String name;
    protected int buyingPrice;
    protected int sellingPrice;

    public String getName() {
        return name;
    }

    /***
     * Items are objects that grant the player a specific
     * ConsumableItems - boost is applied when used and lasts only one turn
     * Potions - boosts are purchased in shop and last whole game
     * @param iName name of item
     * @param iBuyingPrice buying price of item
     * @param iSellingPrice selling price of item
     */
    public Item(String iName, int iBuyingPrice, int iSellingPrice) {
        name = iName;
        buyingPrice = iBuyingPrice;
        sellingPrice = iSellingPrice;
    }

    /***
     * @return buying price of item
     */
    public int getBuyingPrice() {
        return buyingPrice;
    }

    /***
     * @return selling price of item
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        return "";
    }

    /***
     * Method that when called will act as if the item is used
     * @return item specific boost
     */
    public abstract double useItem();

    /***
     * shop summary is the information of the item that is dislayed in the shop
     * @return String that describes shop information of item
     */
    public abstract String getShopSummary();
}
