package models.items;

public class Potion extends Item{

    private boolean isEquipped;

    public Potion(String iName, int iBuyingPrice, int iSellingPrice){
       super(iName, iBuyingPrice, iSellingPrice);
       isEquipped = false;
    }

    @Override
    public double useItem() {
        return 0; // TODO: fix useItem()
    }

    @Override
    public String getShopSummary() {
        return null;
    }

    public void equip(){
        isEquipped = true;
    }

    public void unEquip(){
        isEquipped = false;
    }

}
