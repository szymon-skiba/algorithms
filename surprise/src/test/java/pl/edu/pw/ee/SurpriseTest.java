package pl.edu.pw.ee;

import org.junit.Test;

public class SurpriseTest {
    
    @Test
    public void testCountChanges() {
        Surprise s = new Surprise();
        int result = s.countChanges("MMMMMMMMMMMK", 2);
        System.out.println(result);
    }
    
}
