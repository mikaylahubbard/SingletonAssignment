## Coding Assignment: Singleton Pattern in Online Ticket Booking System

### Assignment Title:
Implementing the Singleton Design Pattern for a Central Booking Manager in an Online Ticket Booking System

### Learning Objectives:
By completing this assignment, students will:

- Understand the need for the Singleton pattern in systems managing shared resources.

- Implement a thread-safe Singleton class in C++ or Java.

- Integrate the Singleton with other components (e.g., seat management, payment).

- Reflect on design decisions and their implications for system scalability and concurrency.

### Problem Description:
In an Online Ticket Booking System, multiple users can attempt to book tickets for the same event simultaneously.
To ensure consistency and prevent double-booking, the system must have a single, globally accessible instance of the BookingManager class that manages seat allocation and booking confirmation.

Your task is to design and implement a Singleton class BookingManager that:

- Manages available seats.

- Handles booking requests from multiple users.

- Ensures that no two users can book the same seat simultaneously.

### Coding Requirements:
Create a BookingManager Singleton class.
It should:

- Maintain a list or map of available seats (e.g., seat numbers 1–10).

- Provide methods like:

  - bool bookSeat(int seatNumber, string userName);
  - void showAvailableSeats();
  - Prevent creation of multiple instances 

- Add a test for Singleton behavior:

  - Attempt to create two instances of BookingManager.

  - Verify that both references point to the same memory location (i.e., the same instance).

  - Demonstrate this by booking seats through both references and showing that the seat availability is shared.

