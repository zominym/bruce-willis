package nego.communication;

import nego.agents.Agent;

import nego.Item;

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
    public boolean estLu;

    public Message(Agent exp, Agent dest, Performatif perf, Item obj, Message prev) {
        this.expediteur = exp;
        this.destinataire = dest;
        this.type = perf;
        this.objet = obj;
        this.id_proposition = 0;
        if (prev != null)
            this.id_proposition = prev.id_proposition + 1;
        this.previous = prev;
        this.estLu = false;
    }

    public Message createReponse(Performatif perf, Item obj) {
        return new Message(destinataire, expediteur, perf, obj, this);
    }

    public String send() {
        Agent.armoire.get(destinataire).add(this);
        return this.toString();
    }

    public boolean estAccepte() {
        boolean truc = type == Performatif.ACCEPTATION;
        return truc;
    }

    public boolean estRefuse() {
        boolean truc = type == Performatif.REFUS;
        return truc;
    }

    public boolean estProposition(){
        return type == Performatif.PROPOSITION;
    }

    public void read() {
        this.estLu = true;
    }

    public String toString() {
        String ret = "";
        ret += "DE : " +expediteur.getNom() + ", A : " + destinataire.getNom() + ", TYPE : " + type + ", ID : " + id_proposition + "\n";
        if (objet != null)
            ret += objet.toString();
        else
            ret += "Null Item";
        return ret;
    }

    public int getIdProposition() {
        return id_proposition;
    }

    public int getObjetPrix() {
        return objet.getPrix();
    }

    public Item getObjet() {
        return objet;
    }

    public void setObject(Item obj) { this.objet = obj; }

    public void lire() {
        this.estLu = true;
    }
}
