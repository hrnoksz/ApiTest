package day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String baseUrl = "http://3.83.123.243:1000/ords/hr";

    @Test
    public void test1(){

        Response response = RestAssured.get(baseUrl + "/employees");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }

}
