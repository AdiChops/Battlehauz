package models.utilities;

import models.items.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ItemGenerator {

    public static Item generateItems(BufferedReader in) throws IOException {
        String itemClassification = in.readLine();
        if (itemClassification.equals("co")) {
            return new ConsumableOffensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
        } else if (itemClassification.equals("cd")){
            return new ConsumableDefensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Double.parseDouble((in.readLine())));
        } else if (itemClassification.equals("ch")){
            return new ConsumableHealingItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
        }else{
            return null;
        }
    }

}
