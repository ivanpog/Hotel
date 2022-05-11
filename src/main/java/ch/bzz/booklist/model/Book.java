package ch.bzz.booklist.model;

import java.math.BigDecimal;

public class Book {
    private String bookUUID;
    private String title;
    private String author;
    private Publisher publisher;
    private BigDecimal price;
    private String isbn;

    /**
     * gets bookUUID
     *
     * @return value of bookUUID
     */

    public String getBookUUID() {
        return bookUUID;
    }

    /**
     * sets bookUUID
     *
     * @param bookUUID the value to set
     */

    public void setBookUUID(String bookUUID) {
        this.bookUUID = bookUUID;
    }

    /**
     * gets title
     *
     * @return value of title
     */

    public String getTitle() {
        return title;
    }

    /**
     * sets title
     *
     * @param title the value to set
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets author
     *
     * @return value of author
     */

    public String getAuthor() {
        return author;
    }

    /**
     * sets author
     *
     * @param author the value to set
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * gets publisher
     *
     * @return value of publisher
     */

    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * sets publisher
     *
     * @param publisher the value to set
     */

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * gets price
     *
     * @return value of price
     */

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * sets price
     *
     * @param price the value to set
     */

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * gets isbn
     *
     * @return value of isbn
     */

    public String getIsbn() {
        return isbn;
    }

    /**
     * sets isbn
     *
     * @param isbn the value to set
     */

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
