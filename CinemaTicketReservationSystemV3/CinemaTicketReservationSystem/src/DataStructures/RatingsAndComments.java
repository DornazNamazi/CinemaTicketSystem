package DataStructures;

import Model.Movie;

import java.util.HashMap;
import java.util.List;

public class RatingsAndComments {
    private HashMap<Movie, List<String>> ratings;
    private HashMap<Movie, List<String>> comments;

    public RatingsAndComments()
    {
        this.ratings = new HashMap<>();
        this.comments = new HashMap<>();
    }

    // Add a rating to a movie
    public void addRating(Movie movie, int rating)
    {
        if (ratings.containsKey(movie))
        {
            ratings.get(movie).add(String.valueOf(rating));
            System.out.println("Rating added successfully");
        }
        else
        {
            System.out.println("Movie not found");
        }
    }
    // Add a comment to a movie
    public void addComment(Movie movie, String comment)
    {
        if (comments.containsKey(movie))
        {
            comments.get(movie).add(comment);
            System.out.println("Comment added successfully");
        }
        else
        {
            System.out.println("Movie not found");
        }
    }
    // Get ratings for a movie; return null if no ratings found or movie not found
    public List<String> getRatings(Movie movie)
    {
        if (ratings.containsKey(movie))
        {
            System.out.println("Ratings for movie " + movie.getTitle() + " are: ");
            return ratings.get(movie);
        }
        else if (ratings.get(movie).isEmpty())
        {
            System.out.println("No ratings found for movie " + movie.getTitle());
            return null;
        }
        else
        {
            System.out.println("Movie not found!");
            return null;
        }
    }

    // Get comments for a movie; return null if no comments found or movie not found
    public List<String> getComments(Movie movie)
    {
        if (comments.containsKey(movie))
        {
            System.out.println("Comments for movie " + movie.getTitle() + " are: ");
            return comments.get(movie);
        }
        else if (comments.get(movie).isEmpty()) {
            System.out.println("No comments found for movie " + movie.getTitle());
            return null;
        }
        else
        {
            System.out.println("Movie not found!");
            return null;
        }
    }


    public void setRatings(HashMap<Movie, List<String>> ratings) {
        this.ratings = ratings;
    }

    public void setComments(HashMap<Movie, List<String>> comments) {
        this.comments = comments;
    }
}
