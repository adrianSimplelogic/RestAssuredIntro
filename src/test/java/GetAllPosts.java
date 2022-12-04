import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllPosts extends TestBase{

    @Test
    public void shouldGetAllPosts() {
        RestAssured.when()
                .get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetAllUsers() {
        RestAssured.when()
                .get(BASE_URL+"/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFirstUser() {
        RestAssured.when()
                .get(BASE_URL+"/users/1")
                .then()
                .statusCode(200);
//                .log() - to już niepotrzebne, patrz w TestBase
//                .all();
    }

    @Test
    public void shouldGetFirstUserAndValidateJson() {

        Response response =

                RestAssured.given()
                        .log()
                        .all()
                        .when()
                        .get(BASE_URL+"/users/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("address.geo.lat"),"-37.3159");
    }

    @Test
    public void shouldGetFirstUserWithPAthVar(){
        RestAssured.given()
                .pathParam("userId","1")
                .when()
                .get(BASE_URL+"/users"+"/{userId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetUserWithId4(){
        Response response =
        RestAssured.given()
                .queryParam("id","5")
                .queryParam("username","Kamren")
                .when()
                .get(BASE_URL+"/users")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].id").toString(),"5");
        //powyżej jest [0].id bo najpierw muszę się dodstać do tablicy która zawiera jeden obiekt z indexem 0
    }

}
