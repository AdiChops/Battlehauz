package models.items;

import java.text.DecimalFormat;

public class ConsumableHealingItem extends ConsumableItem {

    /***
     * Consumable healing items are items that heal the player for a percentage of their
     * health when it's used
     * @param iName name
     * @param iBuyingPrice buying price
     * @param iSellingPrice selling price
     * @param iHealingBoost healing boost %
     */
    public ConsumableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, double iHealingBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iHealingBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "Name: " + name + " | Type: Healing consumable | Boost: " + df.format(useItem() * 100) + "% health restored when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /***
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You were healed for " + df.format(useItem() * 100) + "% of your health health!";
    }
}
