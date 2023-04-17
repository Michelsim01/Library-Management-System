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
import javax.ejb.Local;

/**
 *
 * @author michelsim
 */
@Local
public interface MemberSessionLocal {

    public void createMember(Member m) throws InputDataValidationException;

    public List<Member> retrieveAllMembers();

    public Member retrieveMemberById(Long id) throws MemberNotFoundException;

    // public void updateMember(Member m) throws MemberNotFoundException;
    
}
