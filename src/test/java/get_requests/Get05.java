package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class Get05 extends JsonPlaceHolderBaseUrl {
 /*
        Given
            https://jsonplaceholder.typicode.com/todos
        And
	        Accept type is "application/json"
        When
	 	    I send a GET request to the Url
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */

    @Test
    public void get() {
       // i) Url kurulacak
        int expectedSize = 200;
        spec.pathParam("first","todos").accept(ContentType.JSON);

       // ii) Beklenen data belirlenecek
       // iii) Request gönderilip Response alınacak
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        //Response response = given(spec).spec(spec).when().get("{first}"); // bu sekilde de kullanilir, ancak gereksiz uzatmadir


       // iv) Doğrulamalar yapılacak
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)

                //.body("[0].title",equalTo("quis eius est sint explicabo")); Json listi olarak dönen bir yapida herhangi bir elemente index kullanarak ulasilir
                //.body("user[1].title")
                .body("title", hasSize(expectedSize)
                ,"title",hasItem("quis eius est sint explicabo")
                ,"userId",hasItems(2,7,9));


        //hasSize() methodu List dönen yapinin boyutunu döndürür
        //hasItems() methodu listte verilen değerin olup olmadığını kontrol eder
        // hasItems() metodu listte verilen çoklu değerlerin olup olmadığını kontrol eder

    }


}
