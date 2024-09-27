package Model;

import Enums.ReservationStatus;

public class Reservation implements Comparable {
    private static int counter = 0;
    private int id;
    private Ticket ticket;
    private ReservationStatus reservationStatus;

    public Reservation(Ticket ticket, ReservationStatus reservationStatus) {
        this.id = counter;
        this.ticket = ticket;
        this.reservationStatus = reservationStatus;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Reservation.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void display(){
        System.out.println("\tReservation number: " + this.getId());
        System.out.println("\tStatus: " + this.getReservationStatus());
        System.out.println("\tMovie: " + this.getTicket().getMovie().getTitle());
        System.out.println("\tTime: " + this.getTicket().getShowtime().getStartTime());
        System.out.println("\tHall: " + this.getTicket().getShowtime().getCinemaHall().getHallNumber());
        System.out.println("\tAmount payed: $" + this.getTicket().getPrice());
    }
    public Reservation confirmReservation(){
        setReservationStatus(ReservationStatus.CONFIRMED);
        return this;
    }
    public Reservation cancelReservation(){
        setReservationStatus(ReservationStatus.CANCELLED);
        return this;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
