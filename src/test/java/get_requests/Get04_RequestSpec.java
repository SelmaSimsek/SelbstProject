package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
    }
}
