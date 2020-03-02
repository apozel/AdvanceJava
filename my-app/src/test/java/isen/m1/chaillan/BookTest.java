package isen.m1.chaillan;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BookTest {

    /**
     * This test sets a title and check the following specs: 
     * - the title cannot be null: "", null, " ", " \t", "\ n" 
     * - the title must be upper case 
     * - the title cannot start or end with a space char 
     * - Check the character used are in [A-Z-a-z!?]
     * 
     */
    

    @Test
    public void testTitle() {
        String[] nullArrayStrings = { "", null, " ", "\t", "\n" };
        for (String titleString : nullArrayStrings) {
            Book b = Library.getInstance().createBook(titleString);
            assertTrue(b.getTitle().equals(null));
        }
        Book b = Library.getInstance().createBook("Lord of the Rings     ");
        assertTrue(b.getTitle().equals("LORD OF THE RINGS"));
    }
}
