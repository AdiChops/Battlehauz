package models.Items;

public abstract class Item {
    String name;
    int buyingPrice;
    int sellingPrice;

    public Item(String iName, int iBuyingPrice, int iSellingPrice){
        name = iName;
        buyingPrice = iBuyingPrice;
        sellingPrice = iSellingPrice;
    }

    @Override
    public String toString() {
        return "";
    }
}
