package com.playground.warehouse.dto;

import com.playground.warehouse.entities.Article;
import java.util.List;
import lombok.Data;

@Data
public class InventoryDTO {
  List<Article> inventory;
}
