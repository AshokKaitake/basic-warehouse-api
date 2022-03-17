package com.playground.warehouse.services;

import com.playground.warehouse.entities.Product;
import com.playground.warehouse.response.BaseResponseEntities;
import com.playground.warehouse.response.BaseResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  ResponseEntity<BaseResponseEntities<Product>> getAvailableProductWithQuantity();

  ResponseEntity<BaseResponseEntity<Product>> sellProductByProductName(String productName);

  ResponseEntity<BaseResponseEntities<Product>> importProductFile(MultipartFile file);

  ResponseEntity<BaseResponseEntities<Product>> getAllProducts();
}
