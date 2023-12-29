package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public static Map<String,String> bookingMapper (String checkin, String checkout){
        Map<String,String> map = new HashMap<>();
        map.put("checkin",checkin);
        map.put("checkout",checkout);
        return map;
    }

    public static Map<String,Object> herokuAppMapper( String firstname,String lastname,
                                                      Integer totalprice,Boolean depositpaid,
                                                      Map<String,String> bookingdates,String additionalneeds){
        Map<String,Object> map = new HashMap<>();
        if (firstname!=null){
            map.put("firstname",firstname);
        }
        if (lastname!=null) {
            map.put("lastname", lastname);
        }
        if (lastname!=null) {
            map.put("totalprice", totalprice);
        }
        if (lastname!=null) {
            map.put("depositpaid", depositpaid);
        }
        if (lastname!=null) {
            map.put("bookingdates", bookingdates);
        }
        if (additionalneeds!=null) {
            map.put("additionalneeds", additionalneeds);
        }
        return map;
    }

    /*

     */
}