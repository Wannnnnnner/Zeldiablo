package gameLaby.laby;

public class Objet implements Entites {
    int x,y;
    String type = "Objet";
    Boolean estPris;
    String description;


    public Objet(int x, int y){
        this.estPris = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean etrePresent(int x, int y) {
        return this.x == x && this.y == y;
    }

    public Boolean getEstPris() {
        return estPris;
    }

    public String getType() {
        return type;
    }

    public void setEstPris() {
        this.estPris = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return type + " " + description;
    }

}
