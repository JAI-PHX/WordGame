import java.util.Scanner;
import java.util.Random;

public class Turn {

    Scanner scanner = new Scanner(System.in);

    Random random = new Random();

    // Boolean for true or false
    public boolean takeTurn(Players player, Hosts host) {

        // Prompt the user by name to input their letter of choice
        System.out.println(host.getfirstName() + " " + host.getlastName()

                + " says \"" + player.getfirstName() + (player.getlastName().isEmpty() ? "" : " "

                + player.getlastName()) + ", enter your guess for a letter in my phrase\"");

        String playerGuess = scanner.nextLine();

        // Try catch block with the findLetters()
        try {

            // Check user's inputted letter is in the phrase, if not declare it as false
            Phrases.findLetters(playerGuess);

        } catch (MultipleLettersException e) {

            System.out.println(e.getMessage());

            return false;
        }

        // Evaluates the user's inputted letters are in the phrase, if true, result in a random generated award
        if (Phrases.gamePhrase.contains(playerGuess)) {

            boolean isMoneyPrize = random.nextBoolean();

            Award award = isMoneyPrize ? new Money() : new Physical();

            int wins = award.displayWinnings(player, true);

            player.setMoney(player.getMoney() + wins);

            System.out.println(player);
        }

        // update phrase with users' correct inputted letter
        String updatedPhrase = Phrases.getplayingPhrase();

        // Print updated phrase with users inputted letters
        if (updatedPhrase.contains("_")) {

            System.out.println("The phrase to guess is: " + updatedPhrase);
        }

        // Print a congratulation message to including the player correctly filled in the phrase
        if (!updatedPhrase.contains("_")) {

            System.out.println("You solved the puzzle and won the game!");

            return true;
        }

        return false;
    }
}