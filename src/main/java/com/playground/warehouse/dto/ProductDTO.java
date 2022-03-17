package com.playground.warehouse.dto;

import com.playground.warehouse.entities.Product;
import java.util.List;
import lombok.Data;

@Data
public class ProductDTO {
  List<Product> products;
}
