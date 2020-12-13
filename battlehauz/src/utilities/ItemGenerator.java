package utilities;

import models.items.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ItemGenerator {

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
