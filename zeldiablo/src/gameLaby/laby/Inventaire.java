package gameLaby.laby;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> objets;

    public Inventaire() {
        this.objets = new ArrayList<>();
    }

    /**
     * Adds an object to the inventory.
     * @param objet The object to add.
     */
    public void ajouterObjet(Objet objet) {
        if (objet != null) {
            objets.add(objet);
        }
    }

    public void supprimerObjet(Objet objet) {
        if (objet != null) {
            objets.remove(objet);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Objet> getObjets() {
        return objets;
    }

    /**
     *
     * @return
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