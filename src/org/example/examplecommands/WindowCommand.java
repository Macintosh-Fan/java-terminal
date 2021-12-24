package org.example.examplecommands;

import org.example.internal.Command;
import org.example.internal.Terminal;

import javax.swing.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A program that opens a window...
 *
 * @author Macintosh_Fan
 */
public class WindowCommand implements Command {
    /**
     * Called when the command is called.
     *
     * @param terminal the main terminal
     * @param args     inputted arguments by the user (might be null)
     */
    @Override
    public void onCommand(Terminal terminal, String[] args) {
        JFrame frame = new JFrame("A window");
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setSize(400, 150);

        frame.add(panel);
        frame.getContentPane().setPreferredSize(panel.getSize());
        frame.pack();

        JButton button = new JButton("Click me");
        button.setBounds(
                panel.getWidth() / 2 - (button.getPreferredSize().width / 2),
                panel.getHeight() / 2 - (button.getPreferredSize().height / 2),
                button.getPreferredSize().width,
                button.getPreferredSize().height
        );
        button.addActionListener(e -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://youtu.be/xvFZjo5PgG0"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace(terminal.stdErrStream);
                }
            }
        });
        panel.add(button);

        frame.setVisible(true);
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
        return "window";
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
        return "opens a window...";
    }
}
