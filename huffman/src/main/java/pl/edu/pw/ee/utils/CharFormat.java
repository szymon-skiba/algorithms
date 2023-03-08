package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.exceptions.StreamManagerException;

import java.io.*;

public class CharFormat {

    private final boolean ON = true;
    private final boolean OFF = false;


    private boolean INPUT_MODE = OFF;
    private boolean OUTPUT_MODE = OFF;

    private BufferedWriter out;
    private BufferedReader in;

    public void createInput(String path) {

        if (path == null) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path : cannot be null");
        }

        File f = new File(path);
        if (!f.exists()) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating output");
        }

        try {
            this.in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating input: " + e.getMessage());
        }

        INPUT_MODE = ON;
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
            this.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        } catch (FileNotFoundException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Wrong file path while creating output: " + e.getMessage());
        }

        OUTPUT_MODE = ON;
    }


    public int read() {
        if (INPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Input was not created");
        }
        int c;

        try {
            c = in.read();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while reading data: " + e.getMessage());
        }

        return c;
    }


    public void write(char c) {
        if (OUTPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Output was not created");
        }

        try {
            out.write(c);
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while writing data: " + e.getMessage());
        }
    }


    public void closeInput() {
        if (INPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Output was not created");
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while closing input: " + e.getMessage());
        }
    }


    public void closeOutput() {
        if (OUTPUT_MODE == OFF) {
            throw new StreamManagerException(this.getClass().getName() + ": Output was not created");
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new StreamManagerException(this.getClass().getName() + ": Error while closing output: " + e.getMessage());
        }
    }
}
