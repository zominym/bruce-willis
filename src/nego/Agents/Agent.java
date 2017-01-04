package nego.Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nego.Item;
import nego.Message;
import nego.Performatif;

/**
 * Created by atanakar on 03/01/17.
 */
public abstract class Agent {
    int nego_dispo;
    Item objectif;

    public static HashMap<Agent, List<Message>> armoire = new HashMap<>();

    public Agent() {
        armoire.put(this, new ArrayList<Message>());
    }

    public Agent(int nego, Item obj) {
        this();
        this.nego_dispo = nego;
        this.objectif = obj;
    }

    public List<Message> getMessagesNonLus() {
        List<Message> ret = new ArrayList<>();
        for (Message m : armoire.get(this)) {
            if (!m.isRead)
                ret.add(m);
        }
        return ret;
    }

    public abstract Message negocier(Message m);

    public abstract Message initieNegotiation();
}
