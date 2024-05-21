// Package used to collect user input.
import java.util.Scanner;

// Public class named GamePlay.
public class GamePlay {

    // Main method
    public static void main(String[] args) {

        // Crease a scan object to scan and read user input.
        Scanner scanner = new Scanner(System.in);

        // Create a host named Bob Barker.
        Hosts host = new Hosts("Bob", "Barker");

        // Prompt the user for their first and last name.
        // Ask the user to insert their first name and scan the input.
        System.out.print("What is your first name?\n");

        String first_name = scanner.nextLine();

        // Ask the user to insert their last name and scan the input.
        System.out.print("Would you like to enter a last name? Leave blank if not.\n");

        String last_name = scanner.nextLine();

        // Create an instance of Person based on user input when prompted for their last name.
        // Evaluates user input of last name. If the user did not user input their last name. It creates an instance of a Person using only their first name.
        // If the user inputs their last name. It will create an instance of a Person using their first and last name
        Players player;
        if (last_name.isEmpty()) {

            // If the last name not inputted, only the first name is used.
            player = new Players(first_name);

        } else {

            // If the last name is inputted, the first and last name is used.
            player = new Players(first_name, last_name);
        }

        // Create a new instance of the Numbers class and generate a random number.
        host.randomizeNum();

        // Instance of Turn.java
        Turn turn = new Turn(1000, 200);


        // Loop to prompt the user by name to enter a guess.
        boolean repeatGame = true;
        while (repeatGame) {
            boolean correctGuess = false;
            while (!correctGuess) {
                correctGuess = turn.takeTurn(player, host);
            }

            System.out.print("Play another game? (y or n)\n");
            String reply = scanner.next();
            repeatGame = reply.equalsIgnoreCase("y");

            if (repeatGame) {
                host.randomizeNum(); // Generate a new random number for the next game
            }
        }

        scanner.close();

    }
}



