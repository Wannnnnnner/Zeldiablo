package gameLaby.laby;

import java.util.Random;

public abstract class Monstre implements Entites {
    private final Labyrinthe laby;
    private int x, y, vie;
    private boolean traverse;

    public Monstre(int x, int y, int v, Labyrinthe laby) {
        this.x = x;
        this.y = y;
        this.vie = v;
        this.laby = laby;
        this.traverse = false;
    }

    @Override
    public boolean etrePresent(int x, int y) {
        return (this.x == x && this.y == y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void deplacerRandom() {
        Random randX = new Random();
        Random randY = new Random();
        int newX = randX.nextInt(5);
        int newY = randY.nextInt(5);
        int nextX = 1;
        int nextY = 1;

        switch (newX) {
            case 0:
                nextX = this.x - 1;
                break;
            case 1, 2, 3:
                nextX = this.x;
                break;
            case 4:
                nextX = this.x + 1;
                break;
        }

        switch (newY) {
            case 0:
                nextY = this.y - 1;
                break;
            case 1, 2, 3:
                nextY = this.y;
                break;
            case 4:
                nextY = this.y + 1;
                break;
        }

        if (!verifColision(nextX, nextY)) {
            this.x = nextX;
            this.y = nextY;
        }
    }

    ;

    Boolean verifColision(int x, int y) {
        //verif mur
        if (laby.getMur(x, y)) {
            return true;
        }
        //verif perso
        if (laby.getPj().getX() == x && laby.getPj().getY() == y) {
            return true;
        }
        //verif monstre
        for (Monstre m : laby.getMonstresList()) {
            if (m.getX() == x && m.getY() == y && m != this) {
                return true;
            }
        }
        return false;
    }

    Boolean verifPerso(int x, int y) {
        return laby.getPj().getX() == x && laby.getPj().getY() == y;
    }

    public abstract int attaquer();

    public void recevoirDegats(int pv){
        this.vie-=pv;
    }

    public int getVie(){
        return vie;
    }
}
