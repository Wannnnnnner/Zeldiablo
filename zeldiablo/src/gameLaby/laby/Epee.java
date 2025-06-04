package gameLaby.laby;

/**
 * La classe Epee.
 */
public class Epee extends Objet {
    private int degats;

    /**
     * Istancie une nouvelle Epee.
     *
     * @param x      abscisse
     * @param y      ordonnee
     * @param degats les degats
     */
    public Epee(int x, int y, int degats) {
        super(x, y);
        this.type = "Épée";
        this.degats = degats;
        this.description = "Une épée avec " + degats + " dégâts";
    }

    @Override
    public int getValeur() {
        return degats;
    }

    @Override
    public String toString() {
        return type + " " + degats + "\n Info : " + description;
    }
}
