package isen.m1.chaillan;

import isen.m1.chaillan.crud.DiskBookDAO;
import isen.m1.chaillan.factory.KindleLibrary;
import isen.m1.chaillan.factory.Library;
import isen.m1.chaillan.util.Book;

/**
 * BookMainIO
 */
public class BookMainIO {

    public static void main(String[] args) {
        DiskBookDAO memory = null;
        memory = DiskBookDAO.getInstance();
        for (Book livre : memory.findAll()) {
            System.out.println(livre.getTitle());
        }
        memory.insertBook(KindleLibrary.getInstance().createBook("moby dick", "eXXXX"));
        memory.insertBook(KindleLibrary.getInstance().createBook("Bible", "eXXXT"));
        memory.insertBook(Library.getInstance().createBook("lord of the ring", "pXXXX"));
        memory.insertBook(Library.getInstance().createBook("game of thrones", "pXXXT"));

        memory.close();
    }
}