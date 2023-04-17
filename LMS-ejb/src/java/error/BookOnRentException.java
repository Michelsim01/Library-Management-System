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
public class BookOnRentException extends Exception {

    /**
     * Creates a new instance of <code>BookOnRentException</code> without detail
     * message.
     */
    public BookOnRentException() {
    }

    /**
     * Constructs an instance of <code>BookOnRentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BookOnRentException(String msg) {
        super(msg);
    }
}
