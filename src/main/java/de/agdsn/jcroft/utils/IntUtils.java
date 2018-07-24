package de.agdsn.jcroft.utils;

public class IntUtils {

    /**
     * private constructor, so others cannot create an instance of this class
     */
    protected IntUtils() {
        //
    }

    public static void requireNumberGreaterNull(int i, String description) {
        if (i <= 0) {
            throw new IllegalArgumentException(description + " has to be > 0.");
        }
    }

    public static void requireNumberGreaterNull(int i) {
        IntUtils.requireNumberGreaterNull(i, "integer");
    }

}
