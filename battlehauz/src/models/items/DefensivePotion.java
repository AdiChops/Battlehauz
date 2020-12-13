package models.items;

import java.text.DecimalFormat;

public class DefensivePotion extends Potion {

    public DefensivePotion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost) {
        super(iName, iBuyingPrice, iSellingPrice, iBoost);
    }

    public String getShopSummary() {
        return "Name: " + name + " | Type: Defensive Potion | Boost: " + useItem() * 100 + "% incoming damage decreased each turn when used. " +
                "\nBuying Price: " + getBuyingPrice() + " coins | Selling Price: " + getSellingPrice() + " coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(useItem() * 100) + "% less damage each turn!";
    }
}
