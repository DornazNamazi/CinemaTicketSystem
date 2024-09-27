package DataStructures;

import Model.Reservation;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ReservationRequestQueue {
    private Queue<Reservation> reservationRequests;
    public ReservationRequestQueue(){
        this.reservationRequests = new PriorityQueue<>();
    }
    public void enqueueReservation(Reservation reservation){
        reservationRequests.add(reservation);
    }
    public void dequeueReservation(Reservation reservation){
        reservationRequests.remove(reservation);
    }

    public Reservation confirmReservation(){
        reservationRequests.peek().confirmReservation();
        System.out.println("\n\tReservation number " + reservationRequests.peek().getId() + " confirmed successfully\n");
        return reservationRequests.poll().confirmReservation();
    }

    public Reservation rejectReservation(){
        reservationRequests.peek().cancelReservation();
        System.out.println("\n\tReservation number " + reservationRequests.peek().getId() + " cancelled successfully\n");
        return reservationRequests.poll().cancelReservation();
    }
    public Stack<Reservation> confirmAllReservations(){
        int counter = 0;
        Stack<Reservation> confirmedReservations = new Stack<>();
        for (Reservation request : reservationRequests) {
            confirmedReservations.add(reservationRequests.poll().confirmReservation());
            counter++;
        }
        System.out.println("\n\tAll " + counter + " requests confirmed successfully\n");
        return confirmedReservations;
    }
    public Stack<Reservation> rejectAllReservations(){
        int counter = 0;
        Stack<Reservation> confirmedReservations = new Stack<>();
        for (Reservation request : reservationRequests) {
            confirmedReservations.add(reservationRequests.poll().cancelReservation());
            counter++;
        }
        System.out.println("\n\tAll " + counter + " requests cancelled successfully\n");
        return confirmedReservations;
    }

    public boolean displayAll(){
        if(reservationRequests.isEmpty()) {
            System.out.println("\tNo requests have been submitted so far\n");
            return false;
        }else{
            for (Reservation reservation: reservationRequests) {
                reservation.display();
                System.out.println("\n----------------------------------");
            }
        }
        return true;
    }

    public Queue<Reservation> getReservationRequests() {
        return reservationRequests;
    }
}
