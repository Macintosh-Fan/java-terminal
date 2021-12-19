package org.example.internal;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The main terminal.
 *
 * @author Macintosh_Fan
 */
public final class Terminal implements Runnable {
    /**
     * The commands in the terminal.
     */
    final Command[] COMMANDS;

    /**
     * The standard output stream for the terminal.
     */
    public PrintStream stdOutStream = System.out;

    /**
     * The standard error stream for the terminal.
     */
    public PrintStream stdErrStream = System.err;

    /**
     * The standard input stream for the terminal.
     */
    public InputStream stdInStream = System.in;

    /**
     * Instantiates a new terminal.
     *
     * @param commands additional commands for the terminal
     */
    public Terminal(Command... commands) {
        InternalCommand[] internalCommands = {
                new HelpCommand(),
                new ExitCommand()
        };

        if (commands.length != 0) {
            COMMANDS = new Command[commands.length + internalCommands.length];
            for (int i = 0; i < COMMANDS.length; i++) {
                if (i < internalCommands.length) {
                    COMMANDS[i] = internalCommands[i];
                } else {
                    COMMANDS[i] = commands[i - internalCommands.length];
                }
            }
        } else {
            COMMANDS = internalCommands;
        }
    }

    /**
     * Starts the terminal.
     *
     * @param separateThread to run in a separate thread
     */
    public void start(boolean separateThread) {
        stdOutStream.println("Starting the terminal...");
        
        for (Command command : COMMANDS) {
            if (!(command instanceof InternalCommand)) {
                break;
            }

            if (command instanceof Setup setup) {
                setup.setup(this);
            }
        }
        
        if (separateThread) {
            Thread thread = new Thread(this);
            thread.start();
        } else {
            run();
        }
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(stdInStream);
        for (Command command : COMMANDS) {
            if (command instanceof HelpCommand helpCommand) {
                stdOutStream.println(helpCommand.helpMessage);
            }
        }
        
        boolean invalidCommand;
        String input;
        String[] args;
        while (true) {
            stdOutStream.print("\n> ");
            input = scanner.nextLine();
            args = input.split(" ", 2);
            invalidCommand = true;
            for (Command command : COMMANDS) {
                if (input.startsWith(command.getName())) {
                    if (args.length == 2) {
                        command.onCommand(this, args[1].split(" "));
                    } else {
                        command.onCommand(this, null);
                    }
                    invalidCommand = false;
                }
            }

            if (invalidCommand) {
                stdErrStream.println("Not a valid command (case-sensitivity error?)!");
            }
        }
    }
}
