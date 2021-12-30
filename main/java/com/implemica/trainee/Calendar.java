package com.implemica.trainee;

import com.implemica.trainee.exception.InvalidDataException;
import com.implemica.trainee.util.Messages;
import static java.lang.Integer.parseInt;

import java.util.Scanner;
/**
 * This class contains methods that allow you to find day of week of the desired date
 * For example :
 * day of week New Year - 1
 * month - 1
 * day - 1
 * You can see result: 1 (Monday)
 */
public class Calendar {
    /** The program counts days from Saturday, this constant is needed so that the result is displayed from Monday*/
    private static final int RESULT_SHIFT = 2;
    /** The program counts days from Saturday, this constant is needed in order to change it to a positive number if the result is less than zero*/
    private static final int RESTORATION_RESULT_LESS_ZERO = 5;

    /**
     * Main function for user interaction.
     * Also, the user can see a message if he entered incorrect data.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int year = stringInputStreamToInteger(Messages.CALENDAR_INPUT_YEAR, scanner);
            int day = stringInputStreamToInteger(Messages.CALENDAR_INPUT_DAY, scanner);
            int month = stringInputStreamToInteger(Messages.CALENDAR_INPUT_MONTH, scanner);

            Calendar calendar = new Calendar();
            int result = calendar.validateDataAndCalculateResult(year, day, month);

            System.out.println(result);
        } catch (NumberFormatException | InvalidDataException e) {
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
        String parameterForParse;

        System.out.println(messageForUser);
        parameterForParse = scanner.next();

        return parseInt(parameterForParse);
    }

    /**
     * This method for validate integer data.
     *
     * @param year  - An integer parameter indicating the day of week of New Year.
     * @param month - An integer parameter indicating the index search month.
     * @param day   - An integer parameter indicating the day.
     * @return Method return exception or program result
     * @throws InvalidDataException - For example:
     *                              year = -1
     *                              Message from program: Invalid data year
     */
    public int validateDataAndCalculateResult(int year, int month, int day) throws InvalidDataException {
        if (year < 1 ^ year > 7) {
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "year"));
        }
        if (day < 1 ^ day > 31) {
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "day"));
        }
        if (month < 1 ^ month > 12) {
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "month"));
        }

        return calculateResult(year, month, day);
    }

    /**
     * This method calculate result working a program.
     *
     * @param year  - An integer parameter indicating the day of week of New Year.
     * @param month - An integer parameter indicating the index search month.
     * @param day   - An integer parameter indicating the index search month.
     * @return - Method return result working a program.
     * @throws InvalidDataException - For example:
     *                              day > count days in January (32)
     *                              Message from program: Invalid data day
     */
    private int calculateResult(int year, int month, int day) throws InvalidDataException {
        int[] codeOfMonths = new int[]{1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6};
        int[] dayOfMonths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (day > dayOfMonths[month - 1]) {
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "day"));
        }

        int dayOfWeek = (day + codeOfMonths[month - 1] + year) % 7;

        if (dayOfWeek - RESULT_SHIFT <= 0) {
            dayOfWeek += RESTORATION_RESULT_LESS_ZERO;
        } else {
            dayOfWeek -= RESULT_SHIFT;
        }

        return dayOfWeek;
    }
}
