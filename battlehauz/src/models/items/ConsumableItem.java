package models.items;

public abstract class ConsumableItem extends Item {
    private double boost;

    public ConsumableItem(String iName, int iBuyingPrice, int iSellingPrice, double iBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        boost = iBoost;
    }

    public double useItem(){return boost;}

    public double getBoost(){return boost;}


}
