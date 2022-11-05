package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.Objects;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndexOfElemInStringHash(HashTable<String> hash, String elem) {
        String fieldHashElems = "hashElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldHashElems);
            field.setAccessible(true);

            Object[] hashElems = (Object[]) field.get(hash);

            for(int i=0; i<hashElems.length; i++){
                if(Objects.equals(hashElems[i], elem)){
                    return i;
                }
            }

            return -1;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void hashWhenSizeIsZero(){
       HashLinearProbing<String> hash = new HashLinearProbing<>();
       
       System.out.println(hash.hashFunc(10, 10));
       
       assert true;
    }

    @Test
    public void should_NotAddNewElems_WhenExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(1, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putShould_throwError_WhenPassingNull() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = null;

        // when
        emptyHash.put(newEleme);

        // then
        assert false;
    }

    @Test
    public void getShould_returnNull_WhenEmptyHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String Elem = "nothing special";

        // when
        String result = emptyHash.get(Elem);

        // then
        assertNull(result);
    }

    @Test
    public void getShould_returnElemAndNotDelete() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String Elem = "nothing special";

        // when
        emptyHash.put(Elem);
        String result = emptyHash.get(Elem);
        int nOfElemsAfterGet = getNumOfElems(emptyHash);

        // then
        assert Elem.compareTo(result) == 0;
        assertEquals(1, nOfElemsAfterGet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getShould_throwError_WhenPassingNull() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String Elem = null;

        // when
        emptyHash.get(Elem);

        // then
        assert false;
    }


    @Test
    public void numOfElemsInHasTable_should_dropAfterDelete() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.delete(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(1, nOfElemsBeforePut);
        assertEquals(0, nOfElemsAfterPut);
    }

    @Test
    public void elemShould_notBeInHashTableAfterDelete() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        emptyHash.delete(newEleme);

        // then
        assertNull(emptyHash.get(newEleme));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletedPointer_InHashTableAfterDelete() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";
        String deletedPointer = "deletedElementPointer_DemonstrationPurpose";

        // when
        emptyHash.put(newEleme);
        emptyHash.delete(newEleme);
        String result = emptyHash.get(deletedPointer);

        // then
        assert false;
    }


    @Test
    public void should_addInPlaceOfDeleted() {
        // given
        HashOpenAdressing<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        int indexBeforeDelete = emptyHash.testSupportMethod_getIndexOfElem(newEleme);
        emptyHash.delete(newEleme);
        emptyHash.put(newEleme);
        int indexAfterDelete = emptyHash.testSupportMethod_getIndexOfElem(newEleme);

        // then
        assertEquals(indexBeforeDelete, indexAfterDelete);
    }
 
}
