package JUnit;
import DataStructures.RatingsAndComments;
import DataStructures.ReservationRequestQueue;
import Model.Movie;
import Model.Reservation;
import Enums.ReservationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RatingsAndCommentsTest {
    private RatingsAndComments ratingsAndComments;
    private Movie movieWithRatings;
    private Movie movieWithComments;

    @BeforeEach
    public void setUp() {
        ratingsAndComments = new RatingsAndComments();

        // Prepare test movies
        movieWithRatings = new Movie("Test Movie with Ratings", "Test Genre", "Test Director", 120);
        movieWithComments = new Movie("Test Movie with Comments", "Test Genre", "Test Director", 120);

        // Prepare test ratings and comments
        List<String> ratings = new ArrayList<>();
        ratings.add("5");
        ratings.add("4");
        ratings.add("3");

        List<String> comments = new ArrayList<>();
        comments.add("Great movie!");
        comments.add("Could be better.");

        // Set ratings and comments for the test movies
        HashMap<Movie, List<String>> testRatingsMap = new HashMap<>();
        HashMap<Movie, List<String>> testCommentsMap = new HashMap<>();

        testRatingsMap.put(movieWithRatings, ratings);
        testCommentsMap.put(movieWithComments, comments);

        ratingsAndComments.setRatings(testRatingsMap);
        ratingsAndComments.setComments(testCommentsMap);
    }

    @Test
    public void testGetRatings_MovieFoundWithRatings() {
        List<String> retrievedRatings = ratingsAndComments.getRatings(movieWithRatings);

        assertNotNull(retrievedRatings);
        assertFalse(retrievedRatings.isEmpty());
        assertEquals(3, retrievedRatings.size());
        assertTrue(retrievedRatings.contains("5"));
    }

    @Test
    public void testGetRatings_MovieFoundWithoutRatings() {
        Movie movieWithoutRatings = new Movie("Movie Without Ratings", "Test Genre", "Test Director", 120);
        List<String> retrievedRatings = ratingsAndComments.getRatings(movieWithoutRatings);

        assertNull(retrievedRatings);
    }

    @Test
    public void testGetRatings_MovieNotFound() {
        Movie nonExistingMovie = new Movie("Non-Existing Movie", "Test Genre", "Test Director", 120);
        List<String> retrievedRatings = ratingsAndComments.getRatings(nonExistingMovie);

        assertNull(retrievedRatings);
    }

    @Test
    public void testGetComments_MovieFoundWithComments() {
        List<String> retrievedComments = ratingsAndComments.getComments(movieWithComments);

        assertNotNull(retrievedComments);
        assertFalse(retrievedComments.isEmpty());
        assertEquals(2, retrievedComments.size());
        assertTrue(retrievedComments.contains("Great movie!"));
    }

    @Test
    public void testGetComments_MovieFoundWithoutComments() {
        Movie movieWithoutComments = new Movie("Movie Without Comments", "Test Genre", "Test Director", 120);
        List<String> retrievedComments = ratingsAndComments.getComments(movieWithoutComments);

        assertNull(retrievedComments);
    }

    @Test
    public void testGetComments_MovieNotFound() {
        Movie nonExistingMovie = new Movie("Non-Existing Movie", "Test Genre", "Test Director", 120);
        List<String> retrievedComments = ratingsAndComments.getComments(nonExistingMovie);

        assertNull(retrievedComments);
    }
}