package com.okandinc.sudokusolver;

public class SudokuSolver {

    private final int EMPTY_CELL = 0;

    public boolean solve(int[][] sudokuPuzzle) {
        return solveCell(sudokuPuzzle,0, 0);
    }

    private boolean solveCell(int[][] sudokuPuzzle, int row, int col) {
        if (col == sudokuPuzzle[row].length) {
            col = 0;
            row++;
            if (row == sudokuPuzzle.length) {
                //Whole puzzle solved.
                return true;
            }
        }
        //If a cell is already filled, skip to the next one.
        if (sudokuPuzzle[row][col] != EMPTY_CELL) {
            return solveCell(sudokuPuzzle, row, col + 1);
        }

        //Placing a value in a empty cell if there is a valid number.
        for (int value = 1; value <= sudokuPuzzle.length; value++) {
            int numberToPlace = value;
            if (isNumberPlaceable(sudokuPuzzle, row, col, numberToPlace)) {
                sudokuPuzzle[row][col] = numberToPlace;
                if (solveCell(sudokuPuzzle, row, col + 1)) {
                    return true;
                }
            }
        }
        //If there is not match. This simulation is not valid.
        sudokuPuzzle[row][col] = EMPTY_CELL;
        return false;
    }

    private boolean isNumberPlaceable(int[][] sudokuPuzzle, int row, int col, int numberToBePlaced) {

        for (int i = 0; i < sudokuPuzzle.length; i++) {
            //Checking every column
            if (numberToBePlaced == sudokuPuzzle[row][i]) {
                return false;
            }
            //Checking every row
            if (numberToBePlaced == sudokuPuzzle[i][col]) {
                return false;
            }
        }

        //Deciding which subgrid cell belongs to
        int subGridRow = row / 3;
        int subGridCol = col / 3;

        //Finding the first element of subgrid (Top Left)
        int firstElementsSubGridRow = 3 * subGridRow;
        int firstElementsSubGridCol = 3 * subGridCol;

        //Checking subgrid if there is a match.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (numberToBePlaced == sudokuPuzzle[firstElementsSubGridRow + i][firstElementsSubGridCol + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
