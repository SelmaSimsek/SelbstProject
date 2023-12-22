package get_requests;

import base_urls.HerOkuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static com.google.common.collect.Range.greaterThan;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
     /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
       And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test
    public void get() {
        spec.pathParams("first", "booking")
                .queryParam("firstname","John"
                            ,"lastname","Smith");

        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //1.yol
        response
                .then()
                .statusCode(200)
                .body("bookingid",hasSize(Matchers.greaterThan(0)))
                .body(containsString("booking"));


        //2.yol
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("booking"));


    }
}
