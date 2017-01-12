package nego.Agents;

import nego.Item;
import nego.Message;
import nego.Performatif;

/**
 * Created by atanakar on 03/01/17.
 */
public class Fournisseur extends Agent {

    public Fournisseur() {
        super();
    }

    public Fournisseur(int nego, Item obj) {
        super(nego, obj);
    }

    @Override
    public Message negocier(Message proposition) {
        System.out.println("Negocie");
        nego_dispo++;

        if(proposition.estAccepte())
            return proposition;

        if(proposition.getIdProposition()>12)
            return proposition.createReponse(Performatif.REFUS,null);

        if(proposition.estProposition()){//si le negociateur à fait une proposition
            Item newProposition = new Item(proposition.getObjet());
            if(proposition.getObjetPrix() < objectif.getPrix() * 0.90){ //trop en dessous de l'objectif


                // et si on a deja fait une offre on baisse notre offre précédente de 5
                if(proposition.previous != null) {
                    newProposition.setPrix(proposition.previous.getObjet().getPrix() - 5);
                }
                else { //sinon on envoi notre objectif
                    newProposition = objectif;
                }


                // si la proposition est toujours acceptable on la propose
                if(newProposition.getPrix() > objectif.getPrix() * 0.90)
                    return proposition.createReponse(Performatif.PROPOSITION, newProposition);
                else //sinon on refuse
                    return proposition.createReponse(Performatif.REFUS,null);
            }
            else{//si la proposition est correct et qu'il ne reste plus de proposition possible on accepte
                if(proposition.getIdProposition()==11)
                    return proposition.createReponse(Performatif.ACCEPTATION,proposition.getObjet());
                else {
                    if(proposition.previous != null) { //on choisis la moitié entre notre derniere proposition et la sienne
                        newProposition.setPrix((proposition.previous.getObjet().getPrix() + proposition.getObjetPrix()) /2);
                    }
                    else { //sinon on envoi notre objectif
                        newProposition = objectif;
                    }
                    return proposition.createReponse(Performatif.PROPOSITION, newProposition);
                }
            }
        }
        return null;
    }

    @Override
    public Message initierNegotiation(Agent dest) {
        return null;
    }
}
