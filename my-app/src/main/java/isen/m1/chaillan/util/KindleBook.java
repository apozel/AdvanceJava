package isen.m1.chaillan.util;

import org.apache.commons.lang3.StringUtils;

import isen.m1.chaillan.exception.KindleIsnbFormatException;

/**
 * KindleBook
 */
public class KindleBook extends Book {

    public KindleBook(String title) {
        super(title);
    }

    public KindleBook() {
    }

    @Override
    public void setIsbn(String isbn) throws KindleIsnbFormatException {
        if (StringUtils.startsWith(isbn, "e")) {
            super.setIsbn(isbn);
        } else {
            throw new KindleIsnbFormatException("must start with \"e\"");
        }
    }
}
