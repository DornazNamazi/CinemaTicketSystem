# CinemaTicketSystem 🎟️

This repository contains the source code for a **Cinema Ticket Booking System** built in **Java (console-based)** as part of an academic project. The application simulates the process of managing movies, customers, and ticket reservations in a cinema environment.

---

## Features

### 1. User Role System
The application supports multiple user roles:
- **Admin**
  - Add, update, or remove movies  
  - Manage cinema halls and seating arrangements  
  - View booking statistics  
- **Customer**
  - Browse available movies and showtimes  
  - Book tickets and view booking details  
  - Cancel reservations if needed  

### 2. Ticket Booking System
- Seat selection within a cinema hall  
- Automatic calculation of ticket price (with taxes applied, e.g., Quebec GST/PST)  
- Unique booking ID generation  

### 3. Design Patterns
Implemented object-oriented design patterns to improve modularity and scalability:
- **Observer Pattern** → notify system of new bookings/cancellations  
- **Factory Pattern** → create different ticket types (Regular, Student, VIP)  
- **Template Method** → define steps for the booking process  
- **Strategy Pattern** → flexible tax calculation (weekday vs. weekend rates, regional rules)  

---

## Technologies Used
- **Java (JDK 17)** – Core programming language  
- **OOP Principles** – Encapsulation, Inheritance, Polymorphism, Abstraction  
- **JUnit 5** – Unit testing for core classes  
- **Maven** – Build and dependency management  

---

## Use Case
The Cinema Ticket System is designed for educational and simulation purposes. It demonstrates how real-world ticket booking systems work, while showcasing knowledge of:
- Object-Oriented Programming (OOP)  
- Software design patterns  
- Console-based user interaction  
- Data persistence (via text/CSV storage or in-memory collections)  

---

## How It Works
1. **Admin Login:** Access management features to add movies or manage cinemas.  
2. **Customer Menu:** Select a movie, choose a seat, and confirm booking.  
3. **Ticket Generation:** The system prints booking details with ID and price.  
4. **Cancellation:** Customers can cancel reservations, triggering Observer notifications.  

---

## Installation
1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd cinema-ticket-system
Build and run with Maven:

bash
Copy code
mvn clean install
mvn exec:java -Dexec.mainClass="com.cinema.Main"
(or compile with javac and run with java if not using Maven)

Future Enhancements
Database integration (MySQL or PostgreSQL instead of text/CSV)

GUI frontend with JavaFX or Swing

Online payment simulation

Reporting dashboard for Admin

License
This project is licensed under the MIT License. See the LICENSE file for details.
