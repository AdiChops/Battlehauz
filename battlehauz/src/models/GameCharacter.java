package models;

import java.util.Arrays;

public abstract class GameCharacter {
    private String name;
    private int max_health;
    private int current_health;
    private int level;
    private Move[] moves;
    private final static int MAX_MOVES = 9; //how many types of moves they can have
    private int moveCount;
    boolean isAlive;

    public GameCharacter(String name, int max_health, int level){
        this.name = name;
        this.max_health = max_health;
        this.current_health = max_health;
        this.moves = new Move[MAX_MOVES];
        this.moveCount = 0;
        this.level = level;
        this.isAlive = true;
    }

    public void setCurrent_health(int health){ this.current_health = health; }

    public int getCurrent_health() { return current_health; }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public void setAlive(boolean alive) { isAlive = alive; }

    public boolean isAlive() { return isAlive; }

    public int getMax_health() { return max_health; }

    public void setMax_health(int health){
        //in case at anytime we want to change the health of an enemy as well, I put this here.
        //to be used for player though.
        this.max_health = health;
    }

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
        return "This is the shopkeeper. He's "+this.name +". You look closer and see that he has "
                +this.max_health+" health and is level "
                +level+"! Wow. He must be immortal. You're starting to wonder how long he's been in the game...";
    }
}
