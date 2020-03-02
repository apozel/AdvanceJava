package isen.m1.chaillan;

/**
 * BookMain
 */
public class BookMain {

    public static void main(final String[] args) {
        final Book livre = Library.getInstance().createBook("Don juan");
        final Book livreBook =  KindleLibrary.getInstance().createBook("bible");
        System.out.println("livre 1 : " + livre.getTitle());
        System.out.println("livre 2 : " +livreBook.getTitle());
    }
}