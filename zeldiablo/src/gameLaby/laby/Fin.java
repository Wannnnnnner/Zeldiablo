package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Fin implements Entites{

    /**
     * position du personnage
     */
    private int x, y;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Fin(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * Gets x.
     *
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     * Setter de x.
     *
     * @param x l'abscisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de y.
     *
     * @param y l'ordonnee
     */
    public void setY(int y) {
        this.y = y;
    }
}
