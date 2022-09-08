package day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrWithPath {

    @BeforeClass
    public void init(){

        baseURI = ConfigurationReader.getProperty("hr_url");
    }
    @Test
    public void getCountries(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");
        response.prettyPrint();

        assertEquals(response.statusCode(), 200);

        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print count value
        System.out.println("response.path(\"count\") = " + response.path("count"));

        //print first country name
        String firstCountryName = response.path("items[0].country_name");
        System.out.println("firstCountryName = " + firstCountryName);

        //print all country names
        List<String> allNames = response.path("items.country_name");

        for (String eachName : allNames) {
            System.out.println("eachName = " + eachName);
        }

        //or
        System.out.println("response.path(\"items.country_name\") = " + response.path("items.country_name"));

        //print first country_id
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print all country Ids
        List<String> allCountryIds = response.path("items.country_id");
        for (String eachCountryId : allCountryIds) {
            System.out.println("eachCountryId = " + eachCountryId);
        }
        // verify that all region id is 3

        List<Integer> allIds = response.path("items.region_id");
        for (int eachId : allIds) {
            assertEquals(eachId, 3);
        }

        //prints first country href
        String firstHref = response.path("items[0].links[0].href");
        System.out.println("firstHref = " + firstHref);


    }

}
