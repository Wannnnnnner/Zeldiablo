package gameLaby.laby;

public class Epee extends Objet {
    private int degats;

    public Epee(int x, int y, int degats) {
        super(x, y);
        this.type = "Épée";
        this.degats = degats;
        this.description = "Une épée avec " + degats + " dégâts";
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public String toString() {
        return type + " " + degats + "\n Info : " + description;
    }
}
