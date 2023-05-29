package cinema;

import static cinema.SeatStatus.*;

public class Seat {
    int cost;
    SeatStatus status;

    public Seat(int cost) {
        this.cost = cost;
        this.status = FREE;
    }

    protected int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return status.toString();
    }

}
