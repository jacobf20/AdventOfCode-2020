import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Program for Day 4's problem.
 * @author Jacob Fricano
 */
public class Day4 {

    int validCount; // count of valid passports

    /**
     * For part 1, checks to see if the passport has all the
     * necessary fields by using an array of keys.
     * @param keys array of keys
     * @return whether or not the passport is valid
     */
    private static boolean checkValidity(ArrayList<String> keys) {
        return keys.contains("byr") && keys.contains("iyr") && keys.contains("eyr")
                && keys.contains("hgt") && keys.contains("hcl") && keys.contains("ecl")
                && keys.contains("pid");
    }

    /**
     * Check that the birth year is within given dates
     * @param year the birth year
     * @return whether or not the year is valid
     */
    private static boolean checkByr(int year) {
        return year >= 1920 && year <=2002;
    }

    /**
     * Check that the issue year is within given dates
     * @param year the issue year
     * @return whether or not the year is valid
     */
    private static boolean checkIyr(int year) {
        return year >= 2010 && year <= 2020;
    }

    /**
     * Check that the expiration year is within given dates
     * @param year the expiration year
     * @return whether or not the year is valid
     */
    private static boolean checkEyr(int year) {
        return year >= 2020 && year <= 2030;
    }

    /**
     * Check that the height field is of the correct format,
     * meaning it has units of cm or in, and check that the
     * actual height is within certain ranges.
     * @param height the height with units
     * @return whether or not the height is valid
     */
    private static boolean checkHgt(String height) {
        if (height.contains("cm")) {
            int endIndex = height.indexOf('c');
            height = height.substring(0,endIndex);
            int hgt = Integer.parseInt(height);
            return hgt >= 150 && hgt <= 193;
        } else if (height.contains("in")) {
            int endIndex = height.indexOf('i');
            height = height.substring(0,endIndex);
            int hgt = Integer.parseInt(height);
            return hgt >= 59 && hgt <= 76;
        }
        return false;
    }

    /**
     * Check that the hair color field is of the correct
     * hexadecimal format, meaning it starts with '#' and
     * only contains 0-9 or a-f.
     * @param color the hair color
     * @return whether or not the hair color is valid
     */
    private static boolean checkHcl(String color) {
        if (color.startsWith("#")) {
            for (int i = 1; i < color.length(); i++) {
                int ascii = color.charAt(i);
                if (!((ascii >= 48 && ascii <= 57) || (ascii >= 97 && ascii <= 102))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check that the eye color field is correct, meaning
     * it has to belong to one of the below options.
     * @param color the eye color
     * @return whether or not the eye color is valid
     */
    private static boolean checkEcl(String color) {
        return color.equals("amb") || color.equals("blu") || color.equals("brn")
                || color.equals("gry") || color.equals("grn") || color.equals("hzl")
                || color.equals("oth");
    }

    /**
     * Check that the passport ID field is correct by
     * making sure it is of length 9 and only contains
     * numbers 0-9.
     * @param id the passport ID
     * @return whether or not the passport ID is valid
     */
    private static boolean checkPid(String id) {
        if (id.length() == 9) {
            for (int i = 0; i < 9; i++) {
                int ascii = id.charAt(i);
                if(!(ascii>=48 && ascii <= 57)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks the validity of a passport for part2 by checking that
     * all of the fields contain the correct info.
     * @param pass_dict that passport with field names as keys and corresponding info as values
     * @return whether or not the passport is valid
     */
    private static boolean checkValidity2(Hashtable<String, String> pass_dict) {
        int byr = Integer.parseInt(pass_dict.get("byr"));
        int iyr = Integer.parseInt(pass_dict.get("iyr"));
        int eyr = Integer.parseInt(pass_dict.get("eyr"));
        String hgt = pass_dict.get("hgt");
        String hcl = pass_dict.get("hcl");
        String ecl = pass_dict.get("ecl");
        String pid = pass_dict.get("pid");
        return checkByr(byr) && checkIyr(iyr) && checkEyr(eyr) && checkHgt(hgt)
                && checkHcl(hcl) && checkEcl(ecl) && checkPid(pid);
    }

    /**
     * Parses the data from input into an array of string representations
     * of passports. The passport strings then get parsed into a key list
     * for part 1 and a hashtable for part 2. Then the number of valid
     * passports is counted and printed out.
     * @param args the filename
     * @throws IOException file not found
     */
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BufferedReader in = new BufferedReader(new FileReader(filename));
        ArrayList<String> passports = new ArrayList<>();
        String line = in.readLine();
        String tmpLine = "";
        Day4 test = new Day4();
        while (true) {
            if (line.equals("")) {
                passports.add(tmpLine);
                tmpLine = "";
            } else {
                tmpLine += line + "\s";
            }
            line = in.readLine();
            if (line == null) {
                passports.add(tmpLine);
                break;
            }
        }
        Hashtable<String, String> pass_dict = new Hashtable<>();
        test.validCount = 0;
        for (String pass : passports) {
            ArrayList<String> keys = new ArrayList<>();
            String[] splitPass = pass.split(":|\s");
            for (int i=0; i< splitPass.length; i++) {
                if (i%2 == 0) {
                    keys.add(splitPass[i]); // for part 1
                    pass_dict.put(splitPass[i], splitPass[i+1]);
                }
            }
            if (checkValidity(keys)) {
                if (checkValidity2(pass_dict)) {
                    test.validCount++;
                }
            }
        }
        System.out.println(test.validCount);
    }
}