package pl.edu.pw.ee.exceptions;

public class NotSupportedDataManagementException extends RuntimeException{

    public NotSupportedDataManagementException() {
        super("Not supported data management option");
    }
}
