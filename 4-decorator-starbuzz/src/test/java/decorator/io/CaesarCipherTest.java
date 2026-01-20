package decorator.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarCipherTest {

    @Test
    void encode_shouldShiftBytesForward() throws IOException {
        byte[] input = "abc".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(input);

        CaesarCipher cipher = new CaesarCipher(in, 1);

        assertEquals('b', cipher.read());
        assertEquals('c', cipher.read());
        assertEquals('d', cipher.read());
        assertEquals(-1, cipher.read());
    }

    @Test
    void decode_shouldShiftBytesBackward() throws IOException {
        byte[] input = "bcd".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(input);

        CaesarCipher cipher = new CaesarCipher(in, -1);
        assertEquals('a', cipher.read());
        assertEquals('b', cipher.read());
        assertEquals('c', cipher.read());
        assertEquals(-1, cipher.read());
    }
}
