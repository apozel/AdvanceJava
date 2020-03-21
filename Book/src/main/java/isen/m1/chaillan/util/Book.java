package isen.m1.chaillan.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * class book 
 *
 */
public class Book implements Comparable<Book>,Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2661770989166845863L;
    private String isbn;
    private String title;
    private Double price;
    private Date issueDate;
    private DateFormat format;

    public Book(String title) {

        this.title = title;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {

        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        String returnString = "Book [isbn=" + isbn + ", price=" + price + ", title=" + title;
        if (format != null) {
            return returnString += ", issueDate=" + format.format(issueDate) + " ]";
        }
        return returnString += ", issueDate=" + issueDate + " ]";

    }

    public DateFormat getFormat() {
        return format;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

    @Override
    public int compareTo(Book o) {
        if (this.equals(o)) {
            return 0;
        } else {
            Book other = (Book) o;
            return this.getTitle().compareTo(other.getTitle());
        }
    }

}
