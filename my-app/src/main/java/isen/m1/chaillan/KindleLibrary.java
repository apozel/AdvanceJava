package isen.m1.chaillan;

import java.text.DateFormat;

import isen.m1.chaillan.exception.TitleIsEmptyException;
import isen.m1.chaillan.exception.TitleIsNullException;

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
    public KindleBook createBook(String title) throws TitleIsEmptyException, TitleIsNullException {
        if (title != null) {
            if (super.checkTitleBook(title)) {
                KindleBook returnBook = new KindleBook(super.toUpperCaseTitle(title));
                if (this.getDateformat() != null) {
                    returnBook.setFormat(this.getDateformat());
                }
                return returnBook;
            }
            throw new TitleIsEmptyException("title is empty");
        }
        throw new TitleIsNullException("title is null");
    }

    @Override
    public KindleBook createBook() {

        return new KindleBook();
    }

    @Override
    public DateFormat getDateformat() {
       
        return super.getDateformat();
    }

    @Override
    public void setDateformat(DateFormat dateformat) {
        
        super.setDateformat(dateformat);
    }

}