package pojos;

public class BookingDatesPojo {
    /*
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
                        },
     */

    private String checkin;
    private String checkout;

    //constructor
    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    //getter
    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    //setter

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //toString

    @Override
    public String toString() {
        return "BookingPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
