package heroku_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static heroku_smoke_test.C01CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06GetDeletedBooking extends HerOkuAppBaseUrl {
        /*
    Test Case: Booking okuma
    Given
        https://restful-booker.herokuapp.com/booking/id
    When
        user send get request
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

    @Test
    public void getCreatedBookingTest() {
        spec.pathParams("first", "booking","second",bookingid);

        BookingDatesPojo bookingsDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 111,
                true, bookingsDates, "Kahvalti");

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


    }
}