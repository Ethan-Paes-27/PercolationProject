/*
 * PercolationVisualizer.java — Student Version
 * ---------------------------------------------
 * GUI starter file for the Percolation Project.
 *
 * This file runs as-is, but uses a manual grid[][] for state.
 * Your job: integrate your Percolation class by completing all 8 TODOs.
 *
 * Search for "TODO" to find each integration point.
 *
 * Percolation API (row and col are 1-based):
 *   Percolation(int n)
 *   void open(int row, int col)
 *   boolean isOpen(int row, int col)
 *   boolean isFull(int row, int col)
 *   boolean percolates()
 *   int numberOfOpenSites()
 *
 * Compile: javac Percolation.java PercolationVisualizer.java
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

        // TODO 1: Declare a Percolation field to replace grid[][].
        //         Add this line:  private Percolation percolation;
        //         Then delete the grid[][] declaration below.
        private int[][] grid;

        private final JLabel statusBar;
        private final Random rng = new Random();

        GridPanel(int n, JLabel statusBar) {
            this.n = n;
            this.statusBar = statusBar;
            // TODO 2: Initialize your Percolation object instead of grid[][].
            //         Replace the line below with:  this.percolation = new Percolation(n);
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
                    if (cellSize <= 0) return;
                    int gridWidth = cellSize * GridPanel.this.n;
                    int gridHeight = cellSize * GridPanel.this.n;
                    int left = (panelWidth - gridWidth) / 2;
                    int top = (panelHeight - gridHeight) / 2;
                    int col = (event.getX() - left) / cellSize;
                    int row = (event.getY() - top) / cellSize;
                    if (row < 0 || row >= GridPanel.this.n ||
                            col < 0 || col >= GridPanel.this.n) return;

                    // TODO 3: Open the clicked site using your Percolation object.
                    //         The GUI uses 0-based row/col; Percolation uses 1-based.
                    //         Replace the line below with:  percolation.open(row + 1, col + 1);
                    grid[row][col] = (grid[row][col] + 1) % 3;

                    updateStatus();
                    repaint();
                }
            });
        }

        void openRandomCell() {
            List<int[]> blockedCells = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    // TODO 4: Use percolation.isOpen() to find blocked cells.
                    //         Replace the condition below with:  if (!percolation.isOpen(row + 1, col + 1))
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
            // TODO 5: Open the randomly chosen site using your Percolation object.
            //         Replace the line below with:  percolation.open(row + 1, col + 1);
            grid[row][col] = 1;
            updateStatus();
            repaint();
        }

        void resetGrid(int newN) {
            n = newN;
            // TODO 6: Create a fresh Percolation object when the grid resets.
            //         Replace the line below with:  percolation = new Percolation(newN);
            grid = new int[newN][newN];
            updateStatus();
            repaint();
        }

        void updateStatus() {
            // TODO 7: Rewrite this method using your Percolation object.
            //   a) int openCount = percolation.numberOfOpenSites();
            //   b) int total = n * n;
            //   c) double pct = openCount * 100.0 / total;
            //   d) if (percolation.percolates()) show: "✓ System percolates!  Open sites: X / total  (P%)"
            //      else show: "Open sites: X / total  (P%)  |  Grid: n×n"
            //   Use String.format() with %.1f%% for the percentage.
            //   Delete the manual counting loop below and replace with the above.
            int openCount = 0;
            int fullCount = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 1) openCount++;
                    else if (grid[row][col] == 2) fullCount++;
                }
            }
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
                    // TODO 8a: Set cell color using your Percolation object.
                    //          Check isFull() first (it implies isOpen()), then isOpen(), then blocked.
                    //          Replace the block below with:
                    //            if (percolation.isFull(row + 1, col + 1))       cellColor = COLOR_FULL;
                    //            else if (percolation.isOpen(row + 1, col + 1))  cellColor = COLOR_OPEN;
                    //            else                                             cellColor = COLOR_BLOCKED;
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

            // TODO 8b: Draw a green outer border when the system percolates, dark otherwise.
            //          Add these lines after the loop:
            //            g.setColor(percolation.percolates() ? new Color(0x22c55e) : COLOR_BORDER);
            //            g.drawRect(left, top, gridWidth - 1, gridHeight - 1);
            //            g.drawRect(left + 1, top + 1, gridWidth - 3, gridHeight - 3);
        }
    }
}
