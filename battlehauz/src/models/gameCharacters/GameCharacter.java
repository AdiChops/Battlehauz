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

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public boolean addMove(Move move) {
        if (moves.size() < MAX_MOVES) {
            moves.add(move);
            return true;
        }
        return false;
    }

    public Move chooseMove(int index) {
        return moves.get(index);
    }

    public abstract void takeDamage(int damage);

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

    public String currentFighterStatus() {
        return "current health: " + getCurrentHealth() + "/" + getMaxHealth() + "\n" +
                progressBar();
    }

}
