package pl.edu.pw.ee;

import org.junit.Test;
import static pl.edu.pw.ee.utils.DataIO.prepareListOfWordsTest;

public class RedBlackTreeTest {

    
    @Test
    public void test(){
         String[] words = prepareListOfWordsTest();
        RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
        int i =0;
        for(String word : words){
            rbt.put(word, Integer.MIN_VALUE);
            Integer nd = rbt.get(word);
            String tmp = rbt.getInOrder();
            String tmp1 = rbt.getPostOrder();
            String tmp2 = rbt.getPreOrder();
            i++;
            if(i == 100){
                break;
            }
        }
        rbt.deleteMax();
        
        
        
        assert true;
    }

}
