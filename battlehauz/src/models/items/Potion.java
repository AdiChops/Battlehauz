package models.items;

public abstract class Potion extends Item{

    private double boost;

    public Potion(String iName, int iBuyingPrice, int iSellingPrice, double iBoost){
       super(iName, iBuyingPrice, iSellingPrice);
       boost = iBoost;
    }

    public double useItem() {
        return boost; // TODO: fix useItem()
    }


}
