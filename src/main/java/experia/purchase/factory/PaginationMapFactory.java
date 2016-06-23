package experia.purchase.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiago on 22/06/16.
 */
public class PaginationMapFactory implements IPaginationMapFactory  {

    @Override
    public Map create(Integer max, Integer offset) {
        Map variables = new HashMap<String, String>();
        variables.put("max",max.toString());
        variables.put("offset",offset.toString());
        return  variables;
    }
}
