package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;
import moteurJeu.MoteurJeu;

import java.io.IOException;

public class LabyJeu implements Jeu {
    private Labyrinthe laby;
    int nbFrame = 0;

    public LabyJeu(Labyrinthe laby) {
        this.laby = laby;
    }

    public LabyJeu(Perso perso) throws IOException {
        this.laby = new Labyrinthe("labySimple/laby10.txt",null);
    }

    public void update(double secondes, Clavier clavier) throws IOException {
        nbFrame++;
        if(clavier.attaquer){
            this.laby.getPj().attaquerMonstres(this.laby.getMonstres());
        }else if (clavier.droite) {
            this.laby.deplacerPerso(Labyrinthe.DROITE);
        }else if (clavier.gauche) {
            this.laby.deplacerPerso(Labyrinthe.GAUCHE);
        }else if (clavier.haut) {
            this.laby.deplacerPerso(Labyrinthe.HAUT);
        }else if(clavier.bas) {
            this.laby.deplacerPerso(Labyrinthe.BAS);
        }else if(clavier.recup){
            this.laby.recup();
        } else if (clavier.sac) {
            System.out.println(laby.getPj().toString());
        }
        if(nbFrame == 4) {
            laby.actionMonstres();
            nbFrame = 0;
        }


        if(etreFini()) {
            Niveaux.fini = true;
            laby = new LabyJeu(new Labyrinthe(Niveaux.chargerSuivant(), this.laby.getPj())).getLaby();
        }

    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return laby.etreFini();
    }

    public Labyrinthe getLaby() {
        return laby;
    }

}
