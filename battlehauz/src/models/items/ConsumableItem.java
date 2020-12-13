package models.items;

public abstract class ConsumableItem extends Item {

    private final double boost;

    /***
     * Consumable items are
     * @param iName name
     * @param iBuyingPrice buying price
     * @param iSellingPrice selling price
     * @param iBoost boost %
     */
    public ConsumableItem(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice);
        boost = iBoost;
    }

    /***
     * Method that applies the boost of a specific item onto the player
     * @return boost percentage
     */
    public double useItem() {
        return boost;
    }

}
