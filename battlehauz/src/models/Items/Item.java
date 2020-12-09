package models.Items;

public abstract class Item {
    String name;
    int buyingPrice;
    int sellingPrice;

    public String getName() { return name; }

    public Item(String iName, int iBuyingPrice, int iSellingPrice){
        name = iName;
        buyingPrice = iBuyingPrice;
        sellingPrice = iSellingPrice;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        return "";
    }

    public abstract int useItem();
}
