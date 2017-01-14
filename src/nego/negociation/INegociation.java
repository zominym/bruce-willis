package nego.negociation;

import nego.Item;
import nego.communication.Message;

/**
 * Created by atanakar on 14/01/17.
 */
public interface INegociation {

    public Item negocier(Message m);
}
