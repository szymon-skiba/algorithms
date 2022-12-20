package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.exceptions.StreamManagerException;
import pl.edu.pw.ee.utils.BitFormat;
import pl.edu.pw.ee.utils.CharFormat;

public class BitFormatTest {

    @Test(expected = StreamManagerException.class)
    public void createInputTestNullGiven() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.createInput(null, 1);

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void createInputWhenBitsToIgnore_given_negative() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.createInput("src/main/resources/niemanieForTests/textToCompress.txt", -10);

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void createInputTestFileDoesntExist() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.createInput("fileDoesntExist.txt", 1);

        //when
        assert false;
    }


    @Test(expected = StreamManagerException.class)
    public void createOutputTestNullGiven() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.createOutput(null);

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void createOutputTestFileDoesntExist() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.createOutput("fileDoesntExist.txt");

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void readWhenInputNotBuilt() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.read();

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void writeWhenOutputNotBuilt() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.write('a');

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void closeWhenInputNotBuilt() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.closeInput();

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void closeWhenOutputNotBuilt() {
        //given
        BitFormat bf = new BitFormat();

        //then
        bf.closeOutput();

        //when
        assert false;
    }

}
