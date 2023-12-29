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
    Test Case: Booking olusturma
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
    When
        user send Post request
    Then
        validates Status Code is 200
    And
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Ali",
        "lastname": "Can",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Kahvalti"
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
