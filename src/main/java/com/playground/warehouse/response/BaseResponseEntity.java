package com.playground.warehouse.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.playground.warehouse.constant.WarehouseBaseResponseConstant;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseEntity<T> {
  protected Integer responseCode;

  protected String responseMessage;

  private T responseData;

  public void setInvalidRequestParamResponse() {
    this.responseCode =
        WarehouseBaseResponseConstant.ResponseCode.INVALID_REQUEST_PARAMETERS.value();
    this.responseMessage =
        WarehouseBaseResponseConstant.ResponseMessage.INVALID_REQUEST_PARAMETERS.value();
  }

  public void setRecordNotFoundResponse() {
    this.responseCode = WarehouseBaseResponseConstant.ResponseCode.RECORD_NOT_FOUND.value();
    this.responseMessage = WarehouseBaseResponseConstant.ResponseMessage.RECORD_NOT_FOUND.value();
  }
}
