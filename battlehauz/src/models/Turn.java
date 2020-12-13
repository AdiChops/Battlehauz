package models;

import models.items.Item;

// Class that is used to return when an opponent performs their turn
public class Turn {
    private final Move usedMove;
    private final boolean success;
    private final Item usedItem;

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

    public Move getUsedMove() {
        return usedMove;
    }

    public Item getUsedItem() {
        return usedItem;
    }

    public boolean isSuccess() {
        return success;
    }

    public String toStringMove() {
        return "used " + getUsedMove().getName() + ((isSuccess()) ? "" : "\nWhoa! The move didn't hit!");
    }

    public String toStringItem() {
        return "used " + getUsedItem().getName() + ". " + getUsedItem();
    }
}
