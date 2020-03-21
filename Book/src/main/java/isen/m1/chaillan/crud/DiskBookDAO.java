package isen.m1.chaillan.crud;

import java.util.Set;

import isen.m1.chaillan.exception.NoSuchBookException;
import isen.m1.chaillan.exception.NoSuchIsnbException;
import isen.m1.chaillan.util.Book;

/**
 * DiskBookDAO
 */
public class DiskBookDAO implements BookDAO, Database {

    private static DiskBookDAO instance = null;
    private InMemoryBookDAO books = InMemoryBookDAO.getInstance();

    private DiskBookDAO() {
    }

    public static synchronized DiskBookDAO getInstance() {
        if (instance == null) {
            instance = new DiskBookDAO();
        }
        return instance;
    }

    @Override
    public boolean deleteBook(Book book) throws NoSuchBookException {
        return this.books.deleteBook(book);
    }

    @Override
    public Set<Book> findAll() {
        return this.books.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) throws NoSuchIsnbException {
        return this.books.findByIsbn(isbn);
    }

    @Override
    public boolean insertBook(Book book) {
        return this.books.deleteBook(book);
    }

    @Override
    public boolean updateBook(Book book) throws NoSuchBookException {
       return this.books.updateBook(book);
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void open() {
        // TODO Auto-generated method stub

    }

}