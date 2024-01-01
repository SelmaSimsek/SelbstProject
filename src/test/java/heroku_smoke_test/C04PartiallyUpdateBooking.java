package heroku_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;

import java.util.Map;

import static heroku_smoke_test.C01CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04PartiallyUpdateBooking extends HerOkuAppBaseUrl {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/:id
   And
       {
   "firstname" : "Naz",
   "lastname" : "Canan",
   "additionalneeds" : "Çay"
}
   When
       Send  patch request
   Then
       Status code is 200
   And
      Body:
{
   "firstname": "Naz",
   "lastname": "Canan",
   "totalprice": 111,
   "depositpaid": true,
   "bookingdates": {
       "checkin": "2018-01-01",
       "checkout": "2019-01-01"
   },
   "additionalneeds": "Çay"
}
    */
    @Test
    public void particalupdateBookingTest() {
        spec.pathParams("first","booking","second",bookingid);
        BookingDatesPojo bookingsDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Naz","Canan",111,
                true,bookingsDates,"Cay");

        Map<String,Object> payLoad = HerOkuAppTestData.herokuAppMapper("Naz",
                "Canan",null,null,null,"Cay");

        Response response=given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingsDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingsDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());





    }
}
