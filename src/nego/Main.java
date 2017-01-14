package nego;

import nego.agents.Agent;
import nego.agents.Fournisseur;
import nego.agents.Negociateur;
import nego.communication.Message;
import nego.negociation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by atanakar on 03/01/17.
 */
public class Main {

    public static List<Fournisseur> fournisseurs = new ArrayList<>();
    public static boolean fournisseursToken = false;

    public static void main(String args[]) {
        int i = 0;

        //Creation des agents allant effectuer la negotiation
        ArrayList<Agent> agents = new ArrayList<>();
        Agent ag1 = new Negociateur(1, new Item(Calendar.getInstance().getTime(), "Paris", "Lyon", 170), new Plus5(), "Nego");
        ag1.start();

        Agent ag2 = new Fournisseur(1, new Item(Calendar.getInstance().getTime(), "Paris", "Lyon", 200), new Moyenne(), "Four");
        ag2.start();

        /*
        //Création de la premiere offre
        Agent nextAgent = agents.get(i);
        Message proposition = nextAgent.initierNegotiation(agents.get(1));
        System.out.println(proposition.toString());
        i++;

        //Boucle de negotiation
        do{
            System.out.println();
            nextAgent = agents.get(i);
            proposition = nextAgent.negocier(proposition);
            i = (i+1)%2;
            if (proposition.getObjet() == null)
                System.out.println(proposition.type);
            System.out.println(proposition.toString());
        }while(proposition.estProposition());

        System.out.println("\n\n***************************************\nresultat de la conversation : \n");
        System.out.println(proposition.toString());
        */

        while(true);

    }
}
