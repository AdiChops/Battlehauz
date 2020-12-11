package models.items;

import java.text.DecimalFormat;

public class ConsumeableOffensiveItem extends ConsumeableItem{

    private double damageBoost;

    public double useItem() { return damageBoost; }

    public ConsumeableOffensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        damageBoost = iDamageBoost;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(damageBoost * 100) + "% more damage on your next turn!";
    }
}
