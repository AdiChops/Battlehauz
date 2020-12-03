package classes;

public class WearableItem extends Item{

    private boolean isEquiped;

    public WearableItem(String iName, int iQuantity, int iBuyingPrice, int iSellingPrice){
       super(iName, iQuantity, iBuyingPrice, iSellingPrice);
       isEquiped = false;
    }

    public void equip(){
        isEquiped = true;
    }

    public void unEquip(){
        isEquiped = false;
    }

}
