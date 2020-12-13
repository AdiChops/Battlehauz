package models.utilities;

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
    private static final String [] moveAdjectives = new String[]{"Blinding", "Gut", "Core", "Soul", "Full", "Top", "Rhythmic", "Awful", "Schemed", "Bottom"};
    private static final String [] moveNames = new String[]{"Punch", "Kick", "Taunt", "Roll", "Chop", "Snap", "Cut", "Scald", "Slap", "Burn", "Roast", "Toast", "Wring", "Hit"};
    private static final Random RND = new Random();


    public static String generateQuote(char mode){
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

    public static String generateEnemyName(){
        int adjIndex = RND.nextInt(nameAdjectives.length);
        int nameIndex = RND.nextInt(names.length);
        return nameAdjectives[adjIndex] + " " + names[nameIndex];
    }

    public static void rollingTextPrint(String text)  {
        text += "\n";
        String[] dialogue = text.split(" ");
        Scanner enterPrompter = new Scanner(System.in);
        for (String s: dialogue){
            System.out.print(s + " ");
            try{
                if (s.contains(".") || s.contains("!") || s.contains("?")){
                    Thread.sleep(1000);
                }else if (s.contains(",")){
                    Thread.sleep(500);
                }else if (s.contains(">") && !s.contains("-->")){
                    enterPrompter.nextLine();
                }else{
                    Thread.sleep(250);
                }
            }catch (InterruptedException ignored){
            }
        }
    }

    public static String generateMoveName(){
        return moveAdjectives[RND.nextInt(moveAdjectives.length)] + moveNames[RND.nextInt(moveNames.length)];
    }

    public static String capitalize(String original){
        if (Character.isUpperCase(original.charAt(0))) return original;
        return Character.toUpperCase(original.charAt(0)) + original.substring(1);
    }

    public static String shopkeeperQuote(){
        int chance = RND.nextInt(100);
        if(chance < 30)
            return Colors.BLUE_BOLD + "You: " + Colors.RESET + "Did I win?\n"+ Colors.GREEN_BOLD + "Shopkeeper Dave: " + Colors.RESET + "Kinda. You lost...\n";
        else
            return "";
    }
}
