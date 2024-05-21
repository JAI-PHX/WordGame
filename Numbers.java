// Package used to generate random numbers.
import java.util.Random;

//Numbers.java class.
public class Numbers {

    // Private static integer field named randomNum. Updated to be static
    private static int randomNum;

    // One public getter and one public setter for randomNum
    // Public static int getrandomNum() is a getter for the random number, which returns the value of randomNum.
    public static int getRandomNum() {

        return randomNum;
    }

    // Public void setrandomNum(int randomNum) is a setter for the randomNum.
    // This setter sets the value and parameter of the randomNum field.
    public static void setRandomNum(int randomNum) {

        Numbers.randomNum = randomNum;
    }

    // Public method named generateNumber() using java.util.Random package
    // to generate a random number between 0 and 100 and setting the value of randomNum to the generated random number.
    public static void generateNumber() {

        Random random = new Random(); // Create a new instance called Random to generate a random number using the java.util package.

        randomNum = random.nextInt(101);// Generates a random specific number between 0 and 100.
    }

    // Public class method calls compareNumber(int guess) that accepts an integer as a guess and returns a true or false.
    public boolean compareNumber(int guess) {

        if (guess == randomNum) {

            // If the Users guessed number is correct as the randomNum, it will return as true and
            // Print "Congratulations, you guessed the number!"
            System.out.println("Congratulations, you guessed the number!");

            return true;

        } else if (guess > randomNum) {

            // If the Users guessed number is too high compared to the randomNum, it will return as false and
            // Print "I'm sorry. That guess was too high.
            System.out.println("I'm sorry. That guess was too high.");

            return false;

        } else {

            // If the Users guessed number is too low compared to the randomNum, it will return as false and
            // Print "I'm sorry. That guess was too low."
            System.out.println("I'm sorry, That guess was too low.");

            return false;
        }
    }
}









