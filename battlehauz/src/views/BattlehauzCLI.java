package views;

import controllers.GameController;
import controllers.InputException;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class BattlehauzCLI {
    private static final Scanner INPUT = new Scanner(System.in);
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
    private static char proceed(){
        try {
            System.out.println("\nHow would you like to proceed?");
            System.out.println("B - Enter BattleHauz\n" +
                    "S - Enter the shop\n" +
                    "F - View Full Stats\n" +
                    "A - About Game\n" +
                    "Q - Quit game");
            String choiceS = INPUT.nextLine();
            return choiceS.toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e){
            System.err.println("Oops! Please enter a choice.");
            return proceed();
        }
    }

    public static void main(String [] args){
        GameController game = new GameController();
        displayStartUp();
        System.out.print("What should we call you? ");
        String name = INPUT.nextLine();
        System.out.println(game.start(name));
        char choice;
        do{
            choice = proceed();
            try {
                switch(choice){
                    case 'B':{
                        System.out.println("Ah, so you have chosen to enter the Battlehauz! Good luck! Oh wait, you don't need luck, you need skill. Good skill!");
                        System.out.println(game.displayRules());
                        //Game starts and continues while player is alive
                        while(game.playerIsAlive()){
                            game.enterBattleFloor(); //generate enemies for floor
                            System.out.println("\nYou have entered floor " + game.getCurrentFloor() + " of the Battlehauz.");
                            while(game.hasMoreEnemies()){ //while the floor still has enemies
                                System.out.println(game.startBattle()); //game.startBattle sets the currentEnemy as the first enemy in the list
                                while(game.currentEnemyIsAlive()){ //while the currentEnemy is still alive
//                                    System.out.println("What would you like to do >");
//                                    System.out.println(game.displayerPlayerOptions());

                                    System.out.println(game.displayPlayerShortSummary());
                                    System.out.print("Which move would you like to select > ");
                                    String moveChoiceS = INPUT.next();
                                    try{
                                        int moveChoice = Integer.parseInt(moveChoiceS);
                                        if (moveChoice < 0 || moveChoice > game.getGamePlayer().getMoves().size()){
                                            throw new InputException("");
                                        }
                                        System.out.println(game.doPlayerTurn(moveChoice));
                                        System.out.println(game.displayEnemyStatus());
                                    } catch(NumberFormatException e){
                                        System.err.println("Oops! Please enter a valid number.");
                                    } catch (InputException e){
                                        System.err.println("That move doesn't exist! Pick a valid move index!");
                                    }
                                }
                            } // game.hasSMoreEnemies, completing floor
                            game.nextFloor();
                        }
                        break;
                    }
                    case 'S':{
                        // TODO: enter shop
                        break;
                    }
                    case 'F':{
                        displayStats(game);
                        break;
                    }
                    case 'A':{
                        displayCredits(game);
                        break;
                    }
                    case 'Q': {
                        quit(game);
                        break;
                    }
                    default:{
                        throw new InputException("Oops! Please enter a valid choice.");
                    }
                }
            }
            catch (InputException e){
                System.err.println(e.getMessage());
            }// end-catch
        }while (choice != 'Q'); // do-while

//                int choice = Integer.parseInt(choiceS);
//                if (choice == 1){
//                    if (totalRounds == 0){
//                        try{
//                            initializeObjects();
//                        }catch (IOException e){
//                            System.out.println("Couldn't find file");
//                        }
//                    }
//                    exit = true;
//                    //When writing actual functions for the game, remove exit = true;
//                }else if (choice == 2){
//                    exit = true;
//                }else if (choice == 3){
//                    displayStats();
//                    exit = true;
//                }else if (choice == 4){
//                    displayCredits(game);
//                    exit = true;
//                }else if (choice == 5){
//                    System.exit(0);
//                }else{
//                    throw new InputException("Invalid input. Please enter a number between 1-5.");
//                }
    }// main()

    private static void displayCredits(GameController game){
        System.out.println(game.credits());
    }
    private static void displayStats(GameController game){
        System.out.println(game.displayStats());
    }

    private static void quit(GameController game){
        displayStats(game);
        displayCredits(game);
        System.out.println("\nThank you for playing Battlehauz! :D");
        System.exit(0);
    }
}
