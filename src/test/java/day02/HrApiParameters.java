package day02;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrApiParameters {

    @BeforeClass
    public void init(){

        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1(){
        /*
       Given accept type is Json
       And parameters: q= "region_id":2
       When user send GET request to /countries
       Then status code is 200
       And response content-type is application/json
       And payload should contain "United States of America"
         */

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        //response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("United States of America"));

    }
}
