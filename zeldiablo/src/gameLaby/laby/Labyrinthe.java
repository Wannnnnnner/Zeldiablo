package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char FIN = 'F';
    public static final char MONSTRE = 'M';
    public static final char OBJET = 'i';
    public static final char FANTOME = 'U';
    public static final char TROLL = 'T';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";
    public static final String RECUP = "Recup";
    public static final String SAC = "TAB";

    /**
     * attribut du personnage
     */
    private Perso pj;

    /**
     * attribut de la case de fin
     */
    private Fin fin;

    private ArrayList<Monstre> monstres;


    private ArrayList<Objet> objets;


    private ArrayList<Mur> murs;

    /**
     * les murs du labyrinthe
     */
    public Object[][] grille;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on diminue colonne
                x--;
                break;

            case SAC:
                //On ouvre l'inventaire
                System.out.println("sac");
                break;

            case RECUP:
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom, Perso perso) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.grille = new Object[nbColonnes][nbLignes];
        this.pj = null;
        this.fin = null;
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.murs = new ArrayList<>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            int num = 0;
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        Mur m = new Mur(colonne, numeroLigne);
                        this.grille[colonne][numeroLigne] = m ;
                        this.murs.add(m);
                        break;
                    case VIDE:
                        this.grille[colonne][numeroLigne] = false;
                        break;
                    case FIN:
                        Fin f = new Fin(colonne, numeroLigne);
                        this.grille[colonne][numeroLigne] = f;
                        this.fin = f;
                        break;
                    case PJ:
                        if (perso!= null) {
                            System.out.println("====================================================");
                            perso.setX(colonne);
                            perso.setY(numeroLigne);
                            this.pj = perso;
                            break;
                        }
                        Perso p = new Perso(colonne, numeroLigne,100);
                        // pas de mur
                        this.grille[colonne][numeroLigne] = p;
                        // ajoute PJ
                        this.pj = p;
                        break;
                    case MONSTRE:
                        Monstre m2 = new MonstreSimple(colonne, numeroLigne,10,this);
                        this.grille[colonne][numeroLigne] = m2;
                        this.monstres.add(m2);
                        num++;
                        break;
                    case FANTOME:
                        Monstre fa = new Fantome(colonne, numeroLigne,10,this);
                        this.grille[colonne][numeroLigne] = fa;
                        this.monstres.add(fa);
                        num++;
                        break;
                    case TROLL:
                        Monstre tro = new Troll(colonne, numeroLigne,10,this);
                        this.grille[colonne][numeroLigne] = tro;
                        this.monstres.add(tro);
                        num++;
                        break;
                    case OBJET:
                        Objet o = new Objet(colonne, numeroLigne);
                        this.grille[colonne][numeroLigne] = o;
                        this.objets.add(o);
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }



    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.pj.getX(), this.pj.getY()};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if(!monstres.isEmpty()) {
            boolean trouve = false;
            for(Monstre m : monstres) {

                if (m.etrePresent(suivante[0], suivante[1]) || murs.contains(grille[suivante[0]][suivante[1]] ) ) {
                    trouve = true;
                }
            }
            if(!trouve) {
                // on met a jour personnage
                this.pj.setX(suivante[0]);
                this.pj.setY(suivante[1]);
            }
        }
        else{
            if (!murs.contains(grille[suivante[0]][suivante[1]] )) {
                // on met a jour personnage
                this.pj.setX(suivante[0]);
                this.pj.setY(suivante[1]);
            }
        }
    }


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
                // deplacement
                if (!persoTrouve) {
                    m.deplacerRandom();
                }
                // attaque
                else {
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
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return fin.getX() == pj.getX() && fin.getY() == pj.getY();
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return grille[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return grille.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        boolean trouve = false;
        for (Mur m : murs) {
            if (m.etrePresent(x, y)) {
                trouve = true;
            }
        }
        return trouve;
    }

    //
    public void recup(){
        System.out.println(pj.toString());
        for (Objet i : objets) {
            if(i.etrePresent(pj.getX(), pj.getY()) && !i.getEstPris()) {
                pj.inventaire.ajouterObjet(i);
                i.setEstPris();
                System.out.println(i.getType() + " à été récupérer !\n" );
            }else{
                System.out.println("Pas d'objet a récupérer ici\n");
            }
        }

    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public Perso getPj(){
        return pj;
    }

    public Fin getFin(){
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
