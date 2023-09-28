import java.util.Scanner;

public class Connect4 {
    private char[][] board;
    private char currentPlayer;

    public Connect4() {
        board = new char[6][7];
        currentPlayer = 'R';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("------------------------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("--------------------------");
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner() {
        // Check rows
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer &&
                        board[i][j + 2] == currentPlayer && board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j] == currentPlayer &&
                        board[i + 2][j] == currentPlayer && board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j + 1] == currentPlayer &&
                        board[i + 2][j + 2] == currentPlayer && board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j - 1] == currentPlayer &&
                        board[i + 2][j - 2] == currentPlayer && board[i + 3][j - 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'R' ? 'Y' : 'R';
    }

    public boolean makeMove(int col) {
        if (col < 0 || col >= 7) {
            return false;
        }

        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == '-') {
                board[i][col] = currentPlayer;
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);

        while (!game.isBoardFull() && !game.isWinner()) {
            game.printBoard();

            System.out.print("Player " + game.currentPlayer + ", enter your move (column [0-6]): ");
            int col = scanner.nextInt();

            if (game.makeMove(col)) {
                game.changePlayer();
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }

        game.printBoard();

        if (game.isWinner()) {
            System.out.println("Player " + game.currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}
