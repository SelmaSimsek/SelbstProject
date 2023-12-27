package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static test_data.HerOkuAppTestData.*;


public class Get11_NestedMap extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/51
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */
    @Test
    public void get(){
        // Url kurulur:
        spec.pathParams("first","booking"
                ,"second", 51);

        // Beklenen data oluşturulur:
        // Nested yapılarda beklenen data oluşturulurken en iç yapıdan başlanır başlanır.

        Map<String ,String> bookingDateMap = new HashMap<>();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");
        System.out.println("bookingDateMap = " + bookingDateMap);

        Map<String ,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDateMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println("expectedData = " + expectedData);

        // Request gönderip Response alınır:
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Doğrulamalar yapılır:


        // Ödev: body içerisinde Hamcrest ile assertion ları yapın

        //BODY ILE:
        response
                .then()
                .statusCode(200)
                .body("firstname",equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin",equalTo(bookingDateMap.get("checkin")));

        // JSON a CEVIREREK:
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"), json.getString("firstname") );
        assertEquals(expectedData.get("checkin"), json.getString("bookingdates.checkin"));


        // MAP a CEVIREREK
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }

    @Test
    public void get11b(){
        // Url Kurulur:
        spec.pathParams("first","booking"
                ,"second",50) ;

        // Beklenen data kurulur:

        // Nested yapılarda beklenen data en içteki yapıdan başlayarak oluşturulur

        Map<String,String> bookingMap = bookingMapper("2018-01-01","2019-01-01");
        System.out.println("bookingMap = " + bookingMap);

        Map<String,Object> expectedData = herokuAppMapper("Jane","Doe",111,true,bookingMap,"Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        // Request---- Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();




        // Doğrulamalar yapılır:
        response
                .then()
                .statusCode(200)
                .body("firstname", equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin",equalTo(bookingMap.get("checkin")));

        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"),json.getString("firstname"));
        assertEquals(bookingMap.get("checkin"),json.getString("bookingdates.checkin"));

        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin") ,((Map)actualData.get("bookingdates")) .get("checkin"));
        assertEquals(bookingMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
    }


}
