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
}
