package models.items;

import java.text.DecimalFormat;

public class ConsumableHealingItem extends ConsumableItem {

    public ConsumableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, double iHealingBoost){
        super(iName, iBuyingPrice, iSellingPrice, iHealingBoost);
    }

    public String getShopSummary(){
        return "Name: " + name + " | Type: Healing consumable | Boost: " +getBoost()*100+ "% health restored when used.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You were healed for " + df.format(getBoost() * 100) + " health!";
    }
}
