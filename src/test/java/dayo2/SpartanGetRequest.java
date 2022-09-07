package dayo2;




import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanGetRequest {

    String sUrl = "http://3.83.123.243:8000";

    @Test
    public void test1(){

        /*
        When user send a get request to /api/spartans3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
         */

       Response response = given().accept(ContentType.JSON)
                            .when().get(sUrl + "/api/spartans/3");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Fidole"));

        response.prettyPrint();
    }
}
