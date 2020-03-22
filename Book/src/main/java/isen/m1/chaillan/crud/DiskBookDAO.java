package isen.m1.chaillan.crud;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private final String filePath = "./src/main/resources/saveBooks.ser";

    private DiskBookDAO() {
        this.open();
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
        return this.books.insertBook(book);
    }

    @Override
    public boolean updateBook(Book book) throws NoSuchBookException {
        return this.books.updateBook(book);
    }

    @Override
    public void close() {
        
        ObjectOutputStream oos = null;
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());

        try {
            final FileOutputStream fichier = new FileOutputStream(file);
            oos = new ObjectOutputStream(fichier);
            oos.writeInt(this.findAll().size());
            for (Book book : this.findAll()) {
                oos.writeObject(book);
            }
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void open() {
        ObjectInputStream ois = null;
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        FileInputStream fichier = null;
        if (file.exists()) {
            try {
                fichier = new FileInputStream(file);
                ois = new ObjectInputStream(fichier);
               int readInt = ois.readInt();
            for (int i = 0; i < readInt; i++) {
                    Book readObject = (Book) ois.readObject();
                    insertBook(readObject);
                }
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (final java.io.IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                        fichier.close();
                        file.delete();
                    }
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            System.out.println("no file");
        }
        
    }

}