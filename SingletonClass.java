import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class Seat {
    private boolean isAvailable;
    private String bookedBy;

    public Seat() {
        this.isAvailable = true;
        this.bookedBy = null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void book(String name) {
        this.isAvailable = false;
        this.bookedBy = name;
    }
}

class BookingManager {
    // static class
    private static BookingManager instance;

    // Store seats (key: seat number, value: Seat object)
    private Map<Integer, Seat> seats;

    private BookingManager() {
        seats = new HashMap<>();
        // Initialize seats (example: 20 total)
        for (int i = 1; i <= 20; i++) {
            seats.put(i, new Seat());
        }
        System.out.println("Singleton BookingManager is Instantiated.");
    }

    public synchronized static BookingManager getInstance() {
        if (instance == null)
            instance = new BookingManager();
        return instance;
    }

    public synchronized void bookTicket(int seatNumber, String name) {
        Seat seat = seats.get(seatNumber);

        if (seat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return;
        }
        if (seat.isAvailable()) {
            seat.book(name);
            System.out.println("Seat " + seatNumber + " has been booked successfully under the name '" + name + "'.");
            return;
        } else {
            System.out.println("Seat " + seatNumber + " is not available.");
            return;
        }

    }

    public synchronized void printAvailability() {
        System.out.println("\nCurrent Availability:");
        for (Map.Entry<Integer, Seat> entry : seats.entrySet()) {
            String status = entry.getValue().isAvailable()
                    ? "Available"
                    : "Booked by " + entry.getValue().getBookedBy();
            System.out.println("Seat " + entry.getKey() + ": " + status);
        }
        System.out.println();
    }
}

public class App {
    public static void main(String[] args) {
        // receive keyboard input
        Scanner kb = new Scanner(System.in);

        // create 2 instances that actually refer to the same instance
        BookingManager manager1 = BookingManager.getInstance();
        BookingManager manager2 = BookingManager.getInstance();
        // Show that manager1 and manager2 refer to the same instance
        System.out.println("Identity Hash Code of mmanager1: " + System.identityHashCode(manager1));
        System.out.println("Identity Hash Code of mmanager2: " + System.identityHashCode(manager2));
        // example of booking seat and showing availability
        manager1.bookTicket(3, "Mikayla");
        manager1.printAvailability();
        // Intro text
        System.out.println("Booking Manager \nOptions: \n\t1. Book a Seat\n\t2. View Availability");
        // user interface of sorts
        boolean isActive = true;
        boolean isManager1 = true;
        while (isActive) {
            System.out.print("\nPlease Enter '1' or '2'. Enter 'q' to quit: ");
            String response = kb.nextLine();
            switch (response) {
                case "q":
                    isActive = false;
                    break;
                case "1":
                    System.out.print("\nPlease enter a seat number: ");
                    int seat = Integer.parseInt(kb.nextLine());
                    System.out.print("\nPlease enter a name: ");

                    // book under either manager
                    String name = kb.nextLine();
                    if (isManager1) {
                        manager1.bookTicket(seat, name);
                        isManager1 = false;
                    } else {
                        manager2.bookTicket(seat, name);
                        isManager1 = true;
                    }
                    break;
                case "2":
                    // works with either manager variable
                    if (isManager1) {
                        manager1.printAvailability();
                        isManager1 = false;
                    } else {
                        manager2.printAvailability();
                        isManager1 = true;
                    }
                    break;
                default:
                    System.out.println("\n--- ERROR: Invalid Input ---");
                    break;
            }
        }
        kb.close();

    }
}
