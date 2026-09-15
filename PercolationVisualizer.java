/*
 * PercolationVisualizer.java
 * --------------------------
 * STUDENT STARTER FILE — GUI Template for the Percolation Project
 *
 * What this file does RIGHT NOW:
 *   - Displays an n×n grid of clickable cells
 *   - Click a cell to cycle: Blocked (gray) → Open (white) → Full (blue) → Blocked
 *   - "New Grid" resets everything
 *
 * YOUR TASKS TO COMPLETE THIS GUI:
 *   1. Import your Percolation class (it's already in the same folder)
 *   2. Replace the manual grid[][] state with a Percolation object
 *   3. On each click, call percolation.open(row, col)
 *   4. After each click, check percolation.isFull(row, col) for each cell to set colors
 *   5. Check percolation.percolates() and display a message when the system percolates
 *   6. Use percolation.numberOfOpenSites() in the status bar
 *
 * Search for "TODO" comments throughout this file to find each integration point.
 *
 * Compile: javac PercolationVisualizer.java
 * Run:     java PercolationVisualizer
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PercolationVisualizer {
    static final Color COLOR_BLOCKED = new Color(0x2d2d2d);
    static final Color COLOR_OPEN    = new Color(0xFFFFFF);
    static final Color COLOR_FULL    = new Color(0x3B82F6);
    static final Color COLOR_BORDER  = new Color(0x1a1a2e);
    static final Color COLOR_BG      = new Color(0x1e1e2e);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Percolation Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 700);
            frame.setMinimumSize(new Dimension(400, 500));
            frame.setLocationRelativeTo(null);

            JPanel root = new JPanel(new java.awt.BorderLayout(0, 8));
            root.setBackground(COLOR_BG);
            root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel controls = new JPanel();
            controls.setBackground(COLOR_BG);

            JLabel sizeLabel = new JLabel("Grid size n:");
            sizeLabel.setForeground(Color.WHITE);
            sizeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

            JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 30, 1));
            sizeSpinner.setPreferredSize(new Dimension(70, 28));

            JButton newGridButton = new JButton("New Grid");
            newGridButton.setMargin(new Insets(4, 12, 4, 12));

            controls.add(sizeLabel);
            controls.add(sizeSpinner);
            controls.add(newGridButton);
            root.add(controls, java.awt.BorderLayout.NORTH);

            JLabel statusBar = new JLabel();
            statusBar.setForeground(Color.WHITE);
            statusBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            statusBar.setHorizontalAlignment(SwingConstants.CENTER);
            statusBar.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

            GridPanel gridPanel = new GridPanel(10, statusBar);

            JButton openRandomButton = new JButton("Open Random Cell");
            openRandomButton.setMargin(new Insets(4, 12, 4, 12));
            controls.add(openRandomButton);
            openRandomButton.addActionListener((ActionEvent event) -> {
                gridPanel.openRandomCell();
            });

            root.add(gridPanel, java.awt.BorderLayout.CENTER);

            JPanel legendPanel = createLegendPanel();
            root.add(legendPanel, java.awt.BorderLayout.SOUTH);

            newGridButton.addActionListener((ActionEvent event) -> {
                int newN = (Integer) sizeSpinner.getValue();
                gridPanel.resetGrid(newN);
            });

            frame.setContentPane(root);
            frame.setVisible(true);
        });
    }

    private static JPanel createLegendPanel() {
        JPanel legend = new JPanel(new GridLayout(1, 3, 12, 0));
        legend.setBackground(COLOR_BG);
        legend.setBorder(BorderFactory.createEmptyBorder(4, 20, 0, 20));

        addLegendItem(legend, COLOR_BLOCKED, "Blocked");
        addLegendItem(legend, COLOR_OPEN, "Open");
        addLegendItem(legend, COLOR_FULL, "Full");
        return legend;
    }

    private static void addLegendItem(JPanel panel, Color color, String labelText) {
        JPanel item = new JPanel();
        item.setBackground(COLOR_BG);

        JPanel colorBox = new JPanel();
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(18, 18));
        colorBox.setBorder(BorderFactory.createLineBorder(COLOR_BORDER));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        item.add(colorBox);
        item.add(label);
        panel.add(item);
    }

    static class GridPanel extends JPanel {
        private int n;

        // TODO: Replace this grid[][] with your Percolation object
        private int[][] grid;

        private final JLabel statusBar;
        private final Random rng = new Random();

        GridPanel(int n, JLabel statusBar) {
            this.n = n;
            this.statusBar = statusBar;
            this.grid = new int[n][n];
            setBackground(COLOR_BG);
            setPreferredSize(new Dimension(560, 560));
            updateStatus();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int cellSize = Math.min(panelWidth, panelHeight) / GridPanel.this.n;

                    if (cellSize <= 0) {
                        return;
                    }

                    int gridWidth = cellSize * GridPanel.this.n;
                    int gridHeight = cellSize * GridPanel.this.n;
                    int left = (panelWidth - gridWidth) / 2;
                    int top = (panelHeight - gridHeight) / 2;

                    int col = (event.getX() - left) / cellSize;
                    int row = (event.getY() - top) / cellSize;

                    if (row < 0 || row >= GridPanel.this.n ||
                            col < 0 || col >= GridPanel.this.n) {
                        return;
                    }

                    // TODO: Call percolation.open(row, col) here
                    grid[row][col] = (grid[row][col] + 1) % 3;

                    // TODO: Call percolation.isFull(row, col) to set state to FULL
                    // TODO: Call percolation.percolates() and update status
                    updateStatus();
                    repaint();
                }
            });
        }

        void openRandomCell() {
            List<int[]> blockedCells = new ArrayList<>();

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 0) {
                        blockedCells.add(new int[]{row, col});
                    }
                }
            }

            if (blockedCells.isEmpty()) {
                statusBar.setText("No blocked cells remaining!");
                return;
            }

            int[] cell = blockedCells.get(rng.nextInt(blockedCells.size()));
            int row = cell[0];
            int col = cell[1];
            // TODO: Call percolation.open(row+1, col+1) here
            grid[row][col] = 1;
            updateStatus();
            repaint();
        }

        void resetGrid(int newN) {
            n = newN;
            // TODO: Create a new Percolation object here: percolation = new Percolation(newN)
            grid = new int[newN][newN];
            updateStatus();
            repaint();
        }

        void updateStatus() {
            int openCount = 0;
            int fullCount = 0;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 1) {
                        openCount++;
                    } else if (grid[row][col] == 2) {
                        fullCount++;
                    }
                }
            }

            // TODO: Use percolation.numberOfOpenSites() instead of counting manually
            statusBar.setText("Open sites: " + openCount
                    + " | Full sites: " + fullCount
                    + " | Grid: " + n + "×" + n);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int cellSize = Math.min(panelWidth, panelHeight) / n;
            int gridWidth = cellSize * n;
            int gridHeight = cellSize * n;
            int left = (panelWidth - gridWidth) / 2;
            int top = (panelHeight - gridHeight) / 2;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    Color cellColor;
                    if (grid[row][col] == 1) {
                        cellColor = COLOR_OPEN;
                    } else if (grid[row][col] == 2) {
                        cellColor = COLOR_FULL;
                    } else {
                        cellColor = COLOR_BLOCKED;
                    }

                    int x = left + col * cellSize;
                    int y = top + row * cellSize;
                    g.setColor(cellColor);
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(COLOR_BORDER);
                    g.drawRect(x, y, cellSize - 1, cellSize - 1);
                }
            }
        }
    }
}
