import Model.CinemaHall;
import Model.Showtime;
import Util.ReservationSystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

class ShowtimeArrayTest {
    private ReservationSystem reservationSystem;

    @Before
    public void setUp() {
        reservationSystem = new ReservationSystem();
        reservationSystem.initializeSampleData();
    }

    @Test
    public void testSelectShowtimeByTitle_ValidTitle() {
        String title = "Dune";
        int expectedIndex = 0;

        int selectedIndex = reservationSystem.showShowtimes(title);

        assertEquals(expectedIndex, selectedIndex);
    }

    @Test
    public void testSelectShowtimeByTitle_InvalidTitle() {
        String title = "Invalid Movie";

        int selectedIndex = reservationSystem.selectShowtimeByTitle(title);

        assertEquals(-1, selectedIndex); // Expecting -1 for invalid title
    }
    @Test
    public void testIsShowtimeColliding_NoOverlap() {
        CinemaHall hall1 = new CinemaHall(1, 10);
        CinemaHall hall2 = new CinemaHall(2, 15);

        Showtime showtimeA = new Showtime(LocalTime.of(12, 0), LocalTime.of(14, 0), hall1, null);
        Showtime showtimeB = new Showtime(LocalTime.of(15, 0), LocalTime.of(17, 0), hall2, null);

        assertFalse(showtimeA.isShowtimeColliding(showtimeB));
    }

    @Test
    public void testIsShowtimeColliding_Overlap() {
        CinemaHall hall1 = new CinemaHall(1, 10);
        CinemaHall hall2 = new CinemaHall(2, 15);

        Showtime showtimeA = new Showtime(LocalTime.of(12, 0), LocalTime.of(14, 0), hall1, null);
        Showtime showtimeB = new Showtime(LocalTime.of(13, 0), LocalTime.of(15, 0), hall1, null);

        assertTrue(showtimeA.isShowtimeColliding(showtimeB));
    }

}