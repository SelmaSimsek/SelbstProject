package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.HerOkuAppPojo;
import pojos.HerOkuRootPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends HerOkuAppBaseUrl{
     /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post() {
        spec.pathParam("first","booking");

        BookingPojo bookingDates = new BookingPojo("2021-09-21","2021-12-21");
        HerOkuAppPojo payLoad = new HerOkuAppPojo("Ali","Can",999,
                true,bookingDates,"Breakfast");

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

         HerOkuRootPojo actualData =response.as(HerOkuRootPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());








    }
}
