package co.com.carvajal.platform.crosscutting.domain;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author dierodca */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDS implements Serializable {

  private static final long serialVersionUID = 5502140212281150700L;

  @NotEmpty(message = "El codigo no debe ser vacio o nulo")
  private String code;

  @NotEmpty private String description;

  @DecimalMin(value = "100")
  private double value;

  @NotEmpty private List<String> files;
}
