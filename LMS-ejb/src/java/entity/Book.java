/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author michelsim
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    
    private String title;
    private String isbn;
    private String author;
    private Boolean onRent;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private ArrayList<LendAndReturn> lendAndReturns;

    public Book() {
        this.lendAndReturns = new ArrayList<>();
        this.onRent = Boolean.FALSE;
    }
    
    public Book(String title, String isbn, String author) {
        this();
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }
            
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the bookId fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Book[ id=" + bookId + " ]";
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the lendAndReturns
     */
    public ArrayList<LendAndReturn> getLendAndReturns() {
        return lendAndReturns;
    }

    /**
     * @param lendAndReturns the lendAndReturns to set
     */
    public void setLendAndReturns(ArrayList<LendAndReturn> lendAndReturns) {
        this.lendAndReturns = lendAndReturns;
    }

    /**
     * @return the onRent
     */
    public Boolean getOnRent() {
        return onRent;
    }

    /**
     * @param onRent the onRent to set
     */
    public void setOnRent(Boolean onRent) {
        this.onRent = onRent;
    }
    
}
