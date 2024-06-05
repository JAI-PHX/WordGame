import java.util.Scanner;

public class Hosts extends Person {

    Scanner scanner = new Scanner(System.in);

    // Two constructors that use first and last name (last name optional) which then calls a
    // superclass to set the users first and last name (last name optional).
    public Hosts(String firstName) {

        // Calls a superclass with users-first name.
        super(firstName);
    }

    // Same operation as the code up above but for users who use their first and last name
    public Hosts(String firstName, String lastName) {

        super(firstName, lastName);
    }

    // Enter chosen phases for the game
    public void enterPhrase() {

        System.out.print("Enter a phrase for the players to guess: \n");

        String phrase = scanner.nextLine();

        Phrases phrases = new Phrases(phrase);

        // Display the phase as underscores _______ __ ___
        System.out.println("The phrase to guess is: " + Phrases.getplayingPhrase());

    }

    // Asks the user if they want to repeat game when a player wins
    public boolean repeat() {

        System.out.print("Play another game? (y or n)\n");

        String reply = scanner.next();

        boolean repeatGame = reply.equalsIgnoreCase("y");

        if (repeatGame) {

            scanner.nextLine();

            // New phrase for the next game
            enterPhrase();
        }

        return repeatGame;
    }
}