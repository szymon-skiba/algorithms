package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.utils.CharFormat;

import java.io.IOException;

public class HuffmanTest {

    @Test
    public void huffmanTest() throws IOException {
        Huffman h = new Huffman();
        try {
            System.out.println(h.huffman("src/main/resources/", true));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void huffmanTest2() throws IOException {
        Huffman h = new Huffman();
        try {
            System.out.println(h.huffman("src/main/resources/", false));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
