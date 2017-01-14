package nego.negociation;

import nego.Item;
import nego.communication.Message;

/**
 * Created by atanakar on 14/01/17.
 */
public class Moyenne implements INegociation {

    @Override
    public Item negocier(Message prev){
        Item ret = new Item(prev.getObjet());
        ret.setPrix((prev.getObjetPrix() + prev.previous.getObjetPrix()) / 2);
        return ret;
    }
}
