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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author michelsim
 */
@Local
public interface LendAndReturnSessionLocal {

    public List<LendAndReturn> retrieveAllRecords();

    public LendAndReturn retrieveRecordById(Long id) throws LendingNotFoundException;

    public BigDecimal calculateFineAmount(LendAndReturn record, Date returnDate);

    public BigDecimal retrieveFineByRecord(Long id, Date returnDate) throws LendingNotFoundException;

    public void returnBook(Long id, Date returnDate) throws FineNotPaidException, LendingNotFoundException;

    public void payFine(Long id, BigDecimal amountPaid) throws LendingNotFoundException, IncorrectAmountPaidException;

    public void createLendAndReturnRecord(LendAndReturn record, Long memberId, String bookIsbn, Date lendDate) throws MemberNotFoundException, BookNotFoundException, BookOnRentException;

    // public void createLendAndReturnRecord(Member m, Book b, Date lendDate);
}
