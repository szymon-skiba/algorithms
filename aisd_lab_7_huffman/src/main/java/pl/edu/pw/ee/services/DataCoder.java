package pl.edu.pw.ee.services;

public interface DataCoder <T extends DataStreamFormat> {
    public T code(T c);
}
