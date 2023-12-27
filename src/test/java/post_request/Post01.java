package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
/*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post() {
        // url hazirlanir
        spec.pathParam("first", "todos");

        //Beklenen datayi olustur
        String payLoad = "{\"userId\": 55,\n" +
                " \"title\": \"Tidy your room\",\n" +
                " \"completed\": false,\n" +
                " \"id\": 201}";


        //Request gönderip response alinir
        Response response = given(spec).when().body(payLoad).post("{first}");
        response.prettyPrint();

        //Dogrulamalar
        JsonPath json = response.jsonPath();
        assertEquals(55, json.getInt("userId"));
        assertEquals("Tidy your room", json.getString("title"));
        assertEquals(false, json.getBoolean("completed"));
        assertEquals(201, response.statusCode());

    }

    @Test
    public void postMap() {
        //Url olusturulacak
        spec.pathParam("first", "todos");

        //Beklenen datayi olustur
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);
        System.out.println(payload);

        //Request gönder response al
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        //Serialization: java objesini Json objesine dönüstürme islemine denir
        //Serialization: Srializer denen Jackson Databind, Gson, Yasson gibi
        //  dependencyleri pom xml e yükleyerek yapilir

        Map<String , Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.statusCode());
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("title"), actualData.get("title"));
        assertEquals(payload.get("completed"), actualData.get("completed"));

    }
}