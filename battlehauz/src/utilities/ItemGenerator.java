package utilities;

import models.items.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ItemGenerator {

    /***
     * Static method that generates items from file input
     * Type of item returned is dependant on the first line of each item attribute description
     * "co" = Consumable Offensive
     * "cd" = Consumable Defensive
     * "ch" = Consumable Healing
     * "po" = Offensive Potion
     * "pd" = Defensive Potion
     * "ph" = Healing Potion
     * @param in Buffered reader of file with item information
     * @return new item of specific type
     * @throws IOException
     */
    public static Item generateItems(BufferedReader in) throws IOException {
        String itemClassification = in.readLine();
        return switch (itemClassification) {
            case "co" -> new ConsumableOffensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
            case "cd" -> new ConsumableDefensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble((in.readLine())));
            case "ch" -> new ConsumableHealingItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
            case "po" -> new OffensivePotion(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
            case "pd" -> new DefensivePotion(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
            case "ph" -> new HealingPotion(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
            default -> null;
        };
    }

}
