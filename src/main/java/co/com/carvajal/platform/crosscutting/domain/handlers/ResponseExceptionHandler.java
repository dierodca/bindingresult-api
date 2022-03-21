package co.com.carvajal.platform.crosscutting.domain.handlers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import co.com.carvajal.platform.crosscutting.domain.ValidationError;
import co.com.carvajal.platform.crosscutting.domain.exceptions.InvalidRequestException;
import co.com.carvajal.platform.crosscutting.patterns.interfaces.IRestResponse;
import co.com.carvajal.platform.crosscutting.util.ResponseEntityUtil;

/** @author dierodca */
@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<IRestResponse<List<ValidationError>>> invalidRegistrant(
      final InvalidRequestException ex) {
    final List<ValidationError> errors = ex.getErrors();
    return ResponseEntityUtil.createResponseValidationError(errors);
  }
}
