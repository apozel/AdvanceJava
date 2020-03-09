package isen.m1.chaillan;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

import isen.m1.chaillan.exception.KindleIsnbFormatException;
import isen.m1.chaillan.exception.TitleIsEmptyException;
import isen.m1.chaillan.exception.TitleIsNullException;

/**
 * Unit test for simple App.
 */
public class BookTest {

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

}
