package com.sebastian_daschner.jamazon.business;

import com.sebastian_daschner.jamazon.business.books.entity.Book;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BookIT {

    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = Persistence.createEntityManagerFactory("it").createEntityManager();
    }

    @Test
    public void test() {
        entityManager.getTransaction().begin();
        final Book book = new Book();
        book.setName("name");
        book.setAuthor("author");
        entityManager.merge(book);
        entityManager.getTransaction().commit();
    }

}
