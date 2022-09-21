package utilities;


import com.github.javafaker.Faker;
import day05_POJO.Spartan;

import java.util.HashMap;
import java.util.Map;

public class SpartanUtils {

    public static void main(String[] args) {

    }

    public static Map<String, Object> getSpartan(String name, String gender, long phone){
        Faker faker = new Faker();
        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("name", faker.name());
        requestJsonMap.put("gender", faker);



    return requestJsonMap;
    }
}
