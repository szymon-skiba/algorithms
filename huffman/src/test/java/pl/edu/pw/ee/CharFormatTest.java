package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.exceptions.StreamManagerException;
import pl.edu.pw.ee.utils.CharFormat;

public class CharFormatTest {

    @Test(expected = StreamManagerException.class)
    public void createInputTestNullGiven() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.createInput(null);

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void createInputTestFileDoesntExist() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.createInput("fileDoesntExist.txt");

        //when
        assert false;
    }


    @Test(expected = StreamManagerException.class)
    public void createOutputTestNullGiven() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.createOutput(null);

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void createOutputTestFileDoesntExist() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.createOutput("fileDoesntExist.txt");

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void readWhenInputNotBuilt() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.read();

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void writeWhenOutputNotBuilt() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.write('a');

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void closeWhenInputNotBuilt() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.closeInput();

        //when
        assert false;
    }

    @Test(expected = StreamManagerException.class)
    public void closeWhenOutputNotBuilt() {
        //given
        CharFormat cf = new CharFormat();

        //then
        cf.closeOutput();

        //when
        assert false;
    }
}
