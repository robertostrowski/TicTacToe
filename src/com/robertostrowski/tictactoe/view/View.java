package com.robertostrowski.tictactoe.view;

import com.robertostrowski.tictactoe.model.GameCell;

import java.util.Scanner;

/**
 * Object used to get input and print to user
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
public class View {
    private Scanner scanner = new Scanner(System.in);
    private int maxCoordinate = 2;

    /**
     * Gets input from player, i.e A2 or b3
     * @return input formatted as int tab = {row, column}
     * @throws IncorrectInputException when input is incorrect
     */
    public int[] getCoordinatesInput() throws IncorrectInputException{
        String input = scanner.nextLine();
        if (input.length() < 2)
            throw new IncorrectInputException();

        int[] coordinates = new int[2];
        coordinates[0] = (int) input.toUpperCase().charAt(0) - 65;   // "A" ASCII Code is 65
        coordinates[1] = input.charAt(1) - 49;                      // "0" ASCII Code is 48, but arrays start at 0

        // if coordinates are too big or small
        if (coordinates[0] < 0 || coordinates[0] > maxCoordinate || coordinates[1] < 0 || coordinates[1] > maxCoordinate) {
            throw new IncorrectInputException();
        }
        return coordinates;
    }

    /**
     * Prints a formatted game grid to the command prompt
     * @param gamegrid grid to print to user
     */
    public void printGameGrid(GameCell[][] gamegrid) {
        clearConsole();
        String resultLine = "";
        for (int i = 0; i < gamegrid.length; i++) {
            resultLine += Character.toString((char) (65 + i));  // A letter
            resultLine += "| ";
            for (int j = 0; j < gamegrid[i].length; j++) {
                switch (gamegrid[i][j].getState()) {
                    case X:
                        resultLine += "X ";
                        break;
                    case O:
                        resultLine += "O ";
                        break;
                    case EMPTY:
                        resultLine += ". ";
                        break;
                }
            }
            resultLine += System.lineSeparator();
        }
        // Bottom row, column numbers
        String endLine = "   ";
        for (int i = 0; i < gamegrid[0].length; i++)
            endLine += (i + 1) + " ";

        resultLine += endLine + System.lineSeparator();
        System.out.print(resultLine);
    }

    /**
     * Prints a message to standard output
     * @param message Message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Clears command prompt
     */
    private static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }
    }

    public void setMaxCoordinate(int max){
        this.maxCoordinate = max;
    }
}
