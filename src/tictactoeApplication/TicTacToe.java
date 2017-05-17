package tictactoeApplication;

public class TicTacToe {

    // A lineral array of 9 spots
    //FOR STORAGE
    // 0 | 1 | 2
    // -----------
    // 3 | 4 | 5
    // -----------
    // 6 | 7 | 8

    //What the user thinks:
    // 1 | 2 | 3
    // -----------
    // 4 | 5 | 6
    // -----------
    // 7 | 8 | 9

    //UI Picture of the game print statements in console:
    // INIT:
    // | - | - | -
    // -----------
    // | - | - | -
    // -----------
    // | - | - | -

    //GAMEPLAY:
    // | O | - | O
    // -----------
    // | - | X | -
    // -----------
    // | - | - | X



    protected char[] board;
    //User and AI selected letters
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;

    public TicTacToe(char playerToken, char aiMarker) {
        this.userMarker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.board = setBoard();
        this.currentMarker = userMarker;
    }

    public char[] setBoard() {
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
        return board;
    }

    public boolean playTurn(int spot) {
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (isValid) {
            board[spot-1] = currentMarker;
            //one line IF statement, criteria in the middle ? true option : false option
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        return isValid;
    }

    //check if our spot is in range
    public boolean withinRange(int number) {
        //between 1 and 9
        return number > 0 && number < board.length + 1;
    }

    public boolean isSpotTaken(int number) {
        //if spot doesn't equal dash then spot is already taken
        return board[number-1] != '-';
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            //if i is a multiple of 3 and is no zero
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("-----------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

    public static void printIndexBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            //if i is a multiple of 3 and is no zero
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("-----------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }

    public boolean isThereAWinner() {
        //check if any of these are true and that board[4] is not empty
        boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdRow()) && board[8] != '-';
        if (diagonalsAndMiddles) {
            this.winner = board[4];
        } else if (topAndFirst) {
            this.winner = board[0];
        } else if (bottomAndThird) {
            this.winner = board[8];
        }

        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }

    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }

    public boolean middleRow() {
        return board[3] == board[4] && board[4] == board[5];
    }

    public boolean bottomRow() {
        return board[6] == board[7] && board[7] == board[8];
    }

    public boolean firstCol() {
        return board[0] == board[3] && board[3] == board[6];
    }

    public boolean secondCol() {
        return board[1] == board[4] && board[4] == board[7];
    }

    public boolean thirdRow() {
        return board[2] == board[5] && board[5] == board[8];
    }

    public boolean rightDi() {
        return board[0] == board[4] && board[4] == board[8];
    }

    public boolean leftDi() {
        return board[2] == board[4] && board[4] == board[6];
    }

    public boolean isBoardFilled() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                return false;
            }
        }
        return true;
    }

    public String gameOver() {
        boolean didSomeoneWin = isThereAWinner();
        if(didSomeoneWin) {
            return "We have a winner. The winnder is " + this.winner;
        } else if (isBoardFilled()) {
            return "Draw: Game Over!";
        } else {
            return "notOver";
        }
    }

}
