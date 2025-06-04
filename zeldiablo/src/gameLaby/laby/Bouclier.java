package gameLaby.laby;

public class Bouclier extends Objet {
    private int protection;

    public Bouclier(int x, int y, int protection) {
        super(x, y);
        this.type = "Bouclier";
        this.protection = protection;
        this.description = "Une bouclier avec " + protection + " de protection quand tu la trouvé";
    }

    @Override
    public int getValeur() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    @Override
    public String toString() {
        return type + " " + protection + "\n Info : " + description;
    }
}