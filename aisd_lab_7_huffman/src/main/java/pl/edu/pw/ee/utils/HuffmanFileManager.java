package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.exceptions.FilesNotMangedException;
import pl.edu.pw.ee.services.FileManagement;

import java.io.File;
import java.io.IOException;

public class HuffmanFileManager implements FileManagement {

    private final String pathToRootDir;
    private final String INPUT_BIN_FILE = "resultOfCompression.txt";
    private final String OUTPUT_BIN_FILE = "resultOfCompression.txt";
    private final String INPUT_CHAR_FILE = "textToCompress.txt";
    private final String OUTPUT_CHAR_FILE = "resultOfDeCompression.txt";
    private final String FREQUENCIES_ALL_FILE = "frequencies.txt";


    private String inputFile;
    private String outputFile;
    private String frequenciesFile;


    @Override
    public String getInputFile() {
        return inputFile;
    }

    @Override
    public String getOutputFile() {
        return outputFile;
    }

    @Override
    public String getSupportFile() {
        return frequenciesFile;
    }


    public HuffmanFileManager(String pathToRootDir, boolean compress) throws FilesNotMangedException {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        this.pathToRootDir = pathToRootDir;
        if (compress) {
            prepareCompressionFiles();
        } else {
            prepareDecompressionFiles();
        }
    }

    private void prepareDecompressionFiles() {
        String checkedInput = pathToRootDir + INPUT_BIN_FILE;
        String checkedOutput = pathToRootDir + OUTPUT_CHAR_FILE;
        String checkedFrequencies = pathToRootDir + FREQUENCIES_ALL_FILE;

        validateFile(checkedInput);


        validateFile(checkedOutput);


        File fq = new File(checkedFrequencies);
        if(!fq.exists() || !fq.canRead() || !fq.isFile()){
            throw new FilesNotMangedException("Error while verifying file for decompression <" + checkedOutput + ">: doesn't exist or is not readable or is not a file");
        }

        inputFile = checkedInput;
        outputFile = checkedOutput;
        frequenciesFile = checkedFrequencies;

    }

    private void prepareCompressionFiles() throws FilesNotMangedException{
        String checkedInput = pathToRootDir + INPUT_CHAR_FILE;
        String checkedOutput = pathToRootDir + OUTPUT_BIN_FILE;
        String checkedFrequencies = pathToRootDir + FREQUENCIES_ALL_FILE;

        validateFile(checkedInput);


        validateFile(checkedOutput);


        validateFile(checkedFrequencies);

        inputFile = checkedInput;
        outputFile = checkedOutput;
        frequenciesFile = checkedFrequencies;

    }

    private void validateFile(String filePath){
        File f = new File(filePath);
        if (f.exists()) {
            if (!f.canRead() || !f.isFile()) {
                throw new FilesNotMangedException("Error while verifying file <" + filePath + ">: not readable or not a file");
            }
        } else {
            try {
                if(!f.createNewFile()){
                    throw new FilesNotMangedException("Error while creating file <" + filePath + ">");
                }

            } catch (IOException e) {
                throw new FilesNotMangedException("Error while creating file <" + filePath + ">: "+e.getMessage());
            }
        }
    }


}
