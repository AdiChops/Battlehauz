package models.gameCharacters;

import models.Move;
import utilities.Colors;

import java.util.ArrayList;

public abstract class GameCharacter {
    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected ArrayList<Move> moves;
    private final static int MAX_MOVES = 9; //how many types of moves they can have

    public GameCharacter(String name) {
        this.name = name;
        this.maxHealth = 1000;
        this.currentHealth = 1000;
        this.moves = new ArrayList<>();
    }

    public GameCharacter(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.moves = new ArrayList<>();
    }

    //********************[Getters and Setters]*********************

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

    //*************[End of Getters and Setters]***************

    //********************[Other Methods]*********************

    /***
     * Determines if character is still alive
     * @return true if character is alive, false otherwise
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    /***
     * adds a move ot the move ArrayList
     * @param move for the move to be added to list
     * @return whether the move was added
     */
    public boolean addMove(Move move) {
        if (moves.size() < MAX_MOVES) {
            moves.add(move);
            return true;
        }
        return false;
    }

    /***
     * selects a move from the moves ArrayList
     * @param index the index at which the move is located
     * @return the move at the specified index
     */
    public Move chooseMove(int index) {
        return moves.get(index);
    }

    /***
     * this abstract method is overridden in the subclasses for the character taking damage from its opponent
     * @param damage how much damage the character has to take
     */
    public abstract void takeDamage(int damage);

    /***
     * this function makes a progress bar based on the character's current health/max health
     * @return progress bar string representation
     */
    protected String progressBar() {
        double percentage = ((double) currentHealth / (double) maxHealth) * 100;
        String color;
        if(percentage <= 30){
            color = Colors.RED;
        }
        else if(percentage <= 50){
            color = Colors.YELLOW;
        }
        else{
            color = Colors.GREEN;
        }
        StringBuilder barBuilder = new StringBuilder(color).append("[");
        for (int i = 0; i < 20; i++) {
            if(percentage == 0){
                barBuilder.append("..");
            }
            else if (i <= percentage / 5)
                barBuilder.append("==");
            else
                barBuilder.append("..");
        }
        barBuilder.append("]").append(Colors.RESET);
        return barBuilder.toString();
    }

    /**
     * returns status for when character is in battle
     * @return String statistics for character in battle
     */
    public String currentFighterStatus() {
        return "current health: " + getCurrentHealth() + "/" + getMaxHealth() + "\n" +
                progressBar();
    }

}
