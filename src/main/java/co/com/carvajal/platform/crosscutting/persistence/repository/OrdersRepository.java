package co.com.carvajal.platform.crosscutting.persistence.repository;

import java.util.List;
import co.com.carvajal.platform.crosscutting.domain.OrderDTO;

/**
 * OrdersRepository
 *
 * @author Carvajal
 * @version 1.0
 * @since 2020-04-22
 *
 */

public interface OrdersRepository {

    List<OrderDTO> getOrdersInformation();

}
