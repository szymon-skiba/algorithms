package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.services.DataManagementOption;

public class HuffmanCompress implements DataManagementOption {

    private final String OUTPUT_FILE_NAME = "compressed_data.txt";
    private final String INPUT_FILE_NAME = "data_to_compress.txt";

    public HuffmanCompress() {

    }

    @Override
    public void readInput(String pathToRootDir) {

    }

    @Override
    public int createOutput() {

        return 0;
    }
}
