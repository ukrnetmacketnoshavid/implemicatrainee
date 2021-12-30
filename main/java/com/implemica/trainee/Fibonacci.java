package com.implemica.trainee;

import com.implemica.trainee.exception.InvalidDataException;
import com.implemica.trainee.util.Messages;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * This class contains methods that allow you to find number of Fibonacci, knows index number's.
 * For example :
 * index - 2
 * You can see result: 1
 */
public class Fibonacci {
    /**
     * Main function for user interaction.
     * Also, the user can see a message if he entered incorrect data.
     */
    public static void main(String[] args) {
        System.out.println(Messages.FIB_MESSAGE_INPUT);

        try (Scanner scanner = new Scanner(System.in)) {
            Fibonacci fib = new Fibonacci();
            System.out.println(fib.validateDataAndCalculatingResult(scanner.next()));
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method for validate BigInteger data
     *
     * @param inputData - String param indicating index of search number Fibonacci.
     * @return Method return exception or program result
     * @throws InvalidDataException - For example:
     *                              inputData = '*'
     *                              Message from program: Invalid data
     */
    public BigInteger validateDataAndCalculatingResult(String inputData) throws InvalidDataException {
        try {
            BigInteger indexFib = new BigInteger(inputData);
            BigInteger number1 = BigInteger.ONE;
            BigInteger number2 = BigInteger.ONE;

            return startCalculateResult(number1, number2, indexFib.longValue());
        } catch (NumberFormatException e) {
            throw new InvalidDataException(Messages.EXCEPTION_MESSAGE);
        }

    }

    /**
     * This method calculate result working a program.
     *
     * @param number1 - BigInteger param, auxiliary variable.
     * @param number2 - BigInteger param indicating result.
     * @return - Method return result working a program.
     */
    private BigInteger startCalculateResult(BigInteger number1, BigInteger number2, long indexFib) {
        BigInteger number0;
        for (int i = 3; i <= Math.abs(indexFib); i++) {
            number0 = number2;
            number2 = number2.add(number1);
            number1 = number0;
        }
        BigInteger result;
        if (indexFib == 1 | indexFib == 2) {
            result = BigInteger.ONE;
        } else if (indexFib == 0) {
            result = BigInteger.ZERO;
        } else {
            result = number2;
        }

        return result;
    }
}