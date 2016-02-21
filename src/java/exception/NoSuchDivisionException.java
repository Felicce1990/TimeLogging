/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Felix Thomas
 */
public class NoSuchDivisionException extends Exception {

    public NoSuchDivisionException(String msg) {
        super(msg);
    }
    
    public NoSuchDivisionException()
    {
        super("Division doesn't exist");
    }
}
