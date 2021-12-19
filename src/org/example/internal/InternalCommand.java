package org.example.internal;

/**
 * The superclass for all internal commands (mainly for indication).
 *
 * @author Macintosh_Fan
 */
abstract class InternalCommand implements Command {
    /**
     * Don't let public programs instantiate this class.
     */
    protected InternalCommand() {
    }
}
