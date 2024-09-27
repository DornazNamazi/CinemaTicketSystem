package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import DataStructures.ConfirmedReservationStack;
import Enums.ReservationStatus;
import Model.Reservation;
import Model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataStructures.ConfirmedReservationStack;
import Model.Reservation;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfirmedReservationStackTest {
    private ConfirmedReservationStack reservationStack;

    @BeforeEach
    public void setUp() {
        reservationStack = new ConfirmedReservationStack();
    }

    @Test
    public void testPushReservation() {
        Ticket ticket = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Reservation reservation = new Reservation(ticket,ReservationStatus.CONFIRMED);
        reservationStack.pushReservation(reservation);
        Stack<Reservation> confirmedReservations = reservationStack.getConfirmedReservations();
        assertEquals(1, confirmedReservations.size());
        assertEquals(reservation, confirmedReservations.peek());
    }

    @Test
    public void testPushReservation_Stack() {
        Ticket ticket1 = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Ticket ticket2 = new Ticket(null, null, new int[]{3, 4}, 12.0f);

        Reservation reservation1 = new Reservation(ticket1, ReservationStatus.CONFIRMED);
        Reservation reservation2 = new Reservation(ticket2, ReservationStatus.CONFIRMED);

        Stack<Reservation> reservations = new Stack<>();
        reservations.push(reservation1);
        reservations.push(reservation2);

        reservationStack.pushReservation(reservations);
        Stack<Reservation> confirmedReservations = reservationStack.getConfirmedReservations();
        assertEquals(2, confirmedReservations.size());
        assertEquals("Bob", confirmedReservations.pop().getClass());
        assertEquals("Alice", confirmedReservations.pop().getClass());
    }

    @Test
    public void testPopReservation() {
        Ticket ticket1 = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Ticket ticket2 = new Ticket(null, null, new int[]{3, 4}, 12.0f);

        Reservation reservation1 = new Reservation(ticket1, ReservationStatus.CONFIRMED);
        Reservation reservation2 = new Reservation(ticket2, ReservationStatus.CONFIRMED);

        reservationStack.pushReservation(reservation1);
        reservationStack.pushReservation(reservation2);

        Reservation poppedReservation = reservationStack.popReservation();
        assertEquals(reservation2, poppedReservation);
        assertEquals(1, reservationStack.getConfirmedReservations().size());
    }
}