package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.DataManagmentOptionTestOptions;
import pl.edu.pw.ee.utils.HuffmanDecompress;

public class HuffmanCompressTest extends DataManagmentOptionTestOptions {
    @Override
    public DataManagementOption createDataMangment() {
        return new HuffmanDecompress();
    }

    @Test
    public void runTest() {
        assert super.runTests();
    }
}
