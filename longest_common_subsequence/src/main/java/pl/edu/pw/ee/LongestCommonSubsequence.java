package pl.edu.pw.ee;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.exit;

class LongestCommonSubsequence {

    private char[] columns;
    private char[] rows;
    private int[][] matrixOfDirections;
    private int[][] values;
    private int[][] isPartOfPath;

    private final int top = 3;
    private final int diagonally = 2;
    private final int left = 1;


    public LongestCommonSubsequence(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("Wprowadzone napisy nie moga byc null.");
        }

        if (firstStr.isEmpty() || secondStr.isEmpty()) {
            throw new IllegalArgumentException("Wprowadzone napisy nie moga byc puste.");
        }

        try {
            columns = firstStr.toCharArray();
            rows = secondStr.toCharArray();


            matrixOfDirections = new int[secondStr.length() + 1][firstStr.length() + 1];
            values = new int[secondStr.length() + 1][firstStr.length() + 1];
            isPartOfPath = new int[secondStr.length() + 1][firstStr.length() + 1];
        } catch (OutOfMemoryError e) {
            throw e;
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (rows[i] == columns[j]) {
                    values[i + 1][j + 1] = values[i][j] + 1;
                    matrixOfDirections[i + 1][j + 1] = diagonally;
                } else if (values[i][j + 1] >= values[i + 1][j]) {
                    values[i + 1][j + 1] = values[i][j + 1];
                    matrixOfDirections[i + 1][j + 1] = top;
                } else {
                    values[i + 1][j + 1] = values[i + 1][j];
                    matrixOfDirections[i + 1][j + 1] = left;
                }
            }
        }

    }

    public String findLCS() {
        int nRow = rows.length;
        int nColumn = columns.length;

        StringBuilder result = new StringBuilder();

        int currentCell = matrixOfDirections[nRow][nColumn];

        while (nRow != 0 && nColumn != 0) {
            isPartOfPath[nRow][nColumn] = 1;

            if (currentCell == diagonally) {
                result.append(reprChar(rows[nRow - 1]));
                nRow -= 1;
                nColumn -= 1;
            } else if (currentCell == top) {
                nRow -= 1;
            } else if (currentCell == left) {
                nColumn -= 1;
            } else {
                throw new IllegalStateException("Nieokreślony kierunek");
            }

            currentCell = matrixOfDirections[nRow][nColumn];
        }

        return result.reverse().toString();

    }

    private String reprChar(char c) {

        String result;

        switch (c) {

            case '\0':
                result = "\\0";
                break;
            case '\t':
                result = ("\\t");
                break;
            case '\n':
                result = ("\\n");
                break;
            case '\r':
                result = ("\\r");
                break;
            case '\"':
                result = ("\\\"");
                break;
            case '\\':
                result = ("\\\\");
                break;
            default:
                result = String.valueOf(c);
        }

        return result;
    }

    public void display() {
        if (columns.length >= 999999 || rows.length >= 999999) {
            throw new IllegalStateException("Przekroczono maksymalne parametry wyświetlania");
        }
        drawColumnHeaderRow();
        System.out.println();

        for (int i = 0; i < rows.length + 1; i++) {
            drawUpperPartOfCell(i);
            System.out.println();
            try {
                drawMiddlePartOfCell(i);
            } catch (UnsupportedEncodingException e) {
                System.out.println("Blad podczas tworzenia widoku: " + e.getMessage());
            }
            System.out.println();
            drawBottomPartOfCell();
            System.out.println();
            drawRowLine();
            System.out.println();

        }

    }

    private void drawRowLine() {
        for (int i = 0; i < columns.length + 2; i++) {
            if (i == 0) {
                System.out.print("+-------+");
            } else {
                System.out.print("-------+");
            }
        }
    }

    private void drawColumnHeaderRow() {
        drawRowLine();
        System.out.println();

        for (int i = 0; i < columns.length + 2; i++) {
            if (i == 0) {
                System.out.print("|       |");
            } else {
                System.out.print("       |");
            }
        }
        System.out.println();

        for (int i = 0; i < columns.length + 2; i++) {
            if (i == 0) {
                System.out.print("|       |");
            } else if (i == 1) {
                System.out.print("       |");
            } else {
                String str = reprChar(columns[i - 2]);
                int len = 4 - str.length();
                System.out.format("%" + len + "s", "");
                System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
                System.out.print(str + "   |");
            }
        }
        System.out.println();

        for (int i = 0; i < columns.length + 2; i++) {
            if (i == 0) {
                System.out.print("|       |");
            } else {
                System.out.print("       |");
            }
        }
        System.out.println();

        drawRowLine();
    }

    private void drawUpperPartOfCell(int row) {
        System.out.print("|       |");

        for (int i = 0; i < columns.length + 1; i++) {
            if (isPartOfPath[row][i] == 1) {
                int direction = matrixOfDirections[row][i];
                switch (direction) {
                    case diagonally:
                        System.out.print("\\   ");
                        break;
                    case top:
                        System.out.print("   ^");
                        break;
                    default:
                        System.out.print("    ");
                        break;
                }
                System.out.print("   |");
            } else {
                System.out.print("       |");
            }
        }
    }

    private void drawMiddlePartOfCell(int row) throws UnsupportedEncodingException {

        if (row == 0) {
            System.out.print("|       |");
        } else {
            String str = reprChar(rows[row - 1]);
            int len = 4 - str.length();
            System.out.format("|%" + len + "s", "");
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.out.print(str + "   |");
        }

        for (int i = 0; i < columns.length + 1; i++) {

            int direction = matrixOfDirections[row][i];

            if (direction == left && isPartOfPath[row][i] == 1) {
                System.out.print("<");
            } else {
                System.out.print(" ");
            }

            int num = values[row][i];
            int len = 6 - (num == 0 ? 1 : (int) (Math.log10(num) + 1));
            if (len < 0) {
                throw new IllegalStateException("Przekroczono maksymalne parametry wyświetlania");
            }
            System.out.format("%" + len / 2 + "s", "");
            System.out.print(num);
            System.out.format("%" + (len - len / 2) + "s|", "");

        }
    }

    private void drawBottomPartOfCell() {
        for (int i = 0; i < columns.length + 2; i++) {
            if (i == 0) {
                System.out.print("|       |");
            } else {
                System.out.print("       |");
            }
        }
    }

}
