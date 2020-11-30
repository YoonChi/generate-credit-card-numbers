import java.util.Scanner;
import java.util.Random;

public class MCGenerator {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rng = new Random();

        System.out.print("How many MasterCard numbers would you like to generate? ");
        int num = scnr.nextInt();

        for (int i = 0; i < num; i++) {

//            String strFinal = "";
            String s = "";

            // generate random number from 0 to 1
            int randNum = (int) (rng.nextInt(2));

            // If 0, randomly generate a number 222100-272099 as first 6 digits
            if (randNum == 0) {
                int randLong = (rng.nextInt(5000) + 222100);
                s = String.valueOf(randLong);

                // Generate remaining 9 digits to MC number
                for (int j = 0; j < 9; j++) {
                    int randInt = (int) (rng.nextInt(10));
                    String otherS = String.valueOf(randInt);
                    s = s + otherS;
                }
            }
            else {
                // If 1, randomly generate a number 51-55 as first two digits
                int randShort = (int) (rng.nextInt(5) + 51);
                s = String.valueOf(randShort);

                // Generate remaining 13 digits to MC number
                for (int j = 0; j < 13; j++) {
                    int randInt = (int) (rng.nextInt(10));

                    String otherS = String.valueOf(randInt);
                    s = s + otherS;
                }
            }

            // Double every other digit starting from the left-to-last digit
            String doubleS = s;

            for (int x = doubleS.length() - 1; x >= 0; x-=2) {
                int parse = Integer.parseInt("" + doubleS.charAt(x));
                parse = parse * 2;

                // if doubled result is more than 9, subtract 9 from it
                if (parse > 9) {
                    parse = parse - 9;
                }

                // cast number back to string
                String strInt = String.valueOf(parse);

                // append updated digit to its original index
                doubleS = doubleS.substring(0,x) + strInt + doubleS.substring(x + 1);
            }

//            System.out.println("After every other number is doubled/subtracted 9: " + doubleS);

            int addS = 0;
            // parse string into int again, this time to add each digit up
            for (int x = 0; x < doubleS.length(); x++) {
                int parse2 = Integer.parseInt("" + doubleS.charAt(x));
                addS = addS + parse2;
            }

            // Iterate through 0 - 9, add each digit to addNum, and then check if it is divisible by 10
            for (int y = 0; y <= 9; y++) {
                addS = addS + y;
                if (addS % 10 == 0){
                    // cast last digit as string
                    String strDigit = String.valueOf(y);
                    // add string to rest of MC number
                    s = s + strDigit;
                    break;
                }
                else {
                    addS = addS - y;
                }
            }

            System.out.println(s);
        }
    }
}
