package models;

public abstract class GameCharacter {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int XP;
    private Move[] moves;
    private int moveCount;
    private final static int MAX_MOVES = 9; //how many types of moves they can have

    public GameCharacter(String name, int maxHealth, int xp){
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.moves = new Move[MAX_MOVES];
        this.XP = xp;
        this.moveCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public Move[] getMoves() {
        return moves;
    }

    public int calculateLevel() { return XP/1000;}

    public boolean isAlive() { return currentHealth > 0; }


    public boolean addMove(Move move){
        if (checkMove(move)) {
            if (moveCount < MAX_MOVES) {
                moves[moveCount] = move;
                moveCount++;
                return true;
            }
        }
        return false;
    }

    public boolean checkMove(Move move){
        //this method checks if the move is already in the inventory of the user.
        //for now, it's just checking when you try to add a move.
        //can change so it gives an error message of its own.
        for(int i = 0; i < moveCount; i++) {
            if (moves[i].getName() == move.getName()) {
                return false;
            }
        }
        return true;
    }

    public String speak(char mode){
        return "";
    }

    @Override
    public String toString() {
        if (this instanceof Player) {
            return "This is you: "+this.name+", level "+level+" with "+(Player(this)).getXP()+"/"
                    +(Player(this)).getMaxXP()+" XP and "+this.current_health+"/"+this.max_health+
                    " regenerated health. You have "+(Player(this)).getCoins()+" coins, "+this.moveCount+
                    " moves in your arsenal, and "+(Player(this)).getItemCount()+" items ready to be used in battle.";
        }
        else if (this instanceof Enemy) {
            return "This is an enemy, beware! It's a "+(Enemy(this)).getKind()+" called "+this.name+
                    ", level "+level+" with "+this.max_health+" health.";
        }
//        return "This is the shopkeeper. He's "+this.name +". You look closer and see that he has "
//                +this.max_health+" health and is level "
//                +level+"! Wow. He must be immortal. You're starting to wonder how long he's been in the game...";
    }
}
