package models.items;

import java.text.DecimalFormat;

public class ConsumeableDefensiveItem extends ConsumeableItem{

    private double defenseBoost;

    public double useItem() { return defenseBoost; }

    public ConsumeableDefensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        defenseBoost = iDamageBoost;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(defenseBoost * 100) + "% less damage on your next turn!";
    }
}
