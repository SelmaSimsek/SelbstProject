package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonIlkelSablon extends JsonPlaceHolderBaseUrl {
    /*
        Given
           https://jsonplaceholder.typicode.com/todos
           {
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
        // url kurulacak
        spec.pathParam("first", "todos");

        // beklenen data olusturulur
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(10,"Tidy your room", false);

        //Request ------Response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Dogrulamalar yapilir
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getUserId(), actualData.getUserId());
        assertEquals(payLoad.getTitle(), actualData.getTitle());
        assertEquals(payLoad.getCompleted(), actualData.getCompleted());


    }
}
