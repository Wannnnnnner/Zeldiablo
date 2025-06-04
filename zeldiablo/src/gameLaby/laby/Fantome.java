package gameLaby.laby;

/**
 * La classe Fantome.
 */
public class Fantome extends Monstre{
    private boolean traverse;

    /**
     * Instancie un nouveau Fantome.
     *
     * @param x    abscisse
     * @param y    ordonnee
     * @param v    vie
     * @param laby labyrinthe
     */
    public Fantome(int x, int y, int v, Labyrinthe laby) {
        super(x, y, v, laby);
        traverse = true;
    }

    public int attaquer(){
        return 5;
    }

    /**
     * Getter de Traverse
     *
     * @return le booleen
     */
    public boolean getTraverse(){
        return traverse;
    }


}
