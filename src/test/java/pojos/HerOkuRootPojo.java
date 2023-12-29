package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerOkuRootPojo {
    /*
    "bookingid": 16,
 	"booking" :{
     */
    private Integer bookingid;
    private BookingPojo booking;

    public HerOkuRootPojo() {
    }

    public HerOkuRootPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBookingid(Integer bookingId) {
        this.bookingid = bookingId;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerOkuRootPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
