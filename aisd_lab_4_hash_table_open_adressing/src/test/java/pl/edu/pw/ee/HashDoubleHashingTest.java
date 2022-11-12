package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashDoubleHashingTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashDoubleHashing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsThree() {
        // given
        int initialSize = 3;

        // when
        HashTable<Double> unusedHash = new HashDoubleHashing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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

    @Test
    public void should_NotAddNewElems_WhenExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
        String newEleme = null;

        // when
        emptyHash.put(newEleme);

        // then
        assert false;
    }

    @Test
    public void getShould_returnNull_WhenEmptyHashTable() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
        String Elem = "nothing special";

        // when
        String result = emptyHash.get(Elem);

        // then
        assertNull(result);
    }

    @Test
    public void getShould_returnElemAndNotDelete() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
        String Elem = null;

        // when
        emptyHash.get(Elem);

        // then
        assert false;
    }


    @Test
    public void numOfElemsInHasTable_should_dropAfterDelete() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashOpenAdressing<String> emptyHash = new HashDoubleHashing<>();
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

    @Test
    public void should_addInPlaceOfDeletedWithInteger() {
        // given
        HashOpenAdressing emptyHash = new HashDoubleHashing<>();
        Integer newEleme = 1;

        emptyHash.put(newEleme);
        int indexBeforeDelete = emptyHash.testSupportMethod_getIndexOfElem(newEleme);
        emptyHash.delete(newEleme);
        emptyHash.put(newEleme);
        int indexAfterDelete = emptyHash.testSupportMethod_getIndexOfElem(newEleme);

        // then
        assertEquals(indexBeforeDelete, indexAfterDelete);
    }
}
