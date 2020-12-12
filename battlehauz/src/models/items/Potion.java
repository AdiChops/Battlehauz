package models.items;

public class Potion extends Item{

    private boolean isEquiped;

    public Potion(String iName, int iBuyingPrice, int iSellingPrice){
       super(iName, iBuyingPrice, iSellingPrice);
       isEquiped = false;
    }

    @Override
    public int useItem() {
        return 0; // TODO: fix useItem()
    }

    public void equip(){
        isEquiped = true;
    }

    public void unEquip(){
        isEquiped = false;
    }

}
