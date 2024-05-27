import java.util.Random;
import java.util.Scanner;

public class Turn {

    public Turn(){
    }

    // True or false boolean for user's guesses.
    public boolean takeTurn(Players player, Hosts host) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(host.getfirstName() + " " + host.getlastName()

                + " says \"" + player.getfirstName() + (player.getlastName().isEmpty() ? "" : " "

                + player.getlastName()) + ", enter your guess for my random number between 0 and 100\"");

        int playerGuess = scanner.nextInt();

        // Get the randomNum generated from Hosts.java.
        int numToGuess = Numbers.getRandomNum();

        Random random = new Random();

        boolean isMoneyprize = random.nextBoolean();

        Award award;

        if (isMoneyprize) {

            award = new Money();
        }
        else {
            award = new Physical();
        }

        // Evaluates the user's input to see if it matches the randomNum generated.
        if (playerGuess == numToGuess) {

            System.out.println("Congratulations, you guessed the number!");

            int wins = award.displayWinnings(player, true);

            player.setMoney(player.getMoney() + wins);

            System.out.println(player);

            return true;

        }
        else {

            System.out.println("I'm sorry. That guess was " + (playerGuess > numToGuess ? "too high." : "too low."));

            int wins = award.displayWinnings(player, false);

            player.setMoney(player.getMoney() + wins);

            System.out.println(player);

            return false;
        }
    }
}
