package weisner.httpserver;

import java.util.Arrays;
import java.util.List;

public class Bytes {
    public static byte[] convert(List<Byte> bytes) {
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }

    public static byte[] combine(byte[] b1, byte[] b2) {
        byte[] result = Arrays.copyOf(b1, b1.length+b2.length);
        System.arraycopy(b2, 0, result, b1.length, b2.length);
        return result;
    }
}
