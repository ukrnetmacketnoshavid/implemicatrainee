package com.implemica.trainee;

import com.implemica.trainee.exception.InvalidDataException;
import com.implemica.trainee.util.Messages;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 * This class contains methods that allow you to find an entrance and floor of an apartment,
 * knowing its index and the dimension of the entrance.
 * For example :
 * numberOfApartments - 5
 * numberOfFloors - 8
 * indexOfSearchApartments - 5
 * User can see result: 1 1.(1 entrance, 1 floor)
 */
public class Apartments {
    /**
     * Main function for user interaction.
     * Also, the user can see a message if he entered incorrect data.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfApartments = stringInputStreamToInteger(Messages.APARTMENTS_INPUT_COUNT_X, scanner);
            int numberOfFloors = stringInputStreamToInteger(Messages.APARTMENTS_INPUT_COUNT_Y, scanner);
            int indexOfSearchApartments = stringInputStreamToInteger(Messages.APARTMENTS_INPUT_SEARCH, scanner);

            Apartments apartments = new Apartments();
            System.out.println(apartments.validateNumberData(numberOfApartments, numberOfFloors, indexOfSearchApartments));
        } catch (NumberFormatException | InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method for converting input stream data to input integer data.
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
     * @param numberOfApartments      - An integer parameter indicating the number of apartments on one floor.
     * @param numberOfFloors          - An integer parameter indicating the number of floors on one entrance.
     * @param indexOfSearchApartments - An integer parameter indicating the index of search apartment.
     * @return Method return exception or program result
     * @throws InvalidDataException - For example:
     *                              numberOfFloors = -1
     *                              Message from program: Invalid data numberOfFloors
     */
    public String validateNumberData(int numberOfApartments, int numberOfFloors, int indexOfSearchApartments) throws InvalidDataException {
        if (numberOfFloors <= 0)
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "numberOfFloors"));
        if (numberOfApartments <= 0)
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "numberOfApartments"));
        if (indexOfSearchApartments <= 0)
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "indexOfSearchApartments"));

        return calculateResult(numberOfApartments, numberOfFloors, indexOfSearchApartments);
    }

    /**
     * This method calculate result working a program.
     *
     * @param numberOfApartments      - An integer parameter indicating the number of apartments on one floor.
     * @param numberOfFloors          - An integer parameter indicating the number of floors on one entrance.
     * @param indexOfSearchApartments - An integer parameter indicating the index of search apartment.
     * @return - Method return result working a program.
     * @throws InvalidDataException - For example:
     *                              size Entrance > Integer.MAX_VALUE
     *                              Message from program: Invalid data sizeEntrance
     */
    private String calculateResult(int numberOfApartments, int numberOfFloors, int indexOfSearchApartments) throws InvalidDataException {
        long sizeEntrance = (long) numberOfApartments * numberOfFloors;

        if (sizeEntrance > Integer.MAX_VALUE) {
            throw new InvalidDataException(String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "sizeEntrance"));
        }

        int resultEntrance = (int) Math.ceil(Math.abs((double) indexOfSearchApartments / sizeEntrance));
        int resultFloor = (int) Math.ceil(Math.abs((double) (indexOfSearchApartments - (sizeEntrance * (resultEntrance - 1))) / numberOfApartments));

        return resultEntrance + " " + resultFloor;
    }
}
