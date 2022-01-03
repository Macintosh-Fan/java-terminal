package org.example.examplecommands.chess;

/**
 * The superclass of all Chess pieces.
 *
 * @author Macintosh_Fan
 */
public abstract class ChessPiece {
    /**
     * The Chess piece as a {@code char} to print.
     */
    final char CHESS_PIECE;

    /**
     * Is the piece white?
     */
    boolean white;

    /**
     * Constructs the Chess piece.
     *
     * @param white is the piece white?
     */
    ChessPiece(boolean white) {
        if (white) {
            CHESS_PIECE = getWhitePiece();
        } else {
            CHESS_PIECE = getBlackPiece();
        }
        this.white = white;
    }

    /**
     * Checks if a Chess piece move is valid (if overridden, or it just returns {@code true}).
     * <p>
     * For developers: it is recommended that you override this method
     *
     * @param chessBoardArray the Chess board (as an array)
     * @param oldPlaceNumber the old place index number (eg: '2' in e2)
     * @param oldPlaceLetter the old place index letter (eg: 'e' in e2)
     * @param newPlaceNumber the new place index number (eg: '4' in e4)
     * @param newPlaceLetter the new place index letter (eg: 'e' in e4)
     * @return {@code true} if the move is valid
     */
    boolean move(ChessPiece[][] chessBoardArray, int oldPlaceNumber, int oldPlaceLetter, int newPlaceNumber, int newPlaceLetter) {
        return true;
    }

    /**
     * Gets the white piece of the figure.
     *
     * @return the white piece of the figure
     */
    abstract char getWhitePiece();

    /**
     * Gets the black piece of the figure.
     *
     * @return the black piece of the figure
     */
    abstract char getBlackPiece();

    /**
     * Returns the exact chess piece ascii character for the displayed Chess board.
     *
     * @return the chess piece ascii character as a {@link String}
     */
    @Override
    public final String toString() {
        return String.valueOf(CHESS_PIECE);
    }
}
