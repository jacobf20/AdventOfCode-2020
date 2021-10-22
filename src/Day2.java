import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Program for Day 2's problem
 * @author Jacob Fricano
 */
public class Day2 {

    /**
     * Check the validity of a password for part 1.
     * @param lowLim lower limit of count
     * @param highLim upper limit of count
     * @param letter desired letter
     * @param pass the password
     * @return whether pass is valid or not
     */
    private boolean checkValidity1(int lowLim, int highLim, char letter, String pass) {
        int letterCount = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == letter) {
                letterCount += 1;
            }
        }
        return lowLim <= letterCount && letterCount <= highLim;
    }

    /**
     * Check validity of a password for part 2
     * @param lowIndex lower index to check
     * @param highIndex upper index to check
     * @param letter desired letter
     * @param pass the password
     * @return whether pass is valid or not
     */
    private boolean checkValidity2(int lowIndex, int highIndex, char letter, String pass) {
        if (pass.charAt(lowIndex-1) == letter && pass.charAt(highIndex-1) != letter) {
            return true;
        } else if (pass.charAt(highIndex-1) == letter && pass.charAt(lowIndex-1) != letter) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Parses the input data, and then counts how many valid passwords
     * there are according to the respective rules.
     * @param args the data filename
     * @throws IOException file not found
     */
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BufferedReader input = new BufferedReader(new FileReader(filename));
        Day2 test = new Day2();
        String line = input.readLine();
        int passCount1 = 0;
        int passCount2 = 0;
        while (line != null) {
            String delims = "[-: ]";
            String[] tokens = line.split(delims);
            boolean valid1 = test.checkValidity1(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]), tokens[2].charAt(0), tokens[4]);
            boolean valid2 = test.checkValidity2(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]), tokens[2].charAt(0), tokens[4]);
            if (valid1) {
                passCount1++;
            }
            if (valid2) {
                passCount2++;
            }
            line = input.readLine();
        }
        System.out.println(passCount1);
        System.out.println(passCount2);
    }
}
