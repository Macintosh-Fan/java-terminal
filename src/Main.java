import org.example.examplecommands.ChessCommand;
import org.example.examplecommands.PrintNumberCommand;
import org.example.examplecommands.TypingCommand;
import org.example.examplecommands.WindowCommand;
import org.example.internal.Command;
import org.example.internal.Terminal;

/**
 * The main class.
 *
 * @author Macintosh_Fan
 */
public final class Main {
    /**
     * Don't let anyone instantiate this class.
     */
    private Main() {
    }

    /**
     * The main method.
     *
     * @param args does nothing
     */
    public static void main(String[] args) {
        Command[] commands = {
                new PrintNumberCommand(),
                new TypingCommand(),
                new WindowCommand(),
                new ChessCommand()
        };
        Terminal terminal = new Terminal(commands);
        terminal.start(false);
    }
}
