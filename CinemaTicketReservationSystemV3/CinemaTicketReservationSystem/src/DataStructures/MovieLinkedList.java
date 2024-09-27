package DataStructures;

import Model.Movie;

import java.util.LinkedList;

public class MovieLinkedList {
    private LinkedList<Movie> movies;
    public MovieLinkedList() {
        movies = new LinkedList<>();
    }
    public LinkedList<Movie> getMovies(){
        return this.movies;
    }

    public void addMovie(Movie movie){
        movies.addLast(movie);
    }
    public void removeMovie(Movie movie){
        movies.remove(movie);
    }
    public Movie searchMovie(String title){
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }
    public void displayMovies(){
        for (int i = 0; i < movies.size(); i++) {
            System.out.println();
            movies.get(i).display();
        }
        System.out.println();
    }

    public void displayTitles(){
        System.out.println();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println("\t" + i + ". " + movies.get(i).getTitle());
        }
        System.out.println();
    }
}
