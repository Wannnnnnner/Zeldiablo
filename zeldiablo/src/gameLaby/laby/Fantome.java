package gameLaby.laby;

public class Fantome extends Monstre{
    private boolean traverse;
    public Fantome(int x, int y, int v, Labyrinthe laby) {
        super(x, y, v, laby);
        traverse = true;
    }

    public int attaquer(){
        return 5;
    }


}
