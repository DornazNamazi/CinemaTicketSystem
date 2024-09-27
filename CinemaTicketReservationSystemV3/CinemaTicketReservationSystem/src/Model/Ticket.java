package Model;

public class Ticket {
    private Movie movie;
    private Showtime showtime;
    private int[] seat;
    private float price;

    public Ticket(Movie movie, Showtime showtime, int[] seat, float price) {
        this.movie = movie;
        this.showtime = showtime;
        this.seat = seat;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public int[] getSeat() {
        return seat;
    }

    public void setSeat(int[] seat) {
        this.seat = seat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
