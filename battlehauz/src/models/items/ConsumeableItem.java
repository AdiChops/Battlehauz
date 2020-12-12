package models.items;

public abstract class ConsumeableItem extends Item {
    private double boost;

    public ConsumeableItem(String iName, int iBuyingPrice, int iSellingPrice, double iBoost){
        super(iName, iBuyingPrice, iSellingPrice);
        boost = iBoost;
    }

    public double useItem(){return boost;}

    public double getBoost(){return boost;}

    public String shopSummary(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: "+this.getName() + " | Type: ");
        if(this instanceof ConsumeableOffensiveItem){
            buffer.append("Offensive consumable: "+getBoost()*100+"% more damage when used | ");
        }
        else if(this instanceof ConsumeableDefensiveItem){
            buffer.append("Defensive consumable: "+ +getBoost()*100+"% mitigated damage when used | ");
        }
        else{
            buffer.append("Healing consumable: " +getBoost()*100+ "% health healed when used | ");
        }
        buffer.append("Buying Price: "+getBuyingPrice()+ "coins | Selling Price: "+ getSellingPrice()+" coins | "+toString());
        return buffer.toString();
    }

}
