package solution;

public class Feb_11 {
    public int solution(String[] grid) {
        char[][] cGrid = castGrid(grid);
        markOutsidePoints(cGrid);

        int area = 0;

        for (int r = 0; r < cGrid.length; r++) {
            for (int c = 0; c < cGrid[0].length; c++) {
                if (cGrid[r][c] != 'x') {
                    area += calculateAreaByDfs(cGrid, r, c);
                }
            }
        }

        return area;
    }

    private int calculateAreaByDfs(char[][] cGrid, int r, int c) {
        int numRows = cGrid.length;
        int numCols = cGrid[0].length;

        cGrid[r][c] = 'x';

        int area = 1;

        if (r > 0 && cGrid[r - 1][c] != 'x') {
            area += calculateAreaByDfs(cGrid, r - 1, c);
        }

        if (c > 0 && cGrid[r][c - 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r, c - 1);
        }

        if (r < numRows - 1 && cGrid[r + 1][c] != 'x') {
            area += calculateAreaByDfs(cGrid, r + 1, c);
        }

        if (c < numCols - 1 && cGrid[r][c + 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r, c + 1);
        }

        if (r > 0 && c > 0 && cGrid[r - 1][c - 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r - 1, c - 1);
        }

        if (r > 0 && c < numCols - 1 && cGrid[r - 1][c + 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r - 1, c + 1);
        }

        if (r < numRows - 1 && c > 0 && cGrid[r + 1][c - 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r + 1, c - 1);
        }

        if (r < numRows - 1 && c < numCols - 1 && cGrid[r + 1][c + 1] != 'x') {
            area += calculateAreaByDfs(cGrid, r + 1, c + 1);
        }

        return area;
    }

    private void markOutsidePoints(char[][] cGrid) {
        int numCols = cGrid[0].length;
        int numRows = cGrid.length;

        for (int c = 0; c < numCols; c++) {
            if (cGrid[0][c] == '.') {
                markByDfs(cGrid, 0, c);
            }
            if (cGrid[numRows - 1][c] == '.') {
                markByDfs(cGrid, numRows - 1, c);
            }
        }

        for (int r = 0; r < numRows; r++) {
            if (cGrid[r][0] == '.') {
                markByDfs(cGrid, r, 0);
            }
            if (cGrid[r][numCols - 1] == '.') {
                markByDfs(cGrid, r, numCols - 1);
            }
        }
    }

    private void markByDfs(char[][] cGrid, int r, int c) {
        cGrid[r][c] = 'x';

        if (r > 0 && cGrid[r - 1][c] == '.') {
            markByDfs(cGrid, r - 1, c);
        }

        if (c > 0 && cGrid[r][c - 1] == '.') {
            markByDfs(cGrid, r, c - 1);
        }

        if (r < cGrid.length - 1 && cGrid[r + 1][c] == '.') {
            markByDfs(cGrid, r + 1, c);
        }

        if (c < cGrid[0].length - 1 && cGrid[r][c + 1] == '.') {
            markByDfs(cGrid, r, c + 1);
        }
    }
    private char[][] castGrid(String[] grid) {
        int numCols = grid[0].length();
        int numRows = grid.length;

        char[][] cGrid = new char[numRows][numCols];

        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                cGrid[r][c] = grid[r].charAt(c);
            }
        }

        return cGrid;
    }
}
