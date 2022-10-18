package pl.edu.pw.ee;

import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public class InsertionSortTest {

    private Sorting sorter;
    private Random random;
    private static final double EPS = 0;
    private static final long SEED = 173;
    private static final int TEST_SIZE = 100;

    private double generateRandomWithSeed() {
        return random.nextDouble() * 1000;

    }

    @Before
    public void setUp() {
        random = new Random(SEED);
        sorter = new InsertionSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfNullTest() {
        //given 
        double[] nums = null;

        //when
        sorter.sort(nums);

        //then
        assert false;
    }

    @Test
    public void emptyArrayGiven() {
        //given 
        double[] nums = {};

        //when
        sorter.sort(nums);

        //then
        assertEquals(0, nums.length);
    }

    @Test
    public void arrayWithCharsGiven() {
        //given
        double[] nums = {'d', 'b', 'c', 'a', 1};

        //when
        sorter.sort(nums);

        //then
        double[] expected = {1, 'a', 'b', 'c', 'd'};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void oneElemntArray() {
        //given
        double[] nums = {1};

        //when
        sorter.sort(nums);

        //then
        double[] expected = {1};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void sortedAscArrayGiven() {
        //given
        double[] nums = {1, 2, 3, 4, 5};

        //when
        sorter.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void sortedDescArrayGiven() {
        //given
        double[] nums = {5, 4, 3, 2, 1};

        //when
        sorter.sort(nums);

        //then
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void worstCaseScenarioArrayGiven() {
        //given 
        double[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        //when 
        sorter.sort(nums);

        //then 
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void bestCaseScenarioArrayGiven() {
        //given 
        double[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //when 
        sorter.sort(nums);

        //then 
        double[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void randomArrayGeneretedGiven() {
        //given
        double[] nums = new double[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            nums[i] = generateRandomWithSeed();
        }

        //when 
        sorter.sort(nums);

        //then
        double[] expected = new double[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            expected[i] = generateRandomWithSeed();
        }
        for (int i = 0; i < TEST_SIZE - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                assert false;
            }
        }

        if (Arrays.asList(nums).containsAll(Arrays.asList(expected))) {
            assert true;
        }
    }

}
