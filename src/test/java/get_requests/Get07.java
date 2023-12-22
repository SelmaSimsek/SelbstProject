package get_requests;

import base_urls.HerOkuAppBaseUrl;
import org.junit.Test;



public class Get07 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/11
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
                        {
                            "firstname": "John",
                            "lastname": "Smith",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */

    @Test
    public void get() {
        spec.pathParams("first", "booking"
        ,"second",11);

        /*
        // body("firstname", equalsTo("John"))
                .body("bookingdates.checkin",equalsTo("2018-01-01"))
                .body("bookingdates.checkout",equalsTo("2019-01-01"));
                */
    }


   /*
    Task 1:
    Given URL:
    https://jsonplaceholder.typicode.com/posts/5

    Test Scenario:

    The user sends a GET request to the above URL.
    The HTTP status code should be 200.
    The response should be in "application/json" format.
    The value of the "title" field should be "nesciunt quas odio."
    The value of the "userId" field should be 1.
    The value of the "body" field should be "repudiandae veniam quaerat sunt sed."

            */



/*
 response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("nesciunt quas odio")
                ,"userId", equalTo(1)
                ,"body",containsString("repudiandae veniam quaerat sunt sed."));
 */




}
