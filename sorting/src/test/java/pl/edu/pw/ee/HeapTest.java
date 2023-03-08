package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HeapTest {

    private List<Double> arr;
    private Random random;
    private Heap<Double> heap;

    private static final long SEED = 173;
    private static final double EPS = 0;
    private static final int TEST_SIZE = 1000;

    private double generateRandomWithSeed() {
        return random.nextDouble() * 1000;

    }

    @Before
    public void setUp() {
        random = new Random(SEED);
        arr = new ArrayList<>();
        heap = new Heap<>(arr);
        heap.build();
    }

    @Test
    public void emptyArrayToConstructor() {
        //given
        arr = new ArrayList<>();

        //when
        heap = new Heap<>(arr);

        //then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullToConstructor() {
        //given
        arr = null;

        //when
        heap = new Heap<>(arr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void putTestNullToHeap() {
        //given 
        Double a = null;

        //when
        heap.put(a);

        //then
        assert false;
    }

    @Test
    public void putTestdoubleDataTypeToHeapThenPopTest() {
        //given 
        double a = 1.0;

        //when
        heap.put(a);

        //then
        double expected = 1.0;
        assertEquals(expected, heap.pop(), EPS);
    }

    @Test
    public void putTestDoubleObjectToHeapThenPopTest() {
        //given 
        Double a = 1.0;

        //when
        heap.put(a);

        //then
        double expected = 1.0;
        assertEquals(expected, heap.pop(), EPS);
    }

    @Test
    public void putRandomDoublesToHeapThenPopTest() {
        //given
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            data.add(generateRandomWithSeed());
        }

        //when
        for (Double n : data) {
            heap.put(n);
        }

        //then
        List<Double> result = new ArrayList<>();
        result.add(heap.pop());
        for (int i = 1; i < TEST_SIZE; i++) {
            result.add(heap.pop());
            if (result.get((i - 1)) <= result.get(i)) {
                assert false;
                return;
            }
        }

        if (result.containsAll(data)) {
            assert true;
            return;
        }

        assert false;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popFromEmptyHeap() {
        //given

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popFromNotEmptyHeapButMoreTimesThanElementsInHeap() {
        //given
        Double a = 1.0;

        //when
        heap.put(a);
        heap.pop();
        heap.pop();

        //then
        assert false;
    }

    @Test
    public void heapifyWhenHeapHasOneElement_CallLikeBuildLastIteration() {
        //given
        Double a = 1.0;

        //when
        heap.put(a);
        heap.heapify(0, 1);

        assert true;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void heapifyWCallStartEqualsSize() {
        //given
        Double a = 1.0;

        //when
        heap.put(a);
        heap.heapify(1, 1);

        assert false;
    }

    @Test
    public void buildWhenRandomValuesGivenToHeap() {
        //given
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            data.add(generateRandomWithSeed());
        }
        heap = new Heap<>(data);

        //when
        heap.build();

        //then
        List<Double> result = new ArrayList<>();
        result.add(heap.pop());
        for (int i = 1; i < TEST_SIZE; i++) {
            result.add(heap.pop());
            if (result.get((i - 1)) < result.get(i)) {
                assert false;
                return;
            }
        }

        if (result.containsAll(data)) {
            assert true;
            return;
        }

        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void poppingFromHeapThatWasntBuilded() {
        //given
        heap = new Heap<>(arr);

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void puttingToHeapThatWasntBuilded() {
        //given
        heap = new Heap<>(arr);
        Double a = 1.0;

        //when
        heap.put(a);

        //then
        assert false;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void heapifyWhenHeapIsEmpty() {
        //given 
        int start = 0;
        int end = 0;

        //when
        heap.heapify(start, end);

        //then 
        assert false;
    }
}
