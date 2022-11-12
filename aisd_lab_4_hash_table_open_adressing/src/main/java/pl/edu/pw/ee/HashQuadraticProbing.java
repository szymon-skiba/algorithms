package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private final int a;
    private final int b;


    public HashQuadraticProbing() {
        super();
        this.a = 2;
        this.b = 3;

    }

    public HashQuadraticProbing(int size, int a, int b) {
        super(size);

        if (a == 0 || b == 0) {
            throw new IllegalArgumentException("a and b parameters can not be equal to zero.");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key % m + a * i + b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
