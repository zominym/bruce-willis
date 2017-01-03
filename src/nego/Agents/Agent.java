package nego.Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nego.Message;

/**
 * Created by atanakar on 03/01/17.
 */
public abstract class Agent {

    public static HashMap<Agent, List<Message>> armoire = new HashMap<>();

    public Agent() {
        armoire.put(this, new ArrayList<Message>());
    }

    public abstract Message initieNegotiation();

    public abstract Message negocie(Message proposition);
}
