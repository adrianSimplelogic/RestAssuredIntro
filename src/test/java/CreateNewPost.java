import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateNewPost extends TestBase {

    String body = "{\n" +
            "    \"name\": \"Adrian Szymański\",\n" +
            "    \"username\": \"Adrian2000\",\n" +
            "    \"email\": \"adrian.szymanski@mail.com\",\n" +
            "    \"address\": {\n" +
            "        \"street\": \"Królewska\",\n" +
            "        \"suite\": \"500\",\n" +
            "        \"city\": \"Gotham\",\n" +
            "        \"zipcode\": \"92998-3874\",\n" +
            "        \"geo\": {\n" +
            "            \"lat\": \"-37.3159\",\n" +
            "            \"lng\": \"81.1496\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"phone\": \"0800900100\",\n" +
            "    \"website\": \"otowbloto.com\",\n" +
            "    \"company\": {\n" +
            "        \"name\": \"Simplelogic\",\n" +
            "        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "        \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "}";

    @Test
    public void shouldCreateNewUser(){

        Response response =

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/users")
                .then()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("username"),"Adrian2000");
    }



}
