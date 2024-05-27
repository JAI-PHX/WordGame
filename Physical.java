import java.util.Random;

public class Physical implements Award {

    // String with 5 physical prizes when a user wins
    private String[] physicalPrizes = {
            "new car", "vacation to Hawaii", "Ring Alarm System", "painting", "TV"
    };

    // Use random number to determine users prize
    private int getRandomPrize(){

        Random random = new Random();

        return random.nextInt(physicalPrizes.length);
    }

    // Display true or false messages based on the players' input being right or wrong
    public int displayWinnings(Players player, boolean correctGuess) {

        int prizeIndex = getRandomPrize();

        String prize = physicalPrizes[prizeIndex];

        if (correctGuess) {
            System.out.printf("%s, that is correct! You won a %s!\n", player.getfirstName(), prize);
        } else {
            System.out.printf("%s, sorry, that is incorrect! If you had gotten it correct, you COULD HAVE WON a %s!\n", player.getfirstName(), prize);
        }
        return 0;
    }
}
