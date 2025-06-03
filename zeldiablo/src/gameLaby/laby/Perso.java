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

    public void attaquerMonstres(ArrayList<Monstre> monstres){
        for (Monstre m : monstres) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (m.verifPerso(m.getX() + i, m.getY() + j)) {
                        m.recevoirDegats(this.attaquer());
                        System.out.println("Monstre "+m.toString()+" a reçu dégâts : "+m.getVie());
                    }
                }
            }
        }
    }

    public void recevoirDegats(int pv){
        this.vie-=pv;
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
