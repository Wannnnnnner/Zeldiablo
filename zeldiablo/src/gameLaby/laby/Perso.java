package gameLaby.laby;


import java.util.ArrayList;

/**
 * gere un personnage situe en x,y
 */
public class Perso implements Entites{

    /**
     * position du personnage
     */
    private int x, y,vie;
    private boolean traverse;
    public Inventaire inventaire;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy,int v) {
        this.x = dx;
        this.y = dy;
        this.vie = v;
        this.traverse = false;
        this.inventaire = new Inventaire();
    }

    public void setInventaire(Inventaire i) {
        this.inventaire = i;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    public int attaquer(){
        return 10;
    }

    public void attaquerMonstres(Object[][] grille){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int cx = x + i;
                int cy = y + j;
                if(cx>-1 && cy+j>-1 && cx< grille.length && cy< grille[0].length) {
                    if (grille[cx][cy] instanceof Monstre) {
                        ((Monstre) grille[cx][cy]).recevoirDegats(this.attaquer());}
                }
            }
        }
    }

    public boolean verifMonstre(int x, int y) {
        return x == this.x && y == this.y;
    }

    public void recevoirDegats(int pv){
        if(getMeilleureBouclier()!=null){
            int shield = getMeilleureBouclier().getValeur();
            if(pv>shield){
                inventaire.supprimerObjet(getMeilleureBouclier());
            }else{
                getMeilleureBouclier().setProtection(shield-pv);
            }
        }else{
            this.vie-=pv;
        }
    }

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
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVie() {
        return this.vie;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public String toString() {
        String rep = "Position : " + x +" " + y +
                "\nVie : " + vie + "\n" +
                "Inventaire : " + inventaire;
        return rep;
    }

}
