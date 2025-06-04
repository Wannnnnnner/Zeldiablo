package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

/**
 * La classe MainLaby
 */
public class MainLaby {
    /**
     * Le point d'entree de l'applicatioh.
     *
     * @param args les arguments
     * @throws IOException l'IOException
     */
    public static void main(String[] args) throws IOException {
        int pFPS = 10;
        LabyJeu laby = new LabyJeu(new Labyrinthe(Niveaux.charger(),null));
        LabyDessin labyDessin = new LabyDessin();
        MoteurJeu.setFPS(pFPS);
        MoteurJeu.launch(laby, labyDessin);
    }
}
