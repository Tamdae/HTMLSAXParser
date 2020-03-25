/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author Usuario
 */
public class HTMLSAXException extends Exception {

    /**
     * Creates a new instance of <code>HTMLSAXException</code> without detail
     * message.
     */
    public HTMLSAXException() {
    }

    /**
     * Constructs an instance of <code>HTMLSAXException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HTMLSAXException(String msg) {
        super(msg);
    }
}
