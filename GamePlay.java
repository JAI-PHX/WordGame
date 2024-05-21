import java.util.Scanner;

public class GamePlay {

    // Private field of a Person.
    private Person player;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create a new instance of GamePlay.
        GamePlay gamePlay = new GamePlay();

        // Prompt the user for their first and last name.
        System.out.print("What is your first name?\n");

        String first_name = scanner.nextLine();

        System.out.print("Would you like to enter a last name? Leave blank if not.\n");

        String last_name = scanner.nextLine();

        // Create an instance of a Person based on user input when prompted for their last name.
        // Evaluates user input of last name. If the user did not user input their last name. It creates an instance of a Person using only their first name.
        // If the user inputs their last name. It will create an instance of a Person using their first and last name
        if (last_name.isEmpty()) {

            gamePlay.player = new Person(first_name);

        } else {

            gamePlay.player = new Person(first_name, last_name);
        }

        // Create a new instance of the Numbers class and generate a random number.
        Numbers game = new Numbers();

        // Generate a random number from 0 to 100.
        game.generateNumber();

        // Get full name.
        String fullName = gamePlay.player.getpersonsFirstname() + (gamePlay.player.getpersonsLastname().isEmpty() ? "" : " " + gamePlay.player.getpersonsLastname());

        // Loop to prompt the user by name to enter a guess.
        boolean rightGuess = false;

        while (!rightGuess) {

            System.out.print(fullName + ", guess what number I picked between 0 and 100.\n");

            int guess = scanner.nextInt();

            // Compare the guess generated to the users guess
            rightGuess = game.compareNumber(guess);
        }

        scanner.close();
    }
}



