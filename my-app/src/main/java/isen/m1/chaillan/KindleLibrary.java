package isen.m1.chaillan;

/**
 * KindleLibrary
 */
public class KindleLibrary extends BookFactory {

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
        if (super.checkTitleBook(title)) {
            return new KindleBook(title);
        }
        // TODO creer une exception
        System.out.println("le livre n'a pas de titre");
        return new KindleBook();
    }

    @Override
    public Book createBook() {

        return new KindleBook();
    }

}