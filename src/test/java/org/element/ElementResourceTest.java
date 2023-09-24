package org.element;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ElementResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/element")
          .then()
             .statusCode(200)
             .body(is("ELEMENT"));
    }
}