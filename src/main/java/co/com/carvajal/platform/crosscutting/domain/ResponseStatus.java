package co.com.carvajal.platform.crosscutting.domain;

import co.com.carvajal.platform.crosscutting.domain.enums.ResponseStatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {

  private ResponseStatusCode statusCode;
  private String message;
}
