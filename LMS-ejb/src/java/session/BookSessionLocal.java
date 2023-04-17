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
import javax.ejb.Local;

/**
 *
 * @author michelsim
 */
@Local
public interface BookSessionLocal {

    public void createBook(Book book) throws InputDataValidationException;

    public List<Book> retrieveAllBooks();

    public Book retrieveBookById(Long id) throws BookNotFoundException;

    public Book retrieveBookByIsbn(String isbn) throws BookNotFoundException;
    
}
