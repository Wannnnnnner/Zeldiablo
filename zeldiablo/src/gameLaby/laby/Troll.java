package gameLaby.laby;

public class Troll extends Monstre{
    private boolean traverse ;
    public Troll(int x, int y, int v,Labyrinthe laby) {
        super(x, y, v+10,laby);
    }

    public int attaquer(){
        return 20;
    }
}
