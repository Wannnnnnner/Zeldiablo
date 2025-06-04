package gameLaby.laby;

/**
 * La classe Objet.
 */
public class Objet implements Entites {
    /**
     * La coordonnée X.
     */
    int x,
    /**
     * La coordonnée Y.
     */
    y;
    /**
     * Le type.
     */
    String type = "Objet";
    /**
     * Indique si l'objet est pris.
     */
    Boolean estPris;
    /**
     * La description.
     */
    String description;

    /**
     * Instancie un nouvel Objet.
     *
     * @param x la coordonnée x
     * @param y la coordonnée y
     */
    public Objet(int x, int y) {
        this.estPris = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean etrePresent(int x, int y) {
        return this.x == x && this.y == y;
    }

    /**
     * Obtient la valeur de estPris.
     *
     * @return estPris
     */
    public Boolean getEstPris() {
        return estPris;
    }

    /**
     * Obtient le type.
     *
     * @return le type
     */
    public String getType() {
        return type;
    }

    /**
     * Définit estPris à vrai.
     */
    public void setEstPris() {
        this.estPris = true;
    }

    /**
     * Obtient la coordonnée x.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée y.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Obtient la valeur (par défaut 0).
     *
     * @return la valeur
     */
    public int getValeur() {
        return 0;
    }

    public String toString() {
        return type + " " + description;
    }
}
