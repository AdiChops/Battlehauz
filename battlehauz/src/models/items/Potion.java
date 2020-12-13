package models.items;

public abstract class Potion extends Item {

    private final double boost;

    /***
     * Potions are items that are purchased in the shop and that apply an effect to the player
     * that lasts the whole game
     * @param iName
     * @param iBuyingPrice
     * @param iSellingPrice
     * @param iBoost
     */
    public Potion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice);
        boost = iBoost;
    }

    public double useItem() {
        return boost;
    }

}
