package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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

   @Test
   public void negativeTest(){

      /*
      Verify that the number 500 of Spartans doesn't exist
      status code 404
      body contains "Not Found"
       */

      try {
         Response response = given().accept(ContentType.JSON)
                 .and().pathParam("id", 500)
                 .when().get("/api/spartans/{id}");
         response.prettyPrint();

         assertEquals(response.statusCode(), 404);
         assertTrue(response.body().asString().contains("Not Found"));
      }catch (Exception e){
         e.printStackTrace();
      }

   }

   @Test
   public void testWithQueryParam(){

      /*
      Given accept type is Json
      And query parameter values are:
      gender|Female
      nameContains|e
      When user send GET request to /api/spartans/search
      The response status code should be 200
      And response content-type: application/json
      And "Female" should be in response payload
      And "Janette" should be in response payload
       */
      Map<String, Object> queryMap = new HashMap<>();
      queryMap.put("gender", "Female");
      queryMap.put("nameContains", "e");

      Response response = given().accept(ContentType.JSON)
              .and().queryParams(queryMap)
              .when().get("/api/spartans/search");
      response.prettyPrint();

      assertEquals(response.statusCode(), 200);
      assertEquals(response.contentType(), "application/json");
      assertTrue(response.body().asString().contains("Female"));
      assertTrue(response.body().asString().contains("Janette"));

      //assertFalse(response.body().asString().contains("Male"));

   }

}
