package com.playground.warehouse.services;

import static org.junit.jupiter.api.Assertions.*;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
class InventoryServiceImplTest {

  /** TODO - Did a initial setup (embedded DB and framework) for test. Testing is in progress */
  @Test
  void getAllArticles() {
    assertTrue(true);
  }
}
