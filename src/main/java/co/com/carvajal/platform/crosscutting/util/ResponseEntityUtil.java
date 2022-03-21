package co.com.carvajal.platform.crosscutting.util;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.com.carvajal.platform.crosscutting.domain.ResponseStatus;
import co.com.carvajal.platform.crosscutting.domain.ValidationError;
import co.com.carvajal.platform.crosscutting.domain.enums.ResponseStatusCode;
import co.com.carvajal.platform.crosscutting.patterns.impl.RestResponse;
import co.com.carvajal.platform.crosscutting.patterns.interfaces.IRestResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** @author dierodca */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntityUtil {

  public static <T> ResponseEntity<IRestResponse<T>> createResponseEntity(
      final IRestResponse<T> response) {
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatusCode())).body(response);
  }

  public static ResponseEntity<IRestResponse<List<ValidationError>>> createResponseValidationError(
      final List<ValidationError> errors) {
    final RestResponse<List<ValidationError>> fullResponse = new RestResponse<>();

    if (errors != null && !errors.isEmpty()) {
      final ResponseStatus responseStatus = getErrorResponseStatus("Validation error");

      fullResponse.setResponse(errors);
      fullResponse.setResponseStatus(responseStatus);
      fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    return createResponseEntity(fullResponse);
  }

  public static <T> ResponseEntity<IRestResponse<T>> createSuccessfulResponse(
      final String message, final int httpStatusCode, final T response) {
    final ResponseStatus status = getSuccessResponseStatus(message);

    final RestResponse<T> fullResponse = new RestResponse<>();
    fullResponse.setResponseStatus(status);
    fullResponse.setHttpStatusCode(httpStatusCode);
    fullResponse.setResponse(response);

    return createResponseEntity(fullResponse);
  }

  private static ResponseStatus getErrorResponseStatus(final String message) {
    return ResponseStatus.builder().message(message).statusCode(ResponseStatusCode.ERROR).build();
  }

  private static ResponseStatus getSuccessResponseStatus(final String message) {
    return ResponseStatus.builder().message(message).statusCode(ResponseStatusCode.OK).build();
  }
}
