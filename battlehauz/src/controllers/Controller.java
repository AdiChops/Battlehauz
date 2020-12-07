package controllers;

import java.io.IOException;

public class Controller{

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        views.BattlehauzCLI.displayStartUp();
        game.start();
    }


}
