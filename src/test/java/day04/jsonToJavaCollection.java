package day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class jsonToJavaCollection {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("spartan_url");
    }

    @Test
    public void oneSpartanToMap() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 46)
                .when().get("api/spartans/{id}")
                .then().statusCode(200).extract().response();

        response.prettyPrint();

        Map<String, Object> jsonMap = response.as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        System.out.println("jsonMap.get(\"name\") = " + jsonMap.get("name"));

        int actualId = (int) jsonMap.get("id");
        String actualName = (String) jsonMap.get("name");
        String actualGender = (String) jsonMap.get("gender");
        long actualPhone = (long) jsonMap.get("phone");
        System.out.println("actualPhone = " + actualPhone);

        assertThat(actualId, is(46));
        assertThat(actualName, is("Delora"));
        assertThat(actualGender, is("Female"));
        assertThat(actualPhone, is(4115324496l));
    }
}
