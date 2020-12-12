package models.utilities;
import controllers.InputException;

import java.util.Random;

public class WordsHelper {
    private static String[] beginQuotes;
    private static String[] winQuotes;
    private static String[] lossQuotes;
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
        switch(mode){
            case 'B':{ // A quote when the enemy appears and the battle begins
                int nextIndex = RND.nextInt(beginQuotes.length);
            }
            case 'W':{ // If the enemy won
                int nextIndex = RND.nextInt(winQuotes.length);

            }
            case 'L':{ // If the enemy lost
                int nextIndex = RND.nextInt(lossQuotes.length);

            }
            default:{
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
        String[] dialogue = text.split(" ");
        for (String s: dialogue){
            System.out.print(s + " ");
            try{
                if (s.contains(".") || s.contains("!") || s.contains("?")){
                    Thread.sleep(1000);
                }else if (s.contains(",")){
                    Thread.sleep(500);
                }else{
                    Thread.sleep(250);
                }
            }catch (InterruptedException e){

            }

        }
        System.out.println("");
    }

    public static String generateMoveName(){
        String moveName = moveAdjectives[RND.nextInt(moveAdjectives.length)] + moveNames[RND.nextInt(moveNames.length)];
        return moveName;
    }
}
