package Model;

public class Movie {
    private String title;
    private String genre;
    private String director;
    private int runtime;


    public Movie(String title, String genre, String director, int runtime) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


    public void display(){
        System.out.println("\tTitle: " + getTitle());
        System.out.println("\tDirector: " + getDirector());
        System.out.println("\tGenre: " + getGenre());
        System.out.println("\tRuntime: " + getRuntime());
    }
}
