package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.Arrays;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final T deleted = (T) new Deleted();
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;

    private class Deleted implements Comparable<T> {
        String val = "deletedElementPointer_DemonstrationPurpose";

        @Override
        public int compareTo(T o) {
            return val.compareTo(String.valueOf(o));
        }

        @Override
        public String toString() {
            return val;
        }
    }

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);
        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        int hashIdCheckDeleted = -1;
        int fisrtHashId = hashId;

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].compareTo(newElem) == 0) {
                hashElems[hashId] = newElem;
                return;
            }
            if (deleted.compareTo(hashElems[hashId]) == 0 && hashIdCheckDeleted == -1) {
                hashIdCheckDeleted = hashId;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (hashId == fisrtHashId) {
                if(hashIdCheckDeleted != -1){
                    break;
                }
                doubleResize();
                hashIdCheckDeleted = -1;
                i = 0;
                hashId = hashFunc(key, i);
            }
        }

        if (hashIdCheckDeleted != -1) {
            hashElems[hashIdCheckDeleted] = newElem;
        } else {
            hashElems[hashId] = newElem;
        }
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        int firstHashId = hashId;

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].compareTo(elem) == 0) {
                return hashElems[hashId];
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if (hashId == firstHashId) {
                break;
            }
        }

        return nil;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].compareTo(elem) == 0) {
                hashElems[hashId] = deleted;
                this.nElems--;
                break;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
        if (deleted.compareTo(newElem) == 0) {
            throw new IllegalArgumentException("Input cannot be deleted pointer");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        T[] previousHashElems = Arrays.copyOf(this.hashElems, this.size);
        int previousSize = this.size;
        this.size *= 2;
        this.hashElems = (T[]) new Comparable[this.size];
        this.nElems = 0;
        for (int i = 0; i < previousSize; i++) {
            if (previousHashElems[i] != nil) {
                put(previousHashElems[i]);
            }
        }
    }

    public int testSupportMethod_getIndexOfElem(T elem) {
        for (int i = 0; i < this.size; i++) {
            if (hashElems[i] == elem) {
                return i;
            }
        }
        return -1;
    }
}