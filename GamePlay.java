// Package used to collect user input.
import java.util.Scanner;

// Public class named GamePlay.
public class GamePlay {

    // Private field of a type Person.
    private Person player;

    // Main method
    public static void main(String[] args) {

        // Crease a scan object to scan and read user input.
        Scanner scanner = new Scanner(System.in);

        // Create a new instance of GamePlay.
        GamePlay gamePlay = new GamePlay();

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
        if (last_name.isEmpty()) {

            // If the last name not inputted, only the first name is used.
            gamePlay.player = new Person(first_name);

        } else {

            // If the last name is inputted, the first and last name is used.
            gamePlay.player = new Person(first_name, last_name);
        }

        // Create a new instance of the Numbers class and generate a random number.
        Numbers game = new Numbers();

        // Generate a random number from 0 to 100.
        game.generateNumber();

        // Get full name for use in prompts.
        String fullName = gamePlay.player.getpersonsFirstname() + (gamePlay.player.getpersonsLastname().isEmpty() ? "" : " " + gamePlay.player.getpersonsLastname());

        // Loop to prompt the user by name to enter a guess.
        boolean rightGuess = false;

        while (!rightGuess) {

            // Prompts the user by name to enter a guess.
            System.out.print(fullName + ", guess what number I picked between 0 and 100.\n");

            // Scans for user input.
            int guess = scanner.nextInt();

            // Compare the guess generated to the users guess
            rightGuess = game.compareNumber(guess);
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}



