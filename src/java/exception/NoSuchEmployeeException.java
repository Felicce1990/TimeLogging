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
public class NoSuchEmployeeException extends Exception {

    public NoSuchEmployeeException(String msg) {
        super(msg);
    }
    
    public NoSuchEmployeeException()
    {
        super("Employee doesn't exist");
    }
}
