import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Program for Day 3's problem.
 * @author Jacob Fricano
 */
public class Day3 {

    int row_count; //num of rows in input data

    /**
     * This method counts the number of trees encountered when
     * following a slope down the map, and wrapping around when
     * meeting an edge.
     *
     * @param map the 2D array of the input data
     * @param iSlope the increment in the vertical direction
     * @param jSlope the increment in the horizontal direction
     * @param row_count the num of rows in the input
     * @return the number of trees encountered by following the slope
     */
    private static int countTrees(char[][] map, int iSlope, int jSlope, int row_count) {
        int treeCount = 0;
        int j = jSlope;
        for (int i = iSlope; i < row_count; i+=iSlope) {
            if (map[i][j] == '#') {
                treeCount++;
            }
            j+=jSlope;
            if (j >= 31) {
                j = j%31;
            }
        }
        return treeCount;
    }

    /**
     * Parses the input data into a 2D array, and passes the data
     * and respective slopes to the countTrees() function in order
     * to get an answer for both parts of the problem.
     *
     * @param args input filename
     * @throws IOException file not found
     */
    public static void main (String[] args) throws IOException {
        String filename = args[0];
        BufferedReader input = new BufferedReader(new FileReader(filename));
        Day3 test = new Day3();
        int col_dim = 31;
        test.row_count = 323;
        char[][] map = new char[test.row_count][col_dim];
        String line = input.readLine();
        for (int i = 0; i < test.row_count; i++) {
            for (int j = 0; j < col_dim; ++j) {
                map[i][j] = line.charAt(j);
            }
            line = input.readLine();
        }
        long result1 = countTrees(map, 1, 3, test.row_count);
        long result2 = countTrees(map, 1, 1, test.row_count);
        long result3 = countTrees(map, 1,5, test.row_count);
        long result4 = countTrees(map, 1, 7, test.row_count);
        long result5 = countTrees(map, 2, 1, test.row_count);
        long result = result1 * result2 * result3 * result4 * result5;
        System.out.println(result1);
        System.out.println(result);
    }
}
