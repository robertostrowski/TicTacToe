package com.robertostrowski.tictactoe.controller;

import com.robertostrowski.tictactoe.model.*;
import com.robertostrowski.tictactoe.view.*;

/**
 * Class used for controlling and manipulating Game and View objects
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class Controller {
    private Game game;
    private View view;

    /**
     * Sets given Game and View parameters as his own
     *
     * @param game Game model object to control
     * @param view Game view object to control
     */
    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Players take turns in a loop, until one of the players wins, or game reaches max turn number.
     */
    public void gameLoop() {
        while (true) {
            // Player X turn
            view.printGameGrid(game.getGameGrid());
            if (playerTurn(GameCell.CellState.X)) {
                view.printGameGrid(game.getGameGrid());
                view.printMessage(System.lineSeparator() + "Player X wins");
                return;
            }
            // Checks if after the previous turn game reaches max turns number
            if(game.incTurnCount(1)){
                view.printGameGrid(game.getGameGrid());
                view.printMessage(System.lineSeparator() + "Draw!");
                return;
            }
            view.printGameGrid(game.getGameGrid());
            // Player O turn
            if (playerTurn(GameCell.CellState.O)) {
                view.printGameGrid(game.getGameGrid());
                view.printMessage(System.lineSeparator() + "Player O wins");
                return;
            }
            // Checks if after the previous turn game reaches max turns number
            if(game.incTurnCount(1)){
                view.printGameGrid(game.getGameGrid());
                view.printMessage(System.lineSeparator() + "Draw!");
                return;
            }
        }
    }

    /**
     * Turn involves printing a hint to player, getting correct input and executing input on game, also handles input
     * error and game logic errors
     * @param state
     * @return True, if the executed turn resulted in given player victory, else returns false
     */
    private boolean playerTurn(GameCell.CellState state) {
        view.printMessage("Player " + state.toString() + " turn. Enter coordinates as [letter][number]");
        while (true) {
            try {
                int[] input = view.getCoordinatesInput();   // get input, throws exception if input is incorrect
                game.setCell(input[0], input[1], state);    // set a cell based on input, throws ex if cell is taken
                return game.checkForVictory(state);
            } catch (IncorrectInputException iie) {
                view.printMessage(iie.getMessage());
            } catch (CellOccupiedException coe){
                view.printMessage(coe.getMessage());
            }
        }
    }
}
