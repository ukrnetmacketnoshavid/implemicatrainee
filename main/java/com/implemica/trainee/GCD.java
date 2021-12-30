package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 * This class contains methods that allow you to find GCD four numbers.
 * For example :
 * number 1 - 2
 * number 2 - 12
 * number 3 - 8
 * number 4 - 16
 * User can see result: 2
 */
public class GCD {
    /**
     * Main function for user interaction.
     * Also, the user can see a message if he entered incorrect data.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int number1 = stringInputStreamToInteger(Messages.GCD_INPUT_NUMBER1, scanner);
            int number2 = stringInputStreamToInteger(Messages.GCD_INPUT_NUMBER2, scanner);
            int number3 = stringInputStreamToInteger(Messages.GCD_INPUT_NUMBER3, scanner);
            int number4 = stringInputStreamToInteger(Messages.GCD_INPUT_NUMBER4, scanner);

            GCD gcd = new GCD();
            System.out.println(gcd.calculateGCD(number1, number2, number3, number4));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method for converting input stream data to input integer data
     *
     * @param messageForUser - String param, shows what to enter
     * @param scanner        - Object for parse input stream
     * @return - method returns the conversion result of integer to string.
     */
    private static int stringInputStreamToInteger(String messageForUser, Scanner scanner) {
        System.out.println(messageForUser);
        String parameterForParse = scanner.next();
        return parseInt(parameterForParse);
    }

    /**
     * This method initializes mass indicating input data and calls method start calculate result.
     *
     * @param number1 - Integer param (input data)
     * @param number2 - Integer param (input data)
     * @param number3 - Integer param (input data)
     * @param number4 - Integer param (input data)
     * @return - result working program
     */
    public String calculateGCD(int number1, int number2, int number3, int number4) {
        int[] numbers = new int[4];
        numbers[0] = number1;
        numbers[1] = number2;
        numbers[2] = number3;
        numbers[3] = number4;

        return startCalculateResult(numbers);
    }

    /**
     * This method runs calculate GCD
     *
     * @param numbers - input data
     * @return result working program
     */
    private String startCalculateResult(int[] numbers) {
        int result = 1;

        for (int i = 0; i < numbers.length; i++) {
            if (i + 1 < numbers.length) {
                for (int j = i + 1; j <= i + 1; j++) {
                    result = calculateGCD(numbers[i], numbers[j]);
                }
                numbers[i + 1] = result;
            }
        }

        return String.valueOf(Math.abs(result));
    }

    /**
     * This method calculates GCD
     *
     * @param number1 - input data
     * @param number2 - input data
     * @return - result working program
     */
    private int calculateGCD(int number1, int number2) {
        if (number2 == 0) {
            return number1;
        } else {
            return calculateGCD(number2, number1 % number2);
        }
    }
}
