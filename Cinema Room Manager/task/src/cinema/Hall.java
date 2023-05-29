package cinema;

import java.util.Arrays;

import static cinema.CinemaUtils.*;
import static cinema.SeatStatus.SOLD;

public class Hall {
    public final int ROWS_AMOUNT;
    public final int SEATS_PER_ROW_AMOUNT;
    public final int TOTAL_INCOME;
    private final Seat[][] SEATS;
    private int soldSeatsAmount;

    public Hall(int rowsAmount, int seatsPerRowAmount) {
        this.ROWS_AMOUNT = rowsAmount;
        this.SEATS_PER_ROW_AMOUNT = seatsPerRowAmount;
        soldSeatsAmount = 0;
        
        SEATS = new Seat[rowsAmount][seatsPerRowAmount];
        for (int row = 0; row < rowsAmount; row++) {
            for (int seat = 0; seat < seatsPerRowAmount; seat++) {
                SEATS[row][seat] = new Seat(calculateSeatCost(row));
            }
        }
        
        TOTAL_INCOME = Arrays.stream(SEATS)
                .flatMap(Arrays::stream)
                .mapToInt(Seat::getCost)
                .sum();
    }

   private int calculateSeatCost(int row) {
        int seatCost;
        if (ROWS_AMOUNT * SEATS_PER_ROW_AMOUNT <= HALL_SIZE_WITHOUT_DISCOUNT) {
            seatCost = STANDARD_SEAT_COST;
        } else {
            if (row < ROWS_AMOUNT / 2) {
                seatCost = STANDARD_SEAT_COST;
            } else {
                seatCost = DISCOUNTED_SEAT_COST;
            }
        }
        return seatCost;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(System.lineSeparator())
                .append("Cinema:")
                .append(System.lineSeparator())
                .append(" ");
        for (int row = 1; row <= SEATS_PER_ROW_AMOUNT; row++) {
            result.append(" ")
                    .append(row);
        }
        result.append(System.lineSeparator());
        for (int row = 0; row < ROWS_AMOUNT; row++) {
            result.append(row + 1);
            for (int seat = 0; seat < SEATS_PER_ROW_AMOUNT; seat++) {
                result.append(" ")
                        .append(SEATS[row][seat]);
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public int getSeatCost(int row, int seat) {
        return SEATS[row - 1][seat - 1].getCost();
    }

    public void sellTicket(int row, int seat) {
        SEATS[row - 1][seat - 1].status = SOLD;
        soldSeatsAmount++;
    }


    public int getCurrentIncome() {
        return Arrays.stream(SEATS)
                .flatMap(Arrays::stream)
                .filter(seat -> seat.status == SOLD)
                .mapToInt(Seat::getCost)
                .sum();
    }

    public int getSoldSeatsAmount() {
        return soldSeatsAmount;
    }

    public double getSoldSeatsPercent() {
        return (double) getSoldSeatsAmount() / (ROWS_AMOUNT * SEATS_PER_ROW_AMOUNT) * 100;
    }

    public boolean isSeatSold(int row, int seat) {
        return SEATS[row - 1][seat - 1].status == SOLD;
    }

}