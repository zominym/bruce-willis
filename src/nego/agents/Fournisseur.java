package nego.agents;

import nego.Item;
import nego.Main;
import nego.communication.Message;
import nego.communication.Performatif;
import nego.negociation.INegociation;

/**
 * Created by atanakar on 03/01/17.
 */
public class Fournisseur extends Agent {

    final double SEUIL_REFUS = 0.8;


    public Fournisseur(int nego, Item obj, INegociation tech, String name) {
        super(nego, obj, tech, name);
        Main.fournisseurs.add(this);
        Main.fournisseursToken = true;
    }

    @Override
    public Message negocier(Message proposition) {
        //System.out.println("Negocie");
        Message reponse;

        if(proposition.estAccepte()) {
            this.nego_dispo --;
            return null;
        }

        if (proposition.type == Performatif.REFUS)
            return null;

        if(proposition.getIdProposition()>12 || this.nego_dispo <= 0) {
            reponse = proposition.createReponse(Performatif.REFUS, null);
            reponse.send();
            return reponse;
        }
        if(proposition.estProposition()){//si le negociateur a fait une proposition
            Item newProposition = new Item(proposition.getObjet());
            if(proposition.getObjetPrix() < objectif.getPrix() * SEUIL_REFUS){ //trop en dessous de l'objectif


                // et si on a deja fait une offre on baisse notre offre précédente selon notre technique
                if(proposition.previous != null) {
                    newProposition.setPrix(proposition.previous.getObjetPrix() - 5);
                }
                else { //sinon on envoi notre objectif
                    newProposition = objectif;
                }


                // si la proposition est toujours acceptable on la propose
                if(newProposition.getPrix() > objectif.getPrix() * SEUIL_REFUS)
                    reponse = proposition.createReponse(Performatif.PROPOSITION, newProposition);
                else //sinon on refuse
                    reponse = proposition.createReponse(Performatif.REFUS,null);
            }
            else{//si la proposition est correct et qu'il ne reste plus de proposition possible on accepte
                if(proposition.getIdProposition()==11) {
                    reponse = proposition.createReponse(Performatif.ACCEPTATION, proposition.getObjet());
                    this.nego_dispo --;
                }
                else {
                    if(proposition.previous != null) { //on choisit de négocier le prix selon notre technique
                        newProposition.setPrix(technique.negocier(proposition).getPrix());
                    }
                    else { //sinon on envoi notre objectif
                        newProposition = objectif;
                    }
                    reponse = proposition.createReponse(Performatif.PROPOSITION, newProposition);
                }
            }
            reponse.send();
            return reponse;
        }
        return null;
    }

    @Override
    public Message initierNegotiation(Agent dest) {
        return null;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while(this.nego_dispo > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            for (Message m : getMessagesNonLus()) {
                System.out.println(negocier(m) + "\n");
                m.lire();
            }
        }
        System.out.println("Stopping fournisseur " + nom);
        Main.fournisseurs.remove(this);
        this.stop();
    }
}
