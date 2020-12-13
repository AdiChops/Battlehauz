package models.items;

import java.text.DecimalFormat;

public class ConsumableDefensiveItem extends ConsumableItem {

    /***
     * Consumable defensive items are items decrease incoming damage to the player
     * on the turn after the item is used
     * @param iName name of item
     * @param iBuyingPrice buying price
     * @param iSellingPrice selling price
     * @param iDefenseBoost defensive boost
     */
    public ConsumableDefensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDefenseBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iDefenseBoost);
    }

    /***
     * @return Shop summary for item, includes type, boost, buying and selling price
     */
    public String getShopSummary() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "Name: " + name + " | Type: Defensive consumable | Boost: " + df.format(useItem() * 100) + "% incoming damage decreased when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    /***
     * @return Regular toString, returns summary of action when using item
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(useItem() * 100) + "% less damage on your next turn!";
    }
}
