package models;

import models.items.Item;

// Class that is used to return when a GameCharacter performs their turn
public class Turn {
    private final Move usedMove;
    private final boolean success;
    private final Item usedItem;

    //********************[Constructors]*********************
    public Turn(Move m, boolean s) {
        this.usedMove = m;
        this.usedItem = null;
        this.success = s;
    }

    public Turn(Item i, boolean s) {
        this.usedMove = null;
        this.usedItem = i;
        this.success = s;
    }
    //***************[End of Constructors]*******************
    //*********************[Getters]*************************

    public Move getUsedMove() {
        return usedMove;
    }

    public Item getUsedItem() {
        return usedItem;
    }

    public boolean isSuccess() {
        return success;
    }

    //******************[End of Getters]*********************
    //*****************[String functions]********************

    /**
     * This function returns a string representation of the move the character
     * used and whether or not the move was successful
     * @return String representation of move used and success
     */
    public String toStringMove() {
        return "used " + getUsedMove().getName() + ((isSuccess()) ? "" : "\nWhoa! The move didn't hit!");
    }

    /**
     * This function returns a string representation of the item the character used
     * @return String representation of item used
     */
    public String toStringItem() {
        return "used " + getUsedItem().getName() + ". " + getUsedItem();
    }
}
