package pl.edu.pw.ee;

import org.junit.Ignore;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {

    private final int[] hashTableSizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private final String performanceTestDataPath = "src\\data\\performanceTestData.txt";
    private final String performanceTestResultsPath = "src\\data\\performanceTestResults.txt";
    private HashTable<String> hashTable;


    private List<String> readFromPerformanceTestData() throws IOException {
        List<String> data = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(performanceTestDataPath));
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading test data");
            return null;
        }

        try {
            String line = br.readLine();

            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            br.close();
            System.err.println("Error while reading test dat2a");
            return null;
        }

        br.close();
        return data;
    }

    @Test
    @Ignore
    public void performanceTestForHashTableSizes() {
        PrintWriter writer = null;
        List<String> data = null;
        try {
            data = readFromPerformanceTestData();
        } catch (IOException e) {
            System.err.println("Error while reading test data");
            assert false;
        }
        assert data != null;

        try {
            writer = new PrintWriter(performanceTestResultsPath);
        } catch (IOException ex) {
            System.err.println("Error while creating test data");
            assert false;
        }

        writer.print("Rozmiar;Czas_w_milisekundach\n");

        for (int i : hashTableSizes) {
            long sum = 0;
            for (int k = 0; k < 30; k++) {
                hashTable = new HashLinearProbing<>(i);

                long start = System.currentTimeMillis();
                for (String text : data) {
                    hashTable.put(text);
                }
                long end = System.currentTimeMillis();
                sum += (end - start);
            }
            writer.print((i) + ";" + (sum / 30.0) + "\n");
        }
        writer.close();
        assert true;
    }
}

