package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

/**
 * This class contains methods that allow you to swap two numbers.
 * For example :
 * number 1 - 1
 * number 2 - 2
 * User can see result: 2 1
 */
public class Swap {

    /**
     * Main function for user interaction.
     * Also, the user can see a message if he entered incorrect data.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int number1 = stringInputStreamToInteger(Messages.SWAP_INPUT_NUMBER1, scanner);
            int number2 = stringInputStreamToInteger(Messages.SWAP_INPUT_NUMBER2, scanner);

            Swap Swap = new Swap();
            System.out.println(Swap.calculateSwap(number1, number2));
        } catch (NumberFormatException e) {
            System.out.println(Messages.ALL_MESSAGE_ERROR);
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
     * This method swaps two numbers
     *
     * @param number1 - Input data
     * @param number2 - Input data
     * @return - result working program
     */
    public String calculateSwap(int number1, int number2) {
        number1 += number2;
        number2 = number1 - number2;
        number1 = number1 - number2;

        return format("%d %d", number1, number2);
    }
}
