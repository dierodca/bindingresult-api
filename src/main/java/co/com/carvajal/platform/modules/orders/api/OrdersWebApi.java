package co.com.carvajal.platform.modules.orders.api;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.carvajal.foundation.framework.exceptions.EBusinessApplicationException;
import co.com.carvajal.platform.crosscutting.domain.RequestDS;
import co.com.carvajal.platform.crosscutting.util.ValidationUtil;
import lombok.extern.log4j.Log4j2;

/**
 * Exposes remote rest api for feeding orders Dashboard
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-13
 */
@Log4j2
@RestController
@RequestMapping(
    value = "/orders",
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrdersWebApi {

  @GetMapping("")
  public ResponseEntity<String> listOrders(
      @Valid @RequestBody final RequestDS requestDS, final BindingResult result)
      throws EBusinessApplicationException {

    log.debug("init check active orders");
    ValidationUtil.validateBindingResult(result);

    return ResponseEntity.ok("Proceso ejecutado con exito");
  }
}
