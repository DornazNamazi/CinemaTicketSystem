package JUnit;
import static org.junit.jupiter.api.Assertions.*;
import DataStructures.MovieLinkedList;
import Model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieLinkedListTest {
    private MovieLinkedList movieList;

    @BeforeEach
    public void setUp() {
        movieList = new MovieLinkedList();

        // Add some movies to the list for testing
        movieList.addMovie(new Movie("Dune", "Sci-Fi", "D. Villeneuve", 120));
        movieList.addMovie(new Movie("Nope", "Horror", "Jordan Peele", 95));
        movieList.addMovie(new Movie("La La Land", "Romcom", "Damien Chazelle", 110));
    }

    @Test
    public void testSearchMovie_ExistingTitle() {
        // Search for an existing movie
        String titleToSearch = "Dune";
        Movie foundMovie = movieList.searchMovie(titleToSearch);

        assertNotNull(foundMovie);
        assertEquals(titleToSearch, foundMovie.getTitle(), "Returned movie should have matching title");
    }

    @Test
    public void testSearchMovie_NonExistingTitle() {
        String titleToSearch = "Inception";
        Movie foundMovie = movieList.searchMovie(titleToSearch);

        assertNull(foundMovie, "No movie should be found for a non-existing title");
    }

    @Test
    public void testSearchMovie_CaseInsensitive() {
        String titleToSearch = "lA La LaNd";
        Movie foundMovie = movieList.searchMovie(titleToSearch);

        assertNotNull(foundMovie);
        assertEquals("La La Land", foundMovie.getTitle(), "Title matching should be case-insensitive");
    }
}
