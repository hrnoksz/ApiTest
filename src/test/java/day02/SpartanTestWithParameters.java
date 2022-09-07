package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithParameters {

   @BeforeClass
   public void init(){

       baseURI = "http://3.83.123.243:8000";
   }

   @Test
   public void test1(){

       /*
       Given accept type is Json
       And id parameter value is 6
       When user send GET request to /api/spartans/{id}
       Then response status code should be 200
       And response content-type: application/json
       And "Tedmund" should be in response payload/body
        */
      Response response = given().contentType(ContentType.JSON)
              .and().pathParam("id", 6)
              .when().get("/api/spartans/{id}");
      response.prettyPrint();

      assertEquals(response.statusCode(), 200);
      assertEquals(response.contentType(), "application/json");
      assertTrue(response.body().asString().contains("Tedmund"));
   }

}
