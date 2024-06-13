import java.util.*;

public class TicTac {
    private static final int size = 3;
    private static char[][] board = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
    private static char presentMarker;
    private static int presentPlayer;
    private static int playerWon;

    // Board creation
    public static void drawBoard() {
        System.out.println("-------------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // checking whether the place is occupied or not
    public static boolean placeMarker(int slot) {
        int row = (slot - 1) / size;
        int col = (slot - 1) % size;

        if (board[row][col] != 'X' && board[row][col] != 'O') {
            board[row][col] = presentMarker;
            return true;
        }
        return false;
    }

    // check for the winner
    public static int winner() {
        for (int i = 0; i < size; i++) {
            // Check rows and columns
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return presentPlayer;
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return presentPlayer;
        }
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return presentPlayer;
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return presentPlayer;

        return 0;
    }

    // changing the players marker after their turn
    public static void swapPlayerAndMarker() {
        if (presentMarker == 'X') {
            presentMarker = 'O';
        } else {
            presentMarker = 'X';
        }

        if (presentPlayer == 1) {
            presentPlayer = 2;
        } else {
            presentPlayer = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Player 1, choose your marker: ");
        char markerP1 = sc.next().charAt(0);

        presentPlayer = 1;
        presentMarker = markerP1;
        // printing the board before the player marks the slot
        drawBoard();

        for (int i = 0; i < size * size; i++) {
            System.out.print("It's player " + presentPlayer + "'s turn. Enter your slot: ");
            int slot = sc.nextInt();

            if (slot < 1 || slot > 9) {
                System.out.println("Invalid slot! Try again.");
                i--;
                continue;
            }

            if (!placeMarker(slot)) {
                System.out.println("Slot already occupied! Try again.");
                i--;
                continue;
            }

            // printing the board after the player marks their slot
            drawBoard();
            playerWon = winner();

            if (playerWon == 1) {
                System.out.println("Player 1 won! Congratulations!");
                break;
            } else if (playerWon == 2) {
                System.out.println("Player 2 won! Congratulations!");
                break;
            }

            swapPlayerAndMarker();
        }

        if (playerWon == 0) {
            System.out.println("It's a tie!");
        }
    }
}
