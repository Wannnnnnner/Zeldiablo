package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;
import moteurJeu.MoteurJeu;

import java.io.IOException;

/**
 * Classe représentant le jeu de labyrinthe.
 * Implémente l'interface {@link Jeu} du moteur de jeu.
 */
public class LabyJeu implements Jeu {

    /** Le labyrinthe dans lequel se déroule la partie. */
    private Labyrinthe laby;

    /**
     * Compteur de frames pour synchroniser les actions des monstres.
     */
    int nbFrame = 0;

    /**
     * Constructeur du jeu à partir d'un labyrinthe donné.
     *
     * @param laby le labyrinthe utilisé dans ce niveau du jeu
     */
    public LabyJeu(Labyrinthe laby) {
        this.laby = laby;
    }

    /**
     * Constructeur du jeu à partir d'un personnage, initialisant un labyrinthe par défaut.
     *
     * @param perso le personnage joueur
     * @throws IOException si le fichier du labyrinthe ne peut pas être lu
     */
    public LabyJeu(Perso perso) throws IOException {
        this.laby = new Labyrinthe("labySimple/laby10.txt", null);
    }

    /**
     * Met à jour l'état du jeu à chaque frame selon les entrées clavier et la logique de jeu.
     *
     * @param secondes temps écoulé depuis la dernière mise à jour (non utilisé ici)
     * @param clavier objet représentant l'état des touches du clavier
     * @throws IOException si une erreur survient lors de l'accès au fichier d'un nouveau niveau
     */
    public void update(double secondes, Clavier clavier) throws IOException {
        nbFrame++;
        if (clavier.attaquer) {
            this.laby.getPj().attaquerMonstres(this.laby.grille);
        } else if (clavier.droite) {
            this.laby.deplacerPerso(Labyrinthe.DROITE);
        } else if (clavier.gauche) {
            this.laby.deplacerPerso(Labyrinthe.GAUCHE);
        } else if (clavier.haut) {
            this.laby.deplacerPerso(Labyrinthe.HAUT);
        } else if (clavier.bas) {
            this.laby.deplacerPerso(Labyrinthe.BAS);
        } else if (clavier.recup) {
            this.laby.recup();
        } else if (clavier.sac) {
            System.out.println(laby.getPj().toString());
        }

        if (nbFrame == 10) {
            laby.actionMonstres();
            nbFrame = 0;
        }

        if (etreFini()) {
            Niveaux.fini = true;
            laby = new LabyJeu(new Labyrinthe(Niveaux.chargerSuivant(), this.laby.getPj())).getLaby();
        }
    }

    /**
     * Initialise les éléments du jeu. (Méthode vide ici)
     */
    @Override
    public void init() {
        // Initialisation non utilisée dans ce contexte
    }

    /**
     * Indique si le jeu est terminé (ex. si la sortie a été atteinte).
     *
     * @return true si le labyrinthe est terminé, false sinon
     */
    @Override
    public boolean etreFini() {
        return laby.etreFini();
    }

    /**
     * Renvoie le labyrinthe actuel.
     *
     * @return le labyrinthe en cours
     */
    public Labyrinthe getLaby() {
        return laby;
    }

}
