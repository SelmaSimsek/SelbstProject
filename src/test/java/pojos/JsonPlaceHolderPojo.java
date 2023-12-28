package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//NOT:
//@JsonIgnoreProperties(ignoreUnknown = true) annotasyonu, JSON verisindeki bilinmeyen alanları yoksaymak için kullanılır.
//Bu annotasyon, Java sınıfındaki alanlarla eşleşmeyen veya bilinmeyen JSON alanlarını görmezden gelir.


public class JsonPlaceHolderPojo {
    /*
    POJO: "Plain Old Java Object"
        : mükemmel bir sablon örnegidir

    4 Adimda olusturulur:
    1) "private" degiskenler tanimlanir
    2) "constructor" lar üretilir.(parametresiz ve paranetreli)
    3) "getter" ve "setter" methodlari olustururlur
    4) "toString" methodu olusturulur

     */
    /*
    {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
           }
     */
   // 1) "private" degiskenler tanimlanir
    private  Integer userId;
    private String title;
    private Boolean completed;

    //2) "constructor" lar üretilir.(parametresiz ve paranetreli)

    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3) "getter" ve "setter" methodlari olustururlur


    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

     //4) "toString" methodu olusturulur

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
