/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author michelsim
 */
public class IncorrectAmountPaidException extends Exception {

    /**
     * Creates a new instance of <code>IncorrectAmountPaidException</code>
     * without detail message.
     */
    public IncorrectAmountPaidException() {
    }

    /**
     * Constructs an instance of <code>IncorrectAmountPaidException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectAmountPaidException(String msg) {
        super(msg);
    }
}