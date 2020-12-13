package models.items;

import java.text.DecimalFormat;

public class OffensivePotion extends Potion {

    public OffensivePotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    public String getShopSummary() {
        return "Name: " + name + " | Type: Offensive Potion | Boost: " + useItem() * 100 + "% damage increased each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(useItem() * 100) + "% more damage each turn!";
    }
}
