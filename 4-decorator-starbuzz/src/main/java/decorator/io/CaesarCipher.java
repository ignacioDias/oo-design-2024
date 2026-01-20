package decorator.io;

import java.io.*;

public class CaesarCipher extends FilterInputStream {
    private final int code;

    public CaesarCipher(InputStream in, int code) {
        super(in);
        this.code = code;
    }

    @Override
    public int read() throws IOException {
        int c = in.read();
        if(c == -1) return -1;
        if((char) c == ' ') return c;
        return c + code;
    }

    @Override
    public int read(byte[] b, int offset, int len) throws IOException {
        int result = in.read(b, offset, len);
        if(result == -1) return -1;

        for(int i = offset; i < offset + result; i++) {
            if((char) b[i] == ' ') continue;
            b[i] = (byte) (b[i] + code);
        }
        return result;
    }
}
