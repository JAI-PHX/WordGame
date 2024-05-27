class Money implements Award {

     private double amountWon = 1000;

     private double amountLost = 200;

     // Display true or false messages based on the players input being right or wrong
     public int displayWinnings(Players player, boolean correctGuess) {

         if(correctGuess) {

             System.out.printf("%s, that is correct! You won $%.1f\n", player.getfirstName(), amountWon);

             return (int) amountWon;

         } else {

             System.out.printf("%s, sorry, that is incorrect! You lost $%.1f\n", player.getfirstName(), amountLost);

             return (int) -amountLost;
         }
    }
}
