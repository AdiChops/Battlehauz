package views;

import controllers.GameController;
import controllers.InputException;
import models.items.ConsumableOffensiveItem;
import models.utilities.WordsHelper;

import java.io.IOException;
import java.util.Scanner;

public class BattlehauzCLI {
    private static final Scanner INPUT = new Scanner(System.in);
    public static void displayStartUp(){
        System.out.println("""
                Welcome to
                 .----------------. .----------------. .----------------. .----------------. .----------------. .----------------.   .----------------. .----------------. .----------------. .----------------.\s
                | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | | .--------------. | .--------------. | .--------------. | .--------------. |
                | |   ______     | | |      __      | | |  _________   | | |  _________   | | |   _____      | | |  _________   | | | |  ____  ____  | | |      __      | | | _____  _____ | | |   ________   | |
                | |  |_   _ \\    | | |     /  \\     | | | |  _   _  |  | | | |  _   _  |  | | |  |_   _|     | | | |_   ___  |  | | | | |_   ||   _| | | |     /  \\     | | ||_   _||_   _|| | |  |  __   _|  | |
                | |    | |_) |   | | |    / /\\ \\    | | | |_/ | | \\_|  | | | |_/ | | \\_|  | | |    | |       | | |   | |_  \\_|  | | | |   | |__| |   | | |    / /\\ \\    | | |  | |    | |  | | |  |_/  / /    | |
                | |    |  __'.   | | |   / ____ \\   | | |     | |      | | |     | |      | | |    | |   _   | | |   |  _|  _   | | | |   |  __  |   | | |   / ____ \\   | | |  | '    ' |  | | |     .'.' _   | |
                | |   _| |__) |  | | | _/ /    \\ \\_ | | |    _| |_     | | |    _| |_     | | |   _| |__/ |  | | |  _| |___/ |  | | | |  _| |  | |_  | | | _/ /    \\ \\_ | | |   \\ `--' /   | | |   _/ /__/ |  | |
                | |  |_______/   | | ||____|  |____|| | |   |_____|    | | |   |_____|    | | |  |________|  | | | |_________|  | | | | |____||____| | | ||____|  |____|| | |    `.__.'    | | |  |________|  | |
                | |              | | |              | | |              | | |              | | |              | | |              | | | |              | | |              | | |              | | |              | |
                | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | | '--------------' | '--------------' | '--------------' | '--------------' |
                 '----------------' '----------------' '----------------' '----------------' '----------------' '----------------'   '----------------' '----------------' '----------------' '----------------'\s""");

    }
    private static char proceed(){
        try {
            Scanner proceedScanner = new Scanner(System.in);
            System.out.println("\nHow would you like to proceed?");
            System.out.println("""
                    B - Enter BattleHauz
                    S - Enter the shop
                    F - View Full Stats
                    A - About Game
                    Q - Quit game
                    """);
            System.out.print("> ");
            String choiceS = proceedScanner.nextLine();
            return choiceS.toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e){
            System.err.println("Oops! Please enter a choice.");
            return proceed();
        }
    }

    public static void main(String [] args) throws IOException {
        GameController game = new GameController();
        displayStartUp();
        System.out.print("What should we call you? ");
        String name = INPUT.nextLine();
        System.out.println(game.start(name));
        char choice;
        do{
            choice = proceed();
            try {
                switch (choice) {
                    case 'B' -> {
                        game.getGamePlayer().addItem(new ConsumableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        game.getGamePlayer().addItem(new ConsumableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        game.getGamePlayer().addItem(new ConsumableOffensiveItem("Jimmy John's hot stick", 7, 7, 0.07));
                        WordsHelper.rollingTextPrint("Ah, so you have chosen to enter the Battlehauz! Good luck! Oh wait, you don't need luck, you need skill. Good skill!");
                        System.out.println(game.displayRules());
                        //Game starts and continues while player is alive
                        while (game.playerIsAlive()) {
                            game.enterBattleFloor(); //generate enemies for floor
                            WordsHelper.rollingTextPrint("\nYou have entered floor " + game.getCurrentFloor() + " of the Battlehauz.");
                            while (game.hasMoreEnemies() && game.playerIsAlive()) { //while the floor still has enemies
                                WordsHelper.rollingTextPrint(game.startBattle()); //game.startBattle sets the currentEnemy as the first enemy in the list
                                System.out.println();
                                WordsHelper.rollingTextPrint(game.enemyTalk('B'));
                                while (game.currentEnemyIsAlive() && game.playerIsAlive()) { //while the currentEnemy is still alive
                                    System.out.println(game.displayerCurrentFightersStatus() + "\n");
                                    do {
                                        System.out.println("What would you like to do?");
                                        System.out.println(game.displayPlayerOptions()+"\n");
                                        System.out.print("> ");
                                        String actionChoiceS = INPUT.next();
                                        System.out.println();
                                        try {
                                            int actionChoice = Integer.parseInt(actionChoiceS);
                                            if (actionChoice <= 0 || actionChoice > 2) throw new InputException("");
                                            //Adding some dummy items will remove later
                                            switch (actionChoice) {
                                                case 1 -> {
                                                    System.out.println("Which move would you like to select?");
                                                    System.out.println(game.displayPlayerMoves());
                                                    System.out.print("> ");
                                                    String moveChoiceS = INPUT.next();
                                                    try {
                                                        int moveChoice = Integer.parseInt(moveChoiceS);
                                                        if (moveChoice < 0 || moveChoice > game.getGamePlayer().getMoves().size())
                                                            throw new InputException("That move doesn't exist! Pick a valid move index!");
                                                        System.out.println(game.doPlayerTurn(moveChoice));
                                                        System.out.println(game.displayEnemyStatus());
                                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                        System.err.println("Oops! Please enter a valid move index.");
                                                    } catch (InputException e) {
                                                        System.err.println(e.getMessage());
                                                    }
                                                }
                                                case 2 -> {
                                                    System.out.println(game.displayPlayerInventory());
                                                    if (!game.displayPlayerInventory().equals("You have no items you can use!")) {
                                                        try {
                                                            System.out.print("Which item would you like to use > ");
                                                            String itemChoiceS = INPUT.next();
                                                            int itemChoice = Integer.parseInt(itemChoiceS);
                                                            if (itemChoice < 0 || itemChoice > game.getGamePlayer().getOwnedItemNames().size())
                                                                throw new InputException("That item doesn't exist! Pick a valid item index!");
                                                            System.out.println(game.playerUseItem(itemChoice));
                                                        } catch (NumberFormatException e) {
                                                            System.err.println("Oops! Please enter a valid number.");
                                                        } catch (InputException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (NumberFormatException e) {
                                            System.err.println("Oops! Please enter a valid number.");
                                        } catch (InputException e) {
                                            System.err.println("That action doesn't exist! Pick a valid action index!");
                                        }
                                    } while (game.isPlayersTurn());
                                    if (game.currentEnemyIsAlive()) {
                                        WordsHelper.rollingTextPrint(game.doEnemyTurn());
                                        System.out.println();
                                    }
                                    else{
                                        WordsHelper.rollingTextPrint(game.enemyLoss());
                                    }
                                }
                            } // game.hasMoreEnemies, completing floor
                            if (game.playerIsAlive()) game.nextFloor();
                        }
                        game.restorePlayerHealth();
                    }
                    case 'S' -> goToShop(game);
                    case 'F' -> displayStats(game);
                    case 'A' -> displayCredits(game);
                    case 'Q' -> quit(game);
                    default -> throw new InputException("Oops! Please enter a valid choice.");
                }
            }
            catch (InputException e){
                System.err.println(e.getMessage());
            }// end-catch
        }while (choice != 'Q'); // do-while
    }// main()

    private static void goToShop(GameController game){
        WordsHelper.rollingTextPrint("Your character enters the shop. Here, they see the shopkeeper: Dave.");
        WordsHelper.rollingTextPrint(game.enterShop()); //TODO: function needs to be completed
        //TODO: when user decides to go back using 'Q', should loop back here
        System.out.println(game.displayShopOptions() + "\n"); //displays the options to the user
        System.out.print("Choose an option > ");
        String shopChoiceS = INPUT.next();
        System.out.println();
        try {
            int shopChoice = Integer.parseInt(shopChoiceS);
            if (shopChoice <= 0 || shopChoice > 6) throw new InputException("");
            switch (shopChoice) {
                case 1 -> { //Buying a move
                    //TODO:Loop this until user quits
                    System.out.println(game.displayMovesInShop()); //displays the generated moves
                    System.out.println("Q: Return to shop menu");
                    System.out.println("Input a move number above to purchase. Write 'Q' to quit. >");
                    String choice = INPUT.next();
                    if (choice.toLowerCase().equals("q")) {
                        //TODO:quit and return to main menu
                    } else {
                        try {
                            shopChoice = Integer.parseInt(shopChoiceS);
                            if (shopChoice <= 0 || shopChoice > game.getSizeForIndexMatching(1)) throw new InputException("");
                            WordsHelper.rollingTextPrint(game.buyMove(shopChoice));
                            //Tries to buys the move. Returns a fail/success String.
                        } catch (NumberFormatException e) {
                            System.err.println("Oops! Please enter a valid number.");
                        } catch (InputException e) {
                            System.err.println("That move doesn't exist! Pick a valid menu index!"); } } }
                case 2-> { //Buying a consumable item
                    //TODO:Loop this until user quits
                    System.out.println(game.displayConsumableItemsInShop()); //displays the consumable items
                    System.out.println("Q: Return to shop menu");
                    System.out.println("Input an item number above to purchase. Write 'Q' to quit. >");
                    String choice = INPUT.next();
                    if (choice.toLowerCase().equals("q")) {
                        //TODO:quit and return to main menu
                    } else {
                        try {
                            shopChoice = Integer.parseInt(shopChoiceS);
                            if (shopChoice <= 0 || shopChoice > game.getSizeForIndexMatching(2)) throw new InputException("");
                            WordsHelper.rollingTextPrint(game.buyConsumableItem(shopChoice));
                            //Tries to buys the item. Returns a fail/success String.
                        } catch (NumberFormatException e) {
                            System.err.println("Oops! Please enter a valid number.");
                        } catch (InputException e) {
                            System.err.println("That consumable item doesn't exist! Pick a valid menu index!"); } } }
                case 3-> { //buy a potion boost
                    //TODO:Loop this until user quits
                    if(game.boostHasNotBeenPurchasedCheck()){
                        //first checks if a potion has been purchased and NOT used in battle yet
                        //if not, proceed
                        System.out.println(game.displayPotionBoostsInShop()); //displays all of the potion boosts
                        System.out.println("Q: Return to shop menu");
                        System.out.println("Input a potion boost number above to purchase. Write 'Q' to quit. >");
                        String choice = INPUT.next();
                        if (choice.toLowerCase().equals("q")) {
                            //TODO:quit and return to main menu
                        } else {
                            try {
                                shopChoice = Integer.parseInt(shopChoiceS);
                                if (shopChoice <= 0 || shopChoice > game.getSizeForIndexMatching(3)) throw new InputException("");
                                WordsHelper.rollingTextPrint(game.buyPotionBoost(shopChoice));
                                //Tries to buys the potion boost. Returns a fail/success String.
                            } catch (NumberFormatException e) {
                                System.err.println("Oops! Please enter a valid number.");
                            } catch (InputException e) {
                                System.err.println("That potion boost doesn't exist! Pick a valid menu index!"); } }
                    }
                    else{
                        System.out.println("You've already purchased a boost. It will be applied to your next run at the Battlehauz. \n+" +
                                "Until you've used your boost, you can not purchase another one.");
                        //is shown if the user has purchased a boost they have not used.
                        //TODO:return to main menu
                    } }
                case 4-> { //player is trying to sell a move to the shop
                    //TODO:Loop this until user quits
                    System.out.println(game.displayPlayersMoveInventory());
                    //displays all of the moves the player has, including the base moves, with the appropriate stats.
                    System.out.println("Q: Return to shop menu");
                    System.out.println("Input a move number above to sell to the shop. Write 'Q' to quit. >");
                    String choice = INPUT.next();
                    if (choice.toLowerCase().equals("q")) {
                        //TODO:quit and return to main menu
                    } else {
                        try {
                            shopChoice = Integer.parseInt(shopChoiceS);
                            if (shopChoice <= 0 || shopChoice > game.getSizeForIndexMatching(4)) throw new InputException("");
                            WordsHelper.rollingTextPrint( game.sellMoveToShop(shopChoice));
                            //Tries to sell the move. Returns a fail/success String.
                        } catch (NumberFormatException e) {
                            System.err.println("Oops! Please enter a valid number.");
                        } catch (InputException e) {
                            System.err.println("That move doesn't exist! Pick a valid menu index!"); } } }
                case 5-> { //player is tring to sell a consumable item
                    //TODO:Loop this until user quits
                    System.out.println(game.displayPlayersItemInventory());
                    //displays the consumable items the player has, with the appropriate stats and quantity.
                    System.out.println("Q: Return to shop menu");
                    System.out.println("Input an item number above to sell to the shop. Write 'Q' to quit. >");
                    String choice = INPUT.next();
                    if (choice.toLowerCase().equals("q")) {
                        //TODO:quit and return to main menu
                    } else {
                        try {
                            shopChoice = Integer.parseInt(shopChoiceS);
                            if (shopChoice <= 0 || shopChoice > game.getSizeForIndexMatching(5)) throw new InputException("");
                            WordsHelper.rollingTextPrint(game.sellItemToShop(shopChoice));
                            //Tries to sell the consumable item. Returns a fail/success String.
                        } catch (NumberFormatException e) {
                            System.err.println("Oops! Please enter a valid number.");
                        } catch (InputException e) {
                            System.err.println("That item doesn't exist! Pick a valid menu index!"); } } }
            }
        }catch (NumberFormatException e) {
            System.err.println("Oops! Please enter a valid number.");
        }catch (InputException e) {
            System.err.println("That menu item doesn't exist! Pick a menu index!");
        }
    }

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
