package org.example.examplecommands.chess;

/**
 * The king Chess piece.
 *
 * @author Macintosh_Fan
 */
public class King extends ChessPiece {
    /**
     * Constructs the Chess piece.
     *
     * @param white is the piece white?
     */
    King(boolean white) {
        super(white);
    }

    /**
     * Gets the white piece of the figure.
     *
     * @return the white piece of the figure
     */
    @Override
    char getWhitePiece() {
        return '♔';
    }

    /**
     * Gets the black piece of the figure.
     *
     * @return the black piece of the figure
     */
    @Override
    char getBlackPiece() {
        return '♚';
    }
}
