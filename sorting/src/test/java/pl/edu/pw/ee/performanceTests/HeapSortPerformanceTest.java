package pl.edu.pw.ee.performanceTests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.edu.pw.ee.HeapSort;
import pl.edu.pw.ee.services.Sorting;

public class HeapSortPerformanceTest {

    private List<Double> optimisticData;
    private List<Double> pesimisticData;
    private List<Double> randomData;
    private Random randomValue;
    private Sorting sorter;

    private final int TEST_SIZE = 20000;
    private static final long SEED = 200;
    private final String optimisticFile = "heapSortOptimisticPerformanceData.txt";
    private final String pesimisticFile = "heapSortPesimisticPerformanceData.txt";
    private final String randomFile = "heapSortrandomPerformanceData.txt";

    private void generateOptimistic() {
        optimisticData = new ArrayList<>();
        for (int i = TEST_SIZE; i > 0; i--) {
            optimisticData.add(Double.valueOf(i));
        }
    }

    private void generatePesimistic() {
        pesimisticData = new ArrayList<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            pesimisticData.add(Double.valueOf(i));
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
        sorter = new HeapSort();
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
                data[j] = optimisticData.get(j);

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
