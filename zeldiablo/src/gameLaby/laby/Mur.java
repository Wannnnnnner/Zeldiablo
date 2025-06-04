package gameLaby.laby;

/**
 * Classe représentant un mur.
 */
public class Mur implements Entites {
    private int x, y;

    @Override
    public boolean etrePresent(int x, int y) {
        return this.x == x && this.y == y;
    }

    /**
     * Instancie un nouveau mur.
     *
     * @param x la position en X
     * @param y la position en Y
     */
    public Mur(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Obtient la position en X.
     *
     * @return la position en X
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la position en Y.
     *
     * @return la position en Y
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la position en X.
     *
     * @param x la position en X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Définit la position en Y.
     *
     * @param y la position en Y
     */
    public void setY(int y) {
        this.y = y;
    }
}
