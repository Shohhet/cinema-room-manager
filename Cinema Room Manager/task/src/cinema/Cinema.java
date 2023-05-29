package cinema;


import static cinema.CinemaUtils.*;

public class Cinema {
    Hall hall;

    public Cinema() {
        hall = createHall();
    }

    public static void main(String[] args) {
        // Write your code here
        new Cinema().startSales();
    }

    private Hall createHall() {
        boolean wrongInput = false;
        int rows;
        int seats;
        do {
            System.out.println("Enter the number of rows:");
            rows = SCANNER.nextInt();
            System.out.println("Enter the number of seats in each row:");
            seats = SCANNER.nextInt();
            if (rows < MIN_ROWS_OR_SEATS_AMOUNT || rows > MAX_ROWS_OR_SEATS_AMOUNT ||
                    seats < MIN_ROWS_OR_SEATS_AMOUNT || seats > MAX_ROWS_OR_SEATS_AMOUNT) {
                System.out.println("Wrong input!");
                wrongInput = true;
            }
        } while (wrongInput);

        return new Hall(rows, seats);
    }

    private void startSales() {
        boolean isExit = false;

        do {
            Menu.print();
            int userChoice = SCANNER.nextInt();
            if (userChoice < 0 || userChoice >= Menu.values().length) {
                System.out.println("Wrong input!");
            } else {
                isExit = Menu.values()[userChoice].run(hall);
            }
        } while (!isExit);
    }


}