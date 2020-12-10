package models.items;

public class ConsumeableDefensiveItem extends ConsumeableItem{

    private int defenseBoost;

    public int useItem() { return defenseBoost; }

    public ConsumeableDefensiveItem(String iName, int iBuyingPrice, int iSellingPrice, int iDamageBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        defenseBoost = iDamageBoost;
    }

    @Override
    public String toString() {
        return "ConsumeableDefensiveItem{" +
                "defenseBoost=" + defenseBoost +
                ", name='" + name + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
