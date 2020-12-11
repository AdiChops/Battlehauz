package models.items;

import java.text.DecimalFormat;

public class ConsumeableHealingItem extends ConsumeableItem{

    private double healingBoost;

    public double useItem() { return healingBoost; }

    public ConsumeableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, double iHealingBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        healingBoost = iHealingBoost;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You were healed for " + df.format(healingBoost * 100) + " health!";
    }
}
