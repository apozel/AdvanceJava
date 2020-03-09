package isen.m1.chaillan;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import isen.m1.chaillan.crud.InMemoryBookDAO;
import isen.m1.chaillan.exception.NoSuchBookException;
import isen.m1.chaillan.exception.NoSuchIsnbException;
import isen.m1.chaillan.factory.KindleLibrary;
import isen.m1.chaillan.factory.Library;
import isen.m1.chaillan.util.Book;
import isen.m1.chaillan.util.KindleBook;
import isen.m1.chaillan.util.PaperBook;

/**
 * DAOtest
 */
public class DAOtest {

    public InMemoryBookDAO memory;

    @Before
    public void initBook() {
        memory = InMemoryBookDAO.getInstance();
        memory.insertBook(KindleLibrary.getInstance().createBook("moby dick", "eXXXX"));
        memory.insertBook(KindleLibrary.getInstance().createBook("Bible", "eXXXX"));
        memory.insertBook(Library.getInstance().createBook("lord of the ring", "pXXXX"));
        memory.insertBook(Library.getInstance().createBook("game of thrones", "pXXXX"));
    }

    @After
    public void resetBook() {
        for (Book livre : this.memory.findAll()) {
            this.memory.deleteBook(livre);
        }
    }

    /**
     * test : -good isbn
     */
    @Test
    public void testFind() {
        KindleBook bookToFind = KindleLibrary.getInstance().createBook("moby dick", "eXXXX");
        Book findBook = memory.findByIsbn(bookToFind.getIsbn());
        assertTrue(findBook.equals(bookToFind));

        PaperBook bookToFind2 = Library.getInstance().createBook("lord of the ring", "pXXXX");
        Book findBook2 = memory.findByIsbn(bookToFind2.getIsbn());
        assertTrue(findBook2.equals(bookToFind));


    }

    @Test(expected = NoSuchIsnbException.class)
    public void testFindError() {
        KindleBook bookToFind = KindleLibrary.getInstance().createBook("moby dick", "eTXXX");
        memory.findByIsbn(bookToFind.getIsbn());
    }

    @Test
    public void testDeleteTrue() {
        KindleBook bookToDelete = KindleLibrary.getInstance().createBook("moby dick", "eXXXX");
        assertTrue(memory.deleteBook(bookToDelete));

    }

    @Test(expected = NoSuchBookException.class)
    public void testDeleteFalse() {
        KindleBook bookToDelete = KindleLibrary.getInstance().createBook("moby dick", "eTTTT");
        memory.deleteBook(bookToDelete);
    }

    @Test
    public void testUpdate() {
        KindleBook bookToFind = KindleLibrary.getInstance().createBook("moby dick", "eXXXX");
        Book updateBook = memory.findByIsbn(bookToFind.getIsbn());
        updateBook.setTitle("mobydick");
        updateBook.setPrice((double) 20f);
        
        memory.updateBook(updateBook);
        assertTrue(updateBook.getPrice().equals((double) 20f));
        assertTrue(updateBook.getTitle().equals("mobydick"));
        assertTrue(bookToFind.equals(updateBook));
    }
    @Test(expected = NoSuchBookException.class)
    public void testUpdateError() {
        KindleBook updatedBook = KindleLibrary.getInstance().createBook("mobydick", "eTXXX");
        memory.updateBook(updatedBook);
    }
}