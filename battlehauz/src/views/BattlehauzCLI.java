package views;

import controllers.GameController;
import controllers.InputException;

import java.io.IOException;
import java.util.Scanner;

public class BattlehauzCLI {
    public static void displayStartUp(){
        System.out.println("Welcome to\n" +
                " .----------------. .----------------. .----------------. .----------------. .----------------. .----------------.   .----------------. .----------------. .----------------. .----------------. \n" +
                "| .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | | .--------------. | .--------------. | .--------------. | .--------------. |\n" +
                "| |   ______     | | |      __      | | |  _________   | | |  _________   | | |   _____      | | |  _________   | | | |  ____  ____  | | |      __      | | | _____  _____ | | |   ________   | |\n" +
                "| |  |_   _ \\    | | |     /  \\     | | | |  _   _  |  | | | |  _   _  |  | | |  |_   _|     | | | |_   ___  |  | | | | |_   ||   _| | | |     /  \\     | | ||_   _||_   _|| | |  |  __   _|  | |\n" +
                "| |    | |_) |   | | |    / /\\ \\    | | | |_/ | | \\_|  | | | |_/ | | \\_|  | | |    | |       | | |   | |_  \\_|  | | | |   | |__| |   | | |    / /\\ \\    | | |  | |    | |  | | |  |_/  / /    | |\n" +
                "| |    |  __'.   | | |   / ____ \\   | | |     | |      | | |     | |      | | |    | |   _   | | |   |  _|  _   | | | |   |  __  |   | | |   / ____ \\   | | |  | '    ' |  | | |     .'.' _   | |\n" +
                "| |   _| |__) |  | | | _/ /    \\ \\_ | | |    _| |_     | | |    _| |_     | | |   _| |__/ |  | | |  _| |___/ |  | | | |  _| |  | |_  | | | _/ /    \\ \\_ | | |   \\ `--' /   | | |   _/ /__/ |  | |\n" +
                "| |  |_______/   | | ||____|  |____|| | |   |_____|    | | |   |_____|    | | |  |________|  | | | |_________|  | | | | |____||____| | | ||____|  |____|| | |    `.__.'    | | |  |________|  | |\n" +
                "| |              | | |              | | |              | | |              | | |              | | |              | | | |              | | |              | | |              | | |              | |\n" +
                "| '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | | '--------------' | '--------------' | '--------------' | '--------------' |\n" +
                " '----------------' '----------------' '----------------' '----------------' '----------------' '----------------'   '----------------' '----------------' '----------------' '----------------' ");

    }
    public static void main(String [] args){
        GameController game = new GameController();
        displayStartUp();
        game.start();
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
}
