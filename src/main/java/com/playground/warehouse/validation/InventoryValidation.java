package com.playground.warehouse.validation;

import org.springframework.web.multipart.MultipartFile;

public class InventoryValidation {
  private InventoryValidation() {
    throw new IllegalStateException("InventoryValidation class");
  }

  public static boolean isImportFileInvalid(MultipartFile file) {
    return (file.isEmpty() || (!file.getContentType().equalsIgnoreCase("application/json")));
  }
}
