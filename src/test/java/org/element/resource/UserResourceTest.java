package org.element.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.element.model.User;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
class UserResourceTest {

    @Test
    void get() {
        given()
                .when().get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(1),
                        "[0].name", is("admin"));
    }

    /*
    @Test
    void create() {
        given()
                .body("{\"name\":\"test\",\"password\":\"test\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/users")
                .then()
                .body("name", is("test"),
                        "created", not(emptyString())
                );
    }

    @Test
    void createDuplicate() {
        given()
                .body("{\"name\":\"user\",\"password\":\"test\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/users")
                .then()
                .statusCode(409);
    }


    @Test
    void update() {
        var user = given()
                .body("{\"name\":\"to-update\",\"password\":\"test\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/users")
                .as(User.class);

        user.name = "updated";
        given()
                .body(user)
                .contentType(ContentType.JSON)
                .when().put("/api/v1/users/" + user.id)
                .then()
                .statusCode(200)
                .body(
                        "name", is("updated"),
                        "version", is(user.version + 1)
                );
    }
    */
}