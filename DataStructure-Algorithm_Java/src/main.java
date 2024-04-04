import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String str;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a string:");
        str = userInput.nextLine();
        System.out.print("Your entered string is: " + str);
    }
}
