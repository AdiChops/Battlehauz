package models.items;

import java.text.DecimalFormat;

public class ConsumeableDefensiveItem extends ConsumeableItem{

    public ConsumeableDefensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDefenseBoost){
        super(iName, iBuyingPrice, iSellingPrice, iDefenseBoost);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(getBoost() * 100) + "% less damage on your next turn!";
    }
}
