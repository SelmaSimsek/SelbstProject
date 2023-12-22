package get_requests;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.openqa.selenium.devtools.v115.fetch.model.AuthChallengeResponse;

import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
    1) Manuel testler için Postman kulllanacağız
    2) Otomasyon testleri için Rest Assured Library kulllanacağız
    3) Test caseler yazılırken şu adımları takip edelim:
    i) Önkoşullar iyi anlaşılmalı
    ii) Test case yazılırken Gherkin dili kullanılır:
    Given : Önkoşullar -----> url , body, ....
    When: Aksiyon -----> get(), post() .....
    Then: Doğrulamalar
    And: Çoklu durumları birbirine bağlamakta kullanılır

    4) Otomasyon testi yazarken aşağıdaki adımları izleyebiliriz:
    i) Url kurulacak
    ii) Beklenen data belirlenecek
    iii) Request gönderilip Response alınacak
    iv) Doğrulamalar yapılacak
*/

    public static void main(String[] args) {
        String url = "https://petstore.swagger.io/v2/pet/5";
        //Base url : "https://petstore.swagger.io/v2"
        //1.path parametresi :/pet
        //2.path parametresi : /5

        // ii) Beklenen data belirlenecek.....simdilik atlanacak
        //iii) Request gönderilip Response alınacak
        Response response =  given().when().get(url);
        response.prettyPrint();

        // Status code'a nasil ulasilir / yazdirilir:
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

        // ContenType' a nasil ulasilir
        String contenType = response.contentType();
        System.out.println("contenType = " + contenType);

         //Header degerlerine nasil ulasilir:
       String header = response.header("Server");
        System.out.println("header = " + header);

        String headerDate = response.header("Date");
        System.out.println("headerDate = " + headerDate);

        // Bütün headerlar nasil alinir
        Headers headers = response.headers();
        System.out.println("headers = " + headers);

        // Response süresini cagirma
        Long time = response.time();
        System.out.println("time = " + time);








    }
}
