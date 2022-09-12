package day04;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

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

       given()
               .accept(ContentType.JSON)
               .and().pathParam("id", 15)
       .when()
               .get(ConfigurationReader.getProperty("spartan_url") + "/api/spartans/{id}")
       .then().statusCode(200)
               .and().contentType("application/json")
               .and().assertThat()
               .body("id", is(15),
                       "name", is("Meta"),
                       "gender", is("Female"),
                       "phone", is(1938695106));
    }

    @Test
    public void test2() {
        given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 15)
        .when()
                .get(ConfigurationReader.getProperty("spartan_url") + "/api/spartans/{id}")
        .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat()
                .body("id", equalTo(15),
                        "name", equalTo("Meta"),
                        "gender", equalTo("Female"),
                        "phone", equalTo(1938695106));

    }

    @Test
    public void test3() {

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 100).log().all()
        .when()
                .get(ConfigurationReader.getProperty("hr_url") + "/employees/{id}")
        .then()
                .statusCode(200).and()
                .contentType("application/json").and()
                .header("Date", notNullValue())
                .header("Transfer-Encoding", is("chunked"))
                .and().assertThat()
                .body("employee_id", is(100),
                        "first_name", is("Steven"),
                        "last_name", is("King"),
                        "links[0].href", is("http://3.83.123.243:1000/ords/hr/employees/100"))
                .log().all();
    }

    @Test
    public void test4() {

        given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .and().log().all()
        .when()
                .get(ConfigurationReader.getProperty("hr_url") + "/employees")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .header("Transfer-Encoding", is("chunked"))
                .and().assertThat()
                .body("items[0].first_name", is("Alexander"),
                        "items[0].salary", is(9000),
                        "items[0].links[0].href", is("http://3.83.123.243:1000/ords/hr/employees/103"),
                        "items[1].employee_id", is(104),
                        "items.first_name", hasItems("Alexander", "Bruce", "David", "Valli", "Diana"))
                .and().log().all();



    }
}
