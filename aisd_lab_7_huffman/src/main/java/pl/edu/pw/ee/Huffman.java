package pl.edu.pw.ee;

import pl.edu.pw.ee.data_structures.HuffmanTree;
import pl.edu.pw.ee.data_structures.HuffmanTreeNode;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.FileManagement;
import pl.edu.pw.ee.utils.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Type.LEAF;

public class Huffman {
    private final String INPUT_BIN_FILE = "file_to_decompress.txt";
    private final String OUTPUT_BIN_FILE = "file_to_compress.txt";
    private final String INPUT_CHAR_FILE = "file_to_read.txt";
    private final String OUTPUT_CHAR_FILE = "file_to_write.txt";
    private final String FREQUENCIES_FILE = "frequencies.txt";

    private int fill;

    private FileManagement huffmanFileManager;

    public int huffman(String pathToRootDir, boolean compress) throws IOException {

        huffmanFileManager= new HuffmanFileManager(pathToRootDir,compress);

        DataManagementOption dmo;
        if(compress){
            dmo= new HuffmanCompress();
        }else{
            dmo = new HuffmanDecompress();
        }
        System.out.println("bp ");

//        int result;
//        if (compress) {
//            result = compress();
//        } else {
//            result = decompress();
//        }
        int result = -1;
        try {
            result = dmo.transformData(huffmanFileManager);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private int compress() throws IOException {
        BitFormat output = new BitFormat();
        CharFormat input = new CharFormat();


        input.createInput(huffmanFileManager.getInputFile());
        output.createOutput(huffmanFileManager.getOutputFile());


        Map<Character, Integer> frequencies = new HashMap<>();

        int c;
        while ((c = input.read())!=-1) {
            if (frequencies.containsKey((char)c)) {
                if(c == 'm'){
                    System.out.println("m: "+ frequencies.get((char)c));
                }
                frequencies.put((char) c, frequencies.get((char)c) + 1);
            } else {
                frequencies.put((char) c, 1);
            }
        }

        input.closeInput();



        input.createInput(huffmanFileManager.getInputFile());

        HuffmanTree tree = new HuffmanTree(frequencies);
        while (( c = input.read())!=-1) {
            ArrayList<Integer> code = tree.getCode((char) c);
            for (Integer i : code) {
                try {
                    output.write(i);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
        }

        int fill = output.closeOutput();
        saveFrequencies(frequencies, fill);
        return -1;
    }

    private void saveFrequencies(Map<Character, Integer> frequencies, int fill) throws IOException {
        CharFormat output = new CharFormat();
        output.createOutput(huffmanFileManager.getSupportFile());
        output.write((char) fill);
        for (Character c : frequencies.keySet()) {
            output.write(c);
            char[] n = frequencies.get(c).toString().toCharArray();
            for (char digit : n) {
                System.out.print(digit);
                output.write(digit);
            }
            System.out.println();
            output.write('\n');
        }

        output.closeOutput();
    }

    private int decompress() throws IOException {
        BitFormat input = new BitFormat();
        CharFormat output = new CharFormat();

        HashMap<Character, Integer> frequencies = readFrequencies();

        input.createInput(huffmanFileManager.getInputFile(), fill);
        output.createOutput(huffmanFileManager.getOutputFile());



        HuffmanTree tree = new HuffmanTree(frequencies);

        HuffmanTreeNode node = tree.getRoot();

        int c;
        while ((c = input.read()) != -1) {
            System.out.print(c);
            if (c == 1) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
            if (node.getType()==LEAF) {
                output.write(node.getCharacter());
                node = tree.getRoot();
                System.out.println();
            }

        }
        input.closeInput();
        output.closeOutput();
        return -1;
    }

    private HashMap<Character, Integer> readFrequencies() throws IOException {
        CharFormat input = new CharFormat();
        input.createInput(huffmanFileManager.getSupportFile());
        HashMap<Character, Integer> frequencies = new HashMap<>();

        fill = input.read();

        int c;
        while ((c = input.read())!=-1) {
            StringBuilder value = new StringBuilder();
            int tmp;
            while ((char)(tmp = input.read()) != '\n') {
                value.append(Character.getNumericValue(tmp));
            }

            frequencies.put((char)c, Integer.valueOf(value.toString()));

        }
        return frequencies;
    }

}
