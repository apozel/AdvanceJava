package isen.m1.chaillan.factory;

import java.text.DateFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import isen.m1.chaillan.exception.TitleIsEmptyException;
import isen.m1.chaillan.exception.TitleIsNullException;
import isen.m1.chaillan.util.Book;

/**
 * BookFactory
 */
public abstract class BookFactory {

    private DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG,Locale.FRANCE);
    /**
     * factory method to create a book instance
     * @param title of the book to create 
     * @return the book instance if the title is valid 
     * @throws Exception title is not vali
     */
    public abstract Book createBook(String title) throws TitleIsEmptyException,TitleIsNullException;
    public abstract Book createBook();

    public boolean checkTitleBook(String title){
        return !StringUtils.isEmpty(title) && !StringUtils.isBlank(title);
    }

    public String toUpperCaseTitle(String title) {
        return title.toUpperCase();
    }

    public DateFormat getDateformat() {
        return dateformat;
    }

    public void setDateformat(DateFormat dateformat) {
        this.dateformat = dateformat;
    }
    
    
}