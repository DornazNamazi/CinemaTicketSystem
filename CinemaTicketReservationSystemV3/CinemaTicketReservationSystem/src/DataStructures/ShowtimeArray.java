package DataStructures;

import Model.Movie;
import Model.Showtime;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ShowtimeArray {
    private int size;
    private int capacity;
    private Showtime[] showtimes;
    public ShowtimeArray(){
        this.size = 0;
        this.capacity = 9;
        this.showtimes = new Showtime[capacity];
    }

    public Showtime[] getShowtimes() {
        return showtimes;
    }

    public void addShowtime(Showtime showtime){
        if(size != capacity){
            if(isShowtimeColliding(showtime))
                System.out.println("Time not available: Showtime is colliding with already existing showtime.");
            else
                this.showtimes[size++] = showtime;
        }
        else
            System.out.println("No more showtimes are allowed for today.");
    }
    public void removeShowtime(){
        displayAll();
        System.out.println("Choose showtime to delete: ");
        Scanner scanner = new Scanner(System.in);


        int index;
        try {
            index = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Invalid showtime. Removing cancelled.");
            return;
        }
        if(size != 0)
            this.showtimes[index] = null;
        else
            System.out.println("No showtimes to delete");
    }

    public void displayAll(){
        for (int i = 0; i < showtimes.length; i++) {
            System.out.println("\t" + i + ". ");
            this.showtimes[i].displayShowtime();
        }
        System.out.println();
    }

    public void displayByMovie(MovieLinkedList movieLinkedList){

        for (Movie movie : movieLinkedList.getMovies()) {
            System.out.println("\t" + movie.getTitle());
            for (int i = 0; i < this.showtimes.length; i++) {
                if (this.showtimes[i].getMovie().getTitle().equals(movie.getTitle()))
                    System.out.println("\t\t" + this.showtimes[i].getStartTime());
            }
            System.out.println();
        }
    }

    public void displayByStartTimeDetailed(){
        Showtime[] temp = showtimes.clone();
        Arrays.sort(temp, Comparator.comparing(Showtime::getStartTime));

        for (int i = 0; i < this.showtimes.length; i++) {

            System.out.println("\n\tHall " + temp[i].getCinemaHall().getHallNumber() +
                    " at " + temp[i].getStartTime() + "\n" );
            temp[i].getCinemaHall().displaySeatLayout();
            System.out.println("-------------------------------------------");

        }
        System.out.println();

    }

    public int selectShowtimeByTitle(String title){
        int[] iIndxs = new int[3]; // max num of showtimes per movie

        System.out.println();
        for (int i = 0, j = 0; i < showtimes.length; i++) {
            if (showtimes[i].getMovie().getTitle().equals(title)){
                System.out.println("\t" + j + ". " + showtimes[i].getStartTime());
                iIndxs[j] = i;
                j++;
            }
        }

        System.out.print("\nPlease select a time: ");
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        try{
            choice = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Invalid choice: that is not a number");
        }

        if(choice >= 0 && choice < 3){
            return iIndxs[choice];
        }else{
            System.out.println("Invalid choice: no such index for movie");
            return -1;
        }
    }
    private boolean isShowtimeColliding(Showtime A){
        Showtime B = this.showtimes[size];

        boolean isSameHall = false;
        boolean isOverlap = false;

        if(B != null) {
            isSameHall = B.getCinemaHall().equals(A.getCinemaHall());
            isOverlap = A.getStartTime().isBefore(B.getEndTime()) && B.getStartTime().isBefore(A.getEndTime());
        }
        return isSameHall && isOverlap;
    }
}
