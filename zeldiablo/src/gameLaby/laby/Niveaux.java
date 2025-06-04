package gameLaby.laby;

/**
 * La classe Niveaux.
 */
public class Niveaux {
    /**
     * La liste des fichiers de niveaux.
     */
    public static final String[] liste1 = {"laby10.txt", "laby11.txt", "laby12.txt", "laby13.txt"};

    /**
     * Le tableau courant des niveaux.
     */
    public static String[] courant = liste1;

    /**
     * Le niveau actuel.
     */
    public static int lvl = 0;

    /**
     * Indique si le niveau est terminé.
     */
    public static boolean fini = false;

    /**
     * Charge le niveau courant.
     *
     * @return le chemin du fichier du niveau courant
     */
    public static String charger() {
        return "labySimple/" + courant[lvl];
    }

    /**
     * Charge le niveau suivant.
     *
     * @return le chemin du fichier du niveau suivant
     */
    public static String chargerSuivant() {
        lvl++;
        if (lvl >= courant.length) {
            lvl = 0;
        }
        fini = false;
        return "labySimple/" + courant[lvl];
    }
}
