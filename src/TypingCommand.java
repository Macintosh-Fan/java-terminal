import org.example.internal.Command;
import org.example.internal.Terminal;

import java.util.Scanner;

/**
 * Allows a user to check their WPM.
 *
 * @author Macintosh_Fan
 */
public class TypingCommand implements Command {
    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        Scanner scanner = new Scanner(terminal.stdInStream);
        terminal.stdOutStream.println("Press enter when done.\nPreparing typing...");
        for (int i = 3; i != 0; i--) {
            terminal.stdOutStream.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                terminal.stdErrStream.println("Countdown interrupted!");
                return;
            }
        }
        terminal.stdOutStream.print("GO!\n");
        long start = System.currentTimeMillis();
        String text = scanner.nextLine();
        long end = System.currentTimeMillis();
        double timeInSeconds = ((double) (end - start) / 1000);
        terminal.stdOutStream.printf("WPM: %.1f\n", (text.length() / 5 / (timeInSeconds / 60)));
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
        return "typing";
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
        return "checks your WPM for something that you type.";
    }
}
