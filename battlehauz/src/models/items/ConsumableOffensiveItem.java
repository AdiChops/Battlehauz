package models.items;

import java.text.DecimalFormat;

public class ConsumableOffensiveItem extends ConsumableItem {

    public ConsumableOffensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice, iDamageBoost);
    }

    public String getShopSummary(){
        DecimalFormat df = new DecimalFormat("##.#");
        return "Name: " + name + " | Type: Offensive consumable | Boost: " +df.format(useItem() * 100)+ "% damage increase when used. " +
                "\nBuying Price: "+getBuyingPrice()+ " coins | Selling Price: "+getSellingPrice()+" coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will deal " + df.format(useItem() * 100) + "% more damage on your next turn!";
    }
}
