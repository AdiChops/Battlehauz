package models.items;

import java.text.DecimalFormat;

public class DefensivePotion extends Potion {

    /***
     * Defensive potions are potions that, after drinking, will apply
     * an extra defensive effect to the player that lasts as long as they are alive
     * @param iName
     * @param iBuyingPrice
     * @param iSellingPrice
     * @param iBoost
     */
    public DefensivePotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        return "Name: " + name + " | Type: Defensive Potion | Boost: " + useItem() * 100 + "% incoming damage decreased each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /****
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(useItem() * 100) + "% less damage each turn!";
    }
}
