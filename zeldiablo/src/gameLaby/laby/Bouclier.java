package gameLaby.laby;

/**
 * La classe Bouclier.
 */
public class Bouclier extends Objet {
    private int protection;

    /**
     * Instancie un nouveau Bouclier.
     *
     * @param x          abscisse
     * @param y          ordonnee
     * @param protection niveau de protection
     */
    public Bouclier(int x, int y, int protection) {
        super(x, y);
        this.type = "Bouclier";
        this.protection = protection;
        this.description = "Une bouclier avec " + protection + " de protection quand tu l'as trouvé";
    }

    @Override
    public int getValeur() {
        return protection;
    }

    /**
     * Setter de protection.
     *
     * @param protection la protection
     */
    public void setProtection(int protection) {
        this.protection = protection;
    }

    @Override
    public String toString() {
        return type + " " + protection + "\n Info : " + description;
    }
}