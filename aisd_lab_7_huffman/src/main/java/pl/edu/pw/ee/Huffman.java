package pl.edu.pw.ee;

import pl.edu.pw.ee.exceptions.FilesNotMangedException;
import pl.edu.pw.ee.services.DataManagementOption;
import pl.edu.pw.ee.services.FileManagement;
import pl.edu.pw.ee.utils.HuffmanCompress;
import pl.edu.pw.ee.utils.HuffmanDecompress;
import pl.edu.pw.ee.utils.HuffmanFileManager;

import java.io.IOException;

public class Huffman {

    public int huffman(String pathToRootDir, boolean compress){
        FileManagement huffmanFileManager;
        try {
            huffmanFileManager = new HuffmanFileManager(pathToRootDir, compress);
        } catch (FilesNotMangedException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        DataManagementOption dmo;
        if (compress) {
            dmo = new HuffmanCompress();
        } else {
            dmo = new HuffmanDecompress();
        }

        int result = -1;
        try {
            result = dmo.transformData(huffmanFileManager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
