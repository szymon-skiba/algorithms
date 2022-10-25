package pl.edu.pw.ee.performanceTests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.edu.pw.ee.QuickSort;
import pl.edu.pw.ee.services.Sorting;

public class QuickSortPerformanceTest {

    private double[] optimisticData;
    private List<Double> pesimisticData;
    private List<Double> randomData;
    private Random randomValue;
    private Sorting sorter;

    private final int TEST_SIZE = 20000;
    private static final long SEED = 200;
    private final String optimisticFile = "quickSortOptimisticPerformanceData.txt";
    private final String pesimisticFile = "quickSortPesimisticPerformanceData.txt";
    private final String randomFile = "quickSortrandomPerformanceData.txt";

    private void generateOptimistic() {
        optimisticData = new double[TEST_SIZE];
        chooseMid(0, TEST_SIZE - 1, 0, TEST_SIZE - 1);
    }

    private void chooseMid(int startId, int endId, int start, int end) {
        optimisticData[startId] = (start + end) / 2;
        if (startId < endId) {
            chooseMid(startId + 1, (startId + endId) / 2, start, (start + end) / 2 - 1);
            chooseMid((startId + endId) / 2 + 1, endId, (start + end) / 2 + 1, end);
        }
    }

    private void generatePesimistic() {
        pesimisticData = new ArrayList<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            pesimisticData.add(Double.valueOf(1000));
        }
    }

    private void generateRandom() {
        randomData = new ArrayList<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            randomData.add(randomValue.nextDouble() * 10000);
        }
    }

    @Before
    public void setUp() {
        randomValue = new Random(SEED);
        sorter = new QuickSort();
    }

    @Test
    @Ignore
    public void performanceOptimisticTest() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("src\\test\\java\\pl\\edu\\pw\\ee\\performanceTests\\testData\\" + optimisticFile);
        } catch (IOException ex) {
            System.err.println("Error while crerating test data");
            assert false;
        }

        generateOptimistic();
        writer.print("Liczba_elementow;Czas_w_nanosekundach\n");
        for (int i = 50; i <= TEST_SIZE; i += 50) {
            double[] data = new double[i];
            for (int j = 0; j < i; j++) {
                data[j] = optimisticData[j];

            }
            long start = System.nanoTime();
            sorter.sort(data);
            long end = System.nanoTime();
            writer.print((i) + ";" + (end - start) + "\n");
        }
        writer.close();
        assert true;
    }

    @Test
    @Ignore
    public void performancePesimisticTest() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("src\\test\\java\\pl\\edu\\pw\\ee\\performanceTests\\testData\\" + pesimisticFile);
        } catch (IOException ex) {
            System.err.println("Error while crerating test data");
            assert false;
        }

        generatePesimistic();
        writer.print("Liczba_elementow;Czas_w_nanosekundach\n");
        for (int i = 50; i <= TEST_SIZE; i += 50) {
            double[] data = new double[i];
            for (int j = 0; j < i; j++) {
                data[j] = pesimisticData.get(j);

            }
            long start = System.nanoTime();
            sorter.sort(data);
            long end = System.nanoTime();
            writer.print((i) + ";" + (end - start) + "\n");
        }
        writer.close();
        assert true;
    }

    @Test
    @Ignore
    public void performanceRandomTest() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("src\\test\\java\\pl\\edu\\pw\\ee\\performanceTests\\testData\\" + randomFile);
        } catch (IOException ex) {
            System.err.println("Error while crerating test data");
            assert false;
        }

        generateRandom();
        writer.print("Liczba_elementow;Czas_w_nanosekundach\n");
        for (int i = 50; i <= TEST_SIZE; i += 50) {
            double[] data = new double[i + 1];
            for (int j = 0; j < i; j++) {
                data[j] = randomData.get(j);

            }
            long start = System.nanoTime();
            sorter.sort(data);
            long end = System.nanoTime();
            writer.print((i) + ";" + (end - start) + "\n");
        }
        writer.close();
        assert true;
    }

}
