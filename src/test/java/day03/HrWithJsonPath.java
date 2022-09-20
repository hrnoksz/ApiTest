package day03;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrWithJsonPath {

    @BeforeClass
    public void init(){

        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");

        response.prettyPrint();

        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

        //print the third country name
        String thirdCountryName = jsonPath.getString("items[2].country_name");
        System.out.println("thirdCountryName = " + thirdCountryName);

        //print all countries id
        List<String> allIds = jsonPath.getList("items.country_id");
        for (String eachId : allIds) {
            System.out.println("eachId = " + eachId);
        }

        //print all region Ids
       List<Integer> ids = jsonPath.getList("items.region_id");
        for (int eachId : ids) {
            System.out.println("eachId = " + eachId);
        }

        //print all countries name
        List<String> countryNames = jsonPath.getList("items.country_name");
        for (String eachCountryName : countryNames) {
            System.out.println("eachCountryName = " + eachCountryName);
        }
        //print all countries name whose region ids are 4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<String> countryIdFour= jsonPath.getList("items.findAll {it.region_id==4}.country_name");
        System.out.println("countryIdFour = " + countryIdFour);

        //print all countries name whose region id is one
        List<String> countryIdOne = jsonPath.getList("items.findAll {it.region_id==1}.country_name");
        System.out.println("countryIdOne = " + countryIdOne);

        //print all countries name whose region id is two
        List<String> countryIdTwo = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryIdTwo = " + countryIdTwo);

        //print all countries name whose region id is three
        List<String> countryIdThree = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
        System.out.println("countryIdThree = " + countryIdThree);

        //print all countries name whose region id is greater than 2
        List<String> countryIdGreaterThanTwo = jsonPath.getList("items.findAll {it.region_id > 2 }.country_name");
        System.out.println("countryIdGreaterThanTwo = " + countryIdGreaterThanTwo);

    }

    @Test
    public void test2() {

        Response response = get("/employees");

        JsonPath jsonPath = response.jsonPath();

        jsonPath.prettyPrint();

        //Print all employees first name whose job id is IT_PROG
        List<String> jobIdItProg = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("jobIdItProg = " + jobIdItProg);

        //Print all employees first name whose salary is more than 13000
        List<String> empList = jsonPath.getList("items.findAll{it.salary > 13000}.first_name");
        System.out.println("empList = " + empList);

        //print employee's first name whose salary is maximum
        String nameMaxSalary = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("nameMaxSalary = " + nameMaxSalary);

        //print all employees email whose manager id is 114
        List<String> empEmail = jsonPath.getList("items.findAll{it.manager_id==114}.email");
        System.out.println("empEmail = " + empEmail);

        //print all employees first names whose department id is 30
        List<String> empNames = jsonPath.getList("items.findAll{it.department_id==30}.first_name");
        System.out.println("empNames = " + empNames);

    }
}
