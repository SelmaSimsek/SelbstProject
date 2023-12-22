package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework01 extends JsonPlaceHolderBaseUrl {
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

    @Test
    public void get() {
        // i) Url kurulacak
        spec.pathParams("first", "posts", "second",5);

        //ii) Beklenen data belirlenecek
        //iii) Request gönderilip Response alınacak
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // iv) Doğrulamalar yapılacak
        assertEquals(200,response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        assertTrue(response.body().asString().contains("nesciunt quas odio"));
        assertTrue(response.body().asString().contains("1"));
        assertTrue(response.body().asString().contains("repudiandae veniam quaerat sunt sed"));
    }


}
