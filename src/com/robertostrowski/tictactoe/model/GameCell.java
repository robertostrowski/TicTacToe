package com.robertostrowski.tictactoe.model;

/**
 * Game cell entity
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class GameCell {

    private CellState state;

    /**
     * Initializes a cell as empty
     */
    public GameCell() {
        this.state = CellState.EMPTY;
    }

    /**
     * Sets a state of a cell
     * @param state state to assign to a cell
     */
    public void setState(CellState state) {
            this.state = state;
    }

    /**
     * Gets a state of a cell
     * @return a state of a cell
     */
    public CellState getState() {
        return this.state;
    }

    /**
     * Used to identify a cell state and a player
     */
    public enum CellState {
        EMPTY, X, O
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof GameCell))return false;
        GameCell gc = (GameCell) obj;
        return this.state.equals(((GameCell) obj).state);
    }
}
