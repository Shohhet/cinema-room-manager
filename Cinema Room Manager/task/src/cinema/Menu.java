package cinema;

import static cinema.CinemaUtils.MIN_ROWS_OR_SEATS_AMOUNT;
import static cinema.CinemaUtils.SCANNER;

public enum Menu {
    EXIT("Exit"),
    SHOW("Show the seats") {
        @Override
        public boolean run(Hall hall) {
            System.out.println(hall);
            return false;
        }
    },
    BUY("Buy a ticket") {
        @Override
        public boolean run(Hall hall) {
            boolean wrongInput;
            do {
                wrongInput = false;
                System.out.println();
                System.out.println("Enter a row number:");
                int row = SCANNER.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seat = SCANNER.nextInt();
                if (row < MIN_ROWS_OR_SEATS_AMOUNT || row > hall.ROWS_AMOUNT ||
                        seat < MIN_ROWS_OR_SEATS_AMOUNT || seat > hall.SEATS_PER_ROW_AMOUNT) {
                    System.out.println("Wrong input!");
                    wrongInput = true;
                } else if (hall.isSeatSold(row, seat)) {
                    System.out.println("That ticket has already been purchased!");
                    wrongInput = true;
                } else {
                    hall.sellTicket(row, seat);
                    System.out.printf("Ticket price: $%d%n", hall.getSeatCost(row, seat));
                }
            } while (wrongInput);
            return false;
        }
    },
    STAT("Statistics") {
        @Override
        public boolean run(Hall hall) {
            System.out.printf("Number of purchased tickets: %d%n", hall.getSoldSeatsAmount());
            System.out.printf("Percentage: %.2f%%%n", hall.getSoldSeatsPercent());
            System.out.printf("Current income: $%d%n", hall.getCurrentIncome());
            System.out.printf("Total income: $%d%n", hall.TOTAL_INCOME);
            return false;
        }

    };
    private final String optionName;

    Menu(String name) {
        optionName = name;
    }

    boolean run(Hall hall) {
        return true;
    }

    public String toString() {
        return optionName;
    }

    public static void print() {
        System.out.println();
        System.out.printf("1. %s%n", SHOW);
        System.out.printf("2. %s%n", BUY);
        System.out.printf("3. %s%n", STAT);
        System.out.printf("0. %s%n", EXIT);
    }


}
