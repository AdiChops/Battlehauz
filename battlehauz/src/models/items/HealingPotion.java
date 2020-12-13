package models.items;

import java.text.DecimalFormat;

public class HealingPotion extends Potion {
    public HealingPotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    public String getShopSummary() {
        return "Name: " + name + " | Type: Healing Potion | Boost: " + useItem() * 100 + "% health restored at the end of each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will heal " + df.format(useItem() * 100) + "% of your max health each turn!";
    }
}
