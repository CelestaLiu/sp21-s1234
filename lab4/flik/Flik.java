package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(int a, int b) {
        boolean isEqual = (a == b);
        System.out.println(isEqual);
        return a == b;
    }

    public static void main(String[] args) {
        isSameNumber(128, 128);
    }
}
