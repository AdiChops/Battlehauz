package models.items;

import java.text.DecimalFormat;

public class ConsumableOffensiveItem extends ConsumableItem {

    /***
     * Consumable offensive items are items that increase the attack damage of the player
     * after they are used
     * @param iName name
     * @param iBuyingPrice buying price
     * @param iSellingPrice selling price
     * @param iDamageBoost damage boost %
     */
    public ConsumableOffensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDamageBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iDamageBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "Name: " + name + " | Type: Offensive consumable | Boost: " + df.format(useItem() * 100) + "% damage increase when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /***
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(useItem() * 100) + "% more damage on your next turn!";
    }
}
