package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    
    @Test
    public void testMikolaj(){
        LongestCommonSubsequence lg = new LongestCommonSubsequence("MIKOLAJ","IKONA");
        System.out.println(lg.findLCS());
        lg.display();
    }
}
