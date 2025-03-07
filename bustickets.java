import java.util.Scanner;

class BusTicketBooking {
    private int availableSeats;
    private String[] passengerNames;
    private int[] bookedSeats;
    private int bookedCount;

    // Constructor to initialize bus with a given number of available seats
    public BusTicketBooking(int totalSeats) {
        availableSeats = totalSeats;
        passengerNames = new String[totalSeats];
        bookedSeats = new int[totalSeats];
        bookedCount = 0;
    }

    // Method to show available seats
    public void showAvailableSeats() {
        System.out.println("Available Seats: " + (availableSeats - bookedCount));
    }

    // Method to book a ticket
    public void bookTicket() {
        if (bookedCount < availableSeats) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter seat number (1 to " + availableSeats + "): ");
            int seatNumber = scanner.nextInt();
            
            // Check if the seat is already booked
            if (seatNumber < 1 || seatNumber > availableSeats || bookedSeats[seatNumber - 1] == 1) {
                System.out.println("Sorry, the seat is already booked or invalid. Try again.");
            } else {
                // Book the seat
                bookedSeats[seatNumber - 1] = 1;
                passengerNames[seatNumber - 1] = name;
                bookedCount++;
                System.out.println("Booking successful for " + name + " at seat number " + seatNumber);
            }
        } else {
            System.out.println("Sorry, no seats are available.");
        }
    }

    // Method to display booking details
    public void showBookingDetails() {
        System.out.println("\n----- Booking Details -----");
        if (bookedCount == 0) {
            System.out.println("No bookings made yet.");
        } else {
            for (int i = 0; i < availableSeats; i++) {
                if (bookedSeats[i] == 1) {
                    System.out.println("Seat " + (i + 1) + " booked by: " + passengerNames[i]);
                }
            }
        }
    }

    // Method to cancel a booking
    public void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter seat number to cancel (1 to " + availableSeats + "): ");
        int seatNumber = scanner.nextInt();
        
        if (seatNumber < 1 || seatNumber > availableSeats || bookedSeats[seatNumber - 1] == 0) {
            System.out.println("Invalid seat number or no booking exists for this seat.");
        } else {
            bookedSeats[seatNumber - 1] = 0;
            passengerNames[seatNumber - 1] = null;
            bookedCount--;
            System.out.println("Booking for seat " + seatNumber + " has been canceled.");
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create a bus with 10 available seats
        BusTicketBooking bus = new BusTicketBooking(10);
        
        while (true) {
            System.out.println("\n------ Bus Ticket Booking System ------");
            System.out.println("1. Show Available Seats");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Show Booking Details");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    bus.showAvailableSeats();
                    break;
                case 2:
                    bus.bookTicket();
                    break;
                case 3:
                    bus.showBookingDetails();
                    break;
                case 4:
                    bus.cancelBooking();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
