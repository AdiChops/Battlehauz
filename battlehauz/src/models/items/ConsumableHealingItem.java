package models.items;

import java.text.DecimalFormat;

public class ConsumableHealingItem extends ConsumableItem {

    public ConsumableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, double iHealingBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iHealingBoost);
    }

    public String getShopSummary() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "Name: " + name + " | Type: Healing consumable | Boost: " + df.format(useItem() * 100) + "% health restored when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You were healed for " + df.format(useItem() * 100) + "% of your health health!";
    }
}
