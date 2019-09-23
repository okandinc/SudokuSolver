package com.okandinc.sudokusolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please specify file path to read sudoku puzzle");
        //Change this implementation while running in ide.
        String fileName = System.console().readLine();
        int [][] sudokuPuzzle = null;
        try {
            System.out.println(new File("./").getAbsolutePath());
            if(fileName != null || !fileName.isEmpty()) {
                long startReading = System.currentTimeMillis();
                Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
                int rows = 9;
                int columns = 9;
                sudokuPuzzle = new int[rows][columns];
                while(sc.hasNextLine()) {
                    for (int i=0; i<sudokuPuzzle.length; i++) {
                        String[] line = sc.nextLine().trim().split(",");
                        for (int j=0; j<line.length; j++) {
                            sudokuPuzzle[i][j] = Integer.parseInt(line[j]);
                        }
                    }
                }
                long elapsedTimeToRead = System.currentTimeMillis() - startReading;
                System.out.println("Read in " + elapsedTimeToRead + " ms");
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File is not found. Please specify correct path.");
        }
        SudokuSolver sudokuSolver = new SudokuSolver();
        if(sudokuPuzzle != null) {
            long startSolving = System.currentTimeMillis();
            boolean solved = sudokuSolver.solve(sudokuPuzzle);
            if(solved) {
                long elapsedTimeToSolve = System.currentTimeMillis() - startSolving;
                System.out.println("Solved in " + elapsedTimeToSolve + " ms");
                String row = "";
                for (int i = 0; i < 9; i++) {
                    for(int j = 0; j< 9; j++) {
                        row =  row + " | " + sudokuPuzzle[i][j];
                    }
                    System.out.println(row + " |");
                    row = "";
                }
            }
        }
    }
}
