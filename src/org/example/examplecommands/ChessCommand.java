package org.example.examplecommands;

import org.example.internal.Command;
import org.example.internal.Terminal;
import org.example.util.Utility;

import java.util.Scanner;

/**
 * A simple terminal chess game (does not check for king threats, so you can literally take the king).
 *
 * @author Macintosh_Fan
 */
public class ChessCommand implements Command {
    /**
     * The main terminal.
     */
    private Terminal terminal;

    /**
     * The chess board.
     */
    private String chessBoard;

    /**
     * The chess board array.
     */
    private final char[][] CHESS_BOARD_ARRAY = new char[8][8];

    /**
     * Is the game over?
     */
    boolean gameOver;

    /**
     * Did white win?
     */
    boolean whiteWin;

    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        this.terminal = terminal;
        Scanner scanner = new Scanner(terminal.stdInStream);
        updateChessBoard();
        gameOver = false;
        terminal.stdOutStream.println("""
                Chess commands (this program assumes that you know how to play Chess)
                                
                move oldPlace newPlace: moves the piece from oldPlace to newPlace. Example: "move e2 e4"
                exit: exits the game.
                """);
        String input;
        String[] inputArgs;
        while (!gameOver) {
            terminal.stdOutStream.println(chessBoard);
            terminal.stdOutStream.print("> ");
            input = scanner.nextLine();
            if (input.startsWith("move")) {
                inputArgs = input.split(" ");
                if (inputArgs.length >= 3) {
                    movePiece(inputArgs[1], inputArgs[2]);
                } else {
                    terminal.stdErrStream.println("Not enough arguments! Try again...\n");
                }
            } else if (input.startsWith("exit")) {
                terminal.stdOutStream.println("Goodbye.");
                break;
            } else {
                terminal.stdErrStream.println("Not a valid chess command! Try again...\n");
            }
        }
        if (gameOver) {
            terminal.stdOutStream.println(chessBoard);
            terminal.stdOutStream.print("Game over! ");
            if (whiteWin) {
                terminal.stdOutStream.println("White won!");
            } else {
                terminal.stdOutStream.println("Black won!");
            }
        }
        terminal.stdOutStream.println("Exiting chess...");
    }

    /**
     * Gets the name of the command. <strong>THIS METHOD CANNOT BE NULL!</strong>
     * <p>
     * Example:
     * <code>
     * public void getName() {
     * return "randomNumber";
     * }
     * </code>
     *
     * @return the command name
     */
    @Override
    public String getName() {
        return "chess";
    }

    /**
     * Gets the usage of the command. May be {@code null} if the command has no arguments.
     * <p>
     * Example:
     * <code>
     * public void getUsage() {
     * return "a b";
     * }
     * </code>
     *
     * @return the command name
     */
    @Override
    public String getUsage() {
        return null;
    }

    /**
     * Gets the description of the command. <strong>THIS METHOD CANNOT BE NULL!</strong>
     * <p>
     * Example:
     * <code>
     * public void getDescription() {
     * return "gets random number between a and b.";
     * }
     * </code>
     *
     * @return the command description
     */
    @Override
    public String getDescription() {
        return "a simple chess game in the terminal.";
    }

    /**
     * Moves the chess piece from the old place to the new place.
     *
     * @param oldPlace the old place (eg: e2)
     * @param newPlace the new place (eg: e4)
     */
    private void movePiece(String oldPlace, String newPlace) {
        byte[] oldPlaceByteArray = {
                (byte) oldPlace.charAt(0),
                (byte) oldPlace.charAt(1)
        };
        byte[] newPlaceByteArray = {
                (byte) newPlace.charAt(0),
                (byte) newPlace.charAt(1)
        };
        if (!Utility.isInRange(oldPlaceByteArray[0], 97, 104) ||
                !Utility.isInRange(oldPlaceByteArray[1], 49, 56) ||
                !Utility.isInRange(newPlaceByteArray[0], 97, 104) ||
                !Utility.isInRange(newPlaceByteArray[1], 49, 56)) {
            terminal.stdErrStream.println("A place is not valid! Try again...\n");
            return;
        }

        int oldPlaceNumber = oldPlaceByteArray[1] - 49;
        int oldPlaceLetter = oldPlaceByteArray[0] - 97;
        int newPlaceNumber = newPlaceByteArray[1] - 49;
        int newPlaceLetter = newPlaceByteArray[0] - 97;
        updateChessBoard(oldPlaceNumber, oldPlaceLetter, newPlaceNumber, newPlaceLetter);
    }

    /**
     * Updates the Chess board for the start of the game.
     */
    private void updateChessBoard() {
        chessBoard = """
                ╔═╤═╤═╤═╤═╤═╤═╤═╗╮
                ║♜│♞│♝│♛│♚│♝│♞│♜║8
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║♟│♟│♟│♟│♟│♟│♟│♟║7
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║ │ │ │ │ │ │ │ ║6
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║ │ │ │ │ │ │ │ ║5
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║ │ │ │ │ │ │ │ ║4
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║ │ │ │ │ │ │ │ ║3
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║♙│♙│♙│♙│♙│♙│♙│♙║2
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║♖│♘│♗│♕│♔│♗│♘│♖║1
                ╚═╧═╧═╧═╧═╧═╧═╧═╝┊
                ╰a┈b┈c┈d┈e┈f┈g┈h┈╯
                """;
        for (int i = 0; i < CHESS_BOARD_ARRAY.length; i++) {
            if (i == 0) {
                CHESS_BOARD_ARRAY[i] = new char[]{'♖', '♘', '♗', '♕', '♔', '♗', '♘', '♖'};
            } else if (i == 1) {
                CHESS_BOARD_ARRAY[i] = new char[]{'♙', '♙', '♙', '♙', '♙', '♙', '♙', '♙'};
            } else if (i == 6) {
                CHESS_BOARD_ARRAY[i] = new char[]{'♟', '♟', '♟', '♟', '♟', '♟', '♟', '♟'};
            } else if (i == 7) {
                CHESS_BOARD_ARRAY[i] = new char[]{'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜'};
            } else {
                CHESS_BOARD_ARRAY[i] = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
            }
        }
    }

    /**
     * Updates the Chess board during the game.
     *
     * @param oldPlaceNumber the old place number (eg: '2' in e2)
     * @param oldPlaceLetter the old place letter (eg: 'e' in e2)
     * @param newPlaceNumber the new place number (eg: '4' in e4)
     * @param newPlaceLetter the new place letter (eg: 'e' in e4)
     */
    private void updateChessBoard(int oldPlaceNumber, int oldPlaceLetter, int newPlaceNumber, int newPlaceLetter) {
        char oldPlace = CHESS_BOARD_ARRAY[oldPlaceNumber][oldPlaceLetter];
        char newPlace = CHESS_BOARD_ARRAY[newPlaceNumber][newPlaceLetter];

        if (oldPlace == ' ') {
            terminal.stdErrStream.println("Can't move from a blank space!");
            return;
        }

        CHESS_BOARD_ARRAY[newPlaceNumber][newPlaceLetter] = oldPlace;
        CHESS_BOARD_ARRAY[oldPlaceNumber][oldPlaceLetter] = ' ';

        if (newPlace == '♔' || newPlace == '♚') {
            gameOver = true;
            whiteWin = newPlace == '♚';
        }
        chessBoard = String.format("""
                ╔═╤═╤═╤═╤═╤═╤═╤═╗╮
                ║%c│%c│%c│%c│%c│%c│%c│%c║8
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║7
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║6
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║5
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║4
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║3
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║2
                ╟─┼─┼─┼─┼─┼─┼─┼─╢┊
                ║%c│%c│%c│%c│%c│%c│%c│%c║1
                ╚═╧═╧═╧═╧═╧═╧═╧═╝┊
                ╰a┈b┈c┈d┈e┈f┈g┈h┈╯
                """,
                CHESS_BOARD_ARRAY[7][0], CHESS_BOARD_ARRAY[7][1], CHESS_BOARD_ARRAY[7][2], CHESS_BOARD_ARRAY[7][3], CHESS_BOARD_ARRAY[7][4], CHESS_BOARD_ARRAY[7][5], CHESS_BOARD_ARRAY[7][6], CHESS_BOARD_ARRAY[7][7],
                CHESS_BOARD_ARRAY[6][0], CHESS_BOARD_ARRAY[6][1], CHESS_BOARD_ARRAY[6][2], CHESS_BOARD_ARRAY[6][3], CHESS_BOARD_ARRAY[6][4], CHESS_BOARD_ARRAY[6][5], CHESS_BOARD_ARRAY[6][6], CHESS_BOARD_ARRAY[6][7],
                CHESS_BOARD_ARRAY[5][0], CHESS_BOARD_ARRAY[5][1], CHESS_BOARD_ARRAY[5][2], CHESS_BOARD_ARRAY[5][3], CHESS_BOARD_ARRAY[5][4], CHESS_BOARD_ARRAY[5][5], CHESS_BOARD_ARRAY[5][6], CHESS_BOARD_ARRAY[5][7],
                CHESS_BOARD_ARRAY[4][0], CHESS_BOARD_ARRAY[4][1], CHESS_BOARD_ARRAY[4][2], CHESS_BOARD_ARRAY[4][3], CHESS_BOARD_ARRAY[4][4], CHESS_BOARD_ARRAY[4][5], CHESS_BOARD_ARRAY[4][6], CHESS_BOARD_ARRAY[4][7],
                CHESS_BOARD_ARRAY[3][0], CHESS_BOARD_ARRAY[3][1], CHESS_BOARD_ARRAY[3][2], CHESS_BOARD_ARRAY[3][3], CHESS_BOARD_ARRAY[3][4], CHESS_BOARD_ARRAY[3][5], CHESS_BOARD_ARRAY[3][6], CHESS_BOARD_ARRAY[3][7],
                CHESS_BOARD_ARRAY[2][0], CHESS_BOARD_ARRAY[2][1], CHESS_BOARD_ARRAY[2][2], CHESS_BOARD_ARRAY[2][3], CHESS_BOARD_ARRAY[2][4], CHESS_BOARD_ARRAY[2][5], CHESS_BOARD_ARRAY[2][6], CHESS_BOARD_ARRAY[2][7],
                CHESS_BOARD_ARRAY[1][0], CHESS_BOARD_ARRAY[1][1], CHESS_BOARD_ARRAY[1][2], CHESS_BOARD_ARRAY[1][3], CHESS_BOARD_ARRAY[1][4], CHESS_BOARD_ARRAY[1][5], CHESS_BOARD_ARRAY[1][6], CHESS_BOARD_ARRAY[1][7],
                CHESS_BOARD_ARRAY[0][0], CHESS_BOARD_ARRAY[0][1], CHESS_BOARD_ARRAY[0][2], CHESS_BOARD_ARRAY[0][3], CHESS_BOARD_ARRAY[0][4], CHESS_BOARD_ARRAY[0][5], CHESS_BOARD_ARRAY[0][6], CHESS_BOARD_ARRAY[0][7]);
    }
}
