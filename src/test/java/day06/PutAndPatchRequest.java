package day06;

import org.testng.annotations.BeforeClass;
import day05_POJO.Spartan;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PutAndPatchRequest {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("spartan_url");
    }
    @Test
    public void putRequest(){
        //Just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "Frank");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 5554111333l);

        given().contentType(ContentType.JSON)
                .body(putRequestMap)
                .and().pathParam("id", 295)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", 295)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().as(Spartan.class);

        assertThat(putRequestMap.get("phone"), is(spartanPosted.getPhone()));


    }

}
