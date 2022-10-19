package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

//java generics, szybszy qsort, sortowanie przez scalanie  
public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
    }

    @Override
    public void put(T item) {
        data.add(item);
        int i = data.size()-1;
        while(i>0){
            if(i%2==0){
                if(data.get((i-1)/2).compareTo(item) < 0 && (i-1)/2 >=0 ){
                    swap((i-1)/2,i);
                    i = (i-1)/2;
                }
                else if(data.get((i-2)/2).compareTo(item) < 0 && (i-2)/2 >=0){
                    swap((i-1)/2,i);
                    i = (i-1)/2;
                }
            }
        }
        
    }

    @Override
    public T pop() {
        // TODO
    }

    @Override
    public void build() {
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {
        // TODO
    }

}
