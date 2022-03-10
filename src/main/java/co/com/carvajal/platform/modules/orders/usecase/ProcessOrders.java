package co.com.carvajal.platform.modules.orders.usecase;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import co.com.carvajal.foundation.framework.stereotypes.UseCase;
import co.com.carvajal.platform.modules.orders.dataproviders.OrdersDataProvider;

/**
 * Process Orders use case.
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-13
 */

@UseCase
public class ProcessOrders {

    @Autowired
    private OrdersDataProvider ordersDataProvider;

    public void ckeckOrders(final List<String> files) {
        this.ordersDataProvider.process(files);
    }

}
