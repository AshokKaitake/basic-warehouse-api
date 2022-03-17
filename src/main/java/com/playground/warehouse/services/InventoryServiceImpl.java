package com.playground.warehouse.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.warehouse.dto.InventoryDTO;
import com.playground.warehouse.entities.Article;
import com.playground.warehouse.repository.ArticleRepository;
import com.playground.warehouse.response.BaseResponseEntities;
import com.playground.warehouse.validation.InventoryValidation;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

  @Autowired private ArticleRepository articleRepository;

  /**
   * Get all articles
   *
   * @return ResponseEntity
   */
  @Override
  public ResponseEntity<BaseResponseEntities<Article>> getAllArticles() {

    BaseResponseEntities<Article> getAllArticlesResponse = new BaseResponseEntities<>();
    List<Article> articleList = articleRepository.findAll();

    if (articleList.isEmpty()) {
      log.info("Record not found");
      getAllArticlesResponse.setRecordNotFoundResponse();
      return new ResponseEntity<>(getAllArticlesResponse, HttpStatus.OK);
    }
    getAllArticlesResponse.setRecordFoundResponse(articleList);
    return new ResponseEntity<>(getAllArticlesResponse, HttpStatus.OK);
  }

  /**
   * Import article from inventory
   *
   * @param file
   * @return ResponseEntity with Articles
   */
  @Override
  public ResponseEntity<BaseResponseEntities<Article>> importInventoryFile(MultipartFile file) {
    BaseResponseEntities<Article> importInventoryFileResponse = new BaseResponseEntities<>();
    try {
      if (InventoryValidation.isImportFileInvalid(file)) {
        log.info("Invalid request");
        importInventoryFileResponse.setInvalidRequestParamResponse();
        return new ResponseEntity<>(importInventoryFileResponse, HttpStatus.BAD_REQUEST);
      } else {
        ObjectMapper objectMapper = new ObjectMapper();
        InventoryDTO inventoryDTO = objectMapper.readValue(file.getBytes(), InventoryDTO.class);

        if (inventoryDTO == null || inventoryDTO.getInventory() == null) {

          importInventoryFileResponse.setInvalidRequestParamResponse();
          return new ResponseEntity<>(importInventoryFileResponse, HttpStatus.BAD_REQUEST);
        } else {

          importInventoryFileResponse.setImportFileResponse();
          importInventoryFileResponse.setResponseData(
              articleRepository.saveAll(inventoryDTO.getInventory()));
        }
      }
    } catch (IOException ioException) {
      log.error("Exception in import inventory file {}", ioException.getMessage());
      importInventoryFileResponse.setExceptionResponse("Exception occurred while reading file");
      return new ResponseEntity<>(importInventoryFileResponse, HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<>(importInventoryFileResponse, HttpStatus.OK);
  }

  /**
   * Get article details from articleId
   *
   * @param articleId
   * @return Article
   */
  @Override
  public Article getArticleById(String articleId) {
    return articleRepository.getById(articleId);
  }

  /**
   * Create or Update Article
   *
   * @param article
   * @return Article
   */
  @Override
  public Article createUpdateArticle(Article article) {
    return articleRepository.save(article);
  }

  /**
   * Get stock of article from articleID
   *
   * @param articleId
   * @return stock of article
   */
  @Override
  public Integer getArticleStock(String articleId) {
    return getArticleById(articleId).getStock();
  }

  /**
   * Update article stock after purchase
   *
   * @param articleId
   * @param amountOfPurchase
   */
  @Override
  public void updateArticleStock(String articleId, Integer amountOfPurchase) {
    Article article = getArticleById(articleId);
    int remainingStock = article.getStock() - amountOfPurchase;
    article.setStock(remainingStock);
    createUpdateArticle(article);
  }
}
