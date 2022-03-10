package co.com.carvajal.platform.modules.orders.dataproviders;

import java.util.List;

/**
 * Reflects persistence logic related to Person domain entity
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-20
 */

public interface OrdersDataProvider {

    void process(List<String> files);

}
