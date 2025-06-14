
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private static char row;
    private static int seatNumber;

    public Ticket(char row,int seatNumber){
        Ticket.row = row;
        Ticket.seatNumber =seatNumber;
    }

    public char getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public static int calculateSeatPrice(){
        if (seatNumber >= 1 && seatNumber <= 5) {
            return 200;
        } else if (seatNumber >= 6 && seatNumber <= 9) {
            return 150;
        } else {
            return 180;
        }
    }
    public static void print_ticket_info(){
        System.out.println("Tickets information");
        int total=0;
        for(int i = 0; i< w2051709_planeManagement.tickets.length; i++){
            Ticket ticket= w2051709_planeManagement.tickets[i];
            if (ticket != null) {
                int seatPrice = calculateSeatPrice();
                total += seatPrice;
                System.out.println("Name   : " + Person.getSurname() + " " + Person.getName());
                System.out.println("Email  : " + Person.getEmail());
                System.out.println("Row    : " + ticket.getRow());
                System.out.println("Seat   : " + ticket.getSeatNumber());
                System.out.println("Price  : £" + seatPrice);
                System.out.println();
            }
        }
        System.out.println("Total Amount: £" + total);
    }
    public static void save() {
        String fileName = String.valueOf(row) + seatNumber + ".txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("Name   : " + Person.getSurname() + " " + Person.getName() + "\n");
            fileWriter.write("Email  : " + Person.getEmail() + "\n");
            fileWriter.write("Row    : " + row + "\n");
            fileWriter.write("Seat   : " + seatNumber + "\n");
            fileWriter.write("Price  : £" + calculateSeatPrice() + "\n");
            System.out.println("your ticket is saved to "+row+seatNumber+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


