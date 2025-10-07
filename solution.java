public class SlidingWindowWordSearch {
    static final int ROWS = 10;
    static final int COLS = 10;
    static final String WORD = "XMAS";
    static final String REVERSE_WORD = new StringBuilder(WORD).reverse().toString();

    public static void main(String[] args) {
        char[][] grid = {
            {'M','M','M','S','X','X','M','A','S','M'},
            {'M','S','A','M','X','M','S','M','S','A'},
            {'A','M','X','S','X','M','A','A','M','M'},
            {'M','S','A','M','A','S','M','S','M','X'},
            {'X','M','A','S','A','M','X','A','M','M'},
            {'X','X','A','M','M','X','X','A','M','A'},
            {'S','M','S','M','S','A','S','X','S','S'},
            {'S','A','X','A','M','A','S','A','A','A'},
            {'M','A','M','M','M','X','M','M','M','M'},
            {'M','X','M','X','A','X','M','A','S','X'}
        };

        int total = countXMAS(grid);
        System.out.println("Total occurrences of 'XMAS': " + total);
    }

    static int countXMAS(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int total = 0;

        // Check rows
        for (int i = 0; i < n; i++) {
            total += checkLine(grid[i]);
        }

        // Check columns
        for (int j = 0; j < m; j++) {
            char[] col = new char[n];
            for (int i = 0; i < n; i++) col[i] = grid[i][j];
            total += checkLine(col);
        }

        // Check diagonals (top-left to bottom-right)
        for (int k = 0; k <= n + m - 2; k++) {
            int size = Math.min(k + 1, Math.min(n, m));
            int len = Math.min(k + 1, Math.min(n, m));
            StringBuilder diag = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int j = k - i;
                if (j >= 0 && j < m) diag.append(grid[i][j]);
            }
            total += checkLine(diag.toString().toCharArray());
        }

        // Check diagonals (top-right to bottom-left)
        for (int k = -(n - 1); k <= m - 1; k++) {
            StringBuilder diag = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int j = i + k;
                if (j >= 0 && j < m) diag.append(grid[i][j]);
            }
            total += checkLine(diag.toString().toCharArray());
        }

        return total;
    }

    // Sliding window over a char array
    static int checkLine(char[] line) {
        int count = 0;
        for (int i = 0; i <= line.length - WORD.length(); i++) {
            String sub = new String(line, i, WORD.length());
            if (sub.equals(WORD) || sub.equals(REVERSE_WORD)) count++;
        }
        return count;
    }
}
