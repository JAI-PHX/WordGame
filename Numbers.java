import java.util.Random;

public class Numbers {

    private int randomNum;

    // One public getter and one public setter for randomNum

    // Getter for randomNum
    public int getRandomNum() {

        return randomNum;
    }

    // This setter randomNum
    public void setRandomNum(int randomNum) {

        this.randomNum = randomNum;
    }

    // Generate a random number between 0 and 100 and set the randomNum value
    public void generateNumber() {

        Random random = new Random();

        // Generates random number between 0 and 100
        randomNum = random.nextInt(101);
    }

    // Compares the user's guess to a generated random number
    public boolean compareNumber(int guess) {

        if (guess == randomNum) {

            // Print feedback for user's inputting right number
            System.out.println("Congratulations, you guessed the number!");
            return true;

        } else if (guess > randomNum) {

            // Print feedback for user's inputting their number being too high
            System.out.println("I'm sorry. That guess was too high.");

            return false;

        } else {

            // Print feedback for user's inputting their number being too low
            System.out.println("I'm sorry, That guess was too low.");

            return false;
        }
    }

}









