package gameLaby.laby;

import java.util.ArrayList;

/**
 * La classe Inventaire.
 */
public class Inventaire {
    private ArrayList<Objet> objets;

    /**
     * Instancie un nouvel Inventaire
     */
    public Inventaire() {
        this.objets = new ArrayList<>();
    }

    /**
     * Ajoute un objet a l'inventaire
     *
     * @param objet L'objet a ajouter
     */
    public void ajouterObjet(Objet objet) {
        if (objet != null) {
            objets.add(objet);
        }
    }

    /**
     * Supprimer objet.
     *
     * @param objet L'objet a supprimer
     */
    public void supprimerObjet(Objet objet) {
        if (objet != null) {
            objets.remove(objet);
        }
    }

    /**
     * Getter des objets
     *
     * @return objets
     */
    public ArrayList<Objet> getObjets() {
        return objets;
    }

    /**
     * toString de l'inventaire
     * @return Inventaire sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        if (objets.isEmpty()) {
            return "Inventaire vide.";
        }
        String sb = "\n";
        for (Objet o : objets) {
            sb += "- "+ o.toString() + "\n";
        }
        return sb.toString();
    }
}