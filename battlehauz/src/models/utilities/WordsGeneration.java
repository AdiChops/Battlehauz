package models.utilities;
import java.util.Random;

public class WordsGeneration {
    private static String[] beginQuotes;
    private static String[] winQuotes;
    private static String[] lossQuotes;
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
}
