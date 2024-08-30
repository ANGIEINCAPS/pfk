package sudokusolver;

public class Sudoku implements SudokuSolver {

    private int[][] grid;
    private long startTime;
    private static final long TIMEOUT_MS = 2000;

    public Sudoku() {
        grid = new int[9][9];
    }

    @Override
    public boolean solve() {
        startTime = System.currentTimeMillis();
        return solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (System.currentTimeMillis() - startTime > TIMEOUT_MS) {
            return false; // Timeout, consider unsolvable within time limit
        }

        // BASE CASE: if the reach the last row the puzzle is solved
        if (row == 9) {
            return true;
        }

        // move on to next row if end is reached
        if (col == 9) {
            return solve(row + 1, 0);
        }

        // skip filled cells
        if (grid[row][col] != 0) {
            return solve(row, col + 1);
        }

        // Try placing digits 1 through 9 in the current cell
        for (int digit = 1; digit <= 9; digit++) {
            grid[row][col] = digit;
            // If the placement is valid and the rest of the grid can be solved, return true
            if (isValid(row, col) && solve(row, col + 1)) {
                return true;
            }
            // If not, reset the cell (backtrack)
            grid[row][col] = 0;
        }

        // If no valid digit is found, return false to backtrack
        return false;
    }

    @Override
    public void set(int row, int col, int digit) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Row and column must be in range 0 .. 9.");
        }

        if (digit < 1 || digit > 9) {
            throw new IllegalArgumentException("Digit must be in range 1 .. 9.");
        }

        grid[row][col] = digit;
    }

    @Override
    public int get(int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Row and column must be in range 0..9.");
        }

        return grid[row][col];
    }

    @Override
    public void clear(int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Row and column must be in range 0 .. 9.");
        }

        grid[row][col] = 0;
    }

    @Override
    public void clearAll() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                grid[r][c] = 0;
            }
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new IllegalArgumentException("Row and column must be in range 0 .. 9.");
        }

        int digit = grid[row][col];

        // empty cell is ok
        if (digit == 0) {
            return true;
        }

        // check row
        for (int c = 0; c < 9; c++) {
            if (c != col && grid[row][c] == digit) {
                return false;
            }
        }

        // check column
        for (int r = 0; r < 9; r++) {
            if (r != row && grid[r][col] == digit) {
                return false;
            }
        }

        // check box (3x3 cells)
        int boxRow = row - (row % 3);
        int boxCol = col - (col % 3);
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if ((r != row || c != col) && grid[r][c] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isAllValid() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (grid[r][c] != 0 && !isValid(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setGrid(int[][] m) {
        if (m.length != 9 || m[0].length != 9) {
            throw new IllegalArgumentException("Grid must be 9x9.");
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int digit = m[r][c];
                if (digit < 0 || digit > 9) {
                    throw new IllegalArgumentException("Digits must be in range 0 .. 9.");
                }
                grid[r][c] = digit;
            }
        }
    }

    @Override
    public int[][] getGrid() {
        int[][] matrix = new int[9][9];
        for (int r = 0; r < 9; r++) {
            System.arraycopy(grid[r], 0, matrix[r], 0, 9);
        }
        return matrix;
    }

}
