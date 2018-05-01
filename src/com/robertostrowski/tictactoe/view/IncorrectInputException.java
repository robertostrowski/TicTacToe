package com.robertostrowski.tictactoe.view;
/**
 * Exception thrown on incorrect input
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class IncorrectInputException extends Exception {

    IncorrectInputException(){
        this("Input is incorrect, try again! Examples: a2, c1, B2");
    }

    IncorrectInputException(String msg){
        super(msg);
    }
}
