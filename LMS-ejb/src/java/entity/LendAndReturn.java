/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


/**
 *
 * @author michelsim
 */
@Entity
public class LendAndReturn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lendId;   
    private Boolean finePaid;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lendDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date returnDate;
    
    private BigDecimal fineAmount;
    
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.LAZY)
    private Book book;
    
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.LAZY)
    private Member member;

    public LendAndReturn() {
        this.returnDate = null;
        this.fineAmount = BigDecimal.ZERO;
        this.finePaid = Boolean.FALSE;
    }

    public LendAndReturn(Member member, Book book, Date lendDate) {
        this();
        this.book = book;
        this.member = member;
        this.lendDate = lendDate;
    }
    
    public Long getLendId() {
        return lendId;
    }

    public void setLendId(Long lendId) {
        this.lendId = lendId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lendId != null ? lendId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the lendId fields are not set
        if (!(object instanceof LendAndReturn)) {
            return false;
        }
        LendAndReturn other = (LendAndReturn) object;
        if ((this.lendId == null && other.lendId != null) || (this.lendId != null && !this.lendId.equals(other.lendId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LendAndReturn[ id=" + lendId + " ]";
    }

    /**
     * @return the lendDate
     */
    public Date getLendDate() {
        return lendDate;
    }

    /**
     * @param lendDate the lendDate to set
     */
    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the fineAmount
     */
    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    /**
     * @param fineAmount the fineAmount to set
     */
    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return the finePaid
     */
    public Boolean getFinePaid() {
        return finePaid;
    }

    /**
     * @param finePaid the finePaid to set
     */
    public void setFinePaid(Boolean finePaid) {
        this.finePaid = finePaid;
    }
    
}
