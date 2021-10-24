import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Program for Day 5's problem.
 * @author Jacob Fricano
 */
public class Day5 {


    /**
     * Performs a binary search to find both the row and column
     * of the seat. 'F' for the row search, and 'L' for the column
     * search mean to look in the lower half of the range.
     * @param data the seat code
     * @param index the index of where to look in the seat code
     * @param low least number in range of numbers
     * @param high greatest number in range of numbers
     * @return row or column found based on seat code
     */
    private static int BinarySearch(String data, int index, int low, int high) {
        int middle = low + (high - low) / 2;

        if (middle == low) {
            if (data.charAt(index) == 'F' || data.charAt(index) == 'L') {
                return low;
            } else {
                return high;
            }
        } else if (data.charAt(index) == 'F' || data.charAt(index) == 'L') {
            return BinarySearch(data, index+1, low, middle);
        } else {
            return BinarySearch(data, index+1, middle+1, high);
        }
    }

    /**
     * Parses data into an array of seat codes, and then uses
     * BinarySearch() to make an array of seat id's. For part 1
     * the max of this array is found, and for part 2 the missing
     * id is found.
     * @param args the filename
     * @throws IOException file not found
     */
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BufferedReader in = new BufferedReader(new FileReader(filename));
        ArrayList<String> seats = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            seats.add(line);
            line = in.readLine();
        }
        ArrayList<Integer> seatIds = new ArrayList<>();
        for (String seat : seats) {
            String rowCode = seat.substring(0,7);
            String colCode = seat.substring(7,10);
            int row = BinarySearch(rowCode, 0, 0, 127);
            int col = BinarySearch(colCode, 0, 0, 7);
            int id = row * 8 + col;
            seatIds.add(id);
        }
        int max = 0;
        for (int id : seatIds) {
            if (id > max) {
                max = id;
            }
        }
        Collections.sort(seatIds);
        int mySeat;
        for (int i = 0; i < seatIds.size() - 1; i++) {
            if (seatIds.get(i+1) - seatIds.get(i) == 2) {
                mySeat = seatIds.get(i) + 1;
                System.out.println(mySeat); // for part 2
            }
        }
        System.out.println(max); // for part 1
    }
}
