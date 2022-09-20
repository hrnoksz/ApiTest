package day06;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanPostRequest {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("spartan_url");
    }
    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
        "gender": "Male",
        "name": "Frank",
        "phone": 5554111111
    }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
    */

    @Test
    public void postMethod1(){

        String requestJsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Frank\",\n" +
                "  \"phone\": 5554111111\n" +
                "}";
        Response response = given().accept(ContentType.JSON) //what we are asking from API which is JSON response
                .and().contentType(ContentType.JSON) //What we are sending to API, which is JSON also.
                .body(requestJsonBody) //We use body() method to send our request
                .when().post("api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("data.name"), is("Frank"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(5554111111l));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));
    }

}
