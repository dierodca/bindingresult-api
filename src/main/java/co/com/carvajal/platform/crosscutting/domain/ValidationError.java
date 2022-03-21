package co.com.carvajal.platform.crosscutting.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/** @author dierodca */
@Data
@Builder
@AllArgsConstructor
public class ValidationError implements Serializable {

  private static final long serialVersionUID = -7221913825275180348L;

  private String field;
  private String code;
  private String defaultMessage;
}
