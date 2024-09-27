package DataStructures;

import Model.CinemaHall;

import java.util.HashMap;

public class CinemaHallMap {
    private HashMap<Integer, CinemaHall> cinemaHalls;
    public CinemaHallMap(){
        this.cinemaHalls = new HashMap<>();;
    }
    public void addHall(CinemaHall cinemaHall) {
        this.cinemaHalls.put(cinemaHall.getHallNumber(), cinemaHall);
    }
    public void removeHall(int key){
        cinemaHalls.remove(key);
    }
    public void getHallInfo(int key){
        cinemaHalls.get(key);
    }
    public void displayHalls() {
        for (CinemaHall cinemaHall : cinemaHalls.values()) {
            System.out.println("Hall ID: " + cinemaHall.getHallNumber());
            System.out.println("Capacity: " + cinemaHall.getCapacity());
            System.out.println("-------------------------------------------------");
        }
    }
}
