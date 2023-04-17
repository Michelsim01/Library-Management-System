/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Member;
import error.InputDataValidationException;
import error.MemberNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author michelsim
 */
@Stateless
public class MemberSession implements MemberSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createMember(Member m) throws InputDataValidationException {
        // Perform input data validation here
        if (m.getFirstName().isEmpty()) {
            throw new InputDataValidationException("First name must be provided!");
        }
        if (m.getLastName().isEmpty()) {
            throw new InputDataValidationException("Last name must be provided!");
        }
        if (m.getAge() == null || m.getAge() <= 0) {
            throw new InputDataValidationException("Age must be a positive number!");
        }
        if (m.getIdentityNo().isEmpty()) {
            throw new InputDataValidationException("Identity number must be provided!");
        }
        if (m.getPhone().isEmpty()) {
            throw new InputDataValidationException("Phone number must be provided!");
        }
        if (m.getAddress().isEmpty()) {
            throw new InputDataValidationException("Address must be provided!");
        }
        // If input data is valid, persist the member
        em.persist(m);
    }

    @Override
    public List<Member> retrieveAllMembers() {
        Query query = em.createQuery("SELECT m FROM Member m");
        return query.getResultList();
    }

    @Override
    public Member retrieveMemberById(Long id) throws MemberNotFoundException {
        Member member = em.find(Member.class, id);

        if (member != null) {
            return member;
        } else {
            throw new MemberNotFoundException("Member does not exist!");
        }
    }

    /* @Override
    public void updateMember(Member m) throws MemberNotFoundException {
        Member oldMember = retrieveMemberById(m.getMemberId());
        
        oldMember.setFirstName(m.getFirstName());
        oldMember.setLastName(m.getLastName());
        oldMember.setGender(m.getGender());
        oldMember.setAge(m.getAge());
        oldMember.setIdentityNo(m.getIdentityNo());
        oldMember.setPhone(m.getPhone());
        oldMember.setAddress(m.getAddress());
    } */
}
