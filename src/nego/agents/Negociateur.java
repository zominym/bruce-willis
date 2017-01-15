package nego.agents;

import nego.Item;
import nego.Main;
import nego.communication.Message;
import nego.communication.Performatif;
import nego.negociation.INegociation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atanakar on 03/01/17.
 */
public class Negociateur extends Agent {

    public Negociateur(int nego, Item obj, INegociation tech, String name) {
        super(nego, obj, tech, name);
    }
    List<Fournisseur> contacted = new ArrayList<>();

    @Override
    public Message negocier(Message m) {
        //System.out.println("Negocie");
        if (m.type == Performatif.REFUS)
            return null;
        if (m.type == Performatif.ACCEPTATION) {
            this.nego_dispo --;
            return null;
        }
        if (nego_dispo <= 0) {
            Message mp = m.createReponse(Performatif.REFUS, m.objet);
            mp.send();
            return mp;
        }

        Item newItem = technique.negocier(m.previous);

        //System.out.println(">>> " + newItem.toString());
        if (m.objet.getPrix() < newItem.getPrix()) {
            //System.out.println("accepted because " + prixtemp + ">" + m.objet.getPrix());
            Message mp = m.createReponse(Performatif.ACCEPTATION, m.objet);
            this.nego_dispo --;
            mp.send();
            return mp;
        }
        Item prop = new Item(m.objet.getDateDepart(), m.objet.getVilleDepart(), m.objet.getVilleArrivee(), newItem.getPrix());
        Message mp = m.createReponse(Performatif.PROPOSITION, prop);
        mp.send();
        return mp;
    }

    public Message initierNegotiation(Agent dest) {
        Message mp = new Message(this, dest, Performatif.PROPOSITION, objectif, null);
        mp.send();
        return mp;
    }

    @Override
    public void run() {
        /*
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        */
        while (!Main.fournisseursToken){
            //System.out.println("waiting");
        }
        while (this.nego_dispo > 0) {
            //System.out.println(this.nego_dispo);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            for (Fournisseur f : Main.fournisseurs) {
                if (!contacted.contains(f)) {
                    System.out.println(initierNegotiation(f) + "\n");
                    contacted.add(f);
                }
            }
            for (Message m : getMessagesNonLus()) {
                System.out.println(negocier(m) + "\n");
                m.lire();
            }
        }
        System.out.println("Stopping negociator " + nom);
        this.stop();
    }
}
