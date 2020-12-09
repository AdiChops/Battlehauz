package models.Items;

public class EquipableItem extends Item{

    private boolean isEquiped;

    public EquipableItem(String iName, int iBuyingPrice, int iSellingPrice){
       super(iName, iBuyingPrice, iSellingPrice);
       isEquiped = false;
    }

    public void equip(){
        isEquiped = true;
    }

    public void unEquip(){
        isEquiped = false;
    }

}
