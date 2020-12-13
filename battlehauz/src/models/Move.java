package models;

public class Move {
    private final String name;
    private int baseDamage;
    private final int XPBoost;
    private final int maxUses; //max amount of times you're allowed to use this move
    private int remainingUses; //remaining times you're allowed to use this move
    private final boolean isSellable;
    private final int buyingPrice;
    private double depreciationPercentage; //percent that price is depreciated by.
    //boolean isSellable is necessary for the player to see whether or not they can get the selling price.
    //boolean baseMove is not inherently as necessary because isSellable can be used within the method canUse() as well, but added it until we talk about hierarchy again.

    public Move(String name, int baseDamage, int XPBoost, int maxUses, int buyingPrice) { //constructor for non-basic or "advanced" moves
        this.name = name;
        this.baseDamage = baseDamage;
        this.XPBoost = XPBoost;
        this.maxUses = maxUses;
        this.isSellable = true;
        this.buyingPrice = buyingPrice;
        remainingUses = maxUses;
        depreciationPercentage = 0.0;
    }

    public Move(String name, int baseDamage, int xpBoost) { //constructor for base moves
        this.name = name;
        this.baseDamage = baseDamage;
        this.isSellable = false;
        this.XPBoost = xpBoost;
        this.maxUses = 0; //idk random number
        this.buyingPrice = 0;
        remainingUses = 0;
        remainingUses = 0;
        depreciationPercentage = 0.0;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getXPBoost() {
        return XPBoost;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public boolean isSellable() {
        return isSellable;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    } //for when a character is levelling up and the base move damage needs to be updated.

    public boolean canUse() {
        if (!isSellable) {
            return true;
        } else return remainingUses > 0;
    }

    public void updateMove() {
        if (isSellable) {
            this.remainingUses -= 1;
            depreciationPercentage += 0.01;
        }
    }

    public int calculateSellingPrice() {
        int depreciatedPrice = (int) ((double) buyingPrice * depreciationPercentage);
        return buyingPrice - depreciatedPrice;
    }

    public String getShopSummary() {
        return getName() + " | Base Damage: " + getBaseDamage() + " | " + ((isSellable) ? getRemainingUses() : "Unlimited") + " Uses | XP Boost: " + getXPBoost() + " | Buying Price: " + getBuyingPrice() + " coins | Selling Price: " + calculateSellingPrice();
    }

    /**
     * resetMove() resets the move after each battle
     * it depreciates the maxUses and resets the remainingUses accordingly
     */
    public void resetMove() {
        if (isSellable) {
//            maxUses *= ((1-depreciationPercentage)*maxUses);
            remainingUses = maxUses;
        }
    }


    @Override
    public String toString() { //will update later
        return getName() + " | Base Damage: " + getBaseDamage() + " | XP Boost: " + getXPBoost() + " | " + ((isSellable) ? getRemainingUses() : "Unlimited") + " Uses";
    }
}
