package cinema;

import static cinema.CinemaUtils.FREE_SEAT_SIGN;
import static cinema.CinemaUtils.SOLD_SEAT_SIGN;

public enum SeatStatus {
    FREE(FREE_SEAT_SIGN),
    SOLD(SOLD_SEAT_SIGN);
    final String sign;
     SeatStatus(String sign) {
        this.sign = sign;
    }
     public String toString() {
        return sign;
    }
}
