/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Staff;
import error.StaffNotFoundException;
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
public class StaffSession implements StaffSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createStaff(Staff staff) {
        em.persist(staff);
        em.flush();
    }

    @Override
    public List<Staff> retrieveAllStaff() {
        Query query = em.createQuery("SELECT s FROM Staff s");
        return query.getResultList();
    }

    @Override
    public Staff retrieveStaffById(Long id) throws StaffNotFoundException {
        Staff staff = em.find(Staff.class, id);

        if (staff != null) {
            return staff;
        } else {
            throw new StaffNotFoundException("Staff does not exist!");
        }
    }

    @Override
    public Staff retrieveStaffByUsernameAndPassword(String username, String password) throws StaffNotFoundException {
        Query query = em.createQuery("SELECT s FROM Staff s WHERE s.userName LIKE :username AND s.password LIKE :password")
                .setParameter("username", username)
                .setParameter("password", password);

        try {
            return (Staff) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new StaffNotFoundException("Incorrect username or password entered or Staff does not exist!");
        }
    }
}
