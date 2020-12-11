package views;

import controllers.GameController;
import controllers.InputException;
import models.items.ConsumeableOffensiveItem;
import models.utilities.WordsHelper;

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
            Scanner proceedScanner = new Scanner(System.in);
            System.out.println("\nHow would you like to proceed?");
            System.out.println("B - Enter BattleHauz\n" +
                    "S - Enter the shop\n" +
                    "F - View Full Stats\n" +
                    "A - About Game\n" +
                    "Q - Quit game\n");
            System.out.print("> ");
            String choiceS = proceedScanner.nextLine();
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
        char choice = ' ';
        do{
            choice = proceed();
            try {
                switch(choice){
                    case 'B':{
                        game.getGamePlayer().addItem(new ConsumeableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        game.getGamePlayer().addItem(new ConsumeableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        game.getGamePlayer().addItem(new ConsumeableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        WordsHelper.rollingTextPrint("Ah, so you have chosen to enter the Battlehauz! Good luck! Oh wait, you don't need luck, you need skill. Good skill!");
                        System.out.println(game.displayRules());
                        //Game starts and continues while player is alive
                        while(game.playerIsAlive()){
                            game.enterBattleFloor(); //generate enemies for floor
                            WordsHelper.rollingTextPrint("\nYou have entered floor " + game.getCurrentFloor() + " of the Battlehauz.");
                            while(game.hasMoreEnemies() && game.playerIsAlive()){ //while the floor still has enemies
                                WordsHelper.rollingTextPrint(game.startBattle()); //game.startBattle sets the currentEnemy as the first enemy in the list
                                while(game.currentEnemyIsAlive() && game.playerIsAlive()){ //while the currentEnemy is still alive
                                    System.out.println(game.displayerCurrentFightersStatus() + "\n");
                                    do{
                                        System.out.println(game.displayPlayerOptions());
                                        System.out.print("What would you like to do > ");
                                        String actionChoiceS = INPUT.next();
                                        System.out.println();
                                        try{
                                            int actionChoice = Integer.parseInt(actionChoiceS);
                                            if (actionChoice < 0 || actionChoice > 2) throw new InputException("");
                                            switch (actionChoice){
                                                case 1:
                                                    System.out.println(game.displayPlayerMoves());
                                                    System.out.print("Which move would you like to select");
                                                    String moveChoiceS = INPUT.next();
                                                    try{
                                                        int moveChoice = Integer.parseInt(moveChoiceS);
                                                        if (moveChoice < 0 || moveChoice > game.getGamePlayer().getMoves().size()) throw new InputException("That move doesn't exist! Pick a valid move index!");
                                                        System.out.println(game.doPlayerTurn(moveChoice));
                                                        System.out.println(game.displayEnemyStatus());
                                                    } catch(NumberFormatException e){
                                                        System.err.println("Oops! Please enter a valid number.");
                                                    } catch (InputException e) {
                                                        System.err.println(e.getMessage());
                                                    }
                                                    break;
                                                case 2:
                                                    //Adding some dummy items will remove later
                                                    System.out.println(game.displayPlayerInventory());
                                                    if (!game.displayPlayerInventory().equals("You have no items you can use!")){
                                                        System.out.print("Which item would you like to use > ");
                                                        String itemChoiceS = INPUT.next();
                                                        try{
                                                            int itemChoice = Integer.parseInt(itemChoiceS);
                                                            if (itemChoice < 0 || itemChoice > game.getGamePlayer().getOwnedItemNames().size()) throw new InputException("That item doesn't exist! Pick a valid item index!");
                                                            System.out.println(game.playerUseItem(itemChoice));
                                                        }catch(NumberFormatException e){
                                                            System.err.println("Oops! Please enter a valid number.");
                                                        } catch (InputException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                            }
                                        }catch(NumberFormatException e){
                                            System.err.println("Oops! Please enter a valid number.");
                                        } catch (InputException e){
                                            System.err.println("That action doesn't exist! Pick a valid action index!");
                                        }
                                    }while (game.isPlayersTurn());
                                    if (game.currentEnemyIsAlive()){
                                        System.out.println(game.doEnemyTurn());
                                        System.out.println();
                                    }
                                }
                            } // game.hasMoreEnemies, completing floor
                            game.nextFloor();
                        }
                        game.restorePlayerHealth();
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
