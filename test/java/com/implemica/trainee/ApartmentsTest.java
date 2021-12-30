package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApartmentsTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void setSystemOut() {
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    @AfterEach
    public void setSystemOutDefault() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    void testValidData(String expectedResult, int numberOfApartments, int numberOfFloors, int searchApartments) throws IOException {
        String data = numberOfApartments + System.lineSeparator()
                + numberOfFloors + System.lineSeparator()
                + searchApartments + System.lineSeparator();
        String expected = Messages.APARTMENTS_INPUT_COUNT_X + System.lineSeparator();
        expected += Messages.APARTMENTS_INPUT_COUNT_Y + System.lineSeparator();
        expected += Messages.APARTMENTS_INPUT_SEARCH + System.lineSeparator();
        expected += expectedResult + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Apartments.main(new String[]{});
        }

        String outData = OUT_CONTENT.toString();

        assertEquals(expected, outData);

        OUT_CONTENT.reset();
    }

    void testInvalidDataNumber(int numberOfApartments, int numberOfFloors, int searchApartments) throws IOException {
        String data = numberOfApartments + System.lineSeparator() + numberOfFloors + System.lineSeparator() + searchApartments + System.lineSeparator();
        String expected = Messages.APARTMENTS_INPUT_COUNT_X + System.lineSeparator();
        expected += Messages.APARTMENTS_INPUT_COUNT_Y + System.lineSeparator();
        expected += Messages.APARTMENTS_INPUT_SEARCH + System.lineSeparator();

        if (numberOfFloors <= 0) {
            expected += String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "numberOfFloors" + System.lineSeparator());
        } else if (numberOfApartments <= 0) {
            expected += String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "numberOfApartments" + System.lineSeparator());
        } else if (searchApartments <= 0) {
            expected += String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "indexOfSearchApartments" + System.lineSeparator());
        } else {
            expected += String.format(Messages.EXCEPTION_MESSAGE_FORMATTER, "sizeEntrance" + System.lineSeparator());
        }

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);

            Apartments.main(new String[]{});
        }
        String outData = OUT_CONTENT.toString();

        assertEquals(expected, outData);

        OUT_CONTENT.reset();
    }

    void testInvalidDataNoNumber(char errorValidationDataApartments, char errorValidationDataFloors, char errorValidationDataSearchApartment) throws IOException {
        String data = errorValidationDataApartments + System.lineSeparator()
                + errorValidationDataFloors + System.lineSeparator()
                + errorValidationDataSearchApartment + System.lineSeparator();

        String expected = Messages.APARTMENTS_INPUT_COUNT_X + System.lineSeparator();
        expected += "For input string: \""
                + errorValidationDataApartments + "\""
                + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);

            Apartments.main(new String[]{});
        }

        String outData = OUT_CONTENT.toString();

        assertEquals(expected, outData);

        OUT_CONTENT.reset();
    }

    @Test
    void boundary() throws IOException {

        //region MIN MAX
        testValidData("1 1", 1, Integer.MAX_VALUE, 1);
        testInvalidDataNumber(1, Integer.MAX_VALUE, 0);
        testInvalidDataNumber(0, Integer.MAX_VALUE, 1);
        testInvalidDataNumber(1, Integer.MIN_VALUE, 1);
        testInvalidDataNumber(2, Integer.MAX_VALUE, 1);
        testValidData("1 1", 1, Integer.MAX_VALUE - 1, 1);
        testValidData("1 2", 1, Integer.MAX_VALUE, 2);

        testValidData("1 1", Integer.MAX_VALUE, 1, 1);
        testInvalidDataNumber(Integer.MIN_VALUE, 1, 1);
        testInvalidDataNumber(Integer.MAX_VALUE, 0, 1);
        testInvalidDataNumber(Integer.MAX_VALUE, 1, 0);
        testInvalidDataNumber(Integer.MAX_VALUE, 2, 1);
        testValidData("1 1", Integer.MAX_VALUE - 1, 1, 1);
        testValidData("1 1", Integer.MAX_VALUE, 1, 2);

        testValidData("1 1", 1, 1, 1);
        testInvalidDataNumber(1, 0, 1);
        testInvalidDataNumber(1, 1, 0);
        testInvalidDataNumber(0, 1, 1);
        testValidData("1 1", 2, 1, 1);
        testValidData("2 1", 1, 1, 2);
        testValidData("1 1", 1, 2, 1);


        testValidData(Integer.MAX_VALUE + " 1", 1, 1, Integer.MAX_VALUE);
        testInvalidDataNumber(1, 1, Integer.MIN_VALUE);
        testInvalidDataNumber(0, 1, Integer.MAX_VALUE);
        testInvalidDataNumber(1, 0, Integer.MAX_VALUE);
        testValidData("1073741824 1", 2, 1, Integer.MAX_VALUE);
        testValidData("1073741824 1", 1, 2, Integer.MAX_VALUE);
        testValidData(Integer.MAX_VALUE - 1 + " 1", 1, 1, Integer.MAX_VALUE - 1);


        testValidData("1 " + Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testInvalidDataNumber(1, Integer.MAX_VALUE, Integer.MIN_VALUE);
        testInvalidDataNumber(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testInvalidDataNumber(1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        testInvalidDataNumber(2, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testValidData("2 1", 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        testValidData("1 " + (Integer.MAX_VALUE - 1), 1, Integer.MAX_VALUE, Integer.MAX_VALUE - 1);

        testValidData("1 1", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
        testInvalidDataNumber(Integer.MIN_VALUE, 1, Integer.MAX_VALUE);
        testInvalidDataNumber(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        testInvalidDataNumber(Integer.MAX_VALUE, 1, Integer.MIN_VALUE);
        testInvalidDataNumber(Integer.MAX_VALUE, 2, Integer.MAX_VALUE);
        testValidData("1 1", Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1);
        testValidData("2 1", Integer.MAX_VALUE - 1, 1, Integer.MAX_VALUE);
        //endregion
        //region Subject space
        testValidData("1 5", 1, 9, 5);
        testInvalidDataNumber(0, 9, 5);
        testValidData("1 3", 2, 9, 5);
        testValidData("1 4", 1, 9, 4);
        testValidData("1 6", 1, 9, 6);
        testValidData("1 5", 1, 8, 5);
        testValidData("1 5", 1, 10, 5);


        testValidData("1 1", 10, 1, 3);
        testInvalidDataNumber(10, 0, 3);
        testValidData("1 1", 11, 1, 3);
        testValidData("1 1", 9, 1, 3);
        testValidData("1 1", 10, 2, 3);
        testValidData("1 1", 10, 1, 4);
        testValidData("1 1", 10, 1, 2);

        testValidData("1 1", 20, 1, 15);
        testInvalidDataNumber(20, 0, 15);
        testValidData("1 1", 19, 1, 15);
        testValidData("1 1", 21, 1, 15);
        testValidData("1 1", 20, 2, 15);
        testValidData("1 1", 20, 1, 16);
        testValidData("1 1", 20, 1, 14);


        testValidData("2 1", 17, 1, 19);
        testInvalidDataNumber(17, 0, 19);
        testValidData("2 1", 18, 1, 19);
        testValidData("2 1", 16, 1, 19);
        testValidData("1 2", 17, 2, 19);
        testValidData("2 1", 17, 1, 18);
        testValidData("2 1", 17, 1, 20);

        testValidData("1 1", 5, 8, 1);
        testValidData("1 1", 4, 8, 1);
        testValidData("1 1", 6, 8, 1);
        testValidData("1 1", 5, 7, 1);
        testValidData("1 1", 5, 9, 1);
        testValidData("1 1", 5, 8, 2);
        testInvalidDataNumber(5, 8, 0);

        testValidData("8 1", 1, 1, 8);
        testInvalidDataNumber(0, 1, 8);
        testInvalidDataNumber(1, 0, 8);
        testValidData("4 1", 2, 1, 8);
        testValidData("4 2", 1, 2, 8);
        testValidData("7 1", 1, 1, 7);
        testValidData("9 1", 1, 1, 9);
        //endregion
        //region Random
        testValidData("1 1", 5, 8, 1);
        testValidData("1 1", 4, 8, 1);
        testInvalidDataNumber(5, 8, 0);
        testValidData("1 1", 6, 8, 1);
        testValidData("1 1", 5, 7, 1);
        testValidData("1 1", 5, 9, 1);
        testValidData("1 1", 5, 8, 2);

        testValidData("84295 1", 12738, 2, Integer.MAX_VALUE);
        testInvalidDataNumber(12737, 2, Integer.MIN_VALUE);
        testValidData("84288 2", 12739, 2, Integer.MAX_VALUE);
        testValidData("168589 1", 12738, 1, Integer.MAX_VALUE);
        testValidData("56197 1", 12738, 3, Integer.MAX_VALUE);
        testValidData("84295 1", 12738, 2, Integer.MAX_VALUE);
        testValidData("84295 1", 12738, 2, Integer.MAX_VALUE - 1);

        testValidData("1451 545", 2342, 632, Integer.MAX_VALUE);
        testInvalidDataNumber(2342, 632, Integer.MIN_VALUE);
        testValidData("1451 153", 2343, 632, Integer.MAX_VALUE);
        testValidData("1454 102", 2342, 631, Integer.MAX_VALUE);
        testValidData("1452 305", 2341, 632, Integer.MAX_VALUE);
        testValidData("1449 361", 2342, 633, Integer.MAX_VALUE);
        testValidData("1449 361", 2342, 633, Integer.MAX_VALUE - 1);


        testValidData("1 1", 6345, 1235, 1);
        testInvalidDataNumber(6345, 1235, 0);
        testValidData("1 1", 6344, 1235, 1);
        testValidData("1 1", 6346, 1235, 1);
        testValidData("1 1", 6345, 1234, 1);
        testValidData("1 1", 6345, 1236, 1);
        testValidData("1 1", 6345, 1235, 2);

        testValidData("1 1", Integer.MAX_VALUE, 1, 7219389);
        testInvalidDataNumber(Integer.MIN_VALUE, 1, 7219389);
        testValidData("1 1", Integer.MAX_VALUE - 1, 1, 7219389);
        testInvalidDataNumber(Integer.MAX_VALUE, 0, 7219389);
        testInvalidDataNumber(Integer.MAX_VALUE, 2, 7219389);
        testValidData("1 1", Integer.MAX_VALUE, 1, 7219388);
        testValidData("1 1", Integer.MAX_VALUE, 1, 7219390);

        testValidData("1 643532", 1, Integer.MAX_VALUE, 643532);
        testInvalidDataNumber(0, Integer.MAX_VALUE, 643532);
        testInvalidDataNumber(2, Integer.MAX_VALUE, 643532);
        testValidData("1 643532", 1, Integer.MAX_VALUE - 1, 643532);
        testInvalidDataNumber(1, Integer.MIN_VALUE, 643532);
        testValidData("1 643531", 1, Integer.MAX_VALUE, 643531);
        testValidData("1 643533", 1, Integer.MAX_VALUE, 643533);

        testValidData("357913942 1", 2, 3, Integer.MAX_VALUE);
        testValidData("357913941 3", 2, 3, Integer.MAX_VALUE - 1);
        testValidData("715827883 1", 1, 3, Integer.MAX_VALUE);
        testValidData("238609295 1", 3, 3, Integer.MAX_VALUE);
        testValidData("268435456 4", 2, 4, Integer.MAX_VALUE);
        testValidData("536870912 2", 2, 2, Integer.MAX_VALUE);
        testInvalidDataNumber(2, 3, Integer.MIN_VALUE);

        testValidData("1 1", Integer.MAX_VALUE, 1, 532432);
        testValidData("1 1", Integer.MAX_VALUE - 1, 1, 532432);
        testInvalidDataNumber(Integer.MAX_VALUE, 2, 532432);
        testValidData("1 1", Integer.MAX_VALUE, 1, 532433);
        testValidData("1 1", Integer.MAX_VALUE, 1, 532431);
        testInvalidDataNumber(Integer.MIN_VALUE, 1, 532433);
        testInvalidDataNumber(Integer.MAX_VALUE, 0, 532433);

        testValidData("1 1", Integer.MAX_VALUE, 1, 1);
        testInvalidDataNumber(Integer.MAX_VALUE, 2, 1);
        testValidData("1 1", Integer.MAX_VALUE - 1, 1, 1);
        testValidData("1 1", Integer.MAX_VALUE, 1, 2);
        testInvalidDataNumber(Integer.MAX_VALUE, 0, 1);
        testInvalidDataNumber(Integer.MIN_VALUE, 1, 1);
        testInvalidDataNumber(Integer.MAX_VALUE, 1, 0);


        testValidData("1 1", 821398, 83, 1);
        testValidData("1 1", 821397, 83, 1);
        testValidData("1 1", 821399, 83, 1);
        testValidData("1 1", 821398, 82, 1);
        testValidData("1 1", 821398, 84, 1);
        testValidData("1 1", 821398, 83, 2);
        testInvalidDataNumber(821398, 83, 0);

        testValidData("1 1", 6, 8, 1);
        testInvalidDataNumber(6, 8, 0);
        testValidData("1 1", 5, 8, 1);
        testValidData("1 1", 7, 8, 1);
        testValidData("1 1", 6, 7, 1);
        testValidData("1 1", 6, 9, 1);
        testValidData("1 1", 6, 8, 2);

        testValidData("1 1", 1000, 1000, 1);
        testInvalidDataNumber(1000, 1000, 0);
        testValidData("1 1", 999, 1000, 1);
        testValidData("1 1", 1001, 1000, 1);
        testValidData("1 1", 1000, 1001, 1);
        testValidData("1 1", 1000, 999, 1);
        testValidData("1 1", 1000, 1000, 2);

        testValidData("1 1", 1, 65, 1);
        testInvalidDataNumber(1, 65, 0);
        testInvalidDataNumber(0, 65, 1);
        testValidData("1 1", 2, 65, 1);
        testValidData("1 1", 1, 64, 1);
        testValidData("1 1", 1, 66, 1);
        testValidData("1 2", 1, 66, 2);

        testValidData("1 2", 1, 534, 2);
        testValidData("1 1", 2, 534, 2);
        testValidData("1 2", 1, 533, 2);
        testValidData("1 2", 1, 535, 2);
        testValidData("1 1", 1, 534, 1);
        testValidData("1 3", 1, 534, 3);
        testInvalidDataNumber(0, 534, 2);

        testValidData("1 1", 5, 1, 3);
        testValidData("1 1", 4, 1, 3);
        testValidData("1 1", 6, 1, 3);
        testValidData("1 1", 5, 1, 2);
        testValidData("1 1", 5, 1, 4);
        testValidData("1 1", 5, 2, 3);
        testInvalidDataNumber(5, 0, 3);

        testValidData("1 1", 654, 1, 1);
        testValidData("1 1", 653, 1, 1);
        testValidData("1 1", 655, 1, 1);
        testValidData("1 1", 654, 2, 1);
        testValidData("1 1", 654, 1, 2);
        testInvalidDataNumber(654, 0, 1);
        testInvalidDataNumber(654, 1, 0);

        testValidData("234 1", 1, 1, 234);
        testValidData("117 1", 2, 1, 234);
        testValidData("117 2", 1, 2, 234);
        testValidData("233 1", 1, 1, 233);
        testValidData("235 1", 1, 1, 235);
        testInvalidDataNumber(1, 0, 234);
        testInvalidDataNumber(0, 1, 234);

        testValidData("2 1", 2, 1, 3);
        testValidData("1 1", 3, 1, 3);
        testValidData("3 1", 1, 1, 3);
        testValidData("1 2", 2, 2, 3);
        testValidData("1 1", 2, 1, 2);
        testValidData("2 1", 2, 1, 4);
        testInvalidDataNumber(2, 0, 3);

        testValidData("1 1", 4, 1, 1);
        testValidData("1 1", 3, 1, 1);
        testValidData("1 1", 5, 1, 1);
        testValidData("1 1", 4, 2, 1);
        testInvalidDataNumber(4, 0, 1);
        testValidData("1 1", 4, 1, 2);
        testInvalidDataNumber(4, 1, 0);

        testValidData("1 1", 5, 6, 1);
        testValidData("1 1", 4, 6, 1);
        testValidData("1 1", 6, 6, 1);
        testValidData("1 1", 5, 5, 1);
        testValidData("1 1", 5, 7, 1);
        testValidData("1 1", 5, 6, 2);
        testInvalidDataNumber(5, 6, 0);

        testValidData("1 1", 8, 1, 8);
        testValidData("2 1", 7, 1, 8);
        testValidData("1 1", 9, 1, 8);
        testValidData("1 1", 8, 2, 8);
        testInvalidDataNumber(8, 0, 8);
        testValidData("1 1", 8, 1, 7);
        testValidData("2 1", 8, 1, 9);

        testValidData("2 2", 1, 5, 7);
        testValidData("1 4", 2, 5, 7);
        testValidData("2 3", 1, 4, 7);
        testValidData("2 1", 1, 6, 7);
        testValidData("2 1", 1, 5, 6);
        testValidData("2 3", 1, 5, 8);
        testInvalidDataNumber(0, 5, 7);

        testValidData("1 1", 8972983, 1, 2);
        testValidData("1 1", 8972983, 1, 3);
        testValidData("1 1", 8972982, 1, 2);
        testValidData("1 1", 8972984, 1, 2);
        testValidData("1 1", 8972983, 2, 2);
        testValidData("1 1", 8972983, 1, 1);
        testInvalidDataNumber(8972983, 0, 2);

        testValidData("85 1", 1, 1, 85);
        testValidData("43 1", 2, 1, 85);
        testValidData("43 1", 1, 2, 85);
        testValidData("84 1", 1, 1, 84);
        testValidData("86 1", 1, 1, 86);
        testInvalidDataNumber(1, 0, 85);
        testInvalidDataNumber(0, 1, 85);

        testValidData("106 1", 8, 1, 843);
        testValidData("121 1", 7, 1, 843);
        testValidData("94 1", 9, 1, 843);
        testValidData("106 1", 8, 1, 842);
        testValidData("106 1", 8, 1, 844);
        testInvalidDataNumber(8, 0, 843);
        testValidData("53 2", 8, 2, 843);

        testValidData("1 1", 765, 543, 1);
        testValidData("1 1", 764, 543, 1);
        testValidData("1 1", 766, 543, 1);
        testValidData("1 1", 765, 542, 1);
        testValidData("1 1", 765, 544, 1);
        testValidData("1 1", 765, 543, 2);
        testInvalidDataNumber(765, 543, 0);

        testValidData("1 2", 1, 5, 2);
        testValidData("1 1", 2, 5, 2);
        testValidData("1 2", 1, 4, 2);
        testValidData("1 2", 1, 6, 2);
        testValidData("1 1", 1, 5, 1);
        testValidData("1 3", 1, 5, 3);
        testInvalidDataNumber(0, 5, 2);

        testValidData("1 1", 65, 1, 1);
        testValidData("1 1", 64, 1, 1);
        testValidData("1 1", 66, 1, 1);
        testValidData("1 1", 65, 2, 1);
        testInvalidDataNumber(65, 0, 1);
        testValidData("1 1", 65, 1, 2);
        testInvalidDataNumber(65, 1, 0);

        testValidData("1 3", 1, 3, 3);
        testValidData("1 2", 2, 3, 3);
        testInvalidDataNumber(0, 3, 3);
        testValidData("1 3", 1, 4, 3);
        testValidData("2 1", 1, 2, 3);
        testValidData("2 1", 1, 3, 4);
        testValidData("1 2", 1, 3, 2);

        testValidData("3 2", 1, 2, 6);
        testValidData("2 1", 2, 2, 6);
        testInvalidDataNumber(0, 2, 6);
        testValidData("2 3", 1, 3, 6);
        testValidData("6 1", 1, 1, 6);
        testValidData("3 1", 1, 2, 5);
        testValidData("4 1", 1, 2, 7);
        //endregion
        //region Other
        testValidData("1 1", 13, 1, 13);
        testInvalidDataNumber(13, 0, 13);
        testValidData("1 1", 13, 2, 13);
        testValidData("1 1", 14, 1, 13);
        testValidData("2 1", 12, 1, 13);
        testValidData("2 1", 13, 1, 14);
        testValidData("1 1", 13, 1, 12);

        //endregion
    }

    @Test
    void equivalence_classes() throws IOException {
        //5 8
        testValidData("1 1", 5, 8, 1);
        testInvalidDataNumber(5, 8, 0);
        testValidData("1 1", 5, 8, 2);
        testValidData("1 2", 5, 8, 6);
        testValidData("1 1", 5, 8, 5);
        testValidData("1 2", 5, 8, 7);
        testValidData("1 3", 5, 8, 11);
        testValidData("1 2", 5, 8, 10);
        testValidData("1 3", 5, 8, 12);
        testValidData("1 4", 5, 8, 16);
        testValidData("1 3", 5, 8, 15);
        testValidData("1 4", 5, 8, 17);
        testValidData("1 5", 5, 8, 21);
        testValidData("1 4", 5, 8, 20);
        testValidData("1 5", 5, 8, 22);
        testValidData("1 6", 5, 8, 26);
        testValidData("1 5", 5, 8, 25);
        testValidData("1 6", 5, 8, 27);
        testValidData("1 7", 5, 8, 31);
        testValidData("1 6", 5, 8, 30);
        testValidData("1 7", 5, 8, 32);
        testValidData("1 8", 5, 8, 36);
        testValidData("1 7", 5, 8, 35);
        testValidData("1 8", 5, 8, 37);

        testValidData("1 1", 5, 8, 3);
        testValidData("1 1", 5, 8, 4);

        testValidData("9 1", 5, 8, 321);
        testValidData("8 8", 5, 8, 320);
        testValidData("8 8", 5, 8, 319);
        testValidData("8 8", 5, 8, 318);
        testValidData("8 8", 5, 8, 317);
        testValidData("8 8", 5, 8, 316);
        testValidData("8 7", 5, 8, 315);

        testValidData("53687091 8", 5, 8, 2147483640);
        testValidData("53687091 8", 5, 8, 2147483639);
        testValidData("53687092 1", 5, 8, 2147483641);
        testValidData("53687091 7", 5, 8, 2147483635);
        testValidData("53687091 7", 5, 8, 2147483634);
        testValidData("53687091 8", 5, 8, 2147483636);
        testValidData("53687091 6", 5, 8, 2147483630);
        testValidData("53687091 6", 5, 8, 2147483629);
        testValidData("53687091 7", 5, 8, 2147483631);
        testValidData("53687091 5", 5, 8, 2147483625);
        testValidData("53687091 5", 5, 8, 2147483624);
        testValidData("53687091 6", 5, 8, 2147483626);
        testValidData("53687091 4", 5, 8, 2147483620);
        testValidData("53687091 4", 5, 8, 2147483619);
        testValidData("53687091 5", 5, 8, 2147483621);
        testValidData("53687091 3", 5, 8, 2147483615);
        testValidData("53687091 3", 5, 8, 2147483614);
        testValidData("53687091 4", 5, 8, 2147483616);
        testValidData("53687091 2", 5, 8, 2147483610);
        testValidData("53687091 2", 5, 8, 2147483609);
        testValidData("53687091 3", 5, 8, 2147483611);
        testValidData("53687091 1", 5, 8, 2147483605);
        testValidData("53687091 1", 5, 8, 2147483604);
        testValidData("53687091 2", 5, 8, 2147483606);

        testValidData("134 1", 5, 8, 5324);
        testValidData("19 8", 5, 8, 756);
        testValidData("2162 3", 5, 8, 86452);
        testValidData("32 3", 5, 8, 1253);

        testValidData("1 4", 6, 6, 21);
        //6 6 21 (1 4)
        testInvalidDataNumber(6, 6, 0);
        testInvalidDataNumber(6, 6, Integer.MIN_VALUE);

        testValidData("1 1", 6, 6, 2);
        testValidData("1 1", 6, 6, 3);
        testValidData("1 1", 6, 6, 4);
        testValidData("1 1", 6, 6, 5);
        testValidData("1 1", 6, 6, 6);

        testValidData("1 1", 6, 6, 1);
        testValidData("1 2", 6, 6, 7);
        testValidData("1 2", 6, 6, 8);
        testValidData("1 3", 6, 6, 13);
        testValidData("1 2", 6, 6, 12);
        testValidData("1 3", 6, 6, 14);
        testValidData("1 4", 6, 6, 19);
        testValidData("1 3", 6, 6, 18);
        testValidData("1 4", 6, 6, 20);
        testValidData("1 5", 6, 6, 25);
        testValidData("1 4", 6, 6, 24);
        testValidData("1 5", 6, 6, 26);
        testValidData("1 6", 6, 6, 31);
        testValidData("1 5", 6, 6, 30);
        testValidData("1 6", 6, 6, 32);

        testValidData("6 5", 6, 6, 210);
        testValidData("6 6", 6, 6, 211);
        testValidData("6 6", 6, 6, 212);
        testValidData("6 6", 6, 6, 213);
        testValidData("6 6", 6, 6, 214);
        testValidData("6 6", 6, 6, 215);
        testValidData("6 6", 6, 6, 216);
        testValidData("7 1", 6, 6, 217);

        testValidData("59652323 6", 6, 6, 2147483628);
        testValidData("59652323 6", 6, 6, 2147483627);
        testValidData("59652323 5", 6, 6, 2147483622);
        testValidData("59652323 5", 6, 6, 2147483621);
        testValidData("59652323 6", 6, 6, 2147483623);
        testValidData("59652323 4", 6, 6, 2147483616);
        testValidData("59652323 5", 6, 6, 2147483617);
        testValidData("59652323 4", 6, 6, 2147483615);
        testValidData("59652323 3", 6, 6, 2147483610);
        testValidData("59652323 4", 6, 6, 2147483611);
        testValidData("59652323 3", 6, 6, 2147483609);
        testValidData("59652323 2", 6, 6, 2147483604);
        testValidData("59652323 3", 6, 6, 2147483605);
        testValidData("59652323 2", 6, 6, 2147483603);
        testValidData("59652323 2", 6, 6, 2147483599);
        testValidData("59652323 1", 6, 6, 2147483598);
        testValidData("59652323 1", 6, 6, 2147483597);
        testValidData("59652323 1", 6, 6, 2147483593);
        testValidData("59652323 1", 6, 6, 2147483594);
        testValidData("59652322 6", 6, 6, 2147483592);


        testValidData("1 7", 8, 8, 54);
        //8 8 54 (1 7)
        testInvalidDataNumber(8, 8, 0);

        testValidData("1 1", 8, 8, 1);
        testValidData("1 1", 8, 8, 2);
        testValidData("1 1", 8, 8, 3);
        testValidData("1 1", 8, 8, 4);
        testValidData("1 1", 8, 8, 5);
        testValidData("1 1", 8, 8, 6);
        testValidData("1 1", 8, 8, 7);
        testValidData("1 1", 8, 8, 8);
        testValidData("1 2", 8, 8, 9);

        testValidData("1 2", 8, 8, 10);
        testValidData("1 3", 8, 8, 17);
        testValidData("1 3", 8, 8, 18);
        testValidData("1 2", 8, 8, 16);
        testValidData("1 4", 8, 8, 25);
        testValidData("1 4", 8, 8, 26);
        testValidData("1 3", 8, 8, 24);
        testValidData("1 5", 8, 8, 33);
        testValidData("1 5", 8, 8, 34);
        testValidData("1 4", 8, 8, 32);
        testValidData("1 5", 8, 8, 40);
        testValidData("1 6", 8, 8, 42);
        testValidData("1 6", 8, 8, 41);
        testValidData("1 7", 8, 8, 49);
        testValidData("1 7", 8, 8, 50);
        testValidData("1 6", 8, 8, 48);
        testValidData("1 8", 8, 8, 57);
        testValidData("1 8", 8, 8, 58);
        testValidData("1 7", 8, 8, 56);

        testValidData("9 1", 8, 8, 513);
        testValidData("8 8", 8, 8, 512);
        testValidData("8 8", 8, 8, 511);
        testValidData("8 8", 8, 8, 510);
        testValidData("8 8", 8, 8, 509);
        testValidData("8 8", 8, 8, 508);
        testValidData("8 8", 8, 8, 507);
        testValidData("8 8", 8, 8, 506);
        testValidData("8 8", 8, 8, 505);
        testValidData("8 7", 8, 8, 504);

        testValidData("33554432 1", 8, 8, 2147483585);
        testValidData("33554431 8", 8, 8, 2147483584);
        testValidData("33554431 8", 8, 8, 2147483583);
        testValidData("33554431 7", 8, 8, 2147483576);
        testValidData("33554431 8", 8, 8, 2147483577);
        testValidData("33554431 7", 8, 8, 2147483575);
        testValidData("33554431 6", 8, 8, 2147483568);
        testValidData("33554431 7", 8, 8, 2147483569);
        testValidData("33554431 6", 8, 8, 2147483567);
        testValidData("33554431 5", 8, 8, 2147483560);
        testValidData("33554431 6", 8, 8, 2147483561);
        testValidData("33554431 5", 8, 8, 2147483559);
        testValidData("33554431 4", 8, 8, 2147483552);
        testValidData("33554431 4", 8, 8, 2147483551);
        testValidData("33554431 5", 8, 8, 2147483553);
        testValidData("33554431 3", 8, 8, 2147483544);
        testValidData("33554431 3", 8, 8, 2147483543);
        testValidData("33554431 4", 8, 8, 2147483545);
        testValidData("33554431 2", 8, 8, 2147483536);
        testValidData("33554431 2", 8, 8, 2147483535);
        testValidData("33554431 3", 8, 8, 2147483537);
        testValidData("33554431 1", 8, 8, 2147483528);
        testValidData("33554431 1", 8, 8, 2147483527);
        testValidData("33554431 2", 8, 8, 2147483529);


        testValidData("1 5", 7, 5, 34);
        // 7 5 34 (1 5)
        testInvalidDataNumber(7, 5, 0);

        testValidData("1 1", 7, 5, 1);
        testValidData("1 1", 7, 5, 2);
        testValidData("1 1", 7, 5, 3);
        testValidData("1 1", 7, 5, 4);
        testValidData("1 1", 7, 5, 5);
        testValidData("1 1", 7, 5, 6);
        testValidData("1 1", 7, 5, 7);
        testValidData("1 2", 7, 5, 8);

        testValidData("1 2", 7, 5, 9);
        testValidData("1 3", 7, 5, 15);
        testValidData("1 2", 7, 5, 14);
        testValidData("1 3", 7, 5, 16);
        testValidData("1 4", 7, 5, 22);
        testValidData("1 3", 7, 5, 21);
        testValidData("1 4", 7, 5, 23);
        testValidData("1 5", 7, 5, 29);
        testValidData("1 5", 7, 5, 30);
        testValidData("1 4", 7, 5, 28);

        testValidData("5 4", 7, 5, 168);
        testValidData("5 5", 7, 5, 169);
        testValidData("5 5", 7, 5, 170);
        testValidData("5 5", 7, 5, 171);
        testValidData("5 5", 7, 5, 172);
        testValidData("5 5", 7, 5, 173);
        testValidData("5 5", 7, 5, 174);
        testValidData("5 5", 7, 5, 175);
        testValidData("6 1", 7, 5, 176);

        testValidData("61356675 5", 7, 5, 2147483625);
        testValidData("61356676 1", 7, 5, 2147483626);
        testValidData("61356675 5", 7, 5, 2147483624);
        testValidData("61356675 4", 7, 5, 2147483618);
        testValidData("61356675 5", 7, 5, 2147483619);
        testValidData("61356675 4", 7, 5, 2147483617);
        testValidData("61356675 3", 7, 5, 2147483611);
        testValidData("61356675 4", 7, 5, 2147483612);
        testValidData("61356675 3", 7, 5, 2147483610);
        testValidData("61356675 2", 7, 5, 2147483604);
        testValidData("61356675 3", 7, 5, 2147483605);
        testValidData("61356675 2", 7, 5, 2147483603);
        testValidData("61356675 1", 7, 5, 2147483597);
        testValidData("61356675 2", 7, 5, 2147483598);
        testValidData("61356675 1", 7, 5, 2147483596);
        testValidData("61356675 1", 7, 5, 2147483591);
        testValidData("61356675 1", 7, 5, 2147483592);
        testValidData("61356674 5", 7, 5, 2147483590);

        testValidData("3 1", 10, 3, 63);
        //"3 1", 10, 3, 63
        testInvalidDataNumber(10, 3, 0);
        testValidData("1 1", 10, 3, 1);
        testValidData("1 1", 10, 3, 2);
        testValidData("1 1", 10, 3, 3);
        testValidData("1 1", 10, 3, 4);
        testValidData("1 1", 10, 3, 5);
        testValidData("1 1", 10, 3, 6);
        testValidData("1 1", 10, 3, 7);
        testValidData("1 1", 10, 3, 8);
        testValidData("1 1", 10, 3, 9);
        testValidData("1 1", 10, 3, 10);
        testValidData("1 2", 10, 3, 11);

        testValidData("1 2", 10, 3, 12);
        testValidData("1 3", 10, 3, 21);
        testValidData("1 3", 10, 3, 22);
        testValidData("1 2", 10, 3, 20);
        testValidData("1 3", 10, 3, 30);
        testValidData("2 1", 10, 3, 31);
        testValidData("1 3", 10, 3, 29);
        testValidData("3 2", 10, 3, 80);
        testValidData("3 3", 10, 3, 81);
        testValidData("3 3", 10, 3, 82);
        testValidData("3 3", 10, 3, 83);
        testValidData("3 3", 10, 3, 84);
        testValidData("3 3", 10, 3, 85);
        testValidData("3 3", 10, 3, 86);
        testValidData("3 3", 10, 3, 87);
        testValidData("3 3", 10, 3, 88);
        testValidData("3 3", 10, 3, 89);
        testValidData("3 3", 10, 3, 90);
        testValidData("4 1", 10, 3, 91);

        testValidData("71582787 3", 10, 3, 2147483610);
        testValidData("71582788 1", 10, 3, 2147483611);
        testValidData("71582787 3", 10, 3, 2147483609);
        testValidData("71582787 2", 10, 3, 2147483600);
        testValidData("71582787 3", 10, 3, 2147483601);
        testValidData("71582787 2", 10, 3, 2147483599);
        testValidData("71582787 1", 10, 3, 2147483590);
        testValidData("71582787 1", 10, 3, 2147483589);

        testValidData("1 7", 8, 9, 54);
        //"1 7", 8, 9, 54
        testInvalidDataNumber(8, 9, 0);

        testValidData("1 1", 8, 9, 1);
        testValidData("1 1", 8, 9, 2);
        testValidData("1 1", 8, 9, 3);
        testValidData("1 1", 8, 9, 4);
        testValidData("1 1", 8, 9, 5);
        testValidData("1 1", 8, 9, 6);
        testValidData("1 1", 8, 9, 7);
        testValidData("1 1", 8, 9, 8);
        testValidData("1 2", 8, 9, 9);

        testValidData("1 2", 8, 9, 10);
        testValidData("1 3", 8, 9, 17);
        testValidData("1 2", 8, 9, 16);
        testValidData("1 3", 8, 9, 18);
        testValidData("1 4", 8, 9, 25);
        testValidData("1 3", 8, 9, 24);
        testValidData("1 4", 8, 9, 26);
        testValidData("1 5", 8, 9, 33);
        testValidData("1 5", 8, 9, 34);
        testValidData("1 4", 8, 9, 32);
        testValidData("1 6", 8, 9, 41);
        testValidData("1 6", 8, 9, 42);
        testValidData("1 5", 8, 9, 40);
        testValidData("1 7", 8, 9, 49);
        testValidData("1 7", 8, 9, 50);
        testValidData("1 6", 8, 9, 48);
        testValidData("1 8", 8, 9, 57);
        testValidData("1 8", 8, 9, 58);
        testValidData("1 7", 8, 9, 56);
        testValidData("1 9", 8, 9, 65);
        testValidData("1 8", 8, 9, 64);
        testValidData("1 9", 8, 9, 66);


        testValidData("9 8", 8, 9, 640);
        testValidData("9 9", 8, 9, 641);
        testValidData("9 9", 8, 9, 642);
        testValidData("9 9", 8, 9, 643);
        testValidData("9 9", 8, 9, 644);
        testValidData("9 9", 8, 9, 645);
        testValidData("9 9", 8, 9, 646);
        testValidData("9 9", 8, 9, 647);
        testValidData("9 9", 8, 9, 648);
        testValidData("10 1", 8, 9, 649);

        testValidData("29826161 9", 8, 9, 2147483592);
        testValidData("29826161 9", 8, 9, 2147483591);
        testValidData("29826162 1", 8, 9, 2147483593);
        testValidData("29826161 8", 8, 9, 2147483584);
        testValidData("29826161 8", 8, 9, 2147483583);
        testValidData("29826161 9", 8, 9, 2147483585);
        testValidData("29826161 7", 8, 9, 2147483576);
        testValidData("29826161 8", 8, 9, 2147483577);
        testValidData("29826161 7", 8, 9, 2147483575);
        testValidData("29826161 6", 8, 9, 2147483568);
        testValidData("29826161 7", 8, 9, 2147483569);
        testValidData("29826161 6", 8, 9, 2147483567);
        testValidData("29826161 5", 8, 9, 2147483560);
        testValidData("29826161 6", 8, 9, 2147483561);
        testValidData("29826161 5", 8, 9, 2147483559);
        testValidData("29826161 4", 8, 9, 2147483552);
        testValidData("29826161 5", 8, 9, 2147483553);
        testValidData("29826161 4", 8, 9, 2147483551);
        testValidData("29826161 3", 8, 9, 2147483544);
        testValidData("29826161 4", 8, 9, 2147483545);
        testValidData("29826161 3", 8, 9, 2147483543);
        testValidData("29826161 2", 8, 9, 2147483536);
        testValidData("29826161 3", 8, 9, 2147483537);
        testValidData("29826161 2", 8, 9, 2147483535);
        testValidData("29826161 1", 8, 9, 2147483528);
        testValidData("29826161 2", 8, 9, 2147483529);
        testValidData("29826161 1", 8, 9, 2147483527);


        testValidData("1 8", 3, 9, 24);
        //"1 8", 3, 9, 24
        testInvalidDataNumber(3, 9, 0);

        testValidData("1 1", 3, 9, 1);
        testValidData("1 1", 3, 9, 2);
        testValidData("1 1", 3, 9, 3);
        testValidData("1 2", 3, 9, 4);

        testValidData("1 2", 3, 9, 5);
        testValidData("1 3", 3, 9, 7);
        testValidData("1 2", 3, 9, 6);
        testValidData("1 3", 3, 9, 8);
        testValidData("1 4", 3, 9, 10);
        testValidData("1 4", 3, 9, 11);
        testValidData("1 3", 3, 9, 9);
        testValidData("1 5", 3, 9, 13);
        testValidData("1 5", 3, 9, 14);
        testValidData("1 4", 3, 9, 12);
        testValidData("1 6", 3, 9, 16);
        testValidData("1 6", 3, 9, 17);
        testValidData("1 5", 3, 9, 15);
        testValidData("1 7", 3, 9, 19);
        testValidData("1 6", 3, 9, 18);
        testValidData("1 7", 3, 9, 20);
        testValidData("1 8", 3, 9, 22);
        testValidData("1 7", 3, 9, 21);
        testValidData("1 8", 3, 9, 23);
        testValidData("1 9", 3, 9, 25);
        testValidData("1 9", 3, 9, 26);
        testValidData("1 8", 3, 9, 24);

        testValidData("9 8", 3, 9, 240);
        testValidData("9 9", 3, 9, 241);
        testValidData("9 9", 3, 9, 242);
        testValidData("9 9", 3, 9, 243);
        testValidData("10 1", 3, 9, 244);

        testValidData("79536431 9", 3, 9, 2147483637);
        testValidData("79536432 1", 3, 9, 2147483638);
        testValidData("79536431 9", 3, 9, 2147483636);
        testValidData("79536431 8", 3, 9, 2147483633);
        testValidData("79536431 8", 3, 9, 2147483634);
        testValidData("79536431 9", 3, 9, 2147483635);
        testValidData("79536431 7", 3, 9, 2147483631);
        testValidData("79536431 8", 3, 9, 2147483632);
        testValidData("79536431 7", 3, 9, 2147483630);
        testValidData("79536431 6", 3, 9, 2147483628);
        testValidData("79536431 7", 3, 9, 2147483629);
        testValidData("79536431 6", 3, 9, 2147483627);
        testValidData("79536431 5", 3, 9, 2147483625);
        testValidData("79536431 5", 3, 9, 2147483624);
        testValidData("79536431 6", 3, 9, 2147483626);
        testValidData("79536431 4", 3, 9, 2147483622);
        testValidData("79536431 5", 3, 9, 2147483623);
        testValidData("79536431 4", 3, 9, 2147483621);
        testValidData("79536431 3", 3, 9, 2147483619);
        testValidData("79536431 4", 3, 9, 2147483620);
        testValidData("79536431 3", 3, 9, 2147483618);
        testValidData("79536431 2", 3, 9, 2147483616);
        testValidData("79536431 2", 3, 9, 2147483615);
        testValidData("79536431 3", 3, 9, 2147483617);
        testValidData("79536431 1", 3, 9, 2147483613);
        testValidData("79536431 2", 3, 9, 2147483614);
        testValidData("79536431 1", 3, 9, 2147483612);


        testValidData("4 5", 5, 8, 142);
        //"4 5", 5, 8, 142
        testInvalidDataNumber(5, 8, 0);

        testValidData("1 1", 5, 8, 1);
        testValidData("1 1", 5, 8, 2);
        testValidData("1 1", 5, 8, 3);
        testValidData("1 1", 5, 8, 4);
        testValidData("1 1", 5, 8, 5);
        testValidData("1 2", 5, 8, 6);

        testValidData("1 2", 5, 6, 7);
        testValidData("1 3", 5, 6, 11);
        testValidData("1 3", 5, 6, 12);
        testValidData("1 2", 5, 6, 10);
        testValidData("1 4", 5, 6, 16);
        testValidData("1 3", 5, 6, 15);
        testValidData("1 4", 5, 6, 17);
        testValidData("1 5", 5, 6, 21);
        testValidData("1 5", 5, 6, 22);
        testValidData("1 4", 5, 6, 20);
        testValidData("1 6", 5, 6, 26);
        testValidData("1 5", 5, 6, 25);
        testValidData("1 6", 5, 6, 27);

        testValidData("9 1", 5, 8, 321);
        testValidData("8 8", 5, 8, 320);
        testValidData("8 8", 5, 8, 319);
        testValidData("8 8", 5, 8, 318);
        testValidData("8 8", 5, 8, 317);
        testValidData("8 8", 5, 8, 316);
        testValidData("8 7", 5, 8, 315);

        testValidData("53687092 1", 5, 8, 2147483641);
        testValidData("53687091 8", 5, 8, 2147483640);
        testValidData("53687091 8", 5, 8, 2147483639);
        testValidData("53687091 7", 5, 8, 2147483635);
        testValidData("53687091 8", 5, 8, 2147483636);
        testValidData("53687091 7", 5, 8, 2147483634);
        testValidData("53687091 6", 5, 8, 2147483630);
        testValidData("53687091 7", 5, 8, 2147483631);
        testValidData("53687091 6", 5, 8, 2147483629);
        testValidData("53687091 5", 5, 8, 2147483625);
        testValidData("53687091 5", 5, 8, 2147483624);
        testValidData("53687091 6", 5, 8, 2147483626);
        testValidData("53687091 4", 5, 8, 2147483620);
        testValidData("53687091 5", 5, 8, 2147483621);
        testValidData("53687091 4", 5, 8, 2147483619);
        testValidData("53687091 3", 5, 8, 2147483615);
        testValidData("53687091 4", 5, 8, 2147483616);
        testValidData("53687091 3", 5, 8, 2147483614);
        testValidData("53687091 2", 5, 8, 2147483610);
        testValidData("53687091 2", 5, 8, 2147483609);
        testValidData("53687091 3", 5, 8, 2147483611);
        testValidData("53687091 1", 5, 8, 2147483605);
        testValidData("53687091 1", 5, 8, 2147483604);
        testValidData("53687091 2", 5, 8, 2147483606);


        testValidData("4 36811", 1833, 215120, 1250418246);
        //"4 36811",1833,215120,
        testInvalidDataNumber(1833, 215120, 0);

        testValidData("1 1", 1833, 215120, 1);
        testValidData("1 1", 1833, 215120, 2);
        testValidData("1 1", 1833, 215120, 8);
        testValidData("1 1", 1833, 215120, 7);
        testValidData("1 1", 1833, 215120, 9);
        testValidData("1 1", 1833, 215120, 15);
        testValidData("1 1", 1833, 215120, 14);
        testValidData("1 1", 1833, 215120, 16);
        testValidData("1 1", 1833, 215120, 1543);
        testValidData("1 1", 1833, 215120, 1542);
        testValidData("1 1", 1833, 215120, 1544);
        testValidData("1 1", 1833, 215120, 876);
        testValidData("1 1", 1833, 215120, 877);
        testValidData("1 1", 1833, 215120, 875);
        testValidData("1 1", 1833, 215120, 777);
        testValidData("1 1", 1833, 215120, 778);
        testValidData("1 1", 1833, 215120, 776);
        testValidData("1 1", 1833, 215120, 1833);
        testValidData("1 1", 1833, 215120, 1832);
        testValidData("1 2", 1833, 215120, 1834);

        testValidData("1 206993", 1833, 215120, 379416337);
        testValidData("1 206992", 1833, 215120, 379416336);
        testValidData("1 206993", 1833, 215120, 379416338);
        testValidData("1 207000", 1833, 215120, 379429168);
        testValidData("1 206999", 1833, 215120, 379429167);
        testValidData("1 207000", 1833, 215120, 379429169);
        testValidData("1 115453", 1833, 215120, 211623517);
        testValidData("1 115452", 1833, 215120, 211623516);
        testValidData("1 115453", 1833, 215120, 211623518);
        testValidData("1 215120", 1833, 215120, 394314960);
        testValidData("2 1", 1833, 215120, 394314961);
        testValidData("1 215120", 1833, 215120, 394314959);

        testValidData("5 4", 1833, 215120, 1577267172);
        testValidData("5 5", 1833, 215120, 1577267173);
        testValidData("5 5", 1833, 215120, 1577267174);
        testValidData("5 5", 1833, 215120, 1577267287);
        testValidData("5 5", 1833, 215120, 1577267288);
        testValidData("5 5", 1833, 215120, 1577267286);
        testValidData("5 5", 1833, 215120, 1577267671);
        testValidData("5 5", 1833, 215120, 1577267672);
        testValidData("5 5", 1833, 215120, 1577267673);
        testValidData("5 5", 1833, 215120, 1577269005);
        testValidData("5 5", 1833, 215120, 1577269004);
        testValidData("5 6", 1833, 215120, 1577269006);

        testValidData("6 1", 1833, 215120, 1971574801);
        testValidData("5 215120", 1833, 215120, 1971574800);
        testValidData("5 215120", 1833, 215120, 1971574799);
        testValidData("4 215120", 1833, 215120, 1577259840);
        testValidData("5 57805", 1833, 215120, 1683215345);
        testValidData("5 57805", 1833, 215120, 1683215344);
        testValidData("5 57805", 1833, 215120, 1683215346);
        testValidData("5 1", 1833, 215120, 1577259841);
        testValidData("5 1", 1833, 215120, 1577259842);

        testValidData("1 53698541", 12, 80924796, 644382487);
        // "1 53698541",12,80924796,644382487
        testInvalidDataNumber(12, 80924796, 0);

        testValidData("1 1", 12, 80924796, 1);
        testValidData("1 1", 12, 80924796, 2);
        testValidData("1 1", 12, 80924796, 3);
        testValidData("1 1", 12, 80924796, 4);
        testValidData("1 1", 12, 80924796, 5);
        testValidData("1 1", 12, 80924796, 6);
        testValidData("1 1", 12, 80924796, 7);
        testValidData("1 1", 12, 80924796, 8);
        testValidData("1 1", 12, 80924796, 9);
        testValidData("1 1", 12, 80924796, 10);
        testValidData("1 1", 12, 80924796, 11);
        testValidData("1 1", 12, 80924796, 12);
        testValidData("1 2", 12, 80924796, 13);
        testValidData("1 80924796", 12, 80924796, 971097552);
        testValidData("1 80924796", 12, 80924796, 971097551);
        testValidData("2 1", 12, 80924796, 971097553);

        testValidData("1 4", 12, 80924796, 37);
        testValidData("1 4", 12, 80924796, 38);
        testValidData("1 3", 12, 80924796, 36);
        testValidData("1 3", 12, 80924796, 25);
        testValidData("1 2", 12, 80924796, 24);
        testValidData("1 3", 12, 80924796, 25);
        testInvalidDataNumber(12, 80924796, Integer.MIN_VALUE);
        testValidData("1 624215", 12, 80924796, 7490569);
        testValidData("1 624214", 12, 80924796, 7490568);
        testValidData("1 624215", 12, 80924796, 7490570);
        testValidData("1 724", 12, 80924796, 8677);
        testValidData("1 723", 12, 80924796, 8676);
        testValidData("1 724", 12, 80924796, 8678);
        testValidData("1 6590", 12, 80924796, 79069);
        testValidData("1 6590", 12, 80924796, 79070);
        testValidData("1 6589", 12, 80924796, 79068);
        testValidData("1 13", 12, 80924796, 145);
        testValidData("1 12", 12, 80924796, 144);
        testValidData("1 13", 12, 80924796, 146);

        testValidData("3 4", 12, 80924796, 1942195141);
        testValidData("3 3", 12, 80924796, 1942195140);
        testValidData("3 3", 12, 80924796, 1942195139);
        testValidData("3 3", 12, 80924796, 1942195138);
        testValidData("3 3", 12, 80924796, 1942195137);
        testValidData("3 3", 12, 80924796, 1942195136);
        testValidData("3 3", 12, 80924796, 1942195135);
        testValidData("3 3", 12, 80924796, 1942195134);
        testValidData("3 3", 12, 80924796, 1942195133);
        testValidData("3 3", 12, 80924796, 1942195132);
        testValidData("3 3", 12, 80924796, 1942195131);
        testValidData("3 3", 12, 80924796, 1942195130);
        testValidData("3 3", 12, 80924796, 1942195129);
        testValidData("3 2", 12, 80924796, 1942195128);

        testValidData("2 80924796", 12, 80924796, 1942195104);
        testValidData("2 80924796", 12, 80924796, 1942195103);
        testValidData("3 1", 12, 80924796, 1942195105);
        testValidData("2 723", 12, 80924796, 971106228);
        testValidData("2 723", 12, 80924796, 971106227);
        testValidData("2 724", 12, 80924796, 971106229);
        testValidData("2 3", 12, 80924796, 971097587);
        testValidData("2 3", 12, 80924796, 971097588);
        testValidData("2 4", 12, 80924796, 971097589);
        testValidData("2 1", 12, 80924796, 971097564);
        testValidData("2 1", 12, 80924796, 971097563);
        testValidData("2 2", 12, 80924796, 971097565);

        testValidData("1 3173", 395538, 4552, 1254992413);
        //"1 3173",395538,4552,1254992413
        testInvalidDataNumber(395538, 4552, 0);

        testValidData("1 1", 395538, 4552, 1);
        testValidData("1 1", 395538, 4552, 2);
        testValidData("1 1", 395538, 4552, 5);
        testValidData("1 1", 395538, 4552, 4);
        testValidData("1 1", 395538, 4552, 6);
        testValidData("1 1", 395538, 4552, 10);
        testValidData("1 1", 395538, 4552, 9);
        testValidData("1 1", 395538, 4552, 11);
        testValidData("1 1", 395538, 4552, 25412);
        testValidData("1 1", 395538, 4552, 25411);
        testValidData("1 1", 395538, 4552, 25413);
        testValidData("1 1", 395538, 4552, 13);
        testValidData("1 1", 395538, 4552, 12);
        testValidData("1 1", 395538, 4552, 14);
        testValidData("1 1", 395538, 4552, 395538);
        testValidData("1 1", 395538, 4552, 395537);
        testValidData("1 2", 395538, 4552, 395539);

        testValidData("1 4552", 395538, 4552, 1800488976);
        testValidData("2 1", 395538, 4552, 1800488977);
        testValidData("1 4552", 395538, 4552, 1800488975);
        testValidData("1 2", 395538, 4552, 395539);
        testValidData("1 2", 395538, 4552, 395540);
        testValidData("1 1", 395538, 4552, 395538);
        testValidData("1 6", 395538, 4552, 1977691);
        testValidData("1 5", 395538, 4552, 1977690);
        testValidData("1 6", 395538, 4552, 1977692);

        testValidData("1 4551", 395538, 4552, 1800093438);
        testValidData("1 4551", 395538, 4552, 1800093437);
        testValidData("1 4552", 395538, 4552, 1800093439);
        testValidData("1 4552", 395538, 4552, 1800118850);
        testValidData("1 4552", 395538, 4552, 1800118851);
        testValidData("1 4552", 395538, 4552, 1800118849);


        //13 21
        testInvalidDataNumber(13, 21, 0);

        testValidData("1 1", 13, 21, 1);
        testValidData("1 1", 13, 21, 2);
        testValidData("1 1", 13, 21, 12);
        testValidData("1 1", 13, 21, 5);
        testValidData("1 1", 13, 21, 6);
        testValidData("1 1", 13, 21, 4);
        testValidData("1 1", 13, 21, 13);
        testValidData("1 2", 13, 21, 14);

        testValidData("1 2", 13, 21, 15);
        testValidData("1 5", 13, 21, 53);
        testValidData("1 4", 13, 21, 52);
        testValidData("1 5", 13, 21, 54);
        testValidData("1 21", 13, 21, 273);
        testValidData("1 21", 13, 21, 272);
        testValidData("2 1", 13, 21, 274);

        testValidData("22 1", 13, 21, 5734);
        testValidData("21 21", 13, 21, 5733);
        testValidData("21 21", 13, 21, 5732);
        testValidData("21 21", 13, 21, 5731);
        testValidData("21 21", 13, 21, 5730);
        testValidData("21 21", 13, 21, 5729);
        testValidData("21 21", 13, 21, 5728);
        testValidData("21 21", 13, 21, 5727);
        testValidData("21 21", 13, 21, 5726);
        testValidData("21 21", 13, 21, 5725);
        testValidData("21 21", 13, 21, 5724);
        testValidData("21 21", 13, 21, 5723);
        testValidData("21 21", 13, 21, 5722);
        testValidData("21 21", 13, 21, 5721);
        testValidData("21 20", 13, 21, 5720);

        testValidData("7866239 21", 13, 21, 2147483247);
        testValidData("7866240 1", 13, 21, 2147483248);
        testValidData("7866239 21", 13, 21, 2147483246);
        testValidData("7866240 6", 13, 21, 2147483313);
        testValidData("7866240 6", 13, 21, 2147483314);
        testValidData("7866240 5", 13, 21, 2147483312);
        testValidData("7866239 1", 13, 21, 2147482986);
        testValidData("7866239 1", 13, 21, 2147482987);
        testValidData("7866239 2", 13, 21, 2147482988);

        testValidData("1 2", 9, 13, 15);
        // "1 2",9,13,
        testInvalidDataNumber(9, 13, 0);

        testValidData("1 1", 9, 13, 1);
        testValidData("1 1", 9, 13, 2);
        testValidData("1 2", 9, 13, 10);
        testValidData("1 1", 9, 13, 9);
        testValidData("1 2", 9, 13, 11);
        testValidData("1 3", 9, 13, 19);
        testValidData("1 2", 9, 13, 18);
        testValidData("1 3", 9, 13, 20);
        testValidData("1 4", 9, 13, 28);
        testValidData("1 3", 9, 13, 27);
        testValidData("1 4", 9, 13, 29);
        testValidData("1 5", 9, 13, 37);
        testValidData("1 4", 9, 13, 36);
        testValidData("1 5", 9, 13, 38);
        testValidData("1 6", 9, 13, 46);
        testValidData("1 5", 9, 13, 45);
        testValidData("1 6", 9, 13, 47);
        testValidData("1 7", 9, 13, 55);
        testValidData("1 6", 9, 13, 54);
        testValidData("1 7", 9, 13, 56);
        testValidData("1 8", 9, 13, 64);
        testValidData("1 7", 9, 13, 63);
        testValidData("1 8", 9, 13, 65);
        testValidData("1 9", 9, 13, 73);
        testValidData("1 8", 9, 13, 72);
        testValidData("1 9", 9, 13, 74);
        testValidData("1 10", 9, 13, 82);
        testValidData("1 9", 9, 13, 81);
        testValidData("1 10", 9, 13, 83);
        testValidData("1 11", 9, 13, 91);
        testValidData("1 10", 9, 13, 90);
        testValidData("1 11", 9, 13, 92);
        testValidData("1 12", 9, 13, 100);
        testValidData("1 11", 9, 13, 99);
        testValidData("1 12", 9, 13, 101);
        testValidData("1 13", 9, 13, 109);
        testValidData("1 12", 9, 13, 108);
        testValidData("1 13", 9, 13, 110);

        testValidData("1 1", 9, 13, 1);
        testValidData("1 1", 9, 13, 2);
        testValidData("1 1", 9, 13, 3);
        testValidData("1 1", 9, 13, 4);
        testValidData("1 1", 9, 13, 5);
        testValidData("1 1", 9, 13, 6);
        testValidData("1 1", 9, 13, 7);
        testValidData("1 1", 9, 13, 8);
        testValidData("1 1", 9, 13, 9);
        testValidData("1 2", 9, 13, 10);

        testValidData("13 12", 9, 13, 1512);
        testValidData("13 13", 9, 13, 1513);
        testValidData("13 13", 9, 13, 1514);
        testValidData("13 13", 9, 13, 1515);
        testValidData("13 13", 9, 13, 1516);
        testValidData("13 13", 9, 13, 1517);
        testValidData("13 13", 9, 13, 1518);
        testValidData("13 13", 9, 13, 1519);
        testValidData("13 13", 9, 13, 1520);
        testValidData("13 13", 9, 13, 1521);
        testValidData("14 1", 9, 13, 1522);

        testValidData("18354561 13", 9, 13, 2147483637);
        testValidData("18354561 13", 9, 13, 2147483636);
        testValidData("18354562 1", 9, 13, 2147483638);
        testValidData("18354561 12", 9, 13, 2147483628);
        testValidData("18354561 12", 9, 13, 2147483627);
        testValidData("18354561 13", 9, 13, 2147483629);
        testValidData("18354561 11", 9, 13, 2147483619);
        testValidData("18354561 11", 9, 13, 2147483618);
        testValidData("18354561 12", 9, 13, 2147483620);
        testValidData("18354561 10", 9, 13, 2147483610);
        testValidData("18354561 10", 9, 13, 2147483609);
        testValidData("18354561 11", 9, 13, 2147483611);
        testValidData("18354561 9", 9, 13, 2147483601);
        testValidData("18354561 9", 9, 13, 2147483600);
        testValidData("18354561 10", 9, 13, 2147483602);
        testValidData("18354561 8", 9, 13, 2147483592);
        testValidData("18354561 8", 9, 13, 2147483591);
        testValidData("18354561 9", 9, 13, 2147483593);
        testValidData("18354561 7", 9, 13, 2147483583);
        testValidData("18354561 7", 9, 13, 2147483582);
        testValidData("18354561 8", 9, 13, 2147483584);
        testValidData("18354561 6", 9, 13, 2147483574);
        testValidData("18354561 6", 9, 13, 2147483573);
        testValidData("18354561 7", 9, 13, 2147483575);
        testValidData("18354561 5", 9, 13, 2147483565);
        testValidData("18354561 5", 9, 13, 2147483564);
        testValidData("18354561 6", 9, 13, 2147483566);
        testValidData("18354561 4", 9, 13, 2147483556);
        testValidData("18354561 4", 9, 13, 2147483555);
        testValidData("18354561 5", 9, 13, 2147483557);
        testValidData("18354561 3", 9, 13, 2147483547);
        testValidData("18354561 3", 9, 13, 2147483546);
        testValidData("18354561 4", 9, 13, 2147483548);
        testValidData("18354561 2", 9, 13, 2147483538);
        testValidData("18354561 2", 9, 13, 2147483537);
        testValidData("18354561 3", 9, 13, 2147483539);
        testValidData("18354561 1", 9, 13, 2147483529);
        testValidData("18354561 1", 9, 13, 2147483528);
        testValidData("18354561 2", 9, 13, 2147483530);

        testValidData("1 3", 4, 14, 10);
        //"1 3",4,14,

        testInvalidDataNumber(9, 13, 0);

        testValidData("1 1", 4, 14, 1);
        testValidData("1 1", 4, 14, 2);
        testValidData("1 1", 4, 14, 3);
        testValidData("1 1", 4, 14, 4);
        testValidData("1 2", 4, 14, 5);

        testValidData("1 2", 4, 14, 6);
        testValidData("1 3", 4, 14, 9);
        testValidData("1 2", 4, 14, 8);
        testValidData("1 3", 4, 14, 10);
        testValidData("1 4", 4, 14, 13);
        testValidData("1 3", 4, 14, 12);
        testValidData("1 4", 4, 14, 14);
        testValidData("1 5", 4, 14, 17);
        testValidData("1 4", 4, 14, 16);
        testValidData("1 5", 4, 14, 18);
        testValidData("1 6", 4, 14, 21);
        testValidData("1 5", 4, 14, 20);
        testValidData("1 6", 4, 14, 22);
        testValidData("1 7", 4, 14, 25);
        testValidData("1 6", 4, 14, 24);
        testValidData("1 7", 4, 14, 26);
        testValidData("1 8", 4, 14, 29);
        testValidData("1 7", 4, 14, 28);
        testValidData("1 8", 4, 14, 30);
        testValidData("1 9", 4, 14, 33);
        testValidData("1 8", 4, 14, 32);
        testValidData("1 9", 4, 14, 34);
        testValidData("1 10", 4, 14, 37);
        testValidData("1 9", 4, 14, 36);
        testValidData("1 10", 4, 14, 38);
        testValidData("1 11", 4, 14, 41);
        testValidData("1 10", 4, 14, 40);
        testValidData("1 11", 4, 14, 42);
        testValidData("1 12", 4, 14, 45);
        testValidData("1 11", 4, 14, 44);
        testValidData("1 12", 4, 14, 46);
        testValidData("1 13", 4, 14, 49);
        testValidData("1 12", 4, 14, 48);
        testValidData("1 13", 4, 14, 50);
        testValidData("1 14", 4, 14, 53);
        testValidData("1 13", 4, 14, 52);
        testValidData("1 14", 4, 14, 54);

        testValidData("14 13", 4, 14, 780);
        testValidData("14 14", 4, 14, 781);
        testValidData("14 14", 4, 14, 782);
        testValidData("14 14", 4, 14, 783);
        testValidData("14 14", 4, 14, 784);
        testValidData("15 1", 4, 14, 785);

        testValidData("38347922 14", 4, 14, 2147483632);
        testValidData("38347922 14", 4, 14, 2147483631);
        testValidData("38347923 1", 4, 14, 2147483633);
        testValidData("38347922 13", 4, 14, 2147483628);
        testValidData("38347922 13", 4, 14, 2147483627);
        testValidData("38347922 14", 4, 14, 2147483629);
        testValidData("38347922 12", 4, 14, 2147483624);
        testValidData("38347922 12", 4, 14, 2147483623);
        testValidData("38347922 13", 4, 14, 2147483625);
        testValidData("38347922 11", 4, 14, 2147483620);
        testValidData("38347922 11", 4, 14, 2147483619);
        testValidData("38347922 12", 4, 14, 2147483621);
        testValidData("38347922 10", 4, 14, 2147483616);
        testValidData("38347922 10", 4, 14, 2147483615);
        testValidData("38347922 11", 4, 14, 2147483617);
        testValidData("38347922 9", 4, 14, 2147483612);
        testValidData("38347922 9", 4, 14, 2147483611);
        testValidData("38347922 10", 4, 14, 2147483613);
        testValidData("38347922 8", 4, 14, 2147483608);
        testValidData("38347922 8", 4, 14, 2147483607);
        testValidData("38347922 9", 4, 14, 2147483609);
        testValidData("38347922 7", 4, 14, 2147483604);
        testValidData("38347922 7", 4, 14, 2147483603);
        testValidData("38347922 8", 4, 14, 2147483605);
        testValidData("38347922 6", 4, 14, 2147483600);
        testValidData("38347922 6", 4, 14, 2147483599);
        testValidData("38347922 7", 4, 14, 2147483601);
        testValidData("38347922 5", 4, 14, 2147483596);
        testValidData("38347922 5", 4, 14, 2147483595);
        testValidData("38347922 6", 4, 14, 2147483597);
        testValidData("38347922 4", 4, 14, 2147483592);
        testValidData("38347922 4", 4, 14, 2147483591);
        testValidData("38347922 5", 4, 14, 2147483593);
        testValidData("38347922 3", 4, 14, 2147483588);
        testValidData("38347922 3", 4, 14, 2147483587);
        testValidData("38347922 4", 4, 14, 2147483589);
        testValidData("38347922 2", 4, 14, 2147483584);
        testValidData("38347922 2", 4, 14, 2147483583);
        testValidData("38347922 3", 4, 14, 2147483585);
        testValidData("38347922 1", 4, 14, 2147483580);
        testValidData("38347922 1", 4, 14, 2147483579);
        testValidData("38347922 2", 4, 14, 2147483581);

        testValidData("1 2", 10, 20, 16);
        //"1 2",10,20,

        testInvalidDataNumber(10, 20, 0);

        testValidData("1 1", 10, 20, 1);
        testValidData("1 1", 10, 20, 2);
        testValidData("1 2", 10, 20, 11);
        testValidData("1 1", 10, 20, 10);
        testValidData("1 2", 10, 20, 12);
        testValidData("1 3", 10, 20, 21);
        testValidData("1 2", 10, 20, 20);
        testValidData("1 3", 10, 20, 22);
        testValidData("1 4", 10, 20, 31);
        testValidData("1 3", 10, 20, 30);
        testValidData("1 4", 10, 20, 32);
        testValidData("1 5", 10, 20, 41);
        testValidData("1 4", 10, 20, 40);
        testValidData("1 5", 10, 20, 42);
        testValidData("1 6", 10, 20, 51);
        testValidData("1 5", 10, 20, 50);
        testValidData("1 6", 10, 20, 52);
        testValidData("1 7", 10, 20, 61);
        testValidData("1 6", 10, 20, 60);
        testValidData("1 7", 10, 20, 62);
        testValidData("1 8", 10, 20, 71);
        testValidData("1 7", 10, 20, 70);
        testValidData("1 8", 10, 20, 72);
        testValidData("1 9", 10, 20, 81);
        testValidData("1 8", 10, 20, 80);
        testValidData("1 9", 10, 20, 82);
        testValidData("1 10", 10, 20, 91);
        testValidData("1 9", 10, 20, 90);
        testValidData("1 10", 10, 20, 92);
        testValidData("1 11", 10, 20, 101);
        testValidData("1 10", 10, 20, 100);
        testValidData("1 11", 10, 20, 102);
        testValidData("1 12", 10, 20, 111);
        testValidData("1 11", 10, 20, 110);
        testValidData("1 12", 10, 20, 112);
        testValidData("1 13", 10, 20, 121);
        testValidData("1 12", 10, 20, 120);
        testValidData("1 13", 10, 20, 122);
        testValidData("1 14", 10, 20, 131);
        testValidData("1 13", 10, 20, 130);
        testValidData("1 14", 10, 20, 132);
        testValidData("1 15", 10, 20, 141);
        testValidData("1 14", 10, 20, 140);
        testValidData("1 15", 10, 20, 142);
        testValidData("1 16", 10, 20, 151);
        testValidData("1 15", 10, 20, 150);
        testValidData("1 16", 10, 20, 152);
        testValidData("1 17", 10, 20, 161);
        testValidData("1 16", 10, 20, 160);
        testValidData("1 17", 10, 20, 162);
        testValidData("1 18", 10, 20, 171);
        testValidData("1 17", 10, 20, 170);
        testValidData("1 18", 10, 20, 172);
        testValidData("1 19", 10, 20, 181);
        testValidData("1 18", 10, 20, 180);
        testValidData("1 19", 10, 20, 182);
        testValidData("1 20", 10, 20, 191);
        testValidData("1 19", 10, 20, 190);
        testValidData("1 20", 10, 20, 192);


        testValidData("1 1", 10, 20, 3);
        testValidData("1 1", 10, 20, 4);
        testValidData("1 1", 10, 20, 5);
        testValidData("1 1", 10, 20, 6);
        testValidData("1 1", 10, 20, 7);
        testValidData("1 1", 10, 20, 8);
        testValidData("1 1", 10, 20, 9);

        testValidData("20 20", 10, 20, 3999);
        testValidData("20 20", 10, 20, 4000);
        testValidData("21 1", 10, 20, 4001);
        testValidData("21 1", 10, 20, 4002);
        testValidData("21 1", 10, 20, 4003);
        testValidData("21 1", 10, 20, 4004);
        testValidData("21 1", 10, 20, 4005);
        testValidData("21 1", 10, 20, 4006);
        testValidData("21 1", 10, 20, 4007);
        testValidData("21 1", 10, 20, 4008);
        testValidData("21 1", 10, 20, 4009);
        testValidData("21 1", 10, 20, 4010);

        testValidData("10737418 20", 10, 20, 2147483600);
        testValidData("10737418 20", 10, 20, 2147483599);
        testValidData("10737419 1", 10, 20, 2147483601);
        testValidData("10737418 19", 10, 20, 2147483590);
        testValidData("10737418 19", 10, 20, 2147483589);
        testValidData("10737418 20", 10, 20, 2147483591);
        testValidData("10737418 18", 10, 20, 2147483580);
        testValidData("10737418 18", 10, 20, 2147483579);
        testValidData("10737418 19", 10, 20, 2147483581);
        testValidData("10737418 17", 10, 20, 2147483570);
        testValidData("10737418 17", 10, 20, 2147483569);
        testValidData("10737418 18", 10, 20, 2147483571);
        testValidData("10737418 16", 10, 20, 2147483560);
        testValidData("10737418 16", 10, 20, 2147483559);
        testValidData("10737418 17", 10, 20, 2147483561);
        testValidData("10737418 15", 10, 20, 2147483550);
        testValidData("10737418 15", 10, 20, 2147483549);
        testValidData("10737418 16", 10, 20, 2147483551);
        testValidData("10737418 14", 10, 20, 2147483540);
        testValidData("10737418 14", 10, 20, 2147483539);
        testValidData("10737418 15", 10, 20, 2147483541);
        testValidData("10737418 13", 10, 20, 2147483530);
        testValidData("10737418 13", 10, 20, 2147483529);
        testValidData("10737418 14", 10, 20, 2147483531);
        testValidData("10737418 12", 10, 20, 2147483520);
        testValidData("10737418 12", 10, 20, 2147483519);
        testValidData("10737418 13", 10, 20, 2147483521);
        testValidData("10737418 11", 10, 20, 2147483510);
        testValidData("10737418 11", 10, 20, 2147483509);
        testValidData("10737418 12", 10, 20, 2147483511);
        testValidData("10737418 10", 10, 20, 2147483500);
        testValidData("10737418 10", 10, 20, 2147483499);
        testValidData("10737418 11", 10, 20, 2147483501);
        testValidData("10737418 9", 10, 20, 2147483490);
        testValidData("10737418 9", 10, 20, 2147483489);
        testValidData("10737418 10", 10, 20, 2147483491);
        testValidData("10737418 8", 10, 20, 2147483480);
        testValidData("10737418 8", 10, 20, 2147483479);
        testValidData("10737418 9", 10, 20, 2147483481);
        testValidData("10737418 7", 10, 20, 2147483470);
        testValidData("10737418 7", 10, 20, 2147483469);
        testValidData("10737418 8", 10, 20, 2147483471);
        testValidData("10737418 6", 10, 20, 2147483460);
        testValidData("10737418 6", 10, 20, 2147483459);
        testValidData("10737418 7", 10, 20, 2147483461);
        testValidData("10737418 5", 10, 20, 2147483450);
        testValidData("10737418 5", 10, 20, 2147483449);
        testValidData("10737418 6", 10, 20, 2147483451);
        testValidData("10737418 4", 10, 20, 2147483440);
        testValidData("10737418 4", 10, 20, 2147483439);
        testValidData("10737418 5", 10, 20, 2147483441);
        testValidData("10737418 3", 10, 20, 2147483430);
        testValidData("10737418 3", 10, 20, 2147483429);
        testValidData("10737418 4", 10, 20, 2147483431);
        testValidData("10737418 2", 10, 20, 2147483420);
        testValidData("10737418 2", 10, 20, 2147483419);
        testValidData("10737418 3", 10, 20, 2147483421);
        testValidData("10737418 1", 10, 20, 2147483410);
        testValidData("10737418 1", 10, 20, 2147483409);
        testValidData("10737418 2", 10, 20, 2147483411);

        testValidData("2 2", 1, 5, 7);
        //"2 2",1,5,7
        testInvalidDataNumber(1, 5, 0);
        testValidData("1 1", 1, 5, 1);
        testValidData("1 2", 1, 5, 2);
        testValidData("1 3", 1, 5, 3);
        testValidData("1 5", 1, 5, 5);
        testValidData("2 1", 1, 5, 6);
        testValidData("1 4", 1, 5, 4);

        testValidData("5 5", 1, 5, 25);
        testValidData("5 4", 1, 5, 24);
        testValidData("6 1", 1, 5, 26);

        testValidData("429496729 5", 1, 5, 2147483645);
        testValidData("429496730 1", 1, 5, 2147483646);
        testValidData("429496729 4", 1, 5, 2147483644);
        testValidData("429496729 3", 1, 5, 2147483643);
        testValidData("429496729 2", 1, 5, 2147483642);
        testValidData("429496729 1", 1, 5, 2147483641);
        testValidData("429496728 5", 1, 5, 2147483640);

        testValidData("1 3", 5, 9, 11);
        //"1 3",5,9,11

        testValidData("1 1", 5, 9, 1);
        testValidData("1 1", 5, 9, 2);
        testValidData("1 1", 5, 9, 3);
        testValidData("1 1", 5, 9, 4);
        testValidData("1 1", 5, 9, 5);
        testValidData("1 2", 5, 9, 6);
        testValidData("1 2", 5, 9, 7);
        testValidData("1 3", 5, 9, 11);
        testValidData("1 2", 5, 9, 10);
        testValidData("1 3", 5, 9, 12);
        testValidData("1 4", 5, 9, 16);
        testValidData("1 3", 5, 9, 15);
        testValidData("1 4", 5, 9, 17);
        testValidData("1 5", 5, 9, 21);
        testValidData("1 4", 5, 9, 20);
        testValidData("1 5", 5, 9, 22);
        testValidData("1 5", 5, 9, 25);
        testValidData("1 6", 5, 9, 26);
        testValidData("1 6", 5, 9, 27);
        testValidData("1 7", 5, 9, 31);
        testValidData("1 7", 5, 9, 32);
        testValidData("1 6", 5, 9, 30);
        testValidData("1 8", 5, 9, 36);
        testValidData("1 7", 5, 9, 35);
        testValidData("1 8", 5, 9, 37);
        testValidData("1 9", 5, 9, 41);
        testValidData("1 8", 5, 9, 40);
        testValidData("1 9", 5, 9, 42);
        testValidData("1 9", 5, 9, 45);
        testValidData("1 9", 5, 9, 44);
        testValidData("2 1", 5, 9, 46);

        testValidData("9 8", 5, 9, 400);
        testValidData("9 9", 5, 9, 401);
        testValidData("9 9", 5, 9, 402);
        testValidData("9 9", 5, 9, 403);
        testValidData("9 9", 5, 9, 404);
        testValidData("9 9", 5, 9, 405);
        testValidData("10 1", 5, 9, 406);

        testValidData("47721859 1", 5, 9, 2147483611);
        testValidData("47721858 9", 5, 9, 2147483610);
        testValidData("47721858 9", 5, 9, 2147483609);
        testValidData("47721858 9", 5, 9, 2147483608);
        testValidData("47721858 9", 5, 9, 2147483607);
        testValidData("47721858 9", 5, 9, 2147483606);
        testValidData("47721858 8", 5, 9, 2147483605);
        testValidData("47721858 8", 5, 9, 2147483604);
        testValidData("47721858 7", 5, 9, 2147483600);
        testValidData("47721858 8", 5, 9, 2147483601);
        testValidData("47721858 7", 5, 9, 2147483599);
        testValidData("47721858 6", 5, 9, 2147483595);
        testValidData("47721858 7", 5, 9, 2147483596);
        testValidData("47721858 6", 5, 9, 2147483594);
        testValidData("47721858 5", 5, 9, 2147483590);
        testValidData("47721858 6", 5, 9, 2147483591);
        testValidData("47721858 5", 5, 9, 2147483589);
        testValidData("47721858 4", 5, 9, 2147483585);
        testValidData("47721858 5", 5, 9, 2147483586);
        testValidData("47721858 4", 5, 9, 2147483584);
        testValidData("47721858 3", 5, 9, 2147483580);
        testValidData("47721858 4", 5, 9, 2147483581);
        testValidData("47721858 3", 5, 9, 2147483579);
        testValidData("47721858 2", 5, 9, 2147483575);
        testValidData("47721858 3", 5, 9, 2147483576);
        testValidData("47721858 2", 5, 9, 2147483574);
        testValidData("47721858 1", 5, 9, 2147483570);
        testValidData("47721858 2", 5, 9, 2147483571);
        testValidData("47721857 9", 5, 9, 2147483565);
        testValidData("47721858 1", 5, 9, 2147483566);
        testValidData("47721858 1", 5, 9, 2147483567);

        testValidData("1 1", 7, 6, 4);
        //"1 1", 7,6,
        testInvalidDataNumber(7, 6, 0);


        testValidData("1 1", 7, 6, 1);
        testValidData("1 1", 7, 6, 2);
        testValidData("1 1", 7, 6, 3);
        testValidData("1 1", 7, 6, 4);
        testValidData("1 1", 7, 6, 5);
        testValidData("1 1", 7, 6, 6);
        testValidData("1 1", 7, 6, 7);
        testValidData("1 2", 7, 6, 8);

        testValidData("1 2", 7, 6, 9);
        testValidData("1 2", 7, 6, 14);
        testValidData("1 3", 7, 6, 15);
        testValidData("1 3", 7, 6, 16);
        testValidData("1 4", 7, 6, 23);
        testValidData("1 4", 7, 6, 22);
        testValidData("1 3", 7, 6, 21);
        testValidData("1 5", 7, 6, 30);
        testValidData("1 5", 7, 6, 29);
        testValidData("1 4", 7, 6, 28);
        testValidData("1 6", 7, 6, 36);
        testValidData("1 6", 7, 6, 37);
        testValidData("1 5", 7, 6, 35);

        testValidData("6 5", 7, 6, 245);
        testValidData("6 6", 7, 6, 246);
        testValidData("6 6", 7, 6, 247);
        testValidData("6 6", 7, 6, 248);
        testValidData("6 6", 7, 6, 249);
        testValidData("6 6", 7, 6, 250);
        testValidData("6 6", 7, 6, 251);
        testValidData("6 6", 7, 6, 252);
        testValidData("7 1", 7, 6, 253);

        testValidData("51130564 1", 7, 6, 2147483647);
        testValidData("51130563 6", 7, 6, 2147483646);
        testValidData("51130563 6", 7, 6, 2147483645);
        testValidData("51130563 5", 7, 6, 2147483639);
        testValidData("51130563 6", 7, 6, 2147483640);
        testValidData("51130563 5", 7, 6, 2147483638);
        testValidData("51130563 4", 7, 6, 2147483632);
        testValidData("51130563 4", 7, 6, 2147483631);
        testValidData("51130563 5", 7, 6, 2147483633);
        testValidData("51130563 3", 7, 6, 2147483625);
        testValidData("51130563 4", 7, 6, 2147483626);
        testValidData("51130563 3", 7, 6, 2147483624);
        testValidData("51130563 2", 7, 6, 2147483618);
        testValidData("51130563 3", 7, 6, 2147483619);
        testValidData("51130563 2", 7, 6, 2147483617);
        testValidData("51130563 1", 7, 6, 2147483611);
        testValidData("51130563 2", 7, 6, 2147483612);
        testValidData("51130563 1", 7, 6, 2147483610);


        testValidData("1 3", 3, 7, 9);
        //"1 3",3,7,
        testInvalidDataNumber(3, 7, 0);

        testValidData("1 1", 3, 7, 1);
        testValidData("1 1", 3, 7, 2);
        testValidData("1 1", 3, 7, 3);
        testValidData("1 2", 3, 7, 4);

        testValidData("1 2", 3, 7, 5);
        testValidData("1 3", 3, 7, 7);
        testValidData("1 3", 3, 7, 8);
        testValidData("1 2", 3, 7, 6);
        testValidData("1 4", 3, 7, 11);
        testValidData("1 4", 3, 7, 10);
        testValidData("1 3", 3, 7, 9);
        testValidData("1 5", 3, 7, 13);
        testValidData("1 4", 3, 7, 12);
        testValidData("1 5", 3, 7, 14);
        testValidData("1 6", 3, 7, 16);
        testValidData("1 6", 3, 7, 17);
        testValidData("1 5", 3, 7, 15);

        testValidData("8 1", 3, 7, 148);
        testValidData("7 7", 3, 7, 147);
        testValidData("7 7", 3, 7, 146);
        testValidData("7 7", 3, 7, 145);
        testValidData("7 6", 3, 7, 144);

        testValidData("102261127 1", 3, 7, 2147483647);
        testValidData("102261126 7", 3, 7, 2147483646);
        testValidData("102261126 7", 3, 7, 2147483645);
        testValidData("102261126 6", 3, 7, 2147483643);
        testValidData("102261126 7", 3, 7, 2147483644);
        testValidData("102261126 6", 3, 7, 2147483642);
        testValidData("102261126 5", 3, 7, 2147483640);
        testValidData("102261126 6", 3, 7, 2147483641);
        testValidData("102261126 5", 3, 7, 2147483639);
        testValidData("102261126 4", 3, 7, 2147483637);
        testValidData("102261126 5", 3, 7, 2147483638);
        testValidData("102261126 4", 3, 7, 2147483636);
        testValidData("102261126 3", 3, 7, 2147483634);
        testValidData("102261126 4", 3, 7, 2147483635);
        testValidData("102261126 3", 3, 7, 2147483633);
        testValidData("102261126 2", 3, 7, 2147483631);
        testValidData("102261126 3", 3, 7, 2147483632);
        testValidData("102261126 2", 3, 7, 2147483630);
        testValidData("102261126 1", 3, 7, 2147483628);
        testValidData("102261126 2", 3, 7, 2147483629);
        testValidData("102261126 1", 3, 7, 2147483627);


        testValidData("1 4", 2, 12, 8);
        //"1 4",2,12,
        testInvalidDataNumber(2, 12, 0);

        testValidData("1 1", 2, 12, 1);
        testValidData("1 1", 2, 12, 2);
        testValidData("1 2", 2, 12, 3);
        testValidData("1 2", 2, 12, 4);
        testValidData("1 3", 2, 12, 5);
        testValidData("1 3", 2, 12, 6);
        testValidData("1 4", 2, 12, 7);
        testValidData("1 4", 2, 12, 8);
        testValidData("1 5", 2, 12, 9);
        testValidData("1 5", 2, 12, 10);
        testValidData("1 6", 2, 12, 11);
        testValidData("1 6", 2, 12, 12);
        testValidData("1 7", 2, 12, 13);
        testValidData("1 7", 2, 12, 14);
        testValidData("1 8", 2, 12, 15);
        testValidData("1 8", 2, 12, 16);
        testValidData("1 9", 2, 12, 17);
        testValidData("1 9", 2, 12, 18);
        testValidData("1 10", 2, 12, 19);
        testValidData("1 10", 2, 12, 20);
        testValidData("1 11", 2, 12, 21);
        testValidData("1 11", 2, 12, 22);
        testValidData("1 12", 2, 12, 23);
        testValidData("1 12", 2, 12, 24);
        testValidData("2 1", 2, 12, 25);

        testValidData("12 11", 2, 12, 286);
        testValidData("12 12", 2, 12, 288);
        testValidData("12 12", 2, 12, 287);
        testValidData("13 1", 2, 12, 289);

        testValidData("89478485 12", 2, 12, 2147483640);
        testValidData("89478485 12", 2, 12, 2147483639);
        testValidData("89478486 1", 2, 12, 2147483641);
        testValidData("89478485 11", 2, 12, 2147483638);
        testValidData("89478485 11", 2, 12, 2147483637);
        testValidData("89478485 10", 2, 12, 2147483636);
        testValidData("89478485 10", 2, 12, 2147483635);
        testValidData("89478485 9", 2, 12, 2147483634);
        testValidData("89478485 9", 2, 12, 2147483633);
        testValidData("89478485 8", 2, 12, 2147483632);
        testValidData("89478485 8", 2, 12, 2147483631);
        testValidData("89478485 7", 2, 12, 2147483630);
        testValidData("89478485 7", 2, 12, 2147483629);
        testValidData("89478485 6", 2, 12, 2147483628);
        testValidData("89478485 6", 2, 12, 2147483627);
        testValidData("89478485 5", 2, 12, 2147483626);
        testValidData("89478485 5", 2, 12, 2147483625);
        testValidData("89478485 4", 2, 12, 2147483624);
        testValidData("89478485 4", 2, 12, 2147483623);
        testValidData("89478485 3", 2, 12, 2147483622);
        testValidData("89478485 3", 2, 12, 2147483621);
        testValidData("89478485 2", 2, 12, 2147483620);
        testValidData("89478485 2", 2, 12, 2147483619);
        testValidData("89478485 1", 2, 12, 2147483618);
        testValidData("89478485 1", 2, 12, 2147483617);

        testValidData("1 3", 4, 14, 10);
        //4 14
        testValidData("1 1", 4, 14, 1);
        testInvalidDataNumber(4, 14, 0);
        testValidData("1 1", 4, 14, 2);
        testValidData("1 2", 4, 14, 5);
        testValidData("1 1", 4, 14, 4);
        testValidData("1 2", 4, 14, 6);
        testValidData("1 3", 4, 14, 9);
        testValidData("1 2", 4, 14, 8);
        testValidData("1 3", 4, 14, 10);
        testValidData("1 4", 4, 14, 13);
        testValidData("1 3", 4, 14, 12);
        testValidData("1 4", 4, 14, 14);
        testValidData("1 5", 4, 14, 17);
        testValidData("1 4", 4, 14, 16);
        testValidData("1 5", 4, 14, 18);
        testValidData("1 6", 4, 14, 21);
        testValidData("1 5", 4, 14, 20);
        testValidData("1 6", 4, 14, 22);
        testValidData("1 7", 4, 14, 25);
        testValidData("1 6", 4, 14, 24);
        testValidData("1 7", 4, 14, 26);
        testValidData("1 8", 4, 14, 29);
        testValidData("1 7", 4, 14, 28);
        testValidData("1 8", 4, 14, 30);
        testValidData("1 9", 4, 14, 33);
        testValidData("1 8", 4, 14, 32);
        testValidData("1 9", 4, 14, 34);
        testValidData("1 10", 4, 14, 37);
        testValidData("1 9", 4, 14, 36);
        testValidData("1 10", 4, 14, 38);
        testValidData("1 11", 4, 14, 41);
        testValidData("1 10", 4, 14, 40);
        testValidData("1 11", 4, 14, 42);
        testValidData("1 12", 4, 14, 45);
        testValidData("1 11", 4, 14, 44);
        testValidData("1 12", 4, 14, 46);
        testValidData("1 13", 4, 14, 49);
        testValidData("1 12", 4, 14, 48);
        testValidData("1 13", 4, 14, 50);
        testValidData("1 14", 4, 14, 53);
        testValidData("1 13", 4, 14, 52);
        testValidData("1 14", 4, 14, 54);

        testValidData("1 1", 4, 14, 3);

        testValidData("14 14", 4, 14, 782);
        testValidData("14 14", 4, 14, 781);
        testValidData("14 13", 4, 14, 780);
        testValidData("14 14", 4, 14, 784);
        testValidData("14 14", 4, 14, 783);
        testValidData("15 1", 4, 14, 785);

        testValidData("38347922 14", 4, 14, 2147483632);
        testValidData("38347922 14", 4, 14, 2147483631);
        testValidData("38347923 1", 4, 14, 2147483633);
        testValidData("38347922 13", 4, 14, 2147483627);
        testValidData("38347922 13", 4, 14, 2147483626);
        testValidData("38347922 13", 4, 14, 2147483628);
        testValidData("38347922 12", 4, 14, 2147483622);
        testValidData("38347922 12", 4, 14, 2147483621);
        testValidData("38347922 12", 4, 14, 2147483623);
        testValidData("38347922 11", 4, 14, 2147483617);
        testValidData("38347922 10", 4, 14, 2147483616);
        testValidData("38347922 11", 4, 14, 2147483618);
        testValidData("38347922 9", 4, 14, 2147483612);
        testValidData("38347922 9", 4, 14, 2147483611);
        testValidData("38347922 10", 4, 14, 2147483613);
        testValidData("38347922 8", 4, 14, 2147483607);
        testValidData("38347922 8", 4, 14, 2147483606);
        testValidData("38347922 8", 4, 14, 2147483608);
        testValidData("38347922 7", 4, 14, 2147483602);
        testValidData("38347922 7", 4, 14, 2147483601);
        testValidData("38347922 7", 4, 14, 2147483603);
        testValidData("38347922 6", 4, 14, 2147483597);
        testValidData("38347922 5", 4, 14, 2147483596);
        testValidData("38347922 6", 4, 14, 2147483598);
    }

    @Test
    void subject_area() throws IOException {
        testValidData("1 1", 8, 8, 8);
        testValidData("1 4", 6, 6, 21);
        testValidData("1 8", 3, 9, 24);
        testValidData("1 7", 8, 8, 54);
        testValidData("1 9", 5, 9, 41);
        testValidData("1 6", 12, 6, 64);
        testValidData("1 2", 7, 9, 12);
        testValidData("1 5", 8, 5, 34);
        testValidData("3 1", 10, 3, 63);
        testValidData("1 7", 8, 9, 54);
        testValidData("4 5", 5, 8, 142);
        testValidData("1 2", 6, 8, 10);
    }

    @Test
    void realization() throws IOException {
        testInvalidDataNumber(0, 0, 0);
        testInvalidDataNumber(0, 1, 1);
        testInvalidDataNumber(0, 54, 0);
        testInvalidDataNumber(0, -5, 0);
        testInvalidDataNumber(-2, -5, 0);
        testInvalidDataNumber(-4, -5, Integer.MAX_VALUE);
        testInvalidDataNumber(0, -5, Integer.MIN_VALUE);
        testInvalidDataNumber(-1, -5, 0);
        testInvalidDataNumber(0, -5, Integer.MIN_VALUE);
        testInvalidDataNoNumber('w', '2', 's');
        testInvalidDataNoNumber('^', '$', 's');
        testInvalidDataNoNumber('$', '*', '^');
        testInvalidDataNoNumber('-', '+', '/');
    }

    @Test
    void random() throws IOException {
        testValidData("1 53698541", 12, 80924796, 644382487);
        testValidData("2 21035698", 30, 41095324, 1863930650);
        testValidData("5 1", 127094726, 1, 551837192);
        testValidData("1 1012", 385496, 4945, 390013641);
        testValidData("6 655", 315466, 749, 1387899923);
        testValidData("1 35683537", 29, 72851648, 1034822565);
        testValidData("1 294363", 4657, 382686, 1370847810);
        testValidData("1 2210304", 254, 7458390, 561416972);
        testValidData("1 3419", 221397, 6732, 756784428);
        testValidData("1 263923067", 6, 276477929, 1583538402);
        testValidData("2 4622404", 85, 7216042, 1006267829);
        testValidData("1 6764828", 71, 7384158, 480302777);
        testValidData("1 63760", 9660, 144405, 615912819);
        testValidData("2 598", 1221366, 1103, 2076864034);
        testValidData("1 3173", 395538, 4552, 1254992413);
        testValidData("1 5954", 49630, 21208, 295474470);
        testValidData("3 6706261", 20, 25185990, 1141564809);
        testValidData("4 36811", 1833, 215120, 1250418246);
        testValidData("3 2842", 52756, 12731, 1493203202);
        testValidData("2 60271", 307, 6613163, 2048743942);
        testValidData("1 3", 121435613, 3, 357910066);
        testValidData("1 954772061", 1, 1434282602, 954772061);
        testValidData("3 26002264", 5, 95492704, 1084938358);
        testValidData("1 1271061563", 1, 1972684232, 1271061563);
        testValidData("1 110", 6198302, 178, 678088577);
        testValidData("3 1", 39174106, 23, 1837259720);
        testValidData("1 4", 516293849, 4, 1869973245);
        testValidData("1 71443901", 23, 85699959, 1643209708);
    }

    @Test
    void special_args() throws IOException {
        testValidData("1 1", 13, 13, 13);
        testValidData("1 1", 13, 21, 4);
        testValidData("1 4", 4, 21, 13);
        testValidData("1 1", 7, 6, 4);
        testValidData("1 2", 23, 27, 29);
        testValidData("1 2", 17, 21, 23);
        testValidData("1 2", 27, 31, 33);
        testValidData("1 2", 25, 29, 31);
        testValidData("2 2", 1, 5, 7);
        testValidData("1 2", 9, 13, 15);
        testValidData("1 2", 15, 19, 21);
        testValidData("1 2", 11, 15, 17);
        testValidData("1 2", 29, 33, 35);
        testValidData("1 2", 21, 25, 27);
        testValidData("1 2", 19, 23, 25);
        testValidData("1 2", 7, 11, 13);
        testValidData("1 3", 5, 9, 11);
        testValidData("1 2", 13, 17, 19);
        testValidData("1 3", 3, 7, 9);
        testValidData("1 2", 28, 38, 34);
        testValidData("1 2", 6, 16, 12);
        testValidData("1 2", 24, 34, 30);
        testValidData("1 2", 16, 26, 22);
        testValidData("1 2", 18, 28, 24);
        testValidData("1 3", 4, 14, 10);
        testValidData("1 2", 10, 20, 16);
        testValidData("1 2", 8, 18, 14);
        testValidData("1 2", 20, 30, 26);
        testValidData("1 2", 14, 24, 20);
        testValidData("1 2", 12, 22, 18);
        testValidData("1 2", 22, 32, 28);
        testValidData("1 2", 26, 36, 32);
        testValidData("1 4", 2, 12, 8);
    }

}
