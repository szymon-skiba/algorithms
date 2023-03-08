package pl.edu.pw.ee.services;


public abstract class DataManagmentOptionTestOptions {

    protected abstract DataManagementOption createDataMangment();

    public boolean nullGivenThrowsException() {
        //given
        DataManagementOption dm = createDataMangment();

        //when
        try {
            dm.transformData(null);
        } catch (IllegalArgumentException e) {
            return true;
        }

        //then
        return false;
    }

    public boolean runTests() {
        return nullGivenThrowsException();
    }
}
