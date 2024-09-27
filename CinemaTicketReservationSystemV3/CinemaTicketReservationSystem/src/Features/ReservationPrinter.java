package Features;

import DataStructures.ConfirmedReservationStack;
import Model.Reservation;
import Model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReservationPrinter {

    private ConfirmedReservationStack confirmedReservationStack;

    public ReservationPrinter(ConfirmedReservationStack confirmedReservationStack) {
        this.confirmedReservationStack = confirmedReservationStack;
    }

    public void printReservationsSummary() {
        Stack<Reservation> reservations = confirmedReservationStack.getConfirmedReservations(); // Assuming this method is implemented

        int totalReservations = reservations.size();
        Map<String, Integer> movieReservationsCount = new HashMap<>();

        for (Reservation reservation : reservations) {
            String movieTitle = reservation.getTicket().getMovie().getTitle();
            movieReservationsCount.put(movieTitle, movieReservationsCount.getOrDefault(movieTitle, 0) + 1);
        }

        System.out.println("\tThere are " + totalReservations + " total reservations for today\n");
        for (Map.Entry<String, Integer> entry : movieReservationsCount.entrySet()) {
            System.out.println("\t" + entry.getValue() + " for " + entry.getKey());
        }
    }
}
