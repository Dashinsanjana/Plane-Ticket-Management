import java.util.Scanner;

public class Person {
    private static String surname;
    private static String name;
    private static String email;

    public static void person_details() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your surname: ");
        surname = scanner.nextLine();

        while (!isValidName(surname)) {
            System.out.println("Invalid surname . Please enter again: ");
            surname = scanner.nextLine();
        }

        System.out.println("Enter your name: ");
        name = scanner.nextLine();

        while (!isValidName(name)) {
            System.out.println("Invalid name . Please enter again: ");
            name = scanner.nextLine();
        }

        System.out.println("Enter your email : ");
        email = scanner.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("Invalid email format or not a Gmail address. Please enter again: ");
            email = scanner.nextLine();
        }
    }

    public static String getName() {
        return name;
    }

    public static String getSurname() {
        return surname;
    }

    public static String getEmail() {
        return email;
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+") && !name.isEmpty();
    }

    private static boolean isValidEmail(String email) {
        // Basic email format validation and ensuring it's a Gmail address
        return email.matches("[a-zA-Z0-9._%+-]+@gmail.com");
    }
}

