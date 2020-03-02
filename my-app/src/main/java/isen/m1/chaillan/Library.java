package isen.m1.chaillan;


/**
 * Library
 */
public class Library extends BookFactory {

    private static Library instance;

    private Library() {

    }

    @Override
    public PaperBook createBook(String title) {
       if (super.checkTitleBook(title)) {
           return new PaperBook(title);
       }
       //TODO creer une exception 
       System.out.println("le livre n'a pas de titre");
        return new PaperBook();
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