package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class Get04_RequestSpec extends JsonPlaceHolderBaseUrl {
    @Test
    public void get() {
        //i) Url kurulacak
        spec.pathParams("first","todos"
                        , "seconds",23);
        //String url = "https://jsonplaceholder.typicode.com/todos/23";

        //ii) Beklenen data belirlenecek
        //iii) Request gönderilip Response alınacak
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //iv) Doğrulamalar yapılacak
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body( "title",is("et itaque necessitatibus maxime molestiae qui quas velit")) // bu da kullnilir ancak önerilmez
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));
    }
}
