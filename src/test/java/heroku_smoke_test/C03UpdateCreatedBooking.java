package heroku_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static heroku_smoke_test.C01CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C03UpdateCreatedBooking extends HerOkuAppBaseUrl {
    /*
   Test Case: Booking güncelleme
   Given
       https://restful-booker.herokuapp.com/booking/id
   And
       "firstname": "Ali",
       "lastname": "Canan",
       "totalprice": 111,
       "depositpaid": true,
       "bookingdates": {
           "checkin": "2018-01-01",
           "checkout": "2019-01-01"
       },
       "additionalneeds": ""
   When
       user send patch request
   Then
       validates Status Code is 200
   And
      Body
    {
       "firstname": "Nazar",
       "lastname": "Can",
       "totalprice": 111,
       "depositpaid": true,
       "bookingdates": {
           "checkin": "2018-01-01",
           "checkout": "2019-01-01"
       },
       "additionalneeds": "kahve"
   }
}
    */
    @Test
    public void updateBookingTest() {
        spec.pathParams("first","booking","second",bookingid);

        BookingDatesPojo bookingsDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo payLoad = new BookingPojo("Nazar","Han",111,
                true,bookingsDates,"kahve");

        Response response=given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingsDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingsDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getAdditionalneeds());

    }




}
