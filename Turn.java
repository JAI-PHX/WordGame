import java.util.Scanner;

public class Turn {

    private int amountForWin;

    private int penalty;

    // Initialize amountForWin and penalty.
    public Turn(int amountForWin, int penalty) {

        this.amountForWin = amountForWin;

        this.penalty = penalty;
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

        // Evaluates the user's input to see if it matches the randomNum generated.
        if (playerGuess == numToGuess) {

            // If the user's guess is correct, it increases the user's money.
            player.setMoney(player.getMoney() + amountForWin);

            System.out.println("Congratulations, you guessed the number!");

            System.out.println("You win $" + amountForWin);

            System.out.println(player);

            return true;

        }
        else {

            // If the user's guess is wrong, it decreases the user's money.
            player.setMoney(player.getMoney() - penalty);

            // Print a message to notify the user if their guess is too low or high.
            System.out.println("I'm sorry. That guess was " + (playerGuess > numToGuess ? "too high." : "too low."));

            System.out.println("You lose $" + penalty + ".00");

            System.out.println(player);

            return false;
        }
    }
}
