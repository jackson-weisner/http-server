package weisner.httpserver;

import java.util.Arrays;

public class Bytes {
    // helper method to combine byte arrays together
    public static byte[] combine(byte[] b1, byte[] b2) {
        byte[] result = Arrays.copyOf(b1, b1.length+b2.length);
        System.arraycopy(b2, 0, result, b1.length, b2.length);
        return result;
    }
}
