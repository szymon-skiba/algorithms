package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.services.DataStreamFormat;

import java.io.*;

public class BitFormat implements DataStreamFormat<Integer> {
    private final String INPUT_FILE = "file_to_decompress.txt";
    private final String OUTPUT_FILE = "file_to_compress.txt";

    private InputStream in;
    private OutputStream out;

    private Integer currentlyReadByte;
    private Integer currentlyReadByteRemainingBits;

    private Integer currentlyWrittenByte;
    private Integer currentlyWrittenByteFilledBits;


    @Override
    public void createInput(String pathToRootDir) throws FileNotFoundException {
        this.in = new BufferedInputStream(new FileInputStream(pathToRootDir+INPUT_FILE));

        currentlyReadByte = 0;
        currentlyReadByteRemainingBits = 8;
    }

    @Override
    public void createOutput(String pathToRootDir) throws FileNotFoundException {
        this.out = new BufferedOutputStream(new FileOutputStream(pathToRootDir+OUTPUT_FILE));

        currentlyWrittenByte = 0;
        currentlyWrittenByteFilledBits = 0;
    }

    @Override
    public Integer read() throws IOException {
        if (currentlyReadByteRemainingBits == 0) {
            currentlyReadByte = in.read();
            if (currentlyReadByte == -1)
                return -1;
            currentlyReadByteRemainingBits = 8;
        }
        currentlyReadByteRemainingBits--;

        return (currentlyReadByte >>> currentlyReadByteRemainingBits) & 1;
    }

    @Override
    public void write(Integer c) throws IOException {
        if (c != 0 && c != 1)
            throw new IllegalArgumentException("Argument musi byc wartoscia bita: 0 lub 1.");
        currentlyWrittenByte = (currentlyWrittenByte << 1) | c;
        currentlyWrittenByteFilledBits++;

        if (currentlyWrittenByteFilledBits == 8) {
            out.write(currentlyWrittenByte);
            currentlyWrittenByte = 0;
            currentlyWrittenByteFilledBits = 0;
        }

    }

    public void closeInput() throws IOException {
        in.close();
    }

    public void closeOutput() throws IOException {
        while (currentlyWrittenByteFilledBits != 0)
            write(0);
        out.close();
    }
}
