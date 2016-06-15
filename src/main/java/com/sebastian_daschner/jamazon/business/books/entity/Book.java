package com.sebastian_daschner.jamazon.business.books.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQuery(name = Book.FIND_ALL, query = "SELECT b from books b ORDER BY b.name ASC")
@Entity(name = "books")
public class Book {

    public static final String FIND_ALL = "Book.findAll";

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Basic(optional = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    @Basic(optional = false)
    private String author;

    @Column(name = "book_order")
    private String order;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
