package com.robertostrowski.tictactoe.tests;

import com.robertostrowski.tictactoe.model.CellOccupiedException;
import com.robertostrowski.tictactoe.model.Game;
import com.robertostrowski.tictactoe.model.GameCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing game model
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
class GameTest {

    /**
     * Test setting a cell
     */
    @Test
    void setCell() throws CellOccupiedException {
        Game game = new Game();
        // 0 is empty, x is 1, 2 is 0
        int[][] tab = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        setGrid(game, tab);
        // set 0,0 cell as X, should be completed
        game.setCell(0, 0, GameCell.CellState.X);

        // setting the cell again should throw an exception
        assertThrows(CellOccupiedException.class, () -> game.setCell(0, 0, GameCell.CellState.O));

        // Check if set cell is correct
        GameCell gcExpected = new GameCell();
        gcExpected.setState(GameCell.CellState.X);
        GameCell gcActual = game.getGameGrid()[0][0];
        assertEquals(gcExpected, gcActual);
    }

    /**
     * Test checking for victory for every possible winning line
     */
    @Test
    void checkForVictory() {
        Game game = new Game();
        int[][] tab = new int[Game.dimension][Game.dimension];

        // check vertical
        for (int column = 0; column < Game.dimension; column++) {
            clearTab(tab);
            // fill a column with "X"
            for (int row = 0; row < Game.dimension; row++)
                tab[column][row] = 1;

            setGrid(game, tab);
            assertTrue(game.checkForVictory(GameCell.CellState.X));
            assertFalse(game.checkForVictory(GameCell.CellState.O));
        }

        // check horizontal
        for (int row = 0; row < Game.dimension; row++) {
            clearTab(tab);
            // fill a column with "X"
            for (int column = 0; column < Game.dimension; column++)
                tab[column][row] = 1;

            setGrid(game, tab);
            assertTrue(game.checkForVictory(GameCell.CellState.X));
            assertFalse(game.checkForVictory(GameCell.CellState.O));
        }

        // check diagonals down-right
        for (int i = 0 ; i < Game.dimension - game.inARowToWin + 1; i++) {
            for (int j = 0 ; j < Game.dimension - game.inARowToWin + 1; j++) {
                clearTab(tab);
                for (int k = 0; k < Game.dimension; k++) {
                    tab[i + k][j + k] = 1;
                }
                setGrid(game, tab);
                assertTrue(game.checkForVictory(GameCell.CellState.X));
                assertFalse(game.checkForVictory(GameCell.CellState.O));
            }
        }
        // check for diagonals down - left
        for (int i = game.inARowToWin - 1 ; i < Game.dimension; i++) {
            for (int j = 0 ; j < Game.dimension - game.inARowToWin + 1; j++) {
                clearTab(tab);
                for (int k = 0; k < Game.dimension; k++) {
                    tab[i - k][j + k] = 1;
                }
                setGrid(game, tab);
                assertTrue(game.checkForVictory(GameCell.CellState.X));
                assertFalse(game.checkForVictory(GameCell.CellState.O));
            }
        }

    }

    /**
     * Testing game turn counter
     */
    @Test
    void incTurnCount() {
        Game game = new Game();
        assertFalse(game.incTurnCount(1));
        assertTrue(game.incTurnCount(8));
        assertTrue(game.incTurnCount(1000));
    }

    /**
     * Resets a game grid, sets all cells as empty
     *
     * @param game game object to reset
     */
    private void resetGrid(Game game) {
        for (int i = 0; i < Game.dimension; i++)
            for (int j = 0; j < Game.dimension; j++) {
                game.resetCell(i, j);
            }
    }

    /**
     * Sets a game grid from int table, 0 in table is empty, 1 is "X", 2 is "O"
     *
     * @param game    object to set
     * @param gridTab tab containing a stage of a game
     */
    private void setGrid(Game game, int[][] gridTab) {
        resetGrid(game);
        GameCell.CellState cellState;
        for (int i = 0; i < Game.dimension; i++)
            for (int j = 0; j < Game.dimension; j++) {
                switch (gridTab[i][j]) {
                    case 0:
                        cellState = GameCell.CellState.EMPTY;
                        break;
                    case 1:
                        cellState = GameCell.CellState.X;
                        break;
                    case 2:
                        cellState = GameCell.CellState.O;
                        break;
                    default:
                        cellState = GameCell.CellState.EMPTY;
                }
                try {
                    game.setCell(i, j, cellState);
                } catch (CellOccupiedException e) {
                    e.printStackTrace();
                }
            }
    }

    private void clearTab(int[][] tab) {
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = 0;
            }
    }
}