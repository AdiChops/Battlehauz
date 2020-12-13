package views;

import controllers.GameController;
import controllers.InputException;
import utilities.Colors;
import utilities.WordsHelper;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattlehauzCLI {
    private static final Scanner INPUT = new Scanner(System.in);
    private static int step = 0;

    public static void displayStartUp() {
        System.out.println(Colors.BLUE_BOLD+"""
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
                 '----------------' '----------------' '----------------' '----------------' '----------------' '----------------'   '----------------' '----------------' '----------------' '----------------'\s""" + Colors.RESET);

    }

    /***
     * Prompts the user for what selection they'd like to perform
     * @return selection char
     */
    private static char proceed() {
        try {
            Scanner proceedScanner = new Scanner(System.in);
            Pattern p = Pattern.compile("[BSFRAQ]");
            System.out.println("\nHow would you like to proceed?");
            System.out.println("""
                    B - Enter BattleHauz
                    S - Enter the Shop
                    F - View Full Stats
                    R - View Gameplay Rules
                    A - About Game
                    Q - Quit Game
                    """);
            System.out.print("> ");
            String choiceS = proceedScanner.nextLine();
            Matcher m = p.matcher(choiceS.toUpperCase());
            if (!m.find()) throw new InputException("");
            return choiceS.toUpperCase().charAt(0);
        } catch (StringIndexOutOfBoundsException | InputException e) {
            switch (step) {
                case 0 -> System.err.println("Oops! please enter a valid choice.");
                case 1 -> System.err.println("""
                        The valid choices are\s
                        B - Enter BattleHauz
                        S - Enter the Shop
                        F - View Full Stats
                        R - View Gameplay Rules
                        A - About Game
                        Q - Quit Game""");
                case 2 -> System.err.println("Ok, cmon buddy I have a business to run, I don't have all day, now tell me what you wanna do!");
                case 3 -> System.err.println("Seriously I'm not playing around just pick a valid choice");
                case 4 -> System.err.println("The devs only programmed me to talk for so long, I'm not sure what'll happen pretty soon!");
                case 5 -> System.err.println("You're coming down to the wire! I think I only see two or three more if statements!");
                case 6 -> System.err.println("JUST PICK A CHOICE");
                case 7 -> System.err.println("PLEASE");
                case 8 -> System.err.println("You know what fine.");
                case 9 -> System.err.println("FINE");
                case 10 -> System.err.println("Is there any reason for you selecting an invalid choice this many times?");
                case 11 -> System.err.println("You don't have anything else better to do than to test the limits of some sleep deprived programmer?");
                case 12 -> System.err.println("Shame.");
                case 13 -> System.err.println("Well while you're here I might as well tell you a story.");
                case 14 -> System.err.println("Let's see here. Ooh, here's a good one, it's about bricks!");
                case 15 -> System.err.println("Man has used brick for building purpose for thousands of years.");
                case 16 -> System.err.println("Bricks date back to 7000 BC, which makes them one of the oldest known building materials.");
                case 17 -> System.err.println("They were discovered in southern Turkey at the site of an ancient settlement around the city of Jericho.");
                case 18 -> System.err.println("The first bricks, made in areas with warm climates, were mud bricks dried in the sun for hardening.");
                case 19 -> System.err.println("...");
                case 20 -> System.err.println("You're still here?");
                case 21 -> System.err.println("Really?");
                case 22 -> {
                    System.err.println("Alright buddy, you've beat the dev and forced my hand, into BattleHauz you go!");
                    return ('B');
                }
            }
            step++;
            return proceed();
        }
    }

    public static void main(String[] args) throws IOException {
        GameController game = new GameController();
        displayStartUp();
        System.out.print("What should we call you? ");
        String name = INPUT.nextLine();
        if (name.isEmpty()) {
            WordsHelper.rollingTextPrint("Ooh hoo hoo, look at the funny man - wants to input a BlaNK nAmE, huh? \n" +
                    "Well now your name is Jimmy John, how do you feel about that?");
            name = "Jimmy John";
            System.out.println();
        }
        name = WordsHelper.capitalize(name);
        System.out.println(game.start(name));
        char choice;
        do {
            game.movesReset();
            choice = proceed();
            step = 0;
            try {
                switch (choice) {
                    case 'B' -> enterBattlehauz(game);
                    case 'S' -> goToShop(game);
                    case 'F' -> displayStats(game);
                    case 'R' -> displayRules(game);
                    case 'A' -> displayCredits(game);
                    case 'Q' -> quit(game);
                    default -> throw new InputException("Oops! Please enter a valid choice.");
                }
            } catch (InputException e) {
                System.err.println(e.getMessage());
            }// end-catch
        } while (choice != 'Q'); // do-while
    }// main()

    private static void enterBattlehauz(GameController game) {
        WordsHelper.rollingTextPrint("Ah, so you have chosen to enter the Battlehauz!" + Colors.BOLD + " Good luck!" + Colors.RESET + " Oh wait, you don't need luck, you need skill." + Colors.BOLD+ " Good skill!" + Colors.RESET);
        WordsHelper.rollingTextPrint(game.displayRules());
        //Game starts and continues while player is alive
        while (game.playerIsAlive()) {
            game.enterBattleFloor(); //generate enemies for floor
            WordsHelper.rollingTextPrint("\nYou have entered floor " + game.getCurrentFloor() + " of the Battlehauz.");
            System.out.println();
            while (game.hasMoreEnemies() && game.playerIsAlive()) { //while the floor still has enemies
                WordsHelper.rollingTextPrint(game.startBattle()); //game.startBattle sets the currentEnemy as the first enemy in the list
                System.out.println();
                WordsHelper.rollingTextPrint(game.enemyTalk('B'));
                System.out.println();
                int initialLevel = game.playerCurrentLevel();
                battle(game);
                WordsHelper.rollingTextPrint(game.displayLevelUp(initialLevel));
                System.out.println();
            } // game.hasMoreEnemies, completing floor
            if (game.playerIsAlive()) game.nextFloor();
        }
        WordsHelper.rollingTextPrint(game.playerLoss());
        game.restorePlayerHealth();
    }

    private static void battle(GameController game) {
        while (game.currentEnemyIsAlive() && game.playerIsAlive()) {
            System.out.println(game.displayCurrentFightersStatus());
            do {
                System.out.println("What would you like to do?");
                System.out.println(game.displayPlayerOptions());
                System.out.print("> ");
                String actionChoiceS = INPUT.next();
                System.out.println();
                try {
                    int actionChoice = Integer.parseInt(actionChoiceS);
                    switch (actionChoice) {
                        case 1 -> performMove(game);
                        case 2 -> useItem(game);
                        default -> throw new InputException("");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Oops! Please enter a valid number.");
                } catch (InputException e) {
                    System.err.println("That action doesn't exist! Pick a valid action index!");
                }
            } while (game.isPlayersTurn());
            if (game.currentEnemyIsAlive()) {
                WordsHelper.rollingTextPrint(Colors.RED + game.doEnemyTurn() + Colors.RESET);
            } else {
                WordsHelper.rollingTextPrint(game.enemyLoss());
                System.out.println();
                WordsHelper.rollingTextPrint(game.displayPlayerRewards());
            }
            System.out.println();
        } //while the currentEnemy and the player is still alive
    }

    private static void performMove(GameController game) {
        System.out.println("Which move would you like to select?");
        System.out.println(game.displayPlayersMoves());
        System.out.print("> ");
        String moveChoiceS = INPUT.next();
        try {
            int moveChoice = Integer.parseInt(moveChoiceS);
            if (moveChoice < 0 || moveChoice > game.getSizeOfDisplayedMenu(4))
                throw new InputException("That move doesn't exist! Pick a valid move index!");
            System.out.println(Colors.BLUE + game.doPlayerTurn(moveChoice) + Colors.RESET + "\n");
            System.out.println(game.displayEnemyStatus() + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.err.println("Oops! Please enter a valid move index.");
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void useItem(GameController game) {
        System.out.println(game.displayPlayerInventory());
        if (!game.displayPlayerInventory().equals("You have no items you can use!")) {
            try {
                System.out.print("Which item would you like to use > ");
                String itemChoiceS = INPUT.next();
                int itemChoice = Integer.parseInt(itemChoiceS);
                if (itemChoice < 0 || itemChoice > game.getSizeOfDisplayedMenu(5))
                    throw new InputException("That item doesn't exist! Pick a valid item index!");
                System.out.println(game.playerUseItem(itemChoice) + "\n");
            } catch (NumberFormatException e) {
                System.err.println("Oops! Please enter a valid number.");
            } catch (InputException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void goToShop(GameController game) {
        WordsHelper.rollingTextPrint(game.enterShop());
        System.out.println();
        int shopChoice = 0;
        do {
            System.out.println(game.displayShopOptions() + "\n"); //displays the options to the user
            System.out.print("Choose an option > ");
            String shopChoiceS = INPUT.next();
            System.out.println();
            try {
                shopChoice = Integer.parseInt(shopChoiceS);
                if (shopChoice <= 0 || shopChoice > 7) throw new InputException("");
                //there's six options on the shop menu, with the 6th being return to main menu
                switch (shopChoice) {
                    case 1 -> { //Buying a move
                        String moveChoiceS;
                        do {
                            System.out.println(game.displayMovesInShop()); //displays the generated moves
                            System.out.println("Q: Return to shop menu");
                            System.out.println("Input a move number above to purchase. Write 'Q' to quit. >");
                            moveChoiceS = INPUT.next();
                            if (!moveChoiceS.toLowerCase().equals("q")) {
                                try {
                                    int moveChoice = Integer.parseInt(moveChoiceS);
                                    if (moveChoice <= 0 || moveChoice > game.getSizeOfDisplayedMenu(1))
                                        throw new InputException("");
                                    WordsHelper.rollingTextPrint(game.buyMove(moveChoice));
                                    System.out.println();
                                    //Tries to buys the move. Returns a fail/success String.
                                } catch (NumberFormatException e) {
                                    System.err.println("Oops! Please enter a valid number.");
                                } catch (InputException e) {
                                    System.err.println("That move doesn't exist! Pick a valid menu index!");
                                }
                            }
                        } while (!moveChoiceS.equalsIgnoreCase("q"));
                    }
                    case 2 -> { //Buying a consumable item
                        String consumableChoiceS;
                        do {
                            System.out.println(game.displayConsumableItemsInShop()); //displays the consumable items
                            System.out.println("Q: Return to shop menu");
                            System.out.println("Input an item number above to purchase. Write 'Q' to quit. >");
                            consumableChoiceS = INPUT.next();
                            if (!consumableChoiceS.toLowerCase().equals("q")) {
                                try {
                                    int consumableChoice = Integer.parseInt(consumableChoiceS);
                                    if (consumableChoice <= 0 || consumableChoice > game.getSizeOfDisplayedMenu(2))
                                        throw new InputException("");
                                    WordsHelper.rollingTextPrint(game.buyConsumableItem(consumableChoice));
                                    System.out.println();
                                    //Tries to buys the item. Returns a fail/success String.
                                } catch (NumberFormatException e) {
                                    System.err.println("Oops! Please enter a valid number.");
                                } catch (InputException e) {
                                    System.err.println("That consumable item doesn't exist! Pick a valid menu index!");
                                }
                            }
                        } while (!consumableChoiceS.equalsIgnoreCase("q"));
                    }
                    case 3 -> { //buy a potion boost
                        if (game.boostHasNotBeenPurchasedCheck()) {
                            //first checks if a potion has been purchased and NOT used in battle yet
                            //if not, proceed
                            String potionChoiceS;
                            do {
                                System.out.println(game.displayPotionBoostsInShop()); //displays all of the potion boosts
                                System.out.println("Q: Return to shop menu");
                                System.out.println("Input a potion boost number above to purchase. Write 'Q' to quit. >");
                                potionChoiceS = INPUT.next();
                                if (!potionChoiceS.toLowerCase().equals("q")) {
                                    try {
                                        int potionChoice = Integer.parseInt(potionChoiceS);
                                        if (potionChoice <= 0 || potionChoice > game.getSizeOfDisplayedMenu(3))
                                            throw new InputException("");
                                        WordsHelper.rollingTextPrint(game.buyPotionBoost(potionChoice));
                                        System.out.println();
                                        //Tries to buys the potion boost. Returns a fail/success String.
                                    } catch (NumberFormatException e) {
                                        System.err.println("Oops! Please enter a valid number.");
                                    } catch (InputException e) {
                                        System.err.println("That potion boost doesn't exist! Pick a valid menu index!");
                                    }

                                }
                            } while (!potionChoiceS.equalsIgnoreCase("q") && game.boostHasNotBeenPurchasedCheck());
                        } else {
                            System.out.println("You've already purchased a boost. It will be applied to your next run at the Battlehauz. \n" +
                                    "Until you've used your boost, you can not purchase another one.");
                            //is shown if the user has purchased a boost they have not used.
                        }
                    }
                    case 4 -> { //player is trying to sell a move to the shop
                        String moveSellChoiceS;
                        do {
                            System.out.println(game.displayPlayersMovesForShop());
                            //displays all of the moves the player has, including the base moves, with the appropriate stats.
                            System.out.println("Q: Return to shop menu");
                            System.out.println("Input a move number above to sell to the shop. Write 'Q' to quit. >");
                            moveSellChoiceS = INPUT.next();
                            if (!moveSellChoiceS.toLowerCase().equals("q")) {
                                try {
                                    int moveSellChoice = Integer.parseInt(moveSellChoiceS);
                                    if (moveSellChoice <= 0 || moveSellChoice > game.getSizeOfDisplayedMenu(4))
                                        throw new InputException("");
                                    WordsHelper.rollingTextPrint(game.sellMoveToShop(moveSellChoice));
                                    System.out.println();
                                    //Tries to sell the move. Returns a fail/success String.
                                } catch (NumberFormatException e) {
                                    System.err.println("Oops! Please enter a valid number.");
                                } catch (InputException e) {
                                    System.err.println("That move doesn't exist! Pick a valid menu index!");
                                }
                            }
                        } while (!moveSellChoiceS.equals("q"));
                    }
                    case 5 -> { //player is trying to sell a consumable item
                        String itemSellChoiceS;
                        do {
                            System.out.println(game.displayPlayerInventory());
                            //displays the consumable items the player has, with the appropriate stats and quantity.
                            System.out.println("Q: Return to shop menu");
                            System.out.println("Input an item number above to sell to the shop. Write 'Q' to quit. >");
                            itemSellChoiceS = INPUT.next();
                            if (!itemSellChoiceS.toLowerCase().equals("q")) {
                                try {
                                    int itemSellChoice = Integer.parseInt(itemSellChoiceS);
                                    if (itemSellChoice <= 0 || itemSellChoice > game.getSizeOfDisplayedMenu(5))
                                        throw new InputException("");
                                    WordsHelper.rollingTextPrint(game.sellItemToShop(itemSellChoice));
                                    System.out.println();
                                    //Tries to sell the consumable item. Returns a fail/success String.
                                } catch (NumberFormatException e) {
                                    System.err.println("Oops! Please enter a valid number.");
                                } catch (InputException e) {
                                    System.err.println("That item doesn't exist! Pick a valid menu index!");
                                }
                            }
                        } while (!itemSellChoiceS.equals("q"));
                    }
                    case 6 -> {
                        WordsHelper.rollingTextPrint(game.displayShopkeeperConversation());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Oops! Please enter a valid number.");
            } catch (InputException e) {
                System.err.println("That menu item doesn't exist! Pick a menu index!");
            }
        } while (shopChoice != 7);
    }

    private static void displayCredits(GameController game) {
        System.out.println(game.credits());
    }

    private static void displayStats(GameController game) {
        System.out.println(game.displayStats());
    }

    private static void displayRules(GameController game) {
        System.out.println(game.displayGameplayRules());
    }

    private static void quit(GameController game) {
        displayStats(game);
        displayCredits(game);
        System.out.println(Colors.BOLD+"\nThank you for playing Battlehauz! :D"+Colors.RESET);
        System.exit(0);
    }
}
