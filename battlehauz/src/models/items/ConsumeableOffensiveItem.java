package models.items;

import java.text.DecimalFormat;

public class ConsumeableOffensiveItem extends ConsumeableItem{

    public ConsumeableOffensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice, iDamageBoost);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(getBoost() * 100) + "% more damage on your next turn!";
    }
}
