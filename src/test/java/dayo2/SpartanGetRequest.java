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

    @Test
    public void test2(){

         /*
         Given no headers provided
         When user sends GET request to /api/hello
         Then response status code should be 200
         And content type header should be "text/plain;charset=UTF-8
         And header should contain date
         And Content-Length should be 17
         And Connection should be keep-alive
         And body should be "Hello from Sparta"
          */

        Response response = get(sUrl + "/api/hello");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "text/plain;charset=UTF-8");

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals(response.header("Content-Length"), "17");

        assertEquals(response.header("Connection"), "keep-alive");

        assertEquals(response.body().asString(), "Hello from Sparta");

    }
}
