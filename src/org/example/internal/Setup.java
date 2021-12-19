package org.example.internal;

/**
 * Represents a command that needs a separate setup method for the terminal.
 *
 * @author Macintosh_Fan
 */
public interface Setup {
    /**
     * Sets up the command.
     *
     * @param terminal the main terminal
     */
    void setup(Terminal terminal);
}
