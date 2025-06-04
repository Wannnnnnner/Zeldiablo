package gameLaby.laby;

public class MonstreSimple extends Monstre {
    private boolean traverse;
    public MonstreSimple(int x, int y, int v, Labyrinthe laby) {
        super(x, y, v, laby);
        traverse = false;
    }

    @Override
    public int attaquer() {
        return 1;
    }

    public boolean getTraverse(){
        return traverse;
    }

}
