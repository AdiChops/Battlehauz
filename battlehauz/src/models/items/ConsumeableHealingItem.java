package models.items;

import java.text.DecimalFormat;

public class ConsumeableHealingItem extends ConsumeableItem{

    public ConsumeableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, double iHealingBoost){
        super(iName, iBuyingPrice, iSellingPrice, iHealingBoost);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You were healed for " + df.format(getBoost() * 100) + " health!";
    }
}
