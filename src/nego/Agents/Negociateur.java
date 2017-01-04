package nego.Agents;

import nego.Item;
import nego.Message;
import nego.Performatif;

/**
 * Created by atanakar on 03/01/17.
 */
public class Negociateur extends Agent {

    public Negociateur() {
        super();
    }

    public Negociateur(int nego, Item obj) {
        super(nego, obj);
    }

    @Override
    public Message negocier(Message m) {
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
        int prixtemp = (int) (m.previous.objet.getPrix() * 1.10);
        if (m.objet.getPrix() < prixtemp) {
            Message mp = m.createReponse(Performatif.ACCEPTATION, m.objet);
            mp.send();
            return mp;
        }
        Item prop = new Item(m.objet.getDateDepart(), m.objet.getVilleDepart(), m.objet.getVilleArrivee(), prixtemp);
        Message mp = m.createReponse(Performatif.PROPOSITION, prop);
        mp.send();
        return mp;
    }

    public Message initierNegotiation(Agent dest) {
        Message mp = new Message(this, dest, Performatif.PROPOSITION, objectif, null);
        mp.send();
        return mp;
    }
}
