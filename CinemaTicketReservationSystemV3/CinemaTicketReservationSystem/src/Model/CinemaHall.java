package Model;

import DataStructures.ConfirmedReservationStack;
import Enums.ReservationStatus;
import Enums.SeatStatus;

public class CinemaHall {
    private static int hallCounter = 0;
    private int hallNumber;
    private SeatStatus[][] seatLayout;
    private int capacity;
    private int rows;
    public CinemaHall(int capacity, int rows) {
        hallCounter++;
        this.hallNumber = hallCounter;
        this.capacity = capacity;
        this.rows = rows;
        this.seatLayout = new SeatStatus[rows][capacity/rows];
        for (int i = 0; i < seatLayout.length; i++) {
            for (int j = 0; j < seatLayout[i].length; j++) {
                seatLayout[i][j] = SeatStatus.AVAILABLE;
            }
        }
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public SeatStatus[][] getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(SeatStatus[][] seatLayout) {
        this.seatLayout = seatLayout;
    }

    public void setSeat(int row, int col, SeatStatus seatStatus){
        this.seatLayout[row][col] = seatStatus;
    }
    public SeatStatus getSeatStatus(int row, int col){
        return this.seatLayout[row][col];
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRows() {
        return rows;
    }

    public void displaySeatLayout() {
        System.out.println();

        System.out.println("\t//////////////////////////////////////");
        System.out.println("\t///                                ///");
        System.out.println("\t///          SCREEN HERE           ///");
        System.out.println("\t///                                ///");
        System.out.println("\t//////////////////////////////////////\n");

        System.out.print("    ");
        for (int col = 0; col < seatLayout[0].length; col++) {
            System.out.print((col + 1) + "   ");
        }
        System.out.println();


        for (int i = 0; i < seatLayout.length; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < seatLayout[i].length; j++) {
                // Check seat status
                if (seatLayout[i][j] == SeatStatus.BOOKED) {
                    System.out.print("[x] "); // Seat is taken
                } else {
                    System.out.print("[ ] "); // Seat is available
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("\t[ ] = Available\t[x] = Unavailable\n");
    }

    public void randomizeSeatAvailability(ConfirmedReservationStack confirmedReservationStack, Showtime showtime) { // just for populating purposes
        for (int row = 0; row < seatLayout.length; row++) {
            for (int col = 0; col < seatLayout[row].length; col++) {

                double randomNumber = Math.random();
                if (randomNumber < 0.5) {
                    seatLayout[row][col] = SeatStatus.AVAILABLE;
                } else {
                    seatLayout[row][col] = SeatStatus.BOOKED;
                    Ticket temp = new Ticket(showtime.getMovie(), showtime, new int[]{row, col}, 9.99f );
                    confirmedReservationStack.pushReservation(new Reservation(temp, ReservationStatus.CONFIRMED));
                }
            }
        }
    }

}
