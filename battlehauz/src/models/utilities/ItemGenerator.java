package models.utilities;

import models.items.ConsumeableDefensiveItem;
import models.items.ConsumeableHealingItem;
import models.items.ConsumeableOffensiveItem;
import models.items.Item;

import java.io.BufferedReader;
import java.io.IOException;

public class ItemGenerator {

    public static Item generateItems(BufferedReader in) throws IOException {
        String itemClassification = in.readLine();
        if (itemClassification.equals("co")) {
            return new ConsumeableOffensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
        } else if (itemClassification.equals("cd")){
            return new ConsumeableDefensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Double.parseDouble((in.readLine())));
        } else if (itemClassification.equals("ch")){
            return new ConsumeableHealingItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()));
        }else{
            return null;
        }
    }

}
