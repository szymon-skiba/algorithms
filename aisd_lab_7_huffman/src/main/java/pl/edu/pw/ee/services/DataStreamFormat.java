package pl.edu.pw.ee.services;

import pl.edu.pw.ee.HuffmanTree;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataStreamFormat<T> {

    public void createInput(String pathToRootDir) throws FileNotFoundException;
    public void createOutput(String pathToRootDir) throws FileNotFoundException;
    public T read() throws IOException;
    public void write(T c) throws IOException;

    public void closeInput() throws IOException;
    public void closeOutput() throws IOException;


}
