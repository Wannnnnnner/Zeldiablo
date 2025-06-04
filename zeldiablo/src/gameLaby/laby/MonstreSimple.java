package gameLaby.laby;

/**
 * Classe représentant un monstre simple.
 */
public class MonstreSimple extends Monstre {
    private boolean traverse;

    /**
     * Instancie un nouveau monstre simple.
     *
     * @param x    la position en X
     * @param y    la position en Y
     * @param v    les points de vie du monstre
     * @param laby le labyrinthe dans lequel se trouve le monstre
     */
    public MonstreSimple(int x, int y, int v, Labyrinthe laby) {
        super(x, y, v, laby);
        traverse = false;
    }

    @Override
    public int attaquer() {
        return 1; // Le monstre simple inflige 1 point de dégât
    }

    /**
     * Retourne l'état de la variable "traverse".
     *
     * @return vrai si le monstre peut traverser, sinon faux
     */
    public boolean getTraverse() {
        return traverse;
    }
}
