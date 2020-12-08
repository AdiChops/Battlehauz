package models.utilities;

import models.Items.ConsumeableDefensiveItem;
import models.Items.ConsumeableHealingItem;
import models.Items.ConsumeableOffensiveItem;
import models.Items.Item;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ItemGenerator {

    public static Item generateItems(BufferedReader in) throws IOException {
        String itemClassification = in.readLine();
        if (itemClassification.equals("co")) {
            return new ConsumeableOffensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
        } else if (itemClassification.equals("cd")){
            return new ConsumeableDefensiveItem(in.readLine(), Integer.parseInt(in.readLine()),
                Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
        } else if (itemClassification.equals("ch")){
            return new ConsumeableHealingItem(in.readLine(), Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
        }else{
            return null;
        }
    }

}
