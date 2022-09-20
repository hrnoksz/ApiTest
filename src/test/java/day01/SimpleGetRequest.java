package day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String baseUrl = "http://3.83.123.243:1000/ords/hr";
    String sUrl = "http://3.83.123.243:8000";

    @Test
    public void test1(){

        Response response = RestAssured.get(baseUrl + "/employees");
        System.out.println("response.statusCode() = " + response.statusCode());
        //response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void test2(){

        /*
        Given accept type is json
        when user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                            .when().get(baseUrl + "/regions");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/json");

    }

    @Test
    public void test3(){

        Response response = RestAssured.given().accept(ContentType.XML)
                .when().get(sUrl + "/api/spartans");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/xml");

        response.prettyPrint();
    }

    @Test
    public void test4(){

        /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/regions/2");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }

}
