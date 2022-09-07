package day02;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("spartan_url");
    }

    @Test
    public void getOneSpartan(){
        /*
       Given accept type is Json
       And path param id is 10
       When user send GET request to /api/spartans/{id}
       Then status code should be 200
       And response content-type: application/json
       And response payload values match the foll0wing:
                "id": 10,
                "name": "Lorenza",
                "gender": "Female",
                "phone": 3312820936
         */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        response.prettyPrint();

        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"gender\") = " + response.path("gender"));

        int actualId = response.path("id");
        String actualName = response.path("name");
        String actualGender = response.path("gender");
        long actualPhone = response.path("phone");

        assertEquals(actualId, 10);
        assertEquals(actualName, "Lorenza");
        assertEquals(actualGender, "Female");
        assertEquals(actualPhone, 3312820936l);


    }
}
