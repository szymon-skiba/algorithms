package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashListChainingTest {

    private HashTable<String> hashTable;
    private final int DELTA = 0;
    private final int hashListSizeForTEST = 10;

    @Before
    public void setUp(){
        hashTable = new HashListChaining<>(hashListSizeForTEST);
    }
    @Test(expected = IllegalArgumentException.class)
    public void initializeWithZeroSize(){
        //given
        int size = 0;

        //when
        hashTable = new HashListChaining<>(size);

        //then
        assert false;
    }

    @Test
    public void addAndThenGet(){
        //given
        String text = "test";
        String textFromGet;

        //when
        hashTable.add(text);
        textFromGet = hashTable.get("test");

        //then
        assertEquals(text,textFromGet);
    }

    @Test
    public void addTheSameValueTwoTimes(){
        //given
        HashListChaining<Double> hashListChaining = new HashListChaining<>(hashListSizeForTEST);
        Double number = 1.0;
        double loadFactor;

        //when
        hashListChaining.add(number);
        hashListChaining.add(number);
        loadFactor = hashListChaining.countLoadFactor();

        //then
        double expected = 1.0/hashListSizeForTEST;
        assertEquals(expected,loadFactor,DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullAndGetException(){
        //given
        String text = null;

        //when
        hashTable.add(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullAndThrowException(){
        //given
        String text = null;

        //when
        hashTable.get(text);

        //then
        assert false;
    }

    @Test
    public void getValueThatDoesntExist(){
        //given
        String text = "test";
        String got;

        //when
        got = hashTable.get(text);

        //then
        assertNull(got);
    }

    @Test
    public void add50TimesAndGetThemCorrectly(){
        //given

        //when
        for(double i=0;i<50.0;i++){
            hashTable.add(String.valueOf(i));
        }

        //then
        for(double i=0;i<50.0;i++){
            assertEquals(String.valueOf(i),hashTable.get(String.valueOf(i)));
        }
    }

    @Test
    public void add50TimesAndDeleteThemCorrectly(){
        //given

        //when
        for(double i=0;i<50.0;i++){
            hashTable.add(String.valueOf(i));
        }
        for(double i=0;i<50.0;i++){
            hashTable.delete(String.valueOf(i));
        }

        //then
        for(double i=0;i<50.0;i++){
            assertNull(hashTable.get(String.valueOf(i)));
        }
    }



    @Test
    public void addAndThenDelete(){
        //given
        String text = "test";
        String textFromGet;

        //when
        hashTable.add(text);
        hashTable.delete(text);
        textFromGet = hashTable.get(text);

        //then
        assertNull(textFromGet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void passNullToDelete(){
        //given
        String text = null;

        //when
        hashTable.delete(text);

        //then
        assert false;
    }

    @Test
    public void deleteValueThatDoesntExistNothingShouldHappen(){
        //given
        String text = "test";

        //when
        hashTable.delete(text);

        //then
        assert true;
    }

    @Test
    public void countLoadFactorReturnsCorrect() {
        //given
        HashListChaining<Double> hashListChaining = new HashListChaining<>(10);
        Double number = 1.0;
        double loadFactor;

        //when
        hashListChaining.add(number);
        loadFactor = hashListChaining.countLoadFactor();

        //then
        double expected = 1.0/hashListSizeForTEST;
        assertEquals(expected,loadFactor,DELTA);
    }
}
