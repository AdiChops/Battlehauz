package models.Items;

public abstract class ConsumeableItem extends Item {

    public ConsumeableItem(String iName, int iBuyingPrice, int iSellingPrice){
        super(iName, iBuyingPrice, iSellingPrice);
    }

    public abstract int useItem();

}
