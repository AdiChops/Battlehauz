package models;

public class Dragon extends Enemy{
    public Dragon(String name, int maxHealth, Move[] moves, int mana) {
        super(name, maxHealth, moves, mana);
    }

    //        public Dragon (String n, int baseHealth, Moves[] moves, int mana) {
//        super(n, baseHealth, moves, mana);
//    }
    @Override
    public void takeDamage(int damage){
        //do stuff
    }

    @Override
    public Move useMove() {
        return null;
    }
}
