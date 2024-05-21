import java.util.Scanner;

public class GamePlay {

    // Private field
    private Person player;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GamePlay gamePlay = new GamePlay();

        // Ask the user for the first and last name
        System.out.print("What is your first name?\n");

        String first_name = scanner.nextLine();

        System.out.print("Would you like to enter a last name? Leave blank if not.\n");

        String last_name = scanner.nextLine();

        // Create an instance of a Person first and last name based on their input
        if (last_name.isEmpty()) {

            gamePlay.player = new Person(first_name);

        } else {

            gamePlay.player = new Person(first_name, last_name);
        }

        // Create a Numbers class and generate a random number
        Numbers game = new Numbers();

        // Generate a number from 0 to 100
        game.generateNumber();

        // Get full name.
        String fullName = gamePlay.player.getpersonsFirstname() + (gamePlay.player.getpersonsLastname().isEmpty() ? "" : " " + gamePlay.player.getpersonsLastname());

        // Create a loop to ask the user by to enter their guess
        boolean rightGuess = false;

        while (!rightGuess) {

            System.out.print(fullName + ", guess what number I picked between 0 and 100.\n");

            int guess = scanner.nextInt();

            // Compare the users guess to the generated guess
            rightGuess = game.compareNumber(guess);
        }

        scanner.close();
    }
}



