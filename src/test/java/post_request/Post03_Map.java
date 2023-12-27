package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerOkuAppTestData.*;

public class Post03_Map extends HerOkuAppBaseUrl {
     /*
Given
1) https://restful-booker.herokuapp.com/booking
2) {
    "firstname": "John",
    "lastname": "Doe",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
     }
  }
When
I send POST Request to the Url
Then
Status code is 200
And response body should be like {
                                   "bookingid": 5315,
                                   "booking": {
                                       "firstname": "John",
                                       "lastname": "Doe",
                                       "totalprice": 11111,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-09",
                                           "checkout": "2021-09-21"
                                       }
                                   }
                                }
*/

    @Test
    public void post() {
        //Url Kurulur
        spec.pathParam("first","bookinng");

        //Beklenen Data ousturulur:
        Map<String, String> bookingMap = HerOkuAppTestData.bookingMapper("2021-09-09","2021-09-21");

        Map<String,Object> payload = HerOkuAppTestData.herokuAppMapper("John","Doe"
                ,11111,true,bookingMap,null);

        //Request----Respons
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        //Dogrulamalar yapilir
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(payload.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(payload.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(payload.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(payload.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingMap.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingMap.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

        JsonPath json = response.jsonPath();
        assertEquals(bookingMap.get("checkin"), json.getString("booking.bookingdates.checkin"));
        assertEquals(bookingMap.get("checkout"), json.getString("booking.bookingdates.checkout"));







    }



}
