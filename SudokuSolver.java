import java.io.*;
import java.util.*;

public class SudokuSolver {

    static int[][] board = new int[9][9];

    public static void main(String[] args) throws Exception {
        // Optional: Read board from file
        Scanner sc = new Scanner(new File("sudoku_input.txt"));
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = sc.nextInt();
        sc.close();

        System.out.println("🔎 Solving Sudoku...\n");
        if (solveSudoku(0, 0)) {
            printBoard();
        } else {
            System.out.println("❌ No solution exists.");
        }
    }

    static boolean solveSudoku(int row, int col) {
        if (row == 9)
            return true;

        if (col == 9)
            return solveSudoku(row + 1, 0);

        if (board[row][col] != 0)
            return solveSudoku(row, col + 1);

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(row, col + 1))
                    return true;
                board[row][col] = 0; // backtrack
            }
        }
        return false;
    }

    static boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++)
            for (int j = startCol; j < startCol + 3; j++)
                if (board[i][j] == num)
                    return false;

        return true;
    }

    static void printBoard() {
        for (int[] row : board) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}
