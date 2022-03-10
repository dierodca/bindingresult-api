package co.com.carvajal.platform.crosscutting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Domain to manage orders
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-13
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

  private Long id;
  private String code;
  private Long price;

}
