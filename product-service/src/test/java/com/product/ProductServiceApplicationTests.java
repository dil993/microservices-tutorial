package com.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		String requestBody = """
				"name":"Oneplus 12R",
		   		"description" : "Oneplus 12R with hazleblade camera",
		   		"price": 90000
		   """;

		RestAssured.given()
				.header("Content-Type", "application/json")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/api/product/create")
				.then()
				.statusCode(400)
				.body("id", Matchers.notNullValue());
	}

}
