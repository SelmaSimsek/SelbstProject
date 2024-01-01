package heroku_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuRootPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01CreateBooking extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
    "firstname" : "Veli",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Koy Kahvaltısı"
}
     When
        Send post request

     Then
        Status code is 200

     And
        Body:
      {
    "bookingid": 1046,
    "booking": {
        "firstname": "Veli",
        "lastname": "Can",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Koy Kahvaltısı"
    }
}
*/

    public static int bookingid;
    @Test
    public void createBookingTest() {
      spec.pathParam("first","booking");

        BookingDatesPojo bookingsDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo payLoad = new BookingPojo("Ali","Can",111,
                true,bookingsDates,"Kahvalti");

        Response response=given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerOkuRootPojo actualData = response.as(HerOkuRootPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingsDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingsDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        bookingid = response.jsonPath().getInt("bookingid");


    }
}
