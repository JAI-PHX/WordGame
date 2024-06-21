import java.util.Random;

public class Physical implements Award {

    // String with 5 physical prizes when a user wins
    private String[] physicalPrizes = {"new car", "vacation to Hawaii", "Ring Alarm System", "painting", "TV"};

    // Display true or false messages based on the players' input being right or wrong
    public int displayWinnings(Players player, boolean correctGuess) {

        if (correctGuess) {

            Random random = new Random();

            int prizeIndex = random.nextInt(physicalPrizes.length);

            String prize = physicalPrizes[prizeIndex];

            player.setPrize(prize);

            return 0;
        }
        return 0;
    }
}
