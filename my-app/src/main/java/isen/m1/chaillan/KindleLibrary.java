package isen.m1.chaillan;

/**
 * KindleLibrary
 */
public class KindleLibrary implements BookFactory {

    private static KindleLibrary instance;

    private KindleLibrary() {
    }

    /**
     * @return the instance
     */
    public static synchronized KindleLibrary getInstance() {
        if (instance == null) {
            instance = new KindleLibrary();
        }
        return instance;
    }

    @Override
    public Book createBook(String title) {

        return new KindleBook(title);
    }

    @Override
    public Book createBook() {

        return new KindleBook();
    }

}