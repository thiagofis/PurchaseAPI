package experia.purchase.factory;

import java.util.Map;

/**
 * Created by thiago on 22/06/16.
 */
public interface IPaginationMapFactory {

    Map create(Integer max, Integer offset);
}
