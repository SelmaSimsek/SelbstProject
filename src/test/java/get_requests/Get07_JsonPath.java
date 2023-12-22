package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class Get07_JsonPath extends HerOkuAppBaseUrl {

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
        // i) Url kurulacak
        spec.pathParams("first", "booking"
        ,"second",11);

        //ii) Beklenen data belirlenecek
        //iii) Request gönderilip Response alınacak
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // iv) Doğrulamalar yapılacak


        //1.yol
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Josh")
                ,"lastname", equalTo("Allen")
                ,"totalprice",equalTo( 111)
                ,"depositpaid", equalTo( true))
                .body("bookingdates.checkin",equalTo("2018-01-01")
                ,"bookingdates.checkout",equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));


        // 2. Yol
        // JsonPath :  Response data çeşitini Javada tanımlanan bir data çeşidine çevirip, body içerisindeki istenilen dataya
        //             ulaşabilmemizi sağlar(o datayı kaydedip kullanabilmeyi de sağlar)
       JsonPath json = response.jsonPath();
       // assertEquals(200, response.statusCode());
        //  assertEquals("application/json; charset=utf-8", response.contentType());

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        assertEquals("Josh", json.getString("firstname"));
        assertEquals("Allen", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", json.getString("bookingdates.checkout"));
        assertEquals("midnight snack", json.getString("additionalneeds"));


        // 3. Yol: SoftAssertion:  (TestNg dependency indirmemiz lazım)

        // Soft Assertion 3 adımda yapılır:
        // 1. Adım : Soft Assertion objesi oluşturulur

        // 2. Adım : Assertionlar bu obje ile yapılır

        // 3. Adım : assertAll ile assertionlar bitirilir





    }

}
