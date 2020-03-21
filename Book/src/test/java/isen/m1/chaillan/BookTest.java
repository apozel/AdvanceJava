package isen.m1.chaillan;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import isen.m1.chaillan.crud.DiskBookDAO;
import isen.m1.chaillan.crud.InMemoryBookDAO;
import isen.m1.chaillan.exception.KindleIsnbFormatException;
import isen.m1.chaillan.exception.NoSuchBookException;
import isen.m1.chaillan.exception.NoSuchIsnbException;
import isen.m1.chaillan.exception.TitleIsEmptyException;
import isen.m1.chaillan.exception.TitleIsNullException;
import isen.m1.chaillan.factory.KindleLibrary;
import isen.m1.chaillan.factory.Library;
import isen.m1.chaillan.util.Book;
import isen.m1.chaillan.util.KindleBook;
import isen.m1.chaillan.util.PaperBook;

/**
 * Unit test for simple App.
 */
public class BookTest {

    public InMemoryBookDAO memory;
    public DiskBookDAO diskMemory;

    @Before
    public void initBook() {
        memory = InMemoryBookDAO.getInstance();
        memory.insertBook(KindleLibrary.getInstance().createBook("moby dick", "eXXXX"));
        memory.insertBook(KindleLibrary.getInstance().createBook("Bible", "eXXXT"));
        memory.insertBook(Library.getInstance().createBook("lord of the ring", "pXXXX"));
        memory.insertBook(Library.getInstance().createBook("game of thrones", "pXXXT"));
    }

    @After
    public void resetBook() {
        this.memory.clear();
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
        assertTrue(findBook2.equals(bookToFind2));

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
    public void testDeleteFalse() throws NoSuchBookException {
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

    /**
     * This test sets a title and check the following specs: - the title cannot be
     * null: "", null, " ", " \t", "\ n" - the title must be upper case - the title
     * cannot start or end with a space char - Check the character used are in
     * [A-Z-a-z!?]
     * 
     */

    @Test(expected = TitleIsEmptyException.class)
    public void testTitleError() throws TitleIsEmptyException, TitleIsNullException {

        Library.getInstance().createBook("");

    }

    @Test(expected = TitleIsNullException.class)
    public void testTitleError2() throws TitleIsEmptyException, TitleIsNullException {

        Library.getInstance().createBook(null);

    }

    @Test(expected = TitleIsEmptyException.class)
    public void testTitleError3() throws TitleIsEmptyException, TitleIsNullException {

        Library.getInstance().createBook(" ");

    }

    @Test(expected = TitleIsEmptyException.class)
    public void testTitleError4() throws TitleIsEmptyException, TitleIsNullException {

        Library.getInstance().createBook("\t");

    }

    public void testTitleError5() throws TitleIsEmptyException, TitleIsNullException {

        Library.getInstance().createBook("\n");

    }

    @Test
    public void testTitleUpperCase() {
        Book b = null;
        try {
            b = Library.getInstance().createBook("Lord of the Rings");
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(b.getTitle().equals("LORD OF THE RINGS"));
    }

    @Test
    public void testDateFormat() {
        try {

            Date issueDate = new GregorianCalendar(1955, Calendar.OCTOBER, 20).getTime();
            Book b = Library.getInstance().createBook("Lord of the Rings");
            b.setIsbn("XXXXXX");
            b.setIssueDate(issueDate);
            System.out.println(b);
            assertTrue(b.toString().contains("20 octobre 1955"));

            Library.getInstance().setDateformat(DateFormat.getDateInstance(DateFormat.LONG, Locale.US));
            b = Library.getInstance().createBook("Lord of the Rings");
            b.setIsbn("XXXXXX");
            b.setIssueDate(issueDate);
            assertTrue(b.toString().contains("October 20, 1955"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(expected = KindleIsnbFormatException.class)
    public void testIsbnKindle() {
        KindleLibrary.getInstance().createBook("le livre").setIsbn("XXXX");
    }

    @Test
    public void testPrintError() {
        try {
            KindleBook book = KindleLibrary.getInstance().createBook(null);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
