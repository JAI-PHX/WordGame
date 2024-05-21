import java.util.Random;

public class Numbers {

    private int randomNum;

    // One public getter and one public setter for randomNum
    // Public int getrandomNum() is a getter for the random number, which returns the value of randomNum.
    public int getRandomNum() {

        return randomNum;
    }

    // Public void setrandomNum(int randomNum) is a setter for the randomNum.
    // This setter sets the value and parameter of the randomNum field.
    public void setRandomNum(int randomNum) {

        this.randomNum = randomNum;
    }

    // Public method named generateNumber()
    // to generate a random number between 0 and 100 and setting the value of randomNum to the generated random number.
    public void generateNumber() {

        // Create a new instance called Random to generate a random number.
        Random random = new Random();

        // Generates a random number between 0 and 100.
        randomNum = random.nextInt(101);
    }

    // Public class method calls compareNumber(int guess) that accepts an integer as a guess and returns a true or false.
    public boolean compareNumber(int guess) {

        if (guess == randomNum) {

            // If the user's guess is equal to randomNum, it will return as true.
            System.out.println("Congratulations, you guessed the number!");
            return true;

        } else if (guess > randomNum) {

            // If the user's guess is too high compared to the randomNum, it will return as false.
            System.out.println("I'm sorry. That guess was too high.");

            return false;

        } else {

            // If the user's guess is too low compared to the randomNum, it will return as false.
            System.out.println("I'm sorry, That guess was too low.");

            return false;
        }
    }
}









