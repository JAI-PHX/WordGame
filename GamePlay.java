import java.util.Scanner;

public class GamePlay {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create a new host named Bob Barker
        Hosts host = new Hosts("Bob", "Barker");

        // Ask the user for the first and last name
        System.out.print("What is your first name?\n");

        String first_name = scanner.nextLine();

        System.out.print("Would you like to enter a last name? Leave blank if not.\n");

        String last_name = scanner.nextLine();

        // Create an instance of a Person first and last name based on their input
        Players player;

        if (last_name.isEmpty()) {

            // If the last name not inputted, only the first name is used
            player = new Players(first_name);

        } else {

            // If the last name is inputted, the first and last name is used
            player = new Players(first_name, last_name);
        }

        // Create a new instance of the Numbers class and generate a random number
        host.randomizeNum();

        Turn turn = new Turn(1000, 200);

        // Loop that asks the user to enter their guess
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

                // Generate a new random number for the next game
                host.randomizeNum();
            }
        }

        scanner.close();

    }
}