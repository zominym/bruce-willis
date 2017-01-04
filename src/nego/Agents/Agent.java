package nego.Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nego.Message;
import nego.Performatif;

/**
 * Created by atanakar on 03/01/17.
 */
public abstract class Agent {
    int nego_dispo;

    public static HashMap<Agent, List<Message>> armoire = new HashMap<>();

    public Agent() {
        armoire.put(this, new ArrayList<Message>());
    }

    public Agent(int nego) {
        this();
        this.nego_dispo = nego;
    }

    public List<Message> getMessagesNonLus() {
        List<Message> ret = new ArrayList<>();
        for (Message m : armoire.get(this)) {
            if (!m.isRead)
                ret.add(m);
        }
        return ret;
    }

    public abstract void negocier(Message m);

}
