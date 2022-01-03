package org.example.examplecommands.chess;

/**
 * The queen Chess piece.
 *
 * @author Macintosh_Fan
 */
public class Queen extends ChessPiece {
    /**
     * Constructs the Chess piece.
     *
     * @param white is the piece white?
     */
    Queen(boolean white) {
        super(white);
    }

    /**
     * Gets the white piece of the figure.
     *
     * @return the white piece of the figure
     */
    @Override
    char getWhitePiece() {
        return '♕';
    }

    /**
     * Gets the black piece of the figure.
     *
     * @return the black piece of the figure
     */
    @Override
    char getBlackPiece() {
        return '♛';
    }
}
