package com.sebastian_daschner.jamazon.business.books.boundary;

import com.sebastian_daschner.jamazon.business.books.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Stateless
public class BookStore {

    @PersistenceContext
    EntityManager entityManager;

    public List<Book> getBooks() {
        return entityManager.createNamedQuery(Book.FIND_ALL, Book.class).getResultList();
    }

    public Book getBook(final long id) {
        return entityManager.find(Book.class, id);
    }

    public Book save(@Valid final Book book) {
        final Book mergedBook = entityManager.merge(book);
        entityManager.flush();
        return mergedBook;
    }

}
