package day05_POJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class RegionsWithPojo {

    @BeforeClass
    public void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }
    @Test
    public void regionsWithPojo(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .extract().response();

        Regions regions = response.as(Regions.class);
        assertThat(regions.getCount(), is(4));

        List<Item> items = regions.getItems();
        assertThat(items.get(0).getRegion_name(), equalTo("Europe"));

        assertThat(items.get(3).getRegion_id(), is(4));

        List<Link> links = regions.getLinks();
        assertThat(links.get(0).href, is("http://3.83.123.243:1000/ords/hr/regions/"));

    }

    @Test
    public void test1(){

        Regions regions = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200).extract().jsonPath().getObject("", Regions.class);

        for (Item item : regions.getItems()) {
            System.out.println("item.getRegion_id() = " + item.getRegion_id());
            System.out.println("item.getRegion_name() = " + item.getRegion_name());
        }

        assertThat(regions.getCount(), is(4));

        assertThat(regions.getItems().get(0).getRegion_name(), is("Europe"));

        assertThat(regions.getLinks().get(0).getHref(), is("http://3.83.123.243:1000/ords/hr/regions/"));

        System.out.println("regions.getItems().get(0).getLinks().get(0).getHref() = " +
                "" + regions.getItems().get(0).getLinks().get(0).getHref());

    }
}
