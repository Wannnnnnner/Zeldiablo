package gameLaby.laby;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.util.ArrayList;

public class LabyDessin implements DessinJeu {


    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu laby = (LabyJeu) jeu;
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // fond
        gc.setFill(new ImagePattern(new Image("file:labySimple/Sol.png")));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Labyrinthe labyrinthe = laby.getLaby();
        double caseWidth = canvas.getWidth() / labyrinthe.getLength();
        double caseHeight = canvas.getHeight() / labyrinthe.getLengthY();

        // murs
        gc.setFill(new ImagePattern(new Image("file:labySimple/Mur.png")));
        for (int y = 0; y < labyrinthe.getLengthY(); y++) {
            for (int x = 0; x < labyrinthe.getLength(); x++) {
                if (labyrinthe.getMur(x, y)) {
                    gc.fillRect(x * caseWidth, y * caseHeight, caseWidth, caseHeight);
                }
            }
        }

        // perso
        gc.setFill(new ImagePattern(new Image("file:labySimple/perso.png")));
        Perso perso = labyrinthe.getPj();
        gc.fillOval(perso.getX() * caseWidth, perso.getY() * caseHeight, caseWidth, caseHeight);

        // fin
        gc.setFill(new ImagePattern(new Image("file:labySimple/echelle.png")));
        Fin fin = labyrinthe.getFin();
        gc.fillRect(fin.getX() * caseWidth, fin.getY() * caseHeight, caseWidth, caseHeight);

        ArrayList<Monstre> monstres = labyrinthe.getMonstresList();
        if (!monstres.isEmpty()) {
            for (Monstre monstre : monstres) {
                if(monstre instanceof Fantome){
                    gc.setFill(new ImagePattern(new Image("file:labySimple/fant.png")));
                    gc.fillOval(monstre.getX() * caseWidth, monstre.getY() * caseHeight, caseWidth, caseHeight);

                }else if(monstre instanceof Troll){
                    gc.setFill(new ImagePattern(new Image("file:labySimple/troll.png")));
                    gc.fillOval(monstre.getX() * caseWidth, monstre.getY() * caseHeight, caseWidth, caseHeight);

                }else{
                    gc.setFill(new ImagePattern(new Image("file:labySimple/Monstre.png")));

                    gc.fillOval(monstre.getX() * caseWidth, monstre.getY() * caseHeight, caseWidth, caseHeight);
                }
            }
        }

        //  Epee et Bouclier
        ArrayList<Objet> objets = labyrinthe.getObjetsList();
        if (!objets.isEmpty()) {
            for (Objet o : objets) {
                if (!o.estPris) {
                    Image image;
                    if (o instanceof Epee) {
                        if(o.getValeur()>10){
                            image = new Image("file:labySimple/sword2.png");
                        }else{
                            image = new Image("file:labySimple/sword1.png");
                        }

                    } else if (o instanceof Bouclier) {
                        if(o.getValeur()>10){
                            image = new Image("file:labySimple/shield2.png");
                        }else {
                            image = new Image("file:labySimple/shield1.png");
                        }
                    } else {
                        continue;
                    }

                    gc.setFill(new ImagePattern(image));
                    gc.fillOval(o.getX() * caseWidth, o.getY() * caseHeight, caseWidth, caseHeight);
                }
            }
        }

    }
}

