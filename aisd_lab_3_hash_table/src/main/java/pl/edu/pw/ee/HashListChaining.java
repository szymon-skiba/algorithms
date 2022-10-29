package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private final Elem<T>[] hashElems;
    private int nElem;

    private static class Elem<T extends Comparable<T>> {

        private T value;
        private Elem<T> next;

        Elem(T value, Elem<T> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if(size == 0){
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(T value) {
        if(value == null){
            throw new IllegalArgumentException("There has to be object passed, not null");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem<>(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if(value == null){
            throw new IllegalArgumentException("There has to be object passed, not null");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];
        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : (T) nil;
    }

    @Override
    public void delete(T value) {
        if(value == null){
            throw new IllegalArgumentException("There has to be object passed, not null");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];
        Elem<T> elemLast = nil;

        while (elem != nil && !elem.value.equals(value)) {
            elemLast = elem;
            elem = elem.next;
        }

        if (elemLast != nil && elem != nil) {
            elemLast.next = elem.next;
        }else if(elem!=nil){
            hashElems[hashId] = nil;
        }
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }
}
