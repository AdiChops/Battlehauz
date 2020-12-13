package models.items;

import java.text.DecimalFormat;

public class OffensivePotion extends Potion {

    /***
     * Offensive potions are potions that, after drinking, apply an offensive effect
     * to the player that lasts for the whole game
     * @param iName
     * @param iBuyingPrice
     * @param iSellingPrice
     * @param iBoost
     */
    public OffensivePotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        return "Name: " + name + " | Type: Offensive Potion | Boost: " + useItem() * 100 + "% damage increased each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /***
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(useItem() * 100) + "% more damage each turn!";
    }
}
