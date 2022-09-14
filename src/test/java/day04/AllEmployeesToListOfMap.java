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

public class AllEmployeesToListOfMap {
    
    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/employees")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("Date", notNullValue())
                .extract().response();
        //response.prettyPrint();

        Map<String, Object> jsonMap = response.as(Map.class);

        List<Map<String, Object>> jsonList = (List<Map<String, Object>>) jsonMap.get("items");

        int count = 1;
        for (Map<String, Object> eachMap : jsonList) {
            System.out.println("eachMap" + count + " = " + eachMap);
            count++;
        }
    }
}
