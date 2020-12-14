package models;

public class Move {
    private final String name;
    private int baseDamage;
    private final int XPBoost;
    private final int maxUses;
    private int remainingUses;
    private final boolean isSellable;
    private final int buyingPrice;
    private double depreciationPercentage;

    /**
     * this constructor is used to construct moves that are purchasable/sellable and therefore have more stats
     */
    public Move(String name, int baseDamage, int XPBoost, int maxUses, int buyingPrice) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.XPBoost = XPBoost;
        this.maxUses = maxUses;
        this.isSellable = true;
        this.buyingPrice = buyingPrice;
        remainingUses = maxUses;
        depreciationPercentage = 0.0;
    }

    /**
     * this constructor is used to construct the basic moves that the Player objects start off with the enemy basic moves
     * these moves do not require all the stats that sellable moves do
     */
    public Move(String name, int baseDamage, int xpBoost) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.isSellable = false;
        this.XPBoost = xpBoost;
        this.maxUses = 0;
        this.buyingPrice = 0;
        remainingUses = 0;
        remainingUses = 0;
        depreciationPercentage = 0.0;
    }

    //********************[Getters and Setters]*********************

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
    }

    //********************[End of Getters and Setters]*********************

    //********************[Other Functions]*********************

    /**
     * checks is a the Move object can be used
     * if a Move is not sellable, it is a basic move with no limited usage and can always be used.
     * if it's not a sellable move, checks if the remaining uses of this Move are greater than 0
     * @return a boolean that indicates if the Move object is allowed to be used or not
     */
    public boolean canUse() {
        if (!isSellable) {
            return true;
        } else return remainingUses > 0;
    }

    /**
     * updates the remaining uses and price depreciation percentage, indicating it has been used
     * if the move is sellable, its remaining uses is decreased by 1 and the depreciation rate is increased by 1%
     */
    public void updateMove() {
        if (isSellable) {
            this.remainingUses -= 1;
            depreciationPercentage += 0.01;
        }
    }

    /**
     * resetMove() resets the moves after each death in the Battlehauz
     */
    public void resetMove() {
        if (isSellable) {
            remainingUses = maxUses;
        }
    }

    /**
     * calculates the selling price based off of the initial buying price and the depreciation rate
     * the more a Move is used, the less it's worth.
     * @return an integer representing the calculated selling price of this Move object.
     */
    public int calculateSellingPrice() {
        int depreciatedPrice = (int) ((double) buyingPrice * depreciationPercentage);
        return buyingPrice - depreciatedPrice;
    }

    /**
     * compiles all stats of the Move object including buying price and selling price
     * @return a String of Move attributes in summary format
     */
    public String getShopSummary() {
        return getName() + " | Base Damage: " + getBaseDamage() + " | " + ((isSellable) ? getRemainingUses() : "Unlimited") + " Uses | XP Boost: " + getXPBoost() + " | Buying Price: " + getBuyingPrice() + " coins | Selling Price: " + calculateSellingPrice();
    }

    /**
     * compiles certain stats of the Move object excluding buying price and selling price.
     * @return a String of Move attributes in summary format, without buying price or selling price.
     */
    @Override
    public String toString() {
        return getName() + " | Base Damage: " + getBaseDamage() + " | XP Boost: " + getXPBoost() + " | " + ((isSellable) ? getRemainingUses() : "Unlimited") + " Uses";
    }
}
