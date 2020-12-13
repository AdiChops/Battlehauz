package models.items;

import java.text.DecimalFormat;

public class HealingPotion extends Potion {

    /***
     * Healing potions are potions that heal the player for a percentage of their
     * health at the end of each turn (after the enemies turn) after drinking
     * @param iName
     * @param iBuyingPrice
     * @param iSellingPrice
     * @param iBoost
     */
    public HealingPotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        return "Name: " + name + " | Type: Healing Potion | Boost: " + useItem() * 100 + "% health restored at the end of each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /***
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will heal " + df.format(useItem() * 100) + "% of your max health each turn!";
    }
}
