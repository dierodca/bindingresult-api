package co.com.carvajal.platform.crosscutting.patterns.interfaces;

import co.com.carvajal.platform.crosscutting.domain.ResponseStatus;

public interface IRestResponse<T> {

  ResponseStatus getResponseStatus();

  T getResponse();

  int getHttpStatusCode();
}
