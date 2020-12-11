package models;

public class Move {
    private String name;
    private int baseDamage;
    private int XPBoost;
    private int maxUses; //max amount of times you're allowed to use this move
    private int remainingUses; //remaining times you're allowed to use this move
    private int timesUsed; //times move has been used
    private boolean isSellable;
    private int buyingPrice;
    private double depreciationPercentage; //percent that price is depreciated by.
    //boolean isSellable is necessary for the player to see whether or not they can get the selling price.
    //boolean baseMove is not inherently as necessary because isSellable can be used within the method canUse() as well, but added it until we talk about hierarchy again.

    public Move(String name, int baseDamage, int XPBoost, int maxUses, int buyingPrice){
        this.name = name;
        this.baseDamage = baseDamage;
        this.XPBoost = XPBoost;
        this.maxUses = maxUses;
        this.isSellable = true;
        this.buyingPrice = buyingPrice;
        remainingUses = maxUses;
        timesUsed = 0;
        depreciationPercentage = 0.0;
    }

    public Move(String name, int baseDamage){ //constructor for base moves
        this.name = name;
        this.baseDamage = baseDamage;
        this.isSellable = false;
        this.XPBoost = 0;
        this.maxUses = 0; //idk random number
        this.buyingPrice = 0;
        remainingUses = 0;
        timesUsed = 0;
        remainingUses = 0;
        depreciationPercentage = 0.0;
    }

    public String getName() { return name; }

    public int getBaseDamage() { return baseDamage; }

    public int getMaxUses() { return maxUses; }

    public int getXPBoost() { return XPBoost; }

    public int getRemainingUses() { return remainingUses; }

    public int getTimesUsed() { return timesUsed; }

    public int getBuyingPrice() { return buyingPrice; }

    public boolean isSellable() { return isSellable; }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    } //for when a character is levelling up and the base move damage needs to be updated.

    public void setRemainingUses(int remainingUses) { this.remainingUses = remainingUses; }

    public void setTimesUsed(int timesUsed) { this.timesUsed = timesUsed; }

    public boolean canUse(){
        if(!isSellable){
            return true;
        }
        else if(remainingUses != 0){
            return false;
        }
        return true;
    }

    public void updateMove(){
        if(isSellable){
            this.remainingUses -= 1;
            this.timesUsed += 1;
            depreciationPercentage += 0.1;
        }
    }

    public void resetUses() {
        if (isSellable) {
            this.maxUses *= (100-depreciationPercentage);
            this.remainingUses = maxUses;
            this.timesUsed = 0;
        }
    }

    public int calculateSellingPrice(){
        int depreciatedPrice = (int)((double)buyingPrice * depreciationPercentage);
        return buyingPrice - depreciatedPrice;
    }



    @Override
    public String toString() { //will update later
        return getName() + " | Base Damage: " + getBaseDamage() + " | " + ((isSellable)?getRemainingUses():"Unlimited") + " Uses | XP Boost: " + getXPBoost();
    }
}
