package com.robertostrowski.tictactoe.model;

/**
 * Game logic model
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class Game {
    /**
     *     Number of cells in a row and a column
     */
    public static final int dimension = 3;
    public final int inARowToWin = 3;
    private final int maxTurns;
    private int turnCount;
    private GameCell[][] gameGrid;

    /**
     * Sets up the model
     */
    public Game() {
        gameGrid = new GameCell[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                gameGrid[i][j] = new GameCell();
        maxTurns = dimension * dimension;
        turnCount = 0;
    }

    /**
     * Sets a cell state at given coordinates
     *
     * @param x X cell coordinate
     * @param y Y cell coordinate
     * @param cellState state to set, X or O
     * @throws CellOccupiedException when cell to set is already occupied
     */
    public void setCell(int x, int y, GameCell.CellState cellState) throws CellOccupiedException{
        if (!gameGrid[x][y].getState().equals(GameCell.CellState.EMPTY))
            throw new CellOccupiedException();
        gameGrid[x][y].setState(cellState);
    }

    /**
     * resets a cell to an EMPTY state
     * @param x x coordinate of a cell
     * @param y y coordinate of a cell
     */
    public void resetCell(int x, int y){
        gameGrid[x][y].setState(GameCell.CellState.EMPTY);
    }

    /**
     * Checks the whole board diagonally, horizontally and vertically to check for a winning line
     * @param state cell state, for which the winning condition is checked
     * @return true, if winning line is found, else false
     */
    public boolean checkForVictory(GameCell.CellState state) {
        if (state.equals(GameCell.CellState.EMPTY))
            return false;

        // check for rows
        for (int i = 0; i <= dimension - inARowToWin; i++)
            for (int j = 0; j < dimension; j++)
                if (checkInARow(i,j,1,0,state))
                    return true;

        // check for columns
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j <= dimension - inARowToWin; j++)
                if (checkInARow(i,j,0,1,state))
                    return true;

        // check for diagonal
        for (int i = 0; i <= dimension - inARowToWin; i++)
            for (int j = 0; j <= dimension - inARowToWin; j++){
                if (    checkInARow(i,j,1,1,state) ||
                        checkInARow(i + inARowToWin - 1,j,-1,1,state))
                    return true;
        }
        return false;
    }

    /**
     * Checks a given line for winning combination
     *
     * @param startX x coordinate from which checking starts
     * @param startY y coordinate for which checking starts
     * @param vx how much in x dimension should be incremented each iteration
     * @param vy how much in y dimension should be incremented each iteration
     * @param state state for which a line is checked
     * @return true if a full winning line is found
     */
    private boolean checkInARow(int startX, int startY, int vx, int vy, GameCell.CellState state) {
        for (int i = 0; i < inARowToWin; i++) {
            if (!gameGrid[startX + i * vx][startY + i * vy].getState().equals(state))
                return false;
        }
        return true;
    }

    /**
     * Gives a game grid
     * @return requested game grid
     */
    public GameCell[][] getGameGrid() {
        return gameGrid;
    }

    /**
     * Increments turn count and checks if limit is reached
     * @param inc number of turns to increment
     * @return true if max turn is reached, else false
     */
    public boolean incTurnCount(int inc){
        this.turnCount += inc;
        if (turnCount >= maxTurns)
            return true;
        else return false;
    }
}
