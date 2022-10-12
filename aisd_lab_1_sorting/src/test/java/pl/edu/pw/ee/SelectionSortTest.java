package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {
    
    private static final double EPS = 0;
    private Sorting sorter;

    @Before
    public void setUp() {
        sorter = new SelectionSort();
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
    public void oneElemntArray(){
        //given
        double[] nums = {1};
        
        //when
        sorter.sort(nums);
        
        //then
         double[] expected = {1};
        assertArrayEquals(expected,nums,EPS);
    }
            
            
    @Test
    public void sortedAscArrayGiven() {
        //given
        double[] nums = {1,2,3,4,5};

        
        //when
        sorter.sort(nums);
        
        //then
        double[] expected = {1,2,3,4,5};
        assertArrayEquals(expected,nums,EPS);
        
    }
    
    @Test 
    public void sortedDescArrayGiven(){
        //given
        double[] nums = {5,4,3,2,1};

        
        //when
        sorter.sort(nums);
        
        //then
        double[] expected = {1,2,3,4,5};
        assertArrayEquals(expected,nums,EPS);
    }
}
