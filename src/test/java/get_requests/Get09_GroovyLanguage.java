package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09_GroovyLanguage extends JsonPlaceHolderBaseUrl {
     /*
      Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
   */

    @Test
    public void get() {
        // i) Url kurulacak
        spec.pathParams("first", "todos");

        //ii) Beklenen data belirlenecek
        //iii) Request gönderilip Response alınacak
        Response response = given(spec).when().get("{first}");

        // iv) Doğrulamalar yapılacak
        JsonPath json = response.jsonPath();
        response
                .then()
                .statusCode(200);
        // Print all ids greater than 190 on the console

        // 1.yol; Loop kullanarak
        List<Integer> idsGraderThan190 = new ArrayList<>();
        List<Integer> idList= json.getList("id");
        //System.out.println("list = " + idList);
        for (Integer w : idList) {
            if (w>190){
                idsGraderThan190.add(w);
            }
        }
        System.out.println(idsGraderThan190);

        //2.yol

       List<Integer> idsGrater = json.getList("findAll{it.id>190}");
        System.out.println("idsGrater = " + idsGrater);

        //System.out.println(json.getList("findAll{it.id>190}.userId"));
        // System.out.println(json.getList("findAll{it.id>190}.title"));
        // System.out.println(json.getList("findAll{it.title=='tempore ut sint quis recusandae'}.id"));

        // Assert that there are 10 ids greater than 190
        assertEquals(10, idsGrater.size());

       // Print all userIds whose ids are less than 5 on the console
        List<Integer> idsLessThanFive = json.getList("findAll{it.id<5}.userId");
        System.out.println("idsGrater = " + idsLessThanFive);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4, idsLessThanFive.size());

        //Print all titles whose ids are less than 5
        List<String> titelsLessThanFive = json.getList("findAll{it.id<5}.title");
        System.out.println("titelsLessThanFive = " + titelsLessThanFive);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titelsLessThanFive.contains("delectus aut autem"));


    }
    }

