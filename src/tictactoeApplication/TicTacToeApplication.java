package tictactoeApplication;

import java.util.Scanner;


public class TicTacToeApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Allows for cont. games
        boolean doYouWantToPlay = true;
        char playerToken = '?';
        char oppToken;

        while (doYouWantToPlay) {
            //Setting up tokens and AI
            System.out.println("Welcome to Tic Tac Toe! You are about to play a game!");
            //System.out.println("Choose either \"X\" or \"O\"");
            while (playerToken == '?') {
                System.out.println("Choose either \"X\" or \"O\"");
                char playerSelection = Character.toUpperCase(sc.next().charAt(0));
                if(playerSelection == 'X' || playerSelection == 'O') {
                    playerToken = playerSelection;
                    System.out.println(playerToken);
                } else {
                    playerToken = '?';
                }
            }

            if(playerToken == 'X') {
                oppToken = 'O';
            } else {
                oppToken = 'X';
            }

            TicTacToe game = new TicTacToe(playerToken, oppToken);
            AI ai = new AI();

            //Set up the game
            System.out.println();
            System.out.println("Now we can start the game. To play enter a number\nThe numbers go 1-9");
            TicTacToe.printIndexBoard();
            System.out.println();

            //Let's play
            while(game.gameOver().equals("notOver")) {
                if(game.currentMarker == game.userMarker) {
                    System.out.println("It's your turn!");
                    int spot = sc.nextInt();
                    while(!game.playTurn(spot)) {
                        System.out.println("Try again, " + spot + " is taken or out of range!");
                        spot = sc.nextInt();
                    }
                    System.out.println("You Picked " + spot + "!");
                } else {
                    System.out.println("It's my turn!");
                    //AI Pick spot
                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);
                    System.out.println("I picked " + aiSpot + "!");
                }
                //Print out new board
                System.out.println();
                game.printBoard();
            }
            System.out.println(game.gameOver());
            System.out.println();
            //Set up a new game?
            System.out.println("do you want to play again? Y for yes or anything else for no");

            char response = sc.next().charAt(0);
            doYouWantToPlay = (response == 'Y');
            System.out.println();
            System.out.println();
        }

    }
}
