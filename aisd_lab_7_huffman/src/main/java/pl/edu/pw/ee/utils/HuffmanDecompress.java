package pl.edu.pw.ee.utils;

import pl.edu.pw.ee.data_structures.HuffmanTree;
import pl.edu.pw.ee.data_structures.HuffmanTreeNode;
import pl.edu.pw.ee.exceptions.StreamManagerException;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.FileManagement;

import java.util.HashMap;
import java.util.Map;

import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Type.LEAF;
import static pl.edu.pw.ee.services.HuffmanTreeNodeSpecs.Type.ROOT;

public class HuffmanDecompress implements DataManagementOption {

    private FileManagement fileManagement;
    private Map<Character, Integer> frequencies;
    private int fill;


    private void readFrequencies() throws StreamManagerException{
        CharFormat input = new CharFormat();
        input.createInput(fileManagement.getSupportFile());

        frequencies = new HashMap<>();

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

        input.closeInput();
    }

    private void deleteOutput() throws StreamManagerException{
        CharFormat output = new CharFormat();
        output.createOutput(fileManagement.getOutputFile());
        output.closeOutput();
    }

    @Override
    public int transformData(FileManagement fileManagement) {
        if (fileManagement == null) {
            throw new IllegalArgumentException("File management cant be null");
        }

        this.fileManagement = fileManagement;

        int c;
        int result = 0;

        try {
            readFrequencies();

            BitFormat input = new BitFormat();
            CharFormat output = new CharFormat();

            input.createInput(fileManagement.getInputFile(), fill);
            output.createOutput(fileManagement.getOutputFile());

            HuffmanTree tree = new HuffmanTree(frequencies);

            HuffmanTreeNode node = tree.getRoot();

            while ((c = input.read()) != -1) {

                if(node == null){
                    input.closeInput();
                    output.closeOutput();
                    deleteOutput();
                    return -1;
                }

                if (c == 1) {
                    node = node.getRight();
                } else {
                    node = node.getLeft();
                }
                if (node.getType()==LEAF) {
                    result ++;
                    output.write(node.getCharacter());
                    node = tree.getRoot();
                }
            }

            if(node.getType()!=LEAF && node.getType()!=ROOT){
                deleteOutput();
                input.closeInput();
                output.closeOutput();
                return -1;
            }

            input.closeInput();
            output.closeOutput();

        } catch (StreamManagerException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return -1;
        }  catch (IllegalCallerException e){
            System.out.println(e.getMessage());
            return -1;
        }

        return result;
    }
}
