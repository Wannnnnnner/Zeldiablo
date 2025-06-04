package gameLaby.laby;

import java.util.Random;

/**
 * Classe abstraite représentant un monstre.
 */
public abstract class Monstre implements Entites {
    private final Labyrinthe laby;
    private int x, y, vie;
    private boolean traverse;

    /**
     * Instancie un nouveau monstre.
     *
     * @param x    la position en X
     * @param y    la position en Y
     * @param v    la quantité de vie du monstre
     * @param laby le labyrinthe dans lequel se trouve le monstre
     */
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

    /**
     * Retourne la coordonnée X du monstre.
     *
     * @return la coordonnée X
     */
    public int getX() {
        return x;
    }

    /**
     * Définit la coordonnée X du monstre.
     *
     * @param x la nouvelle coordonnée X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée Y du monstre.
     *
     * @return la coordonnée Y
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée Y du monstre.
     *
     * @param y la nouvelle coordonnée Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Déplace le monstre aléatoirement dans une des directions valides.
     */
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
            laby.grille[this.x][this.y] = false;
            this.x = nextX;
            this.y = nextY;
            laby.grille[this.x][this.y] = this;
        }
    }

    /**
     * Vérifie s’il y a une collision à la position (x, y).
     *
     * @param x la position X à vérifier
     * @param y la position Y à vérifier
     * @return vrai si collision détectée, faux sinon
     */
    Boolean verifColision(int x, int y) {
        // Vérifie les murs
        if (laby.getMur(x, y)) {
            return true;
        }
        // Vérifie la présence du personnage
        if (laby.getPj().getX() == x && laby.getPj().getY() == y) {
            return true;
        }
        // Vérifie la présence d'autres monstres
        for (Monstre m : laby.getMonstresList()) {
            if (m.getX() == x && m.getY() == y && m != this) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le personnage est à la position (x, y).
     *
     * @param x la position X à vérifier
     * @param y la position Y à vérifier
     * @return vrai si le personnage est présent, faux sinon
     */
    Boolean verifPerso(int x, int y) {
        return laby.getPj().getX() == x && laby.getPj().getY() == y;
    }

    /**
     * Méthode abstraite pour attaquer.
     *
     * @return les points de dégâts infligés
     */
    public abstract int attaquer();

    /**
     * Applique des dégâts au monstre.
     *
     * @param pv les points de vie à retirer
     */
    public void recevoirDegats(int pv){
        this.vie -= pv;
    }

    /**
     * Retourne les points de vie restants du monstre.
     *
     * @return les points de vie
     */
    public int getVie(){
        return vie;
    }
}
