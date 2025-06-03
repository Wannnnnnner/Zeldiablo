package moteurJeu;

import javafx.scene.input.KeyEvent;

public class Clavier {

    /**
     * controle appuyes
     */
    public boolean haut, bas, gauche, droite, recup;

    public boolean sac = false;

    public boolean attaquer = false;

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void appuyerTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S, DOWN:
                this.bas = true;
                break;

            // si touche haut
            case Z, UP:
                this.haut = true;
                break;

            // si touche gauche
            case Q, LEFT:
                this.gauche = true;
                break;

            // si touche droite
            case D, RIGHT:
                this.droite = true;
                break;

            // si touche E
            case E:
                this.recup = true;
                break;

            // si touche TAB
            case TAB:
                if (this.sac) {
                    this.sac = false;
                }else{
                    this.sac = true;
                }
                break;
            // si touche ESPACE
            case SPACE:
                this.attaquer = true;
                break;
        }

    }

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void relacherTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S, DOWN:
                this.bas = false;
                break;

            // si touche haut
            case Z, UP:
                this.haut = false;
                break;

            // si touche gauche
            case Q, LEFT:
                this.gauche = false;
                break;

            // si touche droite
            case D, RIGHT:
                this.droite = false;
                break;

            // si touche E
            case E:
                this.recup = false;
                break;
            // si touche ESPACE
            case SPACE:
                this.attaquer = false;
                break;
        }
    }
}
