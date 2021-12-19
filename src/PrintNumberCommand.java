import org.example.internal.Command;
import org.example.internal.Terminal;

/**
 * Prints a number to the console.
 */
public class PrintNumberCommand implements Command {
    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        if (args == null) {
            terminal.stdErrStream.println("Missing parameter 'x'!");
        } else {
            try {
                terminal.stdOutStream.println(Long.parseLong(args[0]));
            } catch (NumberFormatException nfe) {
                terminal.stdErrStream.println("Invalid number (too small/big or contained non-numeric characters?)!");
            }
        }
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
        return "printNumber";
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
        return "x";
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
        return "prints parameter 'x' passed to the standard output.";
    }
}
