import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
//    public String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeMethod
    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        //linijka powyżej załątwia mi .log().all()
    }

}
