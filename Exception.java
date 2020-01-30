import java.util.Arrays;

public class Exception {
    private static final class RowMissException extends RuntimeException {
        RowMissException(String message) {super("Rows exception: " + message);}
    }

    private static final class ColunmMissException extends  RuntimeException {
        ColunmMissException(String message) {super("Columns exception: " + message);}
    }

    private static final class YouAreNotNumberException extends RuntimeException {
        YouAreNotNumberException(String message) {super("Not a number found: " + message);}
    }

    private static final String CORRECT_STRING = "1 2 3 -1\n2 5 8 0\n0 1 4 7\n3 6 9 8";
    private static final String EXTRA_ROW_STRING = "1 2 3 -1\n2 5 8 0\n0 1 4 7\n3 6 9 8\n9 8 7 4";
    private static final String EXTRA_COL_STRING = "1 2 3 -1 1\n2 5 8 0 1\n0 1 4 7 1\n3 6 9 8 1";
    private static final String NO_ROW_STRING = "1 2 3 -1\n2 5 8 0\n0 1 4 7";
    private static final String NO_COL_STRING = "1 2 3\n2 5 8\n0 1 4\n3 6 9";
    private static final String HAS_CHAR_STRING = "1 2 3 Z\n2 5 8 0\n0 1 4 7\n3 6 9 A";

    private static final int MATRIX_ROWS = 4;
    private static final int MATRIX_COLS = 4;

    private static String[][] stringToMatrix(String value) {
        String[] rows = value.split("\n");
        if (rows.length != MATRIX_ROWS) throw new RowMissException(rows.length + ":\n" + value);

        String[][] result = new String[MATRIX_ROWS][];
        for (int i = 0; i < result.length; i++) {
            result[i] = rows[i].split(" ");
            if (result[i].length != MATRIX_COLS) throw new ColunmMissException(result[i].length + ":\n" + value);
        }
        return result;
    }

    private static float calcMatrix(String[][] matrix){
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    result += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new YouAreNotNumberException(matrix[i][j]);
                }
            }
        }
        return result / 2f;
    }


    public static void main(String[] args) {
        try {
//            final String[][] matrix = stringToMatrix(CORRECT_STRING);
//            final String[][] matrix = stringToMatrix(NO_ROW_STRING);
            final String[][] matrix = stringToMatrix(EXTRA_ROW_STRING);
//            final String[][] matrix = stringToMatrix(NO_COL_STRING);
//            final String[][] matrix = stringToMatrix(EXTRA_COL_STRING);
//           final String[][] matrix = stringToMatrix(HAS_CHAR_STRING);
            System.out.println(Arrays.deepToString(matrix));
            System.out.println("Half sum = " + calcMatrix(matrix));
        } catch (YouAreNotNumberException e) {
            System.out.println("A NumberFormatException is thrown: " + e.getMessage());
        } catch (RowMissException | ColunmMissException e) {
            System.out.println("A RuntimeException successor is thrown: " + e.getMessage());
        }

    }
}
