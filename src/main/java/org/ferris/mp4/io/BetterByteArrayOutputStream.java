package org.ferris.mp4.io;

import java.io.ByteArrayOutputStream;

public class BetterByteArrayOutputStream extends ByteArrayOutputStream {

    public byte[] getBuffer() {
        return buf;
    }
}
