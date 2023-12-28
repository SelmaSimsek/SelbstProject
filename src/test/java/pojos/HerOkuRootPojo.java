package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerOkuRootPojo {
    /*
    "bookingid": 16,
 	"booking" :{
     */
    private Integer bookingId;
    private HerOkuAppPojo booking;

    public HerOkuRootPojo() {
    }

    public HerOkuRootPojo(Integer bookingId, HerOkuAppPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public HerOkuAppPojo getBooking() {
        return booking;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void setBooking(HerOkuAppPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerOkuRootPojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
