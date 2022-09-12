package day04;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HamcrestMatcherApiTest {

    /*
    Given accept type is Json
    And path param id is 15
    When user send GET request to api/spartans/{id}
    Then status code is 200
    And response content-type: application/json
    And json data is following;

    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
     */

    @Test
    public void spartanWithHamcrest() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get(ConfigurationReader.getProperty("spartan_url") + "/api/spartans/{id}");
        response.prettyPrint();

    }
}
