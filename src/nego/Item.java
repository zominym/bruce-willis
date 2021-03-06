package nego;

import java.util.Date;

/**
 * Created by atanakar on 03/01/17.
 */
public class Item {
    private Date dateDepart;
    private String villeDepart;
    private String villeArrivee;
    private int prix;

    public int getPrix() {
        return prix;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public Item(Date dateDepart, String villeDepart, String villeArrivee, int prix) {
        this.prix = prix;
        this.dateDepart = dateDepart;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }

    public Item(Item item) {
        this.prix = item.getPrix();
        this.dateDepart = item.getDateDepart();
        this.villeDepart = item.getVilleDepart();
        this.villeArrivee = item.getVilleArrivee();
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String toString() {
        String ret = "";
        ret += "PRIX : " + prix + ", DATE_D : " + dateDepart + ", VILLE_D : " + villeDepart + ", VILLE_A : " + villeArrivee;
        return ret;
    }
}
