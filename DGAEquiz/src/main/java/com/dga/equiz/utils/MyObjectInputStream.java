package com.dga.equiz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectInputStream extends ObjectInputStream {

    public MyObjectInputStream() throws IOException, SecurityException {
    }

    public MyObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    public void readStreamHeader() throws IOException {
    }
}
