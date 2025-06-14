
import java.util.Scanner;
public class w2051709_planeManagement {
    public static int num_rows = 4;
    public static int[] seats_per_row = {14, 12, 12, 14};
    public static char[] row_labels = {'A', 'B', 'C', 'D'};
    public static int[][] seat = new int[num_rows][];
    public static Ticket[] tickets=new Ticket[num_rows*seats_per_row[0]];

    public static void main(String[] args) {
        System.out.println("   Welcome to the Plane Management application");
        Scanner scanner = new Scanner(System.in);
        identifySeats();
        int option;
        do {
            System.out.println("       **************");
            System.out.println("        MENU OPTIONS");
            System.out.println("       **************");
            System.out.println("       1. BUY A SEAT");
            System.out.println("       2. Cancel Seat");
            System.out.println("       3. Find first available seat");
            System.out.println("       4. Show seating plan");
            System.out.println("       5. Print Ticket Information");
            System.out.println("       6. Search tickets");
            System.out.println("       0. Quit");

            System.out.println("Please select an option:");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    buy_Seat();
                    break;
                case 2:
                    cancel_Seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    Ticket.print_ticket_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
        scanner.close();
    }

    public static void identifySeats() {
        for (int i = 0; i < num_rows; i++) {
            seat[i] = new int[seats_per_row[i]];
        }
    }

    public static int RowIndex(char row) {
        for (int i = 0; i < num_rows; i++) {
            if (row == row_labels[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void buy_Seat() {
        Scanner scanner = new Scanner(System.in);
        char row;
        while (true) {
            System.out.println("Enter the row name : ");
            row = scanner.next().toUpperCase().charAt(0);
            int rowIndex = RowIndex(row);
            if (rowIndex == -1) {
                System.out.println("invalid row letter.Please try again !");
                continue;
            }

            System.out.println("Enter seat number : ");
            int seatnumber = scanner.nextInt();

            if (seatnumber < 1 || seatnumber > seats_per_row[rowIndex]) {
                System.out.println("Invalid seat number.Please try again.");
                continue;
            }
            if (seat[rowIndex][seatnumber - 1] == 0) {
                seat[rowIndex][seatnumber - 1] = 1;
                add_ticket(row,seatnumber);
                Person.person_details();
                Ticket.print_ticket_info();
                Ticket.save();
                System.out.println("seat " + row + seatnumber + " has successfully booked.");
            } else {
                System.out.println(row + seatnumber + " is already sold");
            }
            System.out.println("Do you want to buy another seat? (Y/N)");
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice != 'Y') {
                break;
            }
        }
    }
    public static void add_ticket(char row, int seatnumber) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                tickets[i] = new Ticket(row, seatnumber);
                break;
            }
        }
    }
    public static void cancel_Seat() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter row letter : ");
            char row = scanner.nextLine().toUpperCase().charAt(0);
            int rowIndex = RowIndex(row);
            if (rowIndex == -1) {
                System.out.println("Invalid row letter.Please try again");
                continue;
            }
            System.out.println("Enter seat number : ");
            int seatnumber = scanner.nextInt();

            if (seatnumber < 1 || seatnumber > seats_per_row[rowIndex]) {
                System.out.println("Invalid seat number.Please try again.");
                continue;
            }
            if (seat[rowIndex][seatnumber - 1] == 0) {
                System.out.println("Seat is already available .");
            } else {
                seat[rowIndex][seatnumber - 1] = 0;
                cancel_ticket(row,seatnumber);
                System.out.println("Cancel " + row + seatnumber + " seat and made available.");
            }
            System.out.println("Do you want to cancel another seat? (Y/N)");
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice != 'Y') {
                break;
            }
        }
    }
    public static void cancel_ticket(char row, int seatNumber) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getRow() == row && tickets[i].getSeatNumber() == seatNumber) {
                tickets[i] = null;
                break;
            }
        }
    }
    public static void find_first_available() {
        boolean seat_found = false;
        for (int i = 0; i < num_rows; i++) {
            char row = row_labels[i];
            for (int j = 0; j < seats_per_row[i]; j++) {
                if (seat[i][j] == 0) {
                    System.out.println("First available seat is " + row + (j +1));
                    seat_found = true;
                    break;
                }
            }
            if (seat_found) {
                break;
            }
        }
        if (!seat_found) {
            System.out.println("No available seat found");

        }
    }

    public static void show_seating_plan() {
        for (int i = 0; i < num_rows; i++) {
            System.out.println(row_labels[i] + " ");
            for (int j = 0; j < seats_per_row[i]; j++) {
                System.out.print(seat[i][j] == 0 ? "O" : "X");

            }
            System.out.println();
        }
    }

    public static void search_ticket() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the row letter : ");
            char row = scanner.next().toUpperCase().charAt(0);
            int rowIndex = RowIndex(row);
            if (rowIndex == -1) {
                System.out.println("Invalid row letter.Please try again.");
            }
            System.out.println("Enter seat number : ");
            int seatNumber = scanner.nextInt();

            if (seatNumber < 1 || seatNumber > seats_per_row[rowIndex]) {
                System.out.println("Invalid seat.Please try again.");
            }
            if (seat[rowIndex][seatNumber - 1] == 0) {
                System.out.println("seat" + row + seatNumber + " is available.");
            } else {
                System.out.println("seat " + row + seatNumber + "is already sold");
            }
            System.out.println("Do you want to search another seat? (Y/N)");
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice != 'Y') {
                break;
            }
        }
    }
}

