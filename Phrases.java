public class Phrases {

    // Player letter guess
    public static String gamePhrase;

    // Game's hidden phrase
    private static String playingPhrase;

    // Set the phrase with underscores
    public Phrases(String phrase){

        gamePhrase = phrase;

        // Replace letters with underscores
        playingPhrase = gamePhrase.replaceAll("[a-zA-Z]", "_");
    }

    public static String getplayingPhrase(){

        return playingPhrase;
    }

    // Find the letter in the phrase and throws MultipleLettersException if more than 1 letter is inputted
    public static void findLetters(String letter) throws MultipleLettersException{

        if (letter.length() != 1){

            throw new MultipleLettersException();
        }

        char[] phraseArray = gamePhrase.toCharArray();

        char[] playingArray = playingPhrase.toCharArray();

        for (int i = 0; i < gamePhrase.length(); i++) {

            if (gamePhrase.charAt(i) == letter.charAt(0)) {

                playingArray[i] = letter.charAt(0);

            }
        }

        // Update the phrase to show the letter
        playingPhrase = new String(playingArray);

        // Puzzle solved for player who won the game
        if (!playingPhrase.contains("_")) {

        }
    }
}
