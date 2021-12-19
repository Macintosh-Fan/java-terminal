package org.example.internal;

/**
 * The internal exit command.
 *
 * @author Macintosh_Fan
 */
class ExitCommand extends InternalCommand {
    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        terminal.stdOutStream.println("Goodbye");
        System.exit(0);
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
        return "exit";
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
        return "exits the terminal.";
    }
}
