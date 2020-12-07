package models.Items;

public class ConsumeableHealingItem extends ConsumeableItem{

    private int healingBoost;

    public int useItem() { return healingBoost; }

    public ConsumeableHealingItem(String iName, int iBuyingPrice, int iSellingPrice, int iHealingBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        healingBoost = iHealingBoost;
    }

    @Override
    public String toString() {
        return "ConsumeableHealingItem{" +
                "healingBoost=" + healingBoost +
                ", name='" + name + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
