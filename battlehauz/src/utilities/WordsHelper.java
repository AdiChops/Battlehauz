package utilities;

import java.util.Random;
import java.util.Scanner;

public class WordsHelper {
    private static final String[] beginQuotes = {"Our battle will be legendary!",
            "Your journey will have many obstacles that you will overcome." +
                    " I am one obstacle you won't be able to overcome!",
            "You have met your match!",
            "Prepare to face your destiny!"};
    private static final String[] winQuotes = {"We had a great battle! Unfortunate for you, I am unstoppable.",
            "Don't take it personally, I'm just the better opponent.",
            "What did you expect when you chose to pick a fight with me?",
            "Don't worry, you just need a little bit more training!",
            "Did you really think you could defeat me?",
            "It's my lucky day today!",
            "Was that the best you got?",
            "Of course I emerged victorious, what did you think would happen?",
            "You put up a good fight. Unfortunately, it was not good enough."};
    private static final String[] lossQuotes = {"A well-deserved win.", "You have done well, I have met my match.",
            "This is not the end.", "This is not done yet.", "You were a worthy opponent,"
            + " I happily accept my defeat.", "I will be seeing you again very soon."};
    private static final String[] nameAdjectives = {"Horrible", "Pickle", "Scary", "Despicable", "Untouchable", "Creepy", "Feared", "Amazing",
            "Incredible", "Legendary", "The Great", "Terrible", "Wicked", "Crazy"};
    private static final String[] names = {"Adi", "Zara", "Erica", "Dawson", "Preethi", "Elias", "Justina", "Anish", "Max", "Veronica", "Judy", "Rachel",
            "Evan", "Sarah", "Forest", "Amelie", "Rick", "Kelsey", "Jarvis", "Michael", "Dave",
            "Bill", "Jack", "Sally", "Donald", "Jeff", "Ahmed", "Dwight", "Toby", "Ed", "Rob", "Victor", "Justin",
            "Amy", "Pamela", "Bert", "Eric", "Bob", "David", "Haley", "Christina", "Chris", "George", "Ned",
            "Bart", "Lisa", "Claire", "Sophia", "Gloria", "Patrick", "Kevin", "Kaley", "Carl", "Victoria", "William"};
    private static final String[] moveAdjectives = new String[]{"Blinding", "Gut", "Core", "Soul", "Full", "Top", "Rhythmic", "Awful", "Schemed", "Bottom"};
    private static final String[] moveNames = new String[]{"Punch", "Kick", "Taunt", "Roll", "Chop", "Snap", "Cut", "Scald", "Slap", "Burn", "Roast", "Toast", "Wring", "Hit"};
    private static final Random RND = new Random();

    /***
     * generates a quote when the battle begins, when an enemy wins and when an enemy loses
     * @param mode letter to represent which quote to generate
     * @return random generated quote from designated word banks
     */
    public static String generateQuote(char mode) {
        switch (mode) {
            case 'B' -> { // A quote when the enemy appears and the battle begins
                int nextIndex = RND.nextInt(beginQuotes.length);
                return beginQuotes[nextIndex];
            }
            case 'W' -> { // If the enemy won
                int nextIndex = RND.nextInt(winQuotes.length);
                return winQuotes[nextIndex];
            }
            case 'L' -> { // If the enemy lost
                int nextIndex = RND.nextInt(lossQuotes.length);
                return lossQuotes[nextIndex];
            }
            default -> {
                return "Whoa!";
            }
        }
    }

    /***
     * generates a random enemy name
     * @return a random combination of an adjective and the name of enemy
     */
    public static String generateEnemyName() {
        int adjIndex = RND.nextInt(nameAdjectives.length);
        int nameIndex = RND.nextInt(names.length);
        return nameAdjectives[adjIndex] + " " + names[nameIndex];
    }


    /***
     * Method that displays text in a rolling fashion (i.e word by word)
     * Splits text into an array then prints it word by word
     * Different sleep delays occur if different characters are present
     * ".", "!" or "?" Indicate end of sentence, 1s delay
     * "," indicate pause, 0.5s delay
     * ">" indicates dialogue between shopkeeper and player, requires player to press enter to continue
     * Otherwise delay 0.25s
     * @param text String to be displayed in rolling fashion
     */
    public static void rollingTextPrint(String text) {
        text += "\n";
        String[] dialogue = text.split(" ");
        Scanner enterPrompter = new Scanner(System.in);
        for (String s : dialogue) {
            System.out.print(s + " ");
            try {
                if (s.contains(".") || s.contains("!") || s.contains("?")) {
                    Thread.sleep(1000);
                } else if (s.contains(",")) {
                    Thread.sleep(500);
                } else if (s.contains(">") && !s.contains("-->")) {
                    enterPrompter.nextLine();
                } else {
                    Thread.sleep(250);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    /***
     * generates random names for moves
     * @return  @return a random combination of an adjective and the name of move
     */
    public static String generateMoveName() {
        return moveAdjectives[RND.nextInt(moveAdjectives.length)] + moveNames[RND.nextInt(moveNames.length)];
    }

    /***
     * capitalizes the first letter of the character's inputted name
     * @param original the name user inputs before entering game
     * @return the name user inputs with the first letter capitalized
     */
    public static String capitalize(String original) {
        if (Character.isUpperCase(original.charAt(0))) return original;
        return Character.toUpperCase(original.charAt(0)) + original.substring(1);
    }

    /***
     * when you die, there's a 30% chance that shopkeeper generates a dying remark
     * @return shopkeeper and player having a conversation after player dies
     */
    public static String shopkeeperQuote() {
        int chance = RND.nextInt(100);
        if (chance < 30)
            return Colors.BLUE_BOLD + "You: " + Colors.RESET + "Did I win?\n" + Colors.GREEN_BOLD + "Shopkeeper Dave: " + Colors.RESET + "Kinda. You lost...\n";
        else
            return "";
    }
}
