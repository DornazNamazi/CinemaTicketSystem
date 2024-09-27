package Model;

import DataStructures.ConfirmedReservationStack;

import java.time.LocalTime;

public class Showtime {
    private LocalTime startTime;
    private LocalTime endTime;
    private CinemaHall cinemaHall;
    private Movie movie;
    private ConfirmedReservationStack confirmedReservations;

    public Showtime(LocalTime startTime, LocalTime endTime, CinemaHall cinemaHall, Movie movie) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHall = cinemaHall;
        this.movie = movie;
        this.confirmedReservations = new ConfirmedReservationStack();
    }



    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void displayShowtime() {
        System.out.println("\t\tMovie: " + getMovie().getTitle());
        System.out.println("\t\tStarts: " + getStartTime());
        System.out.println("\t\tHall: " + getCinemaHall());
    }
    public void randomizeSeats(ConfirmedReservationStack confirmedReservationStack){
        this.cinemaHall.randomizeSeatAvailability(confirmedReservationStack, this); // sample data
    }

    public boolean isShowtimeColliding(Showtime showtimeB) {
        return false;
    }
}
