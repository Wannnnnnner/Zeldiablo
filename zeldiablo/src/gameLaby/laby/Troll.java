package gameLaby.laby;

/**
 * La classe Troll.
 */
public class Troll extends Monstre {
    private boolean traverse;

    /**
     * Instancie un nouveau Troll.
     *
     * @param x    la position x
     * @param y    la position y
     * @param v    la vie
     * @param laby le labyrinthe
     */
    public Troll(int x, int y, int v, Labyrinthe laby) {
        super(x, y, v + 10, laby);
    }

    /**
     * Attaque et retourne la valeur de l'attaque.
     *
     * @return la valeur de l'attaque
     */
    public int attaquer() {
        return 20;
    }

    /**
     * Obtient la valeur de traverse.
     *
     * @return true si le troll traverse, false sinon
     */
    public boolean getTraverse() {
        return traverse;
    }

}
