package com.robertostrowski.tictactoe;

import com.robertostrowski.tictactoe.controller.Controller;
import com.robertostrowski.tictactoe.model.Game;
import com.robertostrowski.tictactoe.view.View;

/**
 * TicTacToe game run in command prompt
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class TicTacToe {
    /**
     * Main function, initializes MVC components and stars the game
     * @param args not used at the moment
     */
    static public void main(String[] args) {
        Game game = new Game();
        View view = new View();
        Controller controller = new Controller(game, view);
        controller.gameLoop();
        try {System.in.read();} catch(Exception e){}        // don't close after game ends
    }
}
