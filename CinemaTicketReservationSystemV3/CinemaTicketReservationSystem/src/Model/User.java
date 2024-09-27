package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class User {
    private String name;
    private int age;
    private String email;
    private static List<User> userList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public User(String name,int age,  String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User verifyOrCreateUser(){
        System.out.println("Enter name:");
        String name = scan.nextLine();

        System.out.println("Enter age:");
        int age = Integer.parseInt(scan.nextLine());

        System.out.println("Enter email:");
        String email = scan.nextLine();

        for(User user:userList){
            if(user.getEmail().toLowerCase().equals(email)){
                System.out.println("Client ID: " + user.hashCode() + " -> already exists");
                return user;
            }
        }
        User newUser = new User(name, age, email);
        userList.add(newUser);
        System.out.println("New client created with ID: " + newUser.hashCode());
        return newUser;
    }
    public void displayUserInfo(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
}
