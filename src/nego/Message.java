package nego;

import nego.Agents.Agent;

import nego.Performatif;

/**
 * Created by atanakar on 03/01/17.
 */
public class Message {

    public Agent expediteur;
    public Agent destinataire;
    public Performatif type;
    public Item objet;
    public int id_proposition;
    public Message previous;

    public void send() {
        Agent.armoire.get(destinataire).add(this);
    }

    public boolean estAccepte() {
        return type == Performatif.ACCEPTATION;
    }

    public boolean estRefuse() {
        return type == Performatif.REFUS;
    }
}
