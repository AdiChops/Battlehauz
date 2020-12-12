package models.items;

import java.text.DecimalFormat;

public class ConsumableDefensiveItem extends ConsumableItem {

    public ConsumableDefensiveItem(String iName, int iBuyingPrice, int iSellingPrice, double iDefenseBoost){
        super(iName, iBuyingPrice, iSellingPrice, iDefenseBoost);
    }

    public String getShopSummary(){
        return "Name: " + name + " | Type: Defensive consumable | Boost: " +getBoost()*100+ "% incoming damage decreased when used. " +
                "\nBuying Price: "+getBuyingPrice()+ " coins | Selling Price: "+getSellingPrice()+" coins.";
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.#");
        return "You will take " + df.format(getBoost() * 100) + "% less damage on your next turn!";
    }
}
