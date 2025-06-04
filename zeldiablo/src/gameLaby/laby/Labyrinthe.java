package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe Labyrinthe. Représente un labyrinthe avec :
 * <ul>
 *     <li>des murs</li>
 *     <li>un personnage (x,y)</li>
 * </ul>
 */
public class Labyrinthe {

    /**
     * Constantes de caractères représentant les éléments du labyrinthe.
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char FIN = 'F';
    public static final char MONSTRE = 'M';
    public static final char FANTOME = 'U';
    public static final char TROLL = 'T';
    public static final char ARME = 'A';
    public static final char BOUCLIER = 'B';

    /**
     * Constantes des actions possibles.
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";
    public static final String RECUP = "Recup";
    public static final String SAC = "TAB";

    /**
     * Le personnage joueur.
     */
    private Perso pj;

    /**
     * La case de fin du labyrinthe.
     */
    private Fin fin;

    private ArrayList<Monstre> monstres;
    private ArrayList<Objet> objets;
    private ArrayList<Mur> murs;

    /**
     * Grille représentant le labyrinthe.
     */
    public Object[][] grille;

    /**
     * Calcule la case suivante en fonction d'une action.
     *
     * @param x      coordonnée x de départ
     * @param y      coordonnée y de départ
     * @param action action à effectuer
     * @return tableau contenant les coordonnées de la case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT -> y--;
            case BAS -> y++;
            case DROITE -> x++;
            case GAUCHE -> x--;
            case SAC -> System.out.println("sac");
            case RECUP -> {}
            default -> throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * Constructeur : charge un labyrinthe à partir d’un fichier.
     *
     * @param nom   nom du fichier contenant le labyrinthe
     * @param perso personnage joueur à placer dans le labyrinthe
     * @throws IOException en cas de problème de lecture du fichier
     */
    public Labyrinthe(String nom, Perso perso) throws IOException {
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes = Integer.parseInt(bfRead.readLine());
        int nbColonnes = Integer.parseInt(bfRead.readLine());

        this.grille = new Object[nbColonnes][nbLignes];
        this.pj = null;
        this.fin = null;
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.murs = new ArrayList<>();

        String ligne = bfRead.readLine();
        int numeroLigne = 0;

        while (ligne != null) {
            int num = 0;
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR -> {
                        Mur m = new Mur(colonne, numeroLigne);
                        this.grille[colonne][numeroLigne] = m;
                        this.murs.add(m);
                    }
                    case VIDE -> this.grille[colonne][numeroLigne] = false;
                    case FIN -> {
                        Fin f = new Fin(colonne, numeroLigne);
                        this.grille[colonne][numeroLigne] = f;
                        this.fin = f;
                    }
                    case PJ -> {
                        if (perso != null) {
                            System.out.println("====================================================");
                            perso.setX(colonne);
                            perso.setY(numeroLigne);
                            this.pj = perso;
                            break;
                        }
                        Perso p = new Perso(colonne, numeroLigne, 100);
                        this.grille[colonne][numeroLigne] = p;
                        this.pj = p;
                    }
                    case MONSTRE -> {
                        Monstre m2 = new MonstreSimple(colonne, numeroLigne, 10, this);
                        this.grille[colonne][numeroLigne] = m2;
                        this.monstres.add(m2);
                        num++;
                    }
                    case FANTOME -> {
                        Monstre fa = new Fantome(colonne, numeroLigne, 10, this);
                        this.grille[colonne][numeroLigne] = fa;
                        this.monstres.add(fa);
                        num++;
                    }
                    case TROLL -> {
                        Monstre tro = new Troll(colonne, numeroLigne, 10, this);
                        this.grille[colonne][numeroLigne] = tro;
                        this.monstres.add(tro);
                        num++;
                    }
                    case ARME -> {
                        Epee e = new Epee(colonne, numeroLigne, (int) Math.round(Math.random() * 10 + 5));
                        this.grille[colonne][numeroLigne] = e;
                        this.objets.add(e);
                        System.out.println("Arme chargée à (" + colonne + ", " + numeroLigne + ")");
                    }
                    case BOUCLIER -> {
                        Bouclier b = new Bouclier(colonne, numeroLigne, (int) Math.round(Math.random() * 20 + 10));
                        this.grille[colonne][numeroLigne] = b;
                        this.objets.add(b);
                        System.out.println("Bouclier chargé à (" + colonne + ", " + numeroLigne + ")");
                    }
                    default -> throw new Error("caractère inconnu " + c);
                }
            }
            ligne = bfRead.readLine();
            numeroLigne++;
        }
        bfRead.close();
    }

    /**
     * Déplace le personnage en fonction de l’action, en gérant les collisions.
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        int[] courante = {this.pj.getX(), this.pj.getY()};
        int[] suivante = getSuivant(courante[0], courante[1], action);

        if (!monstres.isEmpty()) {
            boolean trouve = false;
            for (Monstre m : monstres) {
                if (m.etrePresent(suivante[0], suivante[1]) || murs.contains(grille[suivante[0]][suivante[1]])) {
                    trouve = true;
                }
            }
            if (!trouve) {
                this.grille[suivante[0]][suivante[1]] = false;
                this.pj.setX(suivante[0]);
                this.pj.setY(suivante[1]);
                this.grille[suivante[0]][suivante[1]] = this.pj;
            }
        } else {
            if (!murs.contains(grille[suivante[0]][suivante[1]])) {
                this.grille[suivante[0]][suivante[1]] = false;
                this.pj.setX(suivante[0]);
                this.pj.setY(suivante[1]);
                this.grille[suivante[0]][suivante[1]] = this.pj;
            }
        }
    }

    /**
     * Exécute les actions de chaque monstre : déplacement ou attaque.
     */
    public void actionMonstres() {
        ArrayList<Monstre> monstresMorts = new ArrayList<>();
        for (Monstre m : monstres) {
            if (m.getVie() <= 0) {
                monstresMorts.add(m);
            } else {
                boolean persoTrouve = false;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (m.verifPerso(m.getX() + i, m.getY() + j)) {
                            persoTrouve = true;
                        }
                    }
                }
                if (!persoTrouve) {
                    m.deplacerVolDOiseau(pj.getX(), pj.getY());
                } else {
                    pj.recevoirDegats(m.attaquer());
                    System.out.println("Vie diminuée : " + pj.getVie());
                }
            }
        }
        for (Monstre m : monstresMorts) {
            monstres.remove(m);
        }
    }

    /**
     * Vérifie si le jeu est terminé.
     *
     * @return vrai si le personnage est sur la case de fin
     */
    public boolean etreFini() {
        return fin.getX() == pj.getX() && fin.getY() == pj.getY();
    }

    // ##################################
    // GETTERS / SETTERS
    // ##################################

    /**
     * @return la taille verticale (Y) de la grille
     */
    public int getLengthY() {
        return grille[0].length;
    }

    /**
     * @return la taille horizontale (X) de la grille
     */
    public int getLength() {
        return grille.length;
    }

    /**
     * Vérifie s’il y a un mur à la position (x, y).
     *
     * @param x colonne
     * @param y ligne
     * @return vrai si mur présent
     */
    public boolean getMur(int x, int y) {
        boolean trouve = false;
        for (Mur m : murs) {
            if (m.etrePresent(x, y)) {
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Permet au personnage de récupérer un objet sur sa case.
     */
    public void recup() {
        System.out.println(pj.toString());
        for (Objet i : objets) {
            if (i.etrePresent(pj.getX(), pj.getY()) && !i.getEstPris()) {
                pj.inventaire.ajouterObjet(i);
                i.setEstPris();
                System.out.println(i.getType() + " a été récupéré !\n");
            } else {
                System.out.println("Pas d'objet à récupérer ici\n");
            }
        }
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public Perso getPj() {
        return pj;
    }

    public Fin getFin() {
        return fin;
    }

    public ArrayList<Monstre> getMonstresList() {
        return monstres;
    }

    public void setMonstresList(ArrayList<Monstre> monstres) {
        monstres.clear();
        for (Monstre m : monstres) {
            this.monstres.add(m);
        }
    }

    public ArrayList<Objet> getObjetsList() {
        return objets;
    }

    public void setObjetsList(ArrayList<Objet> objets) {
        objets.clear();
        for (Objet o : objets) {
            this.objets.add(o);
        }
    }

    public ArrayList<Mur> getMursList() {
        return murs;
    }

    public void setMursList(ArrayList<Mur> murs) {
        murs.clear();
        for (Mur m : murs) {
            this.murs.add(m);
        }
    }
}
