package com.playground.warehouse.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.playground.warehouse.constant.WarehouseBaseResponseConstant;
import java.util.List;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseResponseEntities<T> {
  protected Integer responseCode;

  protected String responseMessage;

  private List<T> responseData;

  public void setInvalidRequestParamResponse() {
    this.responseCode =
        WarehouseBaseResponseConstant.ResponseCode.INVALID_REQUEST_PARAMETERS.value();
    this.responseMessage =
        WarehouseBaseResponseConstant.ResponseMessage.INVALID_REQUEST_PARAMETERS.value();
  }

  public void setImportFileResponse() {
    this.responseCode = WarehouseBaseResponseConstant.ResponseCode.RECORD_IMPORTED.value();
    this.responseMessage = WarehouseBaseResponseConstant.ResponseMessage.RECORD_IMPORTED.value();
  }

  public void setRecordFoundResponse(List<T> responsObj) {
    this.responseCode = WarehouseBaseResponseConstant.ResponseCode.RECORD_FOUND.value();
    this.responseMessage = WarehouseBaseResponseConstant.ResponseMessage.RECORD_FOUND.value();
    this.responseData = responsObj;
  }

  public void setRecordNotFoundResponse() {
    this.responseCode = WarehouseBaseResponseConstant.ResponseCode.RECORD_NOT_FOUND.value();
    this.responseMessage = WarehouseBaseResponseConstant.ResponseMessage.RECORD_NOT_FOUND.value();
  }

  public void setExceptionResponse(String message) {
    this.responseCode = WarehouseBaseResponseConstant.ResponseCode.EXCEPTION.value();
    this.responseMessage = message;
  }
}
