package day05_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanWithPojo {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("spartan_url");
    }

    @Test
    public void spartanPojo() {

        Response response = given()
                                .accept(ContentType.JSON)
                                .pathParam("id", 63)
                            .when()
                                .get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        //Deserialize --> JSON to POJO (java custom class)
        // 2 different ways
        // 1. using as() method
        //we use as() method to convert Json response to spartan object!!!!!!!!!!!!!!
        //as() method is coming from jackson library (converting JSON to Java class)
        Spartan spartan63 = response.body().as(Spartan.class);
        System.out.println("spartan63.getId() = " + spartan63.getId());
        System.out.println("spartan63.getName() = " + spartan63.getName());
        System.out.println("spartan63.getGender() = " + spartan63.getGender());
        System.out.println("spartan63.getPhone() = " + spartan63.getPhone());

        assertEquals(spartan63.getId(), 63);
        assertEquals(spartan63.getName(), "Clayton");
        assertEquals(spartan63.getGender(), "Male");
        assertEquals(spartan63.getPhone(), 1782167106);

        //second way of deserialize JSON to java
        //2. using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s63 = jsonPath.getObject("", Spartan.class); //Magical line!!!!!!!!!!!!!!!
        System.out.println(s63);
        System.out.println("s63.getName() = " + s63.getName());
        System.out.println("s63.getPhone() = " + s63.getPhone());

    }

    @Test
    public void gsonExample() {

        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 63,\n" +
                "    \"name\": \"Clayton\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 1782167106\n" +
                "}";
        //using gson method to do deserialize our json body
        Spartan spartanClayton = gson.fromJson(myJsonBody, Spartan.class);
        System.out.println("spartanClayton.toString() = "
                + spartanClayton.toString());

        //serialization Java object --> JSON body

        Spartan spartan = new Spartan(101, "Mike",
                "Male", 321342123);
        //Converting custom class to json body (serialization)
        String jsonBody = gson.toJson(spartan);

        System.out.println("jsonBody = " + jsonBody);
    }
}
