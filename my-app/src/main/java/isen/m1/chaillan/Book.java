package isen.m1.chaillan;

import java.text.DateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class Book {
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

}
