package day04;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class OneEmployeeToMapClass {

    @BeforeClass
    public void init(){

        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 104)
                .when().get("/employees/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("Date", notNullValue())
                .and().header("Transfer-Encoding", "chunked")
                .extract().response();
        response.prettyPrint();

        Map<String, Object> jsonMap = response.as(Map.class);

        int id = (int) jsonMap.get("employee_id");
        String firstName = (String) jsonMap.get("first_name");
        String href = response.path("links[0].href");

        assertThat(id, is(104));
        assertThat(firstName, is("Bruce"));
        assertThat(href, is("http://3.83.123.243:1000/ords/hr/employees/104"));
    }
}
