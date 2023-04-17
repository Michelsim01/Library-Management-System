/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Book;
import entity.Member;
import entity.Staff;
import error.InputDataValidationException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author michelsim
 */
@Singleton
@LocalBean
@Startup
public class DataInitSession {

    @EJB
    private StaffSessionLocal staffSession;

    @EJB
    private MemberSessionLocal memberSession;

    @EJB
    private BookSessionLocal bookSession;

    @PersistenceContext(unitName = "LMS-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        if (em.find(Staff.class, 1l) == null && em.find(Book.class, 1l) == null && em.find(Member.class, 1l) == null) {
            System.out.println("###adding data");
            try {
                Staff staff1 = new Staff("Eric", "Some", "eric", "password");
                Staff staff2 = new Staff("Sarah", "Brightman", "sarah", "password");
                staffSession.createStaff(staff1);
                staffSession.createStaff(staff2);

                Book book1 = new Book("Anna Karenina", "0451528611", "Leo Tolstoy");
                Book book2 = new Book("Madame Bovary", "979-8649042031", "Gustave Flaubert");
                Book book3 = new Book("Hamlet", "1980625026", "William Shakespeare");
                Book book4 = new Book("The Hobbit", "9780007458424", "J R R Tolkien");
                Book book5 = new Book("Great Expectations", "1521853592", "Charles Dickens");
                Book book6 = new Book("Pride and Prejudice", "979-8653642272", "Jane Austen");
                Book book7 = new Book("Wuthering Heights", "3961300224", "Emily Brontë");
                bookSession.createBook(book1);
                bookSession.createBook(book2);
                bookSession.createBook(book3);
                bookSession.createBook(book4);
                bookSession.createBook(book5);
                bookSession.createBook(book6);
                bookSession.createBook(book7);

                Member member1 = new Member("Tony", "Shade", 'M', 31, "S8900678A", "83722773", "13 Jurong East, Ave 3");
                Member member2 = new Member("Dewi", "Tan", 'F', 35, "S8581028X", "94602711", "15 Computing Dr");
                memberSession.createMember(member1);
                memberSession.createMember(member2);

            } catch (InputDataValidationException ex) {
                ex.printStackTrace();
            }
        }

    }
}
