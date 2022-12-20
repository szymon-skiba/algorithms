package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.exceptions.FilesNotMangedException;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.DataManagmentOptionTestOptions;
import pl.edu.pw.ee.services.FileManagement;
import pl.edu.pw.ee.utils.CharFormat;
import pl.edu.pw.ee.utils.HuffmanDecompress;
import pl.edu.pw.ee.utils.HuffmanFileManager;

public class HuffmanDecompressTest extends DataManagmentOptionTestOptions {
    @Override
    public DataManagementOption createDataMangment() {
        return new HuffmanDecompress();
    }

    @Test
    public void runTest() {
        assert super.runTests();
    }

    @Test
    public void frequenciesModified() {
        //given
        HuffmanDecompress hd = new HuffmanDecompress();
        FileManagement fm = null;
        try {
            fm = new HuffmanFileManager("src/main/resources/testsFiles/", false);
        } catch (FilesNotMangedException e) {
            System.err.println("Test Files Changed");
            assert false;
        }

        CharFormat cf = new CharFormat();
        cf.createOutput(fm.getSupportFile());
        cf.write('1');
        cf.closeOutput();

        //when
        int result;
        result = hd.transformData(fm);

        //then
        assert result == -1;

    }
}
