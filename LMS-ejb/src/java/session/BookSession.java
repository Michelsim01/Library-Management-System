/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Book;
import error.BookNotFoundException;
import error.InputDataValidationException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author michelsim
 */
@Stateless
public class BookSession implements BookSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createBook(Book book) throws InputDataValidationException {
        
        if (book.getTitle().isEmpty()) {
            throw new InputDataValidationException("Book Title must be provided!");
        }
        if (book.getIsbn().isEmpty()) {
            throw new InputDataValidationException("ISBN must be provided!");
        }
        if (book.getAuthor().isEmpty()) {
            throw new InputDataValidationException("Author must be provided!");
        }
        em.persist(book);
        em.flush();
    }
    
    @Override
    public List<Book> retrieveAllBooks() {
        Query query = em.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }
    
    @Override
    public Book retrieveBookByIsbn(String isbn) throws BookNotFoundException {
        Query query = em.createQuery("SELECT b FROM Book b WHERE b.isbn LIKE :isbn").setParameter("isbn", isbn);
        
         try {
            return (Book) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new BookNotFoundException("Book does not exist!");
        }
    }
    
    
    @Override
    public Book retrieveBookById(Long id) throws BookNotFoundException {
        Book book = em.find(Book.class, id);
        
        if (book != null) {
            return book;
        } else {
            throw new BookNotFoundException("Book does not exist!");
        }
    }
}
