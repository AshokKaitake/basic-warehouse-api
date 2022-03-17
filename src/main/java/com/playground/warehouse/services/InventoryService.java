package com.playground.warehouse.services;

import com.playground.warehouse.entities.Article;
import com.playground.warehouse.response.BaseResponseEntities;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface InventoryService {
  ResponseEntity<BaseResponseEntities<Article>> getAllArticles();

  ResponseEntity<BaseResponseEntities<Article>> importInventoryFile(MultipartFile file);

  Article getArticleById(String articleId);

  Article createUpdateArticle(Article article);

  Integer getArticleStock(String articleId);

  void updateArticleStock(String articleId, Integer amountOfPurchase);
}
