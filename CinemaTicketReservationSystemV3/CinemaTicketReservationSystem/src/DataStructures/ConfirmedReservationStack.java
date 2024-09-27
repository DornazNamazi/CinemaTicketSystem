package DataStructures;

import Model.Reservation;

import java.util.Stack;

public class ConfirmedReservationStack {
    private Stack<Reservation> confirmedReservations;
    public ConfirmedReservationStack(){
        this.confirmedReservations = new Stack<>();
    }
    public void pushReservation(Reservation reservation){
        this.confirmedReservations.push(reservation);
    }

    public void pushReservation(Stack<Reservation> reservations){
        for (Reservation reservation:reservations) {
            this.confirmedReservations.push(reservation);
        }
    }
    public Reservation popReservation(){
        return this.confirmedReservations.pop();
    }

    public void displayAllSamples(){

        for (Reservation reservation:confirmedReservations) {
            reservation.display();
            System.out.println();
        }
    }

    public Stack<Reservation> getConfirmedReservations() {
        return this.confirmedReservations;
    }
}
