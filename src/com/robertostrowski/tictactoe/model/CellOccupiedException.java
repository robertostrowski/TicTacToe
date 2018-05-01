package com.robertostrowski.tictactoe.model;

/**
 * Exception thrown, when a cell in game is already occupied
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class CellOccupiedException extends Exception {
    public CellOccupiedException(){
        this("Cell is already occupied, please pick another cell");
    }

    public CellOccupiedException(String msg){
        super(msg);
    }
}
