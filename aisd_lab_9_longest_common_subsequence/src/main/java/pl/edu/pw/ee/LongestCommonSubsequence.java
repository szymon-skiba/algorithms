package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private char[] columns;
    private char[] rows;
    private int[][] matrix;
    private int[][] values;

    private final int top = 3;
    private final int diagonally = 2;
    private final int left = 1;

    public LongestCommonSubsequence(String firstStr, String secondStr) {

        columns = firstStr.toCharArray();
        rows = secondStr.toCharArray();

        matrix = new int[secondStr.length() + 1][firstStr.length() + 1];
        values = new int[secondStr.length() + 1][firstStr.length() + 1];

    }

    public String findLCS() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (rows[i] == columns[j]) {
                    values[i + 1][j + 1] = values[i][j] + 1;
                    matrix[i + 1][j + 1] = diagonally;
                    sb.append(rows[i]);
                } else if (values[i][j + 1] >= values[i + 1][j]) {
                    values[i + 1][j + 1] = values[i][j + 1];
                    matrix[i + 1][j + 1] = top;
                } else {
                    values[i + 1][j + 1] = values[i + 1][j];
                    matrix[i + 1][j + 1] = left;
                }
            }
        }

        return sb.toString();
    }

    public void display() {
        for (int i = 0; i < rows.length; i++) {
            if (i == 0) {
                System.out.print("   ");
                for (char c : columns) {
                    System.out.print(" " + c + " ");
                }
                System.out.println();
            }
            System.out.print(" " + rows[i] + " ");
            for (int j = 0; j < columns.length; j++) {
                if (matrix[i + 1][j + 1] == 0) {
                    System.out.print(' ');
                } else {
                    if (matrix[i + 1][j + 1] == left) {
                        System.out.print('<');
                    } else if (matrix[i + 1][j + 1] == top) {
                        System.out.print('^');
                    } else {
                        System.out.print("\\");
                    }
                }
                System.out.print(values[i + 1][j + 1] + " ");
            }
            System.out.println();
        }

    }

}
