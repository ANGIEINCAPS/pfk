package sudokusolver;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SudokuGUI extends JFrame {

    private final JTextField[][] cells;
    private final Sudoku sudoku;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuGUI gui = new SudokuGUI();
            gui.setVisible(true);
        });
    }

    public SudokuGUI() {
        sudoku = new Sudoku();
        cells = new JTextField[9][9];

        // Set up the frame
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Create Sudoku grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 9));

        // Initialize the grid cells with colors
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                cells[row][col].setForeground(new Color(34, 34, 34));

                // Apply alternating colors
                if ((row / 3 + col / 3) % 2 == 0) {
                    cells[row][col].setBackground(new Color(255, 255, 255)); // Hot White
                } else {
                    cells[row][col].setBackground(new Color(101, 175, 255)); // Blue
                }

                gridPanel.add(cells[row][col]);
            }
        }

        // gridpanel
        add(gridPanel, BorderLayout.CENTER);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Solve button
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener((ActionEvent e) -> {
            solveSudoku();
        });

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener((ActionEvent e) -> {
            resetGrid();
        });

        // add buttons
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void solveSudoku() {
        try {
            // read current state
            int[][] grid = new int[9][9];
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    String text = cells[row][col].getText().trim();
                    if (text.isEmpty()) {
                        grid[row][col] = 0;
                    } else {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9) { // check valid range
                            throw new NumberFormatException("Number out of range");
                        }
                        grid[row][col] = value;
                    }
                }
            }
    
            // Set grid
            sudoku.setGrid(grid);
    
            // SOLVE
            if (sudoku.solve()) {
                // Update GIU
                updateGrid(sudoku.getGrid());
            } else {
                JOptionPane.showMessageDialog(this, "No solution found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers (1-9).");
        }
    }
    

    private void updateGrid(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    cells[row][col].setText("");
                } else {
                    cells[row][col].setText(String.valueOf(grid[row][col]));
                }
            }
        }
    }

    private void resetGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("");
            }
        }
    }

}
