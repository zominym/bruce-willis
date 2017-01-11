package nego;

import com.sun.org.apache.xpath.internal.SourceTree;
import nego.Agents.Agent;
import nego.Agents.Fournisseur;
import nego.Agents.Negociateur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by atanakar on 03/01/17.
 */
public class Main {

    public static void main(String args[]) {
        int i = 0;

        //Creation des agents allant effectuer la negotiation
        ArrayList<Agent> agents = new ArrayList<>();
        agents.add(new Negociateur(0, new Item(Calendar.getInstance().getTime(), "Paris", "Lyon", 150)));
        agents.add(new Fournisseur(0, new Item(Calendar.getInstance().getTime(), "Paris", "Lyon", 200)));

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
            System.out.println(proposition.toString());
        }while(proposition.estProposition());

        System.out.println("\n\n***************************************\nresultat de la conversation : \n");
        System.out.println(proposition.toString());

    }
}
