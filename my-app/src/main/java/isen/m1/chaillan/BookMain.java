package isen.m1.chaillan;

import java.util.ArrayList;

import isen.m1.chaillan.factory.KindleLibrary;
import isen.m1.chaillan.factory.Library;
import isen.m1.chaillan.util.Book;

/**
 * BookMain
 */
public class BookMain {

    public static void main(final String[] args) {

        ArrayList<Book> listeLivre = new ArrayList<Book>();
        try {
            for (int i = 0; i < 10; i++) {
                listeLivre.add(Library.getInstance().createBook("livre papier " + i));
                listeLivre.add(KindleLibrary.getInstance().createBook("livre kindle " + i));
            }

            final Book livre = Library.getInstance().createBook("Don juan");
            final Book livreBook = KindleLibrary.getInstance().createBook("bible");
            System.out.println("livre 1 : " + livre.getTitle());
            System.out.println("livre 2 : " + livreBook.getTitle());

            for (Book book : listeLivre) {
                System.out.println(book.getTitle());
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}