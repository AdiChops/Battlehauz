package classes;

public abstract class Item {
    String name;
    int quantity;
    int buyingPrice;
    int sellingPrice;

    public Item(String iName, int iQuantity, int iBuyingPrice, int iSellingPrice){
        name = iName;
        quantity = iQuantity;
        buyingPrice = iBuyingPrice;
        sellingPrice = iSellingPrice;
    }

}
