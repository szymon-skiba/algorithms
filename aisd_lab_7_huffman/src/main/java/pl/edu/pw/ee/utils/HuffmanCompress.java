package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.data_structures.HuffmanTree;
import pl.edu.pw.ee.exceptions.StreamManagerException;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.FileManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCompress implements DataManagementOption {


    private FileManagement fileManagement;
    private Map<Character, Integer> frequencies;


    private void readInput() throws StreamManagerException {
        CharFormat input = new CharFormat();
        input.createInput(fileManagement.getInputFile());

        frequencies = new HashMap<>();

        int c;
        while ((c = input.read()) != -1) {
            if (frequencies.containsKey((char) c)) {
                frequencies.put((char) c, frequencies.get((char) c) + 1);
            } else {
                frequencies.put((char) c, 1);
            }
        }

        input.closeInput();
    }


    private int createOutput() throws StreamManagerException {
        BitFormat output = new BitFormat();
        output.createOutput(fileManagement.getOutputFile());

        CharFormat input = new CharFormat();
        input.createInput(fileManagement.getInputFile());

        HuffmanTree tree = new HuffmanTree(frequencies);

        int c;
        int result = 0;

        while ((c = input.read()) != -1) {

            ArrayList<Integer> code = tree.getCode((char) c);

            if (code == null) {
                input.closeInput();
                output.closeOutput();
                deleteOutput();
                return -1;
            }

            for (Integer i : code) {
                result++;
                output.write(i);
            }
        }

        int fill = output.closeOutput();
        input.closeInput();

        saveFrequencies(frequencies, fill);

        return result + fill;
    }

    private void deleteOutput() throws StreamManagerException {
        BitFormat output = new BitFormat();
        output.createOutput(fileManagement.getOutputFile());
        output.closeOutput();
    }

    private void saveFrequencies(Map<Character, Integer> frequencies, int fill) throws StreamManagerException {
        CharFormat output = new CharFormat();
        output.createOutput(fileManagement.getSupportFile());
        output.write((char) fill);

        for (Character c : frequencies.keySet()) {
            output.write(c);
            char[] n = frequencies.get(c).toString().toCharArray();
            for (char digit : n) {
                output.write(digit);
            }
            output.write('\n');
        }

        output.closeOutput();
    }

    @Override
    public int transformData(FileManagement fileManagement) {
        if (fileManagement == null) {
            throw new IllegalArgumentException("File management cant be null");
        }
        this.fileManagement = fileManagement;
        int result = -1;

        try {
            readInput();
        } catch (StreamManagerException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        try {
            result = createOutput();
        } catch (StreamManagerException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return result;
    }
}
