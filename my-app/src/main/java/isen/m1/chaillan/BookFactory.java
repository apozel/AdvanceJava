package isen.m1.chaillan;

import org.apache.commons.lang3.StringUtils;

/**
 * BookFactory
 */
public abstract class BookFactory {

    public abstract Book createBook(String title);
    public abstract Book createBook();

    public boolean checkTitleBook(String title){
        return !title.isEmpty() && !title.isBlank();
    }
}