/**
 * A utility class with utility methods.
 *
 * @author Macintosh_Fan
 */
public class Utility {
    /**
     * Don't let anyone instantiate this class.
     */
    private Utility() {
    }

    /**
     * Checks if a number is in the range of two numbers.
     *
     * @param n the number to check
     * @param a the lower number
     * @param b the higher number
     * @return {@code true} if n is within the range of a to b.
     * @throws IllegalArgumentException if {@code a} is greater than {@code b}
     */
    public static boolean isInRange(long n, long a, long b) {
        if (a > b) {
            throw new IllegalArgumentException("Parameter 'a' must be smaller than 'b'");
        }
        return n >= a && n <= b;
    }
}
