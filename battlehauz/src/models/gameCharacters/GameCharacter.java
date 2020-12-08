package models.gameCharacters;

import models.Move;

import java.util.ArrayList;

public abstract class GameCharacter {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected ArrayList<Move> moves;
    protected final static int MAX_MOVES = 9; //how many types of moves they can have

    public GameCharacter(String name, int maxHealth){
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.moves = new ArrayList<Move>();
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

    public ArrayList<Move> getMoves() {
        return moves;
    }


    public boolean isAlive() { return currentHealth > 0; }


    public boolean addMove(Move move){
        if (checkMove(move)) {
            if (moves.size() < MAX_MOVES) {
                moves.add(move);
                return true;
            }
        }
        return false;
    }

    public boolean checkMove(Move move){
        //this method checks if the move is already in the inventory of the user.
        //for now, it's just checking when you try to add a move.
        //can change so it gives an error message of its own.
        for(int i = 0; i < moves.size(); i++) {
            if (moves.get(i).getName().equals(move.getName())) {
                return false;
            }
        }
        return true;
    }

    public Move chooseMove(int index){
        if(index < MAX_MOVES && index >= 0)
            return moves.get(index);
        else
            return null; // returning null if invalid index was selected
    }

    public void takeDamage(int damage) {
        // basic takeDamage
        // to expand in other classes
        if (this.getCurrentHealth() >= damage) {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
        }

    }

    public String speak(char mode){
        return "";
    }

}
