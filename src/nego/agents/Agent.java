package nego.agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nego.Item;
import nego.communication.Message;
import nego.negociation.INegociation;

/**
 * Created by atanakar on 03/01/17.
 */
public abstract class Agent extends Thread {
    int nego_dispo;
    Item objectif;
    INegociation technique;
    String nom;
    Message enAttente = null;

    public static HashMap<Agent, List<Message>> armoire = new HashMap<>();

    public Agent(int nego, Item obj, INegociation tech, String name) {
        armoire.put(this, new ArrayList<Message>());
        this.nego_dispo = nego;
        this.objectif = obj;
        this.technique = tech;
        this.nom = name;
    }

    public List<Message> getMessagesNonLus() {
        //System.out.println(armoire.keySet().size());
        //System.out.println(armoire.get(this).size());
        List<Message> ret = new ArrayList<>();
        for (Message m : armoire.get(this)) {
            if (!m.estLu)
                ret.add(m);
        }
        //System.out.println(nom + " : " + ret.size());
        return ret;
    }

    public abstract Message negocier(Message m);

    public abstract Message initierNegotiation(Agent dest);

    @Override
    public abstract void run();

    public String getNom() {
        return nom;
    }
}
