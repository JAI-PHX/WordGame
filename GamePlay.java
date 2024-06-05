import java.util.Scanner;

public class GamePlay {

    //Player array for only 3 users
    private Players[] currentPlayers = new Players[3];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create a new host named Bob Barker
        Hosts host = new Hosts("Bob", "Barker");

        GamePlay game = new GamePlay();

        // Loop that asks 3 players to add their name
        for (int i = 0; i < game.currentPlayers.length; i++) {

            // Ask the user for the first and last name
            System.out.printf("Player #%d, what is your first name?\n", i + 1);

            String first_name = scanner.nextLine();

            System.out.print("Would you like to enter a last name? Leave blank if not.\n");

            String last_name = scanner.nextLine();

            Players player;

            if (last_name.isEmpty()) {

                // If the last name not inputted, only the first name is used
                player = new Players(first_name);

            } else {

                // If the last name is inputted, the first and last name is used
                player = new Players(first_name, last_name);
            }

            // Player array storing all 3 users in currentPlayers
            game.currentPlayers[i] = player;
        }
        // Create a new instance of the Numbers class and generate a random number
        host.enterPhrase();

        Turn turn = new Turn();

        // Loop that asks the users to enter their guess every turn
        boolean repeatGame = true;

        while (repeatGame) {

            boolean correctGuess = false;

            while (!correctGuess) {

                for (Players player : game.currentPlayers) {

                    if (!correctGuess) {

                        correctGuess = turn.takeTurn(player, host);

                        if (correctGuess) {

                            break;
                        }
                    }
                }
            }

            //Ask users if they want to repeat the game when a player wins
            repeatGame = host.repeat();

        }

        scanner.close();
    }
}