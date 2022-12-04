import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Cwiczenia extends TestBase {

    //treści zadań
    //https://github.com/mtadla-simplelogic/RestAssuredIntroduction

    @Test
    public void shouldGetAllToDos() {
        when()
                .get(BASE_URL + "/todos")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetToDoWithId4() {
        Response response =
                given()
                        .queryParam("id", "4")
                        .when()
                        .get(BASE_URL + "/todos/")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].userId").toString(), "1");
    }

    @Test
    public void shouldGetToDosCreatedByUserId1() {
//        Response response =
        given()
                .queryParam("userId", "1")
                .when()
                .get(BASE_URL + "/todos/")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetToDosCreatedByUserId1AndCompleted1() {
        given()
                .queryParam("userId", "1")
                .queryParam("completed", "true")
                .when()
                .get(BASE_URL + "/todos/")
                .then()
                .statusCode(200);
    }

    String body = "{\n" +
            "        \"userId\": 9,\n" +
            "        \"id\": null,\n" +
            "        \"title\": \"I should do the homework\",\n" +
            "        \"completed\": true\n" +
            "    }";

    @Test
    public void shouldCreateToDoWithUserId9AndTitle() {
        Response response =
                given()
                        .body(body)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(BASE_URL + "/todos/")
                        .then()
                        .statusCode(201)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("userId").toString(), "9");
        Assert.assertEquals(jsonPath.get("title").toString(), "I should do the homework");
        Assert.assertEquals(jsonPath.get("completed").toString(), "true");
    }

    @Test
    public void shouldGetDeckowCristUsers() {
        Response response =
                given()
                        .queryParam("company.name", "Deckow-Crist")
                        .when()
                        .get(BASE_URL + "/users/")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].company.name").toString(), "Deckow-Crist");
    }

}
