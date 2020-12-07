package controllers;

import models.GameCharacter;
import models.Items.Item;
import models.Items.ItemGenerator;
import models.Player;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/* move starts with Player choosing move/item
 if move, then call performTurn(int index, boolean isMove), which calls:
    - chooseMove(int index): returns Move at specified index
    -

 */
public class Game {

    private int totalRounds = 0;
    private List<Item> allItems = new ArrayList<>();

    public void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("How would you like to proceed");
        System.out.println("1. Enter BattleHauz\n" +
                "2. Enter the shop\n" +
                "3. View stats\n" +
                "4. View credits\n" +
                "5. Quit game");
        boolean exit = false;
        while (!exit) {
            String choiceS = input.nextLine();
            try {
                int choice = Integer.parseInt(choiceS);
                if (choice == 1){
                    if (totalRounds == 0){
                        try{
                            initializeObjects();
                        }catch (IOException e){
                            System.out.println("Couldn't find file");
                        }
                    }
                    exit = true;
                    //When writing actual functions for the game, remove exit = true;
                }else if (choice == 2){
                    exit = true;
                }else if (choice == 3){
                    displayStats();
                    exit = true;
                }else if (choice == 4){
                    displayCredits();
                    exit = true;
                }else if (choice == 5){
                    System.exit(0);
                }else{
                    throw new InputException("Invalid input. Please enter a number between 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a number");
            }
            catch (InputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void initializeObjects() throws IOException {
        createItems();
        createCharacters();
    }

    public void createItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("items.txt"));
        int lineCount = 0;
        while (br.readLine() != null) lineCount++;
        br.close();
        br = new BufferedReader(new FileReader("items.txt"));
        for (int i = 0; i < lineCount / 5; i++){
            allItems.add(ItemGenerator.generateItems(br));
        }
    }

    public void createCharacters() {

    }

    public void goToShop(){

    }

    public void displayStats(){

    }

    public void displayCredits(){
        System.out.println("BattleHauz made with <3 by:\n" +
                "Aaditya Chopra\n" +
                "Elias Hawa \n" +
                "Veronica Yung\n" +
                "Zara Ali");
    }

}


class InputException extends Exception{
    public InputException(String s){
        super(s);
    }
}