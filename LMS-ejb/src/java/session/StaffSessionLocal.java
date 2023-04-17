/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Staff;
import error.InvalidLoginException;
import error.StaffNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author michelsim
 */
@Local
public interface StaffSessionLocal {

    public void createStaff(Staff staff);

    public List<Staff> retrieveAllStaff();

    public Staff retrieveStaffById(Long id) throws StaffNotFoundException;

    public Staff retrieveStaffByUsernameAndPassword(String username, String password) throws StaffNotFoundException;
    
}
