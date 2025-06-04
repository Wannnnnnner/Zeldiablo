package gameLaby.laby;

public class Niveaux {
    public static final String [] liste1 = {"laby10.txt","laby11.txt","laby12.txt","laby13.txt"};
    public static String [] courant = liste1;
    public static int lvl = 0;
    public static boolean fini = false;

    public static String charger(){
        return "zeldiablo/labySimple/"+courant[lvl];
    }

    public static String chargerSuivant(){
        lvl++;
        if(lvl>=courant.length){lvl=0;}
        fini = false;
        return "zeldiablo/labySimple/"+courant[lvl];
    }
}
