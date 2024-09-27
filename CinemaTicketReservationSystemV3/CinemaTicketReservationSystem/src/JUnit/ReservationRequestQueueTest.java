package JUnit;

import Enums.ReservationStatus;
import Model.Reservation;
import DataStructures.ReservationRequestQueue;
import Model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Queue;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRequestQueueTest {
    private ReservationRequestQueue reservationQueue;

    @BeforeEach
    public void setUp() {
        reservationQueue = new ReservationRequestQueue();
    }

    @Test
    public void testEnqueueReservation() {
        Ticket ticket = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Reservation reservation = new Reservation(ticket, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation);
        Queue<Reservation> reservationRequests = reservationQueue.getReservationRequests();
        assertEquals(1, reservationRequests.size());
        assertTrue(reservationRequests.contains(reservation));
    }

    @Test
    public void testDequeueReservation() {
        Ticket ticket = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Reservation reservation = new Reservation(ticket, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation);
        reservationQueue.dequeueReservation(reservation);
        Queue<Reservation> reservationRequests = reservationQueue.getReservationRequests();
        assertEquals(0, reservationRequests.size());
        assertFalse(reservationRequests.contains(reservation));
    }

    @Test
    public void testConfirmReservation() {
        Ticket ticket = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Reservation reservation = new Reservation(ticket, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation);
        Reservation confirmedReservation = reservationQueue.confirmReservation();
        assertNotNull(confirmedReservation);
        assertEquals("CONFIRMED", confirmedReservation.getReservationStatus().toString());
    }

    @Test
    public void testRejectReservation() {
        Ticket ticket = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Reservation reservation = new Reservation(ticket, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation);
        Reservation rejectedReservation = reservationQueue.rejectReservation();
        assertNotNull(rejectedReservation);
        assertEquals("CANCELLED", rejectedReservation.getReservationStatus().toString());
    }

    @Test
    public void testConfirmAllReservations() {
        Ticket ticket1 = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Ticket ticket2 = new Ticket(null, null, new int[]{3, 4}, 12.0f);
        Reservation reservation1 = new Reservation(ticket1, ReservationStatus.CONFIRMED);
        Reservation reservation2 = new Reservation(ticket2, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation1);
        reservationQueue.enqueueReservation(reservation2);
        Stack<Reservation> confirmedReservations = reservationQueue.confirmAllReservations();
        assertEquals(2, confirmedReservations.size());
        assertTrue(confirmedReservations.contains(reservation1));
        assertTrue(confirmedReservations.contains(reservation2));
    }

    @Test
    public void testRejectAllReservations() {
        Ticket ticket1 = new Ticket(null, null, new int[]{1, 2}, 10.0f);
        Ticket ticket2 = new Ticket(null, null, new int[]{3, 4}, 12.0f);
        Reservation reservation1 = new Reservation(ticket1, ReservationStatus.CONFIRMED);
        Reservation reservation2 = new Reservation(ticket2, ReservationStatus.CONFIRMED);
        reservationQueue.enqueueReservation(reservation1);
        reservationQueue.enqueueReservation(reservation2);
        Stack<Reservation> rejectedReservations = reservationQueue.rejectAllReservations();
        assertEquals(2, rejectedReservations.size());
        assertTrue(rejectedReservations.contains(reservation1));
        assertTrue(rejectedReservations.contains(reservation2));
    }

}
