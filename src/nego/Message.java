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
    public boolean isRead;

    public Message(Agent exp, Agent dest, Performatif perf, Item obj, Message prev) {
        this.expediteur = exp;
        this.destinataire = dest;
        this.type = perf;
        this.objet = obj;
        this.id_proposition = 0;
        if (prev != null)
            this.id_proposition = prev.id_proposition + 1;
        this.previous = prev;
        this.isRead = false;
    }

    public Message createReponse(Performatif perf, Item obj) {
        return new Message(destinataire, expediteur, perf, obj, this);
    }

    public void send() {
        Agent.armoire.get(destinataire).add(this);
    }

    public boolean estAccepte() {
        return type == Performatif.ACCEPTATION;
    }

    public boolean estRefuse() {
        return type == Performatif.REFUS;
    }
    public void read() {
        this.isRead = true;
    }

}
