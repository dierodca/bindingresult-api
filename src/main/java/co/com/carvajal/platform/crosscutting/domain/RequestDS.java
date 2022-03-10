package co.com.carvajal.platform.crosscutting.domain;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDS implements Serializable {

  private static final long serialVersionUID = 5502140212281150700L;

  @NotEmpty private String code;
  @NotEmpty private String description;
  @NotNull private double value;
  @NotEmpty private List<String> files;
}
