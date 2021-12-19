package org.example.internal;

/**
 * The internal help command.
 *
 * @author Macintosh_Fan
 */
class HelpCommand extends InternalCommand implements Setup {
    /**
     * The help message to be displayed.
     */
    String helpMessage;

    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        terminal.stdOutStream.println(helpMessage);
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
        return "help";
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
        return "sends the list of valid terminal commands.";
    }

    /**
     * Sets up the command.
     *
     * @param terminal the main terminal
     */
    @Override
    public void setup(Terminal terminal) {
        StringBuilder helpMessageBuilder = new StringBuilder();
        Command command;
        for (int i = 0; i < terminal.COMMANDS.length; i++) {
            command = terminal.COMMANDS[i];
            if (!(i == terminal.COMMANDS.length - 1)) {
                if (command.getUsage() == null) {
                    helpMessageBuilder.append(String.format("%s: %s\n", command.getName(), command.getDescription()));
                } else {
                    helpMessageBuilder.append(String.format("%s %s: %s\n", command.getName(), command.getUsage(), command.getDescription()));
                }
            } else {
                if (command.getUsage() == null) {
                    helpMessageBuilder.append(String.format("%s: %s", command.getName(), command.getDescription()));
                } else {
                    helpMessageBuilder.append(String.format("%s %s: %s", command.getName(), command.getUsage(), command.getDescription()));
                }
            }
        }
        helpMessage = helpMessageBuilder.toString();
    }
}
