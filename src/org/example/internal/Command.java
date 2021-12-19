package org.example.internal;

/**
 * Represents a command for the custom terminal.
 *
 * @author Macintosh_Fan
 */
public interface Command {
    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    void onCommand(Terminal terminal, String[] args);

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
    String getName();

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
    String getUsage();

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
    String getDescription();
}
