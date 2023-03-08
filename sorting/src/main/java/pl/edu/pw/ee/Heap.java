package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

//java generics, szybszy qsort, sortowanie przez scalanie  
public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;
    private boolean isbuilded;

    public Heap(List<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input array cannot be null!");
        }

        this.data = data;
        this.isbuilded = false;
    }

    @Override
    public void put(T item) {
        if (!this.isbuilded) {
            throw new IllegalStateException("Heap is not builded");
        }
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to heap!");
        }
        data.add(item);
        int i = data.size() - 1;

        while (data.get((i - 1) / 2).compareTo(item) < 0 && i > 0) {
            swap((i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    @Override
    public T pop() {
        if (!this.isbuilded) {
            throw new IllegalStateException("Heap is not builded");
        }

        int last_index = data.size() - 1;
        if (last_index < 0) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }

        swap(0, last_index);
        T x = data.remove(last_index);

        if (last_index > 0) {
            heapify(0, last_index);
        }

        return x;
    }

    @Override
    public void build() {
        int n = data.size();
        this.isbuilded = true;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }

    }

    @Override
    public void heapify(int startId, int endId) {
        if (startId < 0 || endId <= 0 || startId >= data.size() || endId > data.size()) {
            throw new IndexOutOfBoundsException("Start or End are not valid");
        }

        if ((startId * 2 + 2) < endId) {
            if (data.get(startId * 2 + 1).compareTo(data.get(startId * 2 + 2)) > 0 && data.get(startId).compareTo(data.get(startId * 2 + 1)) < 0) {
                swap(startId, startId * 2 + 1);
                heapify(startId * 2 + 1, endId);
            } else if (data.get(startId).compareTo(data.get(startId * 2 + 2)) < 0) {
                swap(startId, startId * 2 + 2);
                heapify(startId * 2 + 2, endId);
            }
        } else if ((startId * 2 + 1) < endId) {
            if (data.get(startId).compareTo(data.get(startId * 2 + 1)) < 0) {
                swap(startId, startId * 2 + 1);
                heapify(startId * 2 + 1, endId);
            }
        }
    }

    private void swap(int firstId, int secondId) {
        T firstVal = data.get(firstId);
        data.set(firstId, data.get(secondId));
        data.set(secondId, firstVal);
    }
}
