
package co.com.carvajal.platform.crosscutting.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order
 *
 * @author Carvajal
 * @version 1.0
 * @since 2020-04-22
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Long value;
    private Date dateOrder;

}
