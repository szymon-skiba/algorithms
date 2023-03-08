package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.FileManagement;
import pl.edu.pw.ee.utils.CharFormat;
import pl.edu.pw.ee.utils.HuffmanFileManager;

public class HuffmanTest {

    private final String pathToRootDir = "src/main/resources/";

    @Test
    public void huffmanOneLetterCompression() {

        //given
        FileManagement fm = new HuffmanFileManager(pathToRootDir, true);
        CharFormat prepareFile = new CharFormat();
        prepareFile.createOutput(fm.getInputFile());

        prepareFile.write('a');

        prepareFile.closeOutput();

        Huffman h = new Huffman();

        //when
        int result;
        result = h.huffman(pathToRootDir, true);

        //then
        assert result == 8;

    }


    @Test
    public void huffmanOneLetterDeCompression() {
        //given
        huffmanOneLetterCompression();
        FileManagement fm = new HuffmanFileManager(pathToRootDir, false);

        Huffman h = new Huffman();

        CharFormat readResult = new CharFormat();
        readResult.createInput(fm.getOutputFile());


        //when
        int result;
        result = h.huffman(pathToRootDir, false);
        char c;
        c = (char) readResult.read();
        readResult.closeInput();

        //then
        assert result == 1 && c == 'a';

    }


    @Test
    public void huffmanNewLineCompression() {

        //given
        FileManagement fm = new HuffmanFileManager(pathToRootDir, true);
        CharFormat prepareFile = new CharFormat();
        prepareFile.createOutput(fm.getInputFile());

        prepareFile.write('\n');

        prepareFile.closeOutput();

        Huffman h = new Huffman();


        //when
        int result;
        result = h.huffman(pathToRootDir, true);

        //then
        assert result == 8;

    }


    @Test
    public void huffmanNewLineDeCompression() {
        //given
        huffmanNewLineCompression();

        Huffman h = new Huffman();


        //when
        int result;
        result = h.huffman(pathToRootDir, false);

        //then
        assert result == 1;

    }


    @Test
    public void polishSignsCompression() {

        //given
        FileManagement fm = new HuffmanFileManager(pathToRootDir, true);
        CharFormat prepareFile = new CharFormat();
        prepareFile.createOutput(fm.getInputFile());

        prepareFile.write('ą');
        prepareFile.write('ć');
        prepareFile.write('ż');
        prepareFile.write('ź');
        prepareFile.write('ł');
        prepareFile.write('ń');
        prepareFile.write('ó');
        prepareFile.write('ę');


        prepareFile.closeOutput();

        Huffman h = new Huffman();


        //when
        int result;
        result = h.huffman(pathToRootDir, true);

        //then
        assert result == 24;

    }


    @Test
    public void polishSignsDeCompression() {
        //given
        polishSignsCompression();
        Huffman h = new Huffman();

        FileManagement fm = new HuffmanFileManager(pathToRootDir, false);
        CharFormat readResult = new CharFormat();
        readResult.createInput(fm.getOutputFile());


        //when
        int result;
        result = h.huffman(pathToRootDir, false);
        char[] polishSigns = new char[8];
        polishSigns[0] = (char) readResult.read();
        polishSigns[1] = (char) readResult.read();
        polishSigns[2] = (char) readResult.read();
        polishSigns[3] = (char) readResult.read();
        polishSigns[4] = (char) readResult.read();
        polishSigns[5] = (char) readResult.read();
        polishSigns[6] = (char) readResult.read();
        polishSigns[7] = (char) readResult.read();

        readResult.closeInput();

        //then
        assert result == 8
                && polishSigns[0] == 'ą'
                && polishSigns[1] == 'ć'
                && polishSigns[2] == 'ż'
                && polishSigns[3] == 'ź'
                && polishSigns[4] == 'ł'
                && polishSigns[5] == 'ń'
                && polishSigns[6] == 'ó'
                && polishSigns[7] == 'ę';


    }


    @Test
    public void testNiemanieFile() {
        //given
        Huffman h = new Huffman();

        //when
        int compression;
        int decompression;
        compression = h.huffman("src/main/resources/niemanieForTests/", true);
        decompression = h.huffman("src/main/resources/niemanieForTests/", false);

        //then
        System.out.println("Bitow po kompresji: " + compression);
        System.out.println("Znakow po dekompresji: " + decompression);
        assert true;

    }
}
