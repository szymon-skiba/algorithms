package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.exceptions.StreamManagerException;

import java.io.*;

public class BitFormat {

    private final boolean ON = true;
    private final boolean OFF = false;


    private boolean INPUT_MODE = OFF;
    private boolean OUTPUT_MODE = OFF;


    private InputStream in;
    private OutputStream out;


    private int currentlyReadByte;
    private int currentlyReadByteRemainingBits;
    private int numberOfBitsToIgnore;
    private int nextReadByte;


    private int currentlyWrittenByte;
    private int currentlyWrittenByteFilledBits;


    public void createInput(String path, int numberOfBitsToIgnore) {
        if (path == null) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path : cannot be null");
        }

        if (numberOfBitsToIgnore < 0) {
            throw new StreamManagerException(this.getClass().getName() + ": Ignored bits cant be negative number");
        }

        File f = new File(path);
        if (!f.exists()) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating output");
        }

        try {
            in = new BufferedInputStream(new FileInputStream(path), 1);
        } catch (FileNotFoundException e) {
            throw new StreamManagerException(": Wrong file path while creating input: " + e.getMessage());
        }

        INPUT_MODE = ON;

        this.numberOfBitsToIgnore = numberOfBitsToIgnore;
        currentlyReadByte = 0;
        currentlyReadByteRemainingBits = 0;

        try {
            nextReadByte = in.read();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while reading data: " + e.getMessage());
        }
    }


    public void createOutput(String path) {
        if (path == null) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path : cannot be null");
        }

        File f = new File(path);
        if (!f.exists()) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating output");
        }

        try {
            out = new BufferedOutputStream(new FileOutputStream(path), 1);
        } catch (FileNotFoundException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating output: " + e.getMessage());
        }

        OUTPUT_MODE = ON;

        currentlyWrittenByte = 0;
        currentlyWrittenByteFilledBits = 0;
    }


    public int read() {
        if (INPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Input was not created");
        }

        if (currentlyReadByteRemainingBits == 0) {
            currentlyReadByte = nextReadByte;
            if (currentlyReadByte == -1)
                return -1;
            try {
                nextReadByte = in.read();
            } catch (IOException e) {
                throw new StreamManagerException(this.getClass().getName() + ": Error while reading data: " + e.getMessage());
            }
            currentlyReadByteRemainingBits = 8;
        }

        if ((nextReadByte == -1) && (currentlyReadByteRemainingBits == numberOfBitsToIgnore)) {
            return -1;
        }

        currentlyReadByteRemainingBits--;

        return (currentlyReadByte >>> currentlyReadByteRemainingBits) & 1;
    }


    public void write(int c) {
        if (OUTPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Output was not created");
        }

        if (c != 0 && c != 1)
            throw new StreamManagerException(this.getClass().getName() + ": Argument must be 1 or 0");
        currentlyWrittenByte = (currentlyWrittenByte << 1) | c;
        currentlyWrittenByteFilledBits++;

        if (currentlyWrittenByteFilledBits == 8) {
            try {
                out.write(currentlyWrittenByte);
            } catch (IOException e) {
                throw new StreamManagerException(this.getClass().getName() + ": Error while writing data: " + e.getMessage());
            }
            currentlyWrittenByte = 0;
            currentlyWrittenByteFilledBits = 0;
        }

    }

    public void closeInput() {
        if (INPUT_MODE == OFF) {
            throw new StreamManagerException("Input was not created");
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while closing input: " + e.getMessage());
        }
    }

    public int closeOutput() {
        if (OUTPUT_MODE == OFF) {
            throw new StreamManagerException("Output was not created");
        }

        int fill = 0;

        while (currentlyWrittenByteFilledBits > 0) {
            fill++;
            write(0);
        }

        try {
            out.close();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while closing output: " + e.getMessage());
        }

        return fill;
    }
}
