package isen.m1.chaillan;

import java.text.DateFormat;

import isen.m1.chaillan.exception.TitleIsEmptyException;
import isen.m1.chaillan.exception.TitleIsNullException;

/**
 * Library
 */
public class Library extends BookFactory {

    private static Library instance;

    private Library() {

    }

    @Override
    public PaperBook createBook(String title) throws TitleIsEmptyException, TitleIsNullException {
        if (title != null) {
            if (super.checkTitleBook(title)) {
                PaperBook returnBook = new PaperBook(super.toUpperCaseTitle(title));
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

    @Override
    public DateFormat getDateformat() {
        // TODO Auto-generated method stub
        return super.getDateformat();
    }

    @Override
    public void setDateformat(DateFormat dateformat) {
        // TODO Auto-generated method stub
        super.setDateformat(dateformat);
    }

    
}