package Util;

import DataStructures.*;
import Enums.ReservationStatus;
import Enums.SeatStatus;
import Features.PaymentProcessing.CreditCardPayment;
import Features.PaymentProcessing.PayPalPayment;
import Features.PaymentProcessing.PaymentStrategy;
import Features.ReservationPrinter;
import Model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationSystem {
    private Scanner scanner = new Scanner(System.in);
    private MovieLinkedList currentMovieLinkedList;
    private ShowtimeArray currentShowtimesArray;
    private CinemaHallMap cinemaHallMap;
    private ConfirmedReservationStack confirmedReservationsStack;
    private ReservationRequestQueue reservationRequestQueue;
    private PaymentStrategy paymentStrategy;

    public ReservationSystem(){
        this.currentMovieLinkedList = new MovieLinkedList();
        this.currentShowtimesArray = new ShowtimeArray();
        this.cinemaHallMap = new CinemaHallMap();
        this.reservationRequestQueue = new ReservationRequestQueue();
        this.confirmedReservationsStack = new ConfirmedReservationStack();
        initializeSampleData();
    }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void initializeSampleData(){
        // initialize movies

        Movie dune = new Movie("Dune", "Sci-Fi", "D. Villeneuve", 120);
        Movie nope = new Movie("Nope", "Horror", "Jordan Peele", 95);
        Movie lalaland = new Movie("La la land", "Romcom", "Damien Chazelle", 110);

        this.currentMovieLinkedList.addMovie(dune);
        this.currentMovieLinkedList.addMovie(nope);
        this.currentMovieLinkedList.addMovie(lalaland);

        // initialize halls

        CinemaHall cinemaHall1 = new CinemaHall(80, 8);
        CinemaHall cinemaHall2 = new CinemaHall(70, 7);
        CinemaHall cinemaHall3 = new CinemaHall(90, 9);

        this.cinemaHallMap.addHall(cinemaHall1);
        this.cinemaHallMap.addHall(cinemaHall2);
        this.cinemaHallMap.addHall(cinemaHall3);

        // initialize showtimes

        Showtime showtime1 = new Showtime(LocalTime.of(12, 0), LocalTime.of(14, 0),
                cinemaHall1, dune);
        showtime1.randomizeSeats(confirmedReservationsStack); // populate with sample data
        Showtime showtime2 = new Showtime(LocalTime.of(15, 0), LocalTime.of(17, 0),
                cinemaHall1, dune);
        showtime2.randomizeSeats(confirmedReservationsStack);
        Showtime showtime3 = new Showtime(LocalTime.of(18, 0), LocalTime.of(20, 0),
                cinemaHall1, dune);
        showtime3.randomizeSeats(confirmedReservationsStack);

        Showtime showtime4 = new Showtime(LocalTime.of(12, 0), LocalTime.of(14, 0),
                cinemaHall2, nope);
        showtime4.randomizeSeats(confirmedReservationsStack);
        Showtime showtime5 = new Showtime(LocalTime.of(15, 0), LocalTime.of(17, 0),
                cinemaHall2, nope);
        showtime5.randomizeSeats(confirmedReservationsStack);
        Showtime showtime6 = new Showtime(LocalTime.of(18, 0), LocalTime.of(16, 0),
                cinemaHall2, nope);
        showtime6.randomizeSeats(confirmedReservationsStack);

        Showtime showtime7 = new Showtime(LocalTime.of(12, 0), LocalTime.of(14, 0),
                cinemaHall3, lalaland);
        showtime7.randomizeSeats(confirmedReservationsStack);
        Showtime showtime8 = new Showtime(LocalTime.of(15, 0), LocalTime.of(17, 0),
                cinemaHall3, lalaland);
        showtime7.randomizeSeats(confirmedReservationsStack);
        Showtime showtime9 = new Showtime(LocalTime.of(18, 0), LocalTime.of(20, 0),
                cinemaHall3, lalaland);
        showtime7.randomizeSeats(confirmedReservationsStack);

        this.currentShowtimesArray.addShowtime(showtime1);
        this.currentShowtimesArray.addShowtime(showtime2);
        this.currentShowtimesArray.addShowtime(showtime3);
        this.currentShowtimesArray.addShowtime(showtime4);
        this.currentShowtimesArray.addShowtime(showtime5);
        this.currentShowtimesArray.addShowtime(showtime6);
        this.currentShowtimesArray.addShowtime(showtime7);
        this.currentShowtimesArray.addShowtime(showtime8);
        this.currentShowtimesArray.addShowtime(showtime9);
    }

    public void mainMenu(){
        System.out.println();
        while(true) {
            System.out.println("Welcome to LaSalle Cinema!\n");
            System.out.println("\t1. I want to check the movies");
            System.out.println("\t2. I'm an employee");
            System.out.print("\nChoose the option that applies to you: ");
            String temp = scanner.next();

            switch (temp) {
                case "1":
                    clientBoard();
                    break;
                case "2":
                    employeeBoard();
                    break;
                case "3":
                    System.out.println("See you soon!");
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    private void clientBoard(){

        System.out.println("\nWelcome movie lover!");
        while (true) {
            System.out.println("\n----------------------------------------------------------\n");
            System.out.println("Browse our shows for today " + LocalDate.now().format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + "\n");
            System.out.println("\t1. See available movies");
            System.out.println("\t2. See showtimes today ");
            System.out.println("\t3. See our cinema halls");
            System.out.println("\t4. Make a reservation");
            System.out.println("\t5. Go back");
            System.out.print("\nEnter your choice: ");
            String temp = scanner.next();

            switch (temp) {
                case "1":
                    showAvailableMovies();
                    break;
                case "2":
                    showShowtimes();
                    break;
                case "3":
                    showHalls();
                    break;
                case "4":
                    makeReservation();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    private void showAvailableMovies(){
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\nMovies for today " + LocalDate.now().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        currentMovieLinkedList.displayMovies();
        System.out.println("\t1. Make a reservation");
        System.out.println("\t2. Go back");
        System.out.print("\nEnter your choice: ");
        String temp = scanner.next();

        switch (temp) {
            case "1":
                makeReservation();
                break;
            case "2":
                break;
            default:
                System.out.println("Invalid option. Try again!");
        }
    }

    private void showShowtimes(){
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\nShowtimes for today " + LocalDate.now().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println();
        currentShowtimesArray.displayByMovie(this.currentMovieLinkedList);
        System.out.println("\t1. Make a reservation");
        System.out.println("\t2. Go back");
        System.out.print("\nEnter your choice: ");
        String temp = scanner.next();

        switch (temp) {
            case "1":
                makeReservation();
                break;
            case "2":
                return;
            default:
                System.out.println("Invalid option. Try again!");
        }
    }

    private void showHalls(){
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\nOur functioning halls: ");
        System.out.println("\n-------------------------------------------------");
        cinemaHallMap.displayHalls();
    }

    public void makeReservation(){
        System.out.println("\n----------------------------------------------------------");
        currentMovieLinkedList.displayTitles();
        System.out.print("Which movie do you wish to watch?: ");
        int choice = 0;

        try{
            choice = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Invalid choice: that is not a number");
        }

        Movie movieChoice = null;
        if(choice >= 0 && choice < currentMovieLinkedList.getMovies().size()){
            movieChoice = currentMovieLinkedList.getMovies().get(choice);
        }else{
            System.out.println("Invalid choice: no such index for movie");
            return;
        }

        int idx = currentShowtimesArray.selectShowtimeByTitle(movieChoice.getTitle());
        Showtime showtime;
        if(idx != -1)
            showtime = currentShowtimesArray.getShowtimes()[idx];
        else {
            System.out.println("Invalid choice: no such index for movie");
            return;
        }

        Ticket ticket = generateTicket(showtime);
        Reservation reservation = new Reservation(ticket, ReservationStatus.PENDING);

        if(processPayment(ticket.getPrice())) {
            this.reservationRequestQueue.enqueueReservation(reservation);
            System.out.println("Thanks! Your reservation has been requested successfully. \nOne of our employees will process it soon" +
                    "\n(Go back and log in as an employee to see the reservation request added to the queue)");

        }
        else{
            System.out.println("\nYour reservation has been cancelled.");
        }
    }

    private Ticket generateTicket(Showtime showtime){
        CinemaHall cinemaHall = showtime.getCinemaHall();
        Movie movie = showtime.getMovie();

        System.out.println("\n\tSeat selection");
        System.out.println("\t" + showtime.getMovie().getTitle() + " at " + showtime.getStartTime());
        System.out.println("\tHall " + showtime.getCinemaHall().getHallNumber());

        cinemaHall.displaySeatLayout();
        boolean continueLoop = true;
        Ticket ticket = null;

        while(continueLoop) {
            System.out.print("Select a row: ");
            int row = 0;

            try {
                row = scanner.nextInt();
                row--;
            } catch (Exception e) {
                System.out.println("Invalid choice: that is not a number");
            }

            System.out.print("Select a column: ");
            int col = 0;

            try {
                col = scanner.nextInt();
                col--;
            } catch (Exception e) {
                System.out.println("Invalid choice: that is not a number");
            }

            if (row < 0 || row > cinemaHall.getRows() || col < 0 || col > (cinemaHall.getCapacity() / cinemaHall.getRows()))
                System.out.println("Invalid seat number!");
            else {
                if (cinemaHall.getSeatStatus(row, col) == SeatStatus.BOOKED) {
                    System.out.println("Sorry this seat is not available. Try again");

                }else {
                    cinemaHall.setSeat(row, col, SeatStatus.BOOKED);
                    ticket = new Ticket(movie, showtime, new int[]{row, col}, 9.9F);
                    continueLoop = false;
                }
            }
        }
        return ticket;
    }

    public boolean processPayment(float amount){
        boolean success = false;

        System.out.println("\nSelect your payment of choice:");
        System.out.println("\n\t1. Credit/Debit card");
        System.out.println("\t2. PayPal ");
        System.out.println("\t3. Go back ");
        System.out.print("\nEnter your choice: ");

        String choice = scanner.next();
        PaymentStrategy paymentStrategyTemp = null;

        boolean continueLoop = true;

        switch (choice){
            case "1":
                int cardNum = 0;
                String name = "";

                while (continueLoop) {
                    System.out.println("Please enter the last 4 digits of your card: ");
                    try {
                        cardNum = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid choice: that is not a number");
                        return false;
                    }

                    if (String.valueOf(cardNum).length() != 4) {
                        System.out.println("Invalid card number. Must be 4 digits!");
                    }else {
                        continueLoop = false;
                        System.out.println("Please enter your name: ");
                        name = scanner.next();
                    }
                }
                paymentStrategyTemp = new CreditCardPayment(cardNum, name);
                System.out.println("Card registered successfully");
                success = true;
                break;

            case "2":
                String email = "";

                while (continueLoop) {
                    System.out.println("Please enter your email: ");
                    email = scanner.next();

                    Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
                    if (email == null) {
                        return false;
                    }
                    Matcher matcher = pattern.matcher(email);

                    if(!matcher.matches()){
                        System.out.println("Must be a valid email! ( @whatever.com )");
                    }else {
                        continueLoop = false;
                        System.out.println("Please enter your name: ");
                        name = scanner.next();
                    }
                }
                paymentStrategyTemp = new PayPalPayment(email);
                System.out.println("PayPal registered successfully");
                success = true;
                break;
            case "3":
                return false;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        setPaymentStrategy(paymentStrategyTemp);
        checkout(amount);
        return success;
    }

    private void checkout(float amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment registered.");
        } else {
            paymentStrategy.pay(amount);
        }
    }


    private void employeeBoard(){
        while (true) {
            System.out.println("LaSalle Cinema Management!\n");
            System.out.println(LocalDate.now().format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + "\n");
            System.out.println("\t1. See today's showtimes");
            System.out.println("\t2. Manage reservations ");
            System.out.println("\t3. Manage requests");
            System.out.println("\t4. Go back");
            System.out.print("\nEnter your choice: ");
            String temp = scanner.next();

            switch (temp) {
                case "1":
                    showShowtimeManager();
                    break;
                case "2":
                    showReservationManager();
                    break;
                case "3":
                    makeRequestsManager();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    private void showShowtimeManager(){
        System.out.println("\n----------------------------------------------");
        System.out.println("\nShowtimes for today " + LocalDate.now().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println();
        currentShowtimesArray.displayByMovie(this.currentMovieLinkedList);
        System.out.println("\t1. See details");
        System.out.println("\t2. Go back");
        System.out.print("\nEnter your choice: ");
        String temp = scanner.next();

        switch (temp) {
            case "1":
                currentShowtimesArray.displayByStartTimeDetailed();
                break;
            case "2":
                return;
            default:
                System.out.println("Invalid option. Try again!");
        }
    }

    private void showReservationManager(){
        System.out.println("\n----------------------------------------------");
        System.out.println("\nReservations for today " + LocalDate.now().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println();
        ReservationPrinter reservationPrinter = new ReservationPrinter(confirmedReservationsStack);
        reservationPrinter.printReservationsSummary();
        System.out.println("\n\t1. See details");
        System.out.println("\t2. Go back");
        System.out.print("\nEnter your choice: ");
        String temp = scanner.next();

        switch (temp) {
            case "1":
                confirmedReservationsStack.displayAllSamples();
                break;
            case "2":
                return;
            default:
                System.out.println("Invalid option. Try again!");
        }
        System.out.println("------------------------------\n");
    }

    private void makeRequestsManager(){
        System.out.println("\n----------------------------------------------");
        System.out.println("\nRequests for today " + LocalDate.now().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println();
        if(!reservationRequestQueue.displayAll()){
            System.out.println("----------------------------------------------\n");
            return;
        }
        System.out.println("\n\t1. Confirm first request in queue");
        System.out.println("\t2. Confirm ALL requests in queue");
        System.out.println("\t3. Reject first request in queue");
        System.out.println("\t4. Reject ALL requests in queue");
        System.out.println("\t5. Go back");
        System.out.print("\nEnter your choice: ");
        String temp = scanner.next();

        switch (temp) {
            case "1":
                reservationRequestQueue.confirmReservation();
                break;
            case"2":
                confirmedReservationsStack.pushReservation(reservationRequestQueue.confirmAllReservations());
                break;
            case"3":
                reservationRequestQueue.rejectReservation();
                break;
            case"4":
                confirmedReservationsStack.pushReservation(reservationRequestQueue.rejectAllReservations());
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option. Try again!");
        }
        System.out.println("------------------------------\n");
    }

    public ReservationRequestQueue getReservationRequestQueue() {
        return reservationRequestQueue;
    }

    public MovieLinkedList getCurrentMovieLinkedList() {
        return currentMovieLinkedList;
    }

    public int showShowtimes(String title) {
        return 0;
    }

    public int selectShowtimeByTitle(String title) {
        return 0;
    }
}
