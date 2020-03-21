package isen.m1.chaillan.crud;

import java.util.Set;

import isen.m1.chaillan.exception.NoSuchBookException;
import isen.m1.chaillan.exception.NoSuchIsnbException;
import isen.m1.chaillan.util.Book;

/**
 * BookDAO
 */
public interface BookDAO {

    /**
     * findAll()
     * @return the whole set of book 
     * 
     */
    Set<Book> findAll();
    
    /**
     * @param isbn parameter we use for looking up in the set of book
     * @return a Book if it find an entry with this isbn otherwise throw an exception
     * @throws NoSuchIsnbException
     */
    Book findByIsbn(String isbn) throws NoSuchIsnbException;
    
    /**
     * insert the book in the set on success return true, if the book was already in
     * the database this return false
     * 
     * @param book which is insert in the set
     * @return true if the book is insert in the set, false otherwise
     * 
     */
    boolean insertBook(Book book) ;
    
    /**
     * update a book already in the set on success return true, if the book didn't
     * exist return false
     * 
     * @param book which is update in the set
     * @return true if the update success, exception
     * @throws NoSuchBookException
     */
    boolean updateBook(Book book) throws NoSuchBookException;
    
    /**
     * delete a book already in the set on success return true, if the book didn't
     * exist return false
     * 
     * @param book which is delete in the set
     * @return true if the delete success, exception
     * @throws NoSuchBookException
     */
    boolean deleteBook(Book book) throws NoSuchBookException;
}