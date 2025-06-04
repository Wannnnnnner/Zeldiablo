package gameLaby.laby;

import java.util.ArrayList;

/**
 * Gère un personnage situé en x,y
 */
public class Perso implements Entites {

    /**
     * Position du personnage
     */
    private int x, y, vie;
    private boolean traverse;
    /**
     * L'inventaire.
     */
    public Inventaire inventaire;

    /**
     * Constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     * @param v  la vie
     */
    public Perso(int dx, int dy, int v) {
        this.x = dx;
        this.y = dy;
        this.vie = v;
        this.traverse = false;
        this.inventaire = new Inventaire();
    }

    /**
     * Définit l'inventaire.
     *
     * @param i l'inventaire
     */
    public void setInventaire(Inventaire i) {
        this.inventaire = i;
    }

    /**
     * Permet de savoir si le personnage est en (x, y)
     *
     * @param dx position testée en x
     * @param dy position testée en y
     * @return true si le personnage est bien en (dx, dy)
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    /**
     * Attaque et retourne la valeur de l'attaque.
     *
     * @return la valeur de l'attaque
     */
    public int attaquer() {
        int baseDegats = 10;
        Epee meilleureArme = getMeilleureArme();
        return meilleureArme != null ? baseDegats + meilleureArme.getValeur() : baseDegats;
    }

    public Epee getMeilleureArme() {
        Epee best = null;
        int maxDegats = -1;
        for (Objet o : inventaire.getObjets()) {
            if (o instanceof Epee sword && sword.getValeur() > maxDegats) {
                best = sword;
                maxDegats = sword.getValeur();
            }
        }
        return best;
    }



    /**
     * Attaque les monstres autour du personnage.
     *
     * @param grille la grille de jeu
     */
    public void attaquerMonstres(Object[][] grille) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int cx = x + i;
                int cy = y + j;
                if (cx > -1 && cy > -1 && cx < grille.length && cy < grille[0].length) {
                    if (grille[cx][cy] instanceof Monstre) {
                        ((Monstre) grille[cx][cy]).recevoirDegats(this.attaquer());
                    }
                }
            }
        }
    }

    /**
     * Vérifie si un monstre est à la position (x, y).
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si c'est la position du personnage
     */
    public boolean verifMonstre(int x, int y) {
        return x == this.x && y == this.y;
    }

    /**
     * Le personnage reçoit des dégâts.
     *
     * @param pv points de vie perdus
     */
    public void recevoirDegats(int pv) {
        if (getMeilleureBouclier() != null) {
            int shield = getMeilleureBouclier().getValeur();
            if (pv > shield) {
                inventaire.supprimerObjet(getMeilleureBouclier());
            } else {
                getMeilleureBouclier().setProtection(shield - pv);
            }
        } else {
            this.vie -= pv;
        }
    }

    /**
     * Obtient le meilleur bouclier de l'inventaire.
     *
     * @return le meilleur bouclier ou null s'il n'y en a pas
     */
    public Bouclier getMeilleureBouclier() {
        Bouclier meilleurBouclier = null;
        int maxTank = -1;
        for (Objet o : inventaire.getObjets()) {
            if (o instanceof Bouclier bouclier && bouclier.getValeur() > maxTank) {
                meilleurBouclier = bouclier;
                maxTank = bouclier.getValeur();
            }
        }
        return meilleurBouclier;
    }

    // ############################################
    // GETTERS / SETTERS
    // ############################################

    /**
     * Obtient la position x du personnage.
     *
     * @return position x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Obtient la position y du personnage.
     *
     * @return position y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Définit la position x du personnage.
     *
     * @param x la position x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Définit la position y du personnage.
     *
     * @param y la position y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtient les points de vie du personnage.
     *
     * @return les points de vie
     */
    public int getVie() {
        return this.vie;
    }

    /**
     * Obtient l'inventaire du personnage.
     *
     * @return l'inventaire
     */
    public Inventaire getInventaire() {
        return inventaire;
    }

    public String toString() {
        String rep = "Position : " + x + " " + y +
                "\nVie : " + vie + "\n" +
                "Inventaire : " + inventaire;
        return rep;
    }

}
