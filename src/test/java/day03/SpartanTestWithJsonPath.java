package day03;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {

    @BeforeClass
    public void init(){

        baseURI = ConfigurationReader.getProperty("spartan_url");
    }
    @Test
    public void test1(){
        /*
        Given accept type json
        And path param spartan id is 10
        when user sends a get request to /api/spartans/{id}
        Then status code is 200
        And content type is Json
        "id": 10,
        "name": "Lorenza",
        "gender": "Female",
        "phone": 3312820936
         */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        //verify id and name
        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id, 10);
        assertEquals(name, "Lorenza");

        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        System.out.println("idJson = " + idJson);

        String nameJson = jsonPath.getString("name");
        System.out.println("nameJson = " + nameJson);

        String genderJson = jsonPath.getString("gender");
        System.out.println("genderJson = " + genderJson);

        long phoneJson = jsonPath.getLong("phone");
        System.out.println("phoneJson = " + phoneJson);

        //verify all information
        assertEquals(idJson, 10);
        assertEquals(nameJson, "Lorenza");
        assertEquals(genderJson, "Female");
        assertEquals(phoneJson, 3312820936l);
    }


}
