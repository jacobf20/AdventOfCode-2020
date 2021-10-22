import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Program for solving Day 1's problem.
 *
 * @author Jacob Fricano
 */

public class Day1 {

    /**
     * Find the answer to the problem given source data.
     * @param data the array of provided input data
     * @return the answer
     */
    private int findAnswer(ArrayList<Integer> data) {
        int result = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                for (int s = 0; s < data.size(); s++) {
                    if ((data.get(i) + data.get(j) + data.get(s)) == 2020) {
                        return data.get(i) * data.get(j) *data.get(s);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Parse the input data and pass if to the findAnswer() function
     * @param args unused
     * @throws IOException file not found
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("data/input1.txt"));
        Day1 test = new Day1();
        ArrayList<Integer> data = new ArrayList<>();
        int result;
        String line = in.readLine();
        while (line != null) {
            data.add(Integer.parseInt(line));
            line = in.readLine();
        }
        result = test.findAnswer(data);
        System.out.println(result);
    }
}
