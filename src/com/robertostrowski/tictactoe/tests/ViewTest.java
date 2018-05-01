package com.robertostrowski.tictactoe.tests;

import com.robertostrowski.tictactoe.view.IncorrectInputException;
import com.robertostrowski.tictactoe.view.View;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * View testing class
 *
 * @author Robert Ostrowski
 * @version 1.0
 * @since 2018-04-25
 */
class ViewTest {

    /**
     * Testing for user input
     */
    @Test
    void getCoordinatesInput() throws IncorrectInputException {
        String input = "aaa"    + System.lineSeparator();   // 1st case, wrong input
        input += "b2"           + System.lineSeparator();   // 2nd case, input is correct
        input += "b"            + System.lineSeparator();   // 3rd case, input is too short
        input += "d1"           + System.lineSeparator();   // 4rd case, input correct but out of array bound
        input += "c4"           + System.lineSeparator();   // 5th case, input correct but out of array bound
        input += "A1"           + System.lineSeparator();   // 6th case, input correct and uppercase
        input += ""             + System.lineSeparator();   // 7th case, input is empty

        // Set up
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        View view = new View();

        // 1st case
        assertThrows(IncorrectInputException.class, () ->view.getCoordinatesInput());
        // 2nd case, "b2"
        int[] expected = {1,1}; // 0 is A, 1 is B   2 is second row, so 1 is expected
        assertArrayEquals(expected, view.getCoordinatesInput());
        // 3rd case, "b"
        assertThrows(IncorrectInputException.class, () ->view.getCoordinatesInput());
        // 4th case, "d1"
        assertThrows(IncorrectInputException.class, () ->view.getCoordinatesInput());
        // 5th case, "c4"
        assertThrows(IncorrectInputException.class, () ->view.getCoordinatesInput());
        // 6th case, "A1"
        expected = new int[] {0,0};
        assertArrayEquals(expected, view.getCoordinatesInput());
        // 7th case, ""
        assertThrows(IncorrectInputException.class, ()->view.getCoordinatesInput());
    }
}