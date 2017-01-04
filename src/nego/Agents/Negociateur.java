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

    public Negociateur(int nego) {
        super(nego);
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
            Message mp = m.createReponse(Performatif.REFUS, new Item());
            mp.send();
            return mp;
        }
        return null;
    }

    @Override
    public Message initieNegotiation() {
        return null;
    }
}
