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

        if(proposition.estProposition()){
            if(proposition.getObjetPrix() < objectif.getPrix() * 0.90){
                Item newProposition = proposition.getObjet();
                newProposition.setPrix(objectif.getPrix() + 5);
                return proposition.createReponse(Performatif.PROPOSITION, newProposition);
            }
            else{
                return proposition.createReponse(Performatif.ACCEPTATION,proposition.getObjet());
            }
        }
        return null;
    }

    @Override
    public Message initierNegotiation(Agent dest) {
        return null;
    }
}
