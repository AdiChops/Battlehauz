package models.utilities;

import models.Move;

// Class that is used to return when an opponent performs their turn
public class Turn {
    private final Move usedMove;
    private final boolean success;

    public Turn(Move m, boolean s){
        this.usedMove = m;
        this.success = s;
    }

    public Move getUsedMove() {
        return usedMove;
    }

    public boolean isSuccess() {
        return success;
    }
}
