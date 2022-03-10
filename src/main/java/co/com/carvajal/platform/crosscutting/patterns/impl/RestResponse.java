package co.com.carvajal.platform.crosscutting.patterns.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import co.com.carvajal.platform.crosscutting.domain.ResponseStatus;
import co.com.carvajal.platform.crosscutting.patterns.interfaces.IRestResponse;
import lombok.Setter;

@Setter
public class RestResponse<T> implements IRestResponse<T> {

  private ResponseStatus responseStatus;
  private T response;
  @JsonIgnore private int httpStatusCode;

  @Override
  public ResponseStatus getResponseStatus() {
    return this.responseStatus;
  }

  @Override
  public T getResponse() {
    return this.response;
  }

  @Override
  public int getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
