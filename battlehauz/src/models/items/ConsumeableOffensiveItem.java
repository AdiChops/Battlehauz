package models.items;

public class ConsumeableOffensiveItem extends ConsumeableItem{

    private int damageBoost;

    public int useItem() { return damageBoost; }

    public ConsumeableOffensiveItem(String iName, int iBuyingPrice, int iSellingPrice, int iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        damageBoost = iDamageBoost;
    }

    @Override
    public String toString() {
        return "ConsumeableOffensiveItem{" +
                "damageBoost=" + damageBoost +
                ", name='" + name + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
