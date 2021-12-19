# Java Terminal
A simple terminal-like Java application made by Macintosh_Fan.

## TODOs
### One thing you must do...
READ THE JAVADOCS PROVIDED!

### How do I run this program?
You can run this program using the JAR file, or using `src/Main.java`.

### How do I create custom commands?
#### Initial setup
To create a custom command, create a Java class inside `src` that implements the `org.example.internal.Command` interface.
Implement the required methods; the included Javadoc can help a bit.
Once done with your custom command, add it into the `Command[]` in `Main.main`, and run the program!

#### How does `Command#onCommand` work?
This method is called when your command is called in the terminal (not system terminal, but program terminal).
The first parameter, `Terminal terminal`, allows you to work with the terminal in simple ways: using the standard streams;
**however, DO NOT CALL THE `start()` AND `run()` METHODS INSIDE YOUR CUSTOM COMMAND!**
For the second parameter, `String[] args`, this provides you the arguments that the user put in; items are separated by " "s.
**This parameter may be null if the user did not put in any arguments, so be sure to do a null check.**
