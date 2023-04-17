/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Book;
import entity.LendAndReturn;
import entity.Member;
import error.BookNotFoundException;
import error.BookOnRentException;
import error.FineNotPaidException;
import error.IncorrectAmountPaidException;
import error.LendingNotFoundException;
import error.MemberNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 *
 * @author michelsim
 */
@Stateless
public class LendAndReturnSession implements LendAndReturnSessionLocal {

    @EJB
    private MemberSessionLocal memberSession;

    @EJB
    private BookSessionLocal bookSession;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createLendAndReturnRecord(LendAndReturn record, Long memberId, String bookIsbn, Date lendDate) throws MemberNotFoundException, BookNotFoundException, BookOnRentException {

        Member m = memberSession.retrieveMemberById(memberId);
        Book b = bookSession.retrieveBookByIsbn(bookIsbn);

        if (b.getOnRent().equals(Boolean.FALSE)) {
            m.getLendAndReturns().add(record);
            record.setMember(m);

            b.getLendAndReturns().add(record);
            record.setBook(b);
            b.setOnRent(Boolean.TRUE);

            record.setLendDate(lendDate);

            em.persist(record);
            em.flush();
        } else {
            throw new BookOnRentException("Book is not available!");
        }
    }

    @Override
    public List<LendAndReturn> retrieveAllRecords() {
        Query query = em.createQuery("SELECT r FROM LendAndReturn r");
        return query.getResultList();
    }

    @Override
    public LendAndReturn retrieveRecordById(Long id) throws LendingNotFoundException {
        Query query = em.createQuery("SELECT r FROM LendAndReturn r WHERE r.lendId = :lendId").setParameter("lendId", id);

        try {
            return (LendAndReturn) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new LendingNotFoundException("Lending record does not exist!");
        }
    }
    

    @Override
    public BigDecimal calculateFineAmount(LendAndReturn record, Date returnDate) {
        
        Calendar lendCalendar = Calendar.getInstance();
        lendCalendar.setTime(record.getLendDate());
        
        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(returnDate);
        
        long days = (returnCalendar.getTimeInMillis() - lendCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000);

        if (days > 14) {
            long extraDays = days - 14;
            BigDecimal charge = new BigDecimal(extraDays * 0.50);
            return charge;
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal retrieveFineByRecord(Long id, Date returnDate) throws LendingNotFoundException {
        LendAndReturn record = retrieveRecordById(id);
        BigDecimal fine = calculateFineAmount(record, returnDate);

        if (fine.compareTo(BigDecimal.ZERO) > 0) { // checks if fine exists, if it does return fine, if not return 0 and set fine paid to true
            record.setFineAmount(fine);
            return fine;
        } else {
            record.setFineAmount(BigDecimal.ZERO);
            record.setFinePaid(Boolean.TRUE);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public void payFine(Long id, BigDecimal amountPaid) throws LendingNotFoundException, IncorrectAmountPaidException {
        LendAndReturn record = retrieveRecordById(id);
        
        if (record.getFineAmount().equals(amountPaid)) {
            record.setFinePaid(Boolean.TRUE);
        }
        else {
            throw new IncorrectAmountPaidException("Amount paid is incorrect!");
        }
    }

    @Override
    public void returnBook(Long id, Date returnDate) throws FineNotPaidException, LendingNotFoundException {
        LendAndReturn record = retrieveRecordById(id);

        if (record.getFinePaid().equals(Boolean.FALSE)) {
            throw new FineNotPaidException("Fine has not been paid!");
        } else {
            record.getBook().setOnRent(Boolean.FALSE);
            record.setReturnDate(returnDate);
        }
    }
}
