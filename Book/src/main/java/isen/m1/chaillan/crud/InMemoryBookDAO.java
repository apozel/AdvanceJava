package isen.m1.chaillan.crud;

import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import isen.m1.chaillan.exception.NoSuchBookException;
import isen.m1.chaillan.exception.NoSuchIsnbException;
import isen.m1.chaillan.util.Book;

/**
 * InMemoryBookDAO
 */
public class InMemoryBookDAO implements BookDAO {

    private static InMemoryBookDAO instance = null;

    private static final SortedSet<Book> bookSet = new ConcurrentSkipListSet<Book>();

    private InMemoryBookDAO() {
    }

    public static synchronized InMemoryBookDAO getInstance() {
        if (instance == null) {
            instance = new InMemoryBookDAO();
        }
        return instance;
    }

    @Override
    public boolean deleteBook(Book book) throws NoSuchBookException {
        try {
            bookSet.remove(findByIsbn(book.getIsbn()));
            return true;
        } catch (Exception e) {
            throw new NoSuchBookException("this book didn't exist");
        }

    }

    @Override
    public Set<Book> findAll() {
        return bookSet;
    }

    @Override
    public Book findByIsbn(String isbn) throws NoSuchIsnbException {
        for (Book book : bookSet) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        throw new NoSuchIsnbException("this book didn't exist");
    }

    @Override
    public boolean insertBook(Book book) {
        if (bookSet.add(book))
            return true;

        return false;
    }

    @Override
    public boolean updateBook(Book book) throws NoSuchBookException {

        if (bookSet.contains(book)) {
            bookSet.remove(book);
            bookSet.add(book);
            return true;
        }

        throw new NoSuchBookException("this book didn't exist");
    }

    public void clear(){
        bookSet.clear();
    }

}