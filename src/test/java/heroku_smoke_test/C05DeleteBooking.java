package heroku_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static heroku_smoke_test.C01CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05DeleteBooking extends HerOkuAppBaseUrl {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/:id
   When
       Send get request
   Then
       Status code is 404
   And
       Body is "Not Found"
    */
    @Test
    public void confirmDeleteTest() {
        spec.pathParams("first","booking","seconds", bookingid);

        String expectedData = "Not Found";

        Response response=given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();
            assertEquals(404,response.statusCode());
            assertEquals(expectedData,actualData);



    }


}
