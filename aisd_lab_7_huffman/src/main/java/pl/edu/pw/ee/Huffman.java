package pl.edu.pw.ee;

import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.DataStreamFormat;
import pl.edu.pw.ee.utils.BitFormat;
import pl.edu.pw.ee.utils.CharFormat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Huffman {

    private final String FILE_TO_COMPRESS = "file_to_compress.txt";
    private DataManagementOption dataManger;

    private String pathToRootDir;

    public int huffman(String pathToRootDir, boolean compress) throws IOException {

        this.pathToRootDir = pathToRootDir;

        int result;
        if(compress){
            result = compress();
        }else{
            result = decompress();
        }

        return result;
    }

    private int compress() throws IOException {
        DataStreamFormat<Integer> output = new BitFormat();
        DataStreamFormat<Character> input = new CharFormat();

        input.createInput(pathToRootDir);
        input.createOutput(pathToRootDir);

        Map<Character,Integer> frequencies = new HashMap<>();

        Character c;
        while((c=input.read()) != -1){
            if(frequencies.containsKey(c)){
                frequencies.put(c,frequencies.get(c)+1);
            }else{
                frequencies.put(c,1);
            }
        }

        HuffmanTree tree = new HuffmanTree();
        tree.build(frequencies);

        for (Character character:frequencies.keySet()) {
            String code = tree.getCode(character);

        }
        return -1;
    }

    private int decompress() {

        return -1;
    }

}
