package isen.m1.chaillan;

/**
 * Library
 */
public class Library implements BookFactory {

    private static Library instance;

    private Library() {

    }

    @Override
    public PaperBook createBook(String title) {
       
        return new PaperBook(title);
    }

    @Override
    public PaperBook createBook() {
        
        return new PaperBook();
    }

    /**
     * @return the instance
     */
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    
}