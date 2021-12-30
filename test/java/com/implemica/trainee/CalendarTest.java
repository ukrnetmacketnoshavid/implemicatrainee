package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void systemSetOut() throws IOException {
        OUT_CONTENT.close();
        OUT_CONTENT.close();
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }


    @AfterEach
    public void setSystemOutDefault() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    void testValidData(int expectedResult, int year, int month, int day) throws IOException {
        String data = year + System.lineSeparator()
                + month + System.lineSeparator()
                + day + System.lineSeparator();
        String expected = Messages.CALENDAR_INPUT_YEAR + System.lineSeparator();
        expected += Messages.CALENDAR_INPUT_DAY + System.lineSeparator();
        expected += Messages.CALENDAR_INPUT_MONTH + System.lineSeparator();
        expected += expectedResult + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Calendar.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    void testInvalidDataNumber(int year, int month, int day) throws IOException {
        String expected = Messages.CALENDAR_INPUT_YEAR + System.lineSeparator();
        expected += Messages.CALENDAR_INPUT_DAY + System.lineSeparator();
        expected += Messages.CALENDAR_INPUT_MONTH + System.lineSeparator();
        String data = year + System.lineSeparator() + month + System.lineSeparator() + day + System.lineSeparator();

        if (year < 1 ^ year > 7) {
            expected += "Invalid data year" + System.lineSeparator();
        } else if (day < 1 ^ day > 31) {
            expected += "Invalid data day" + System.lineSeparator();
        } else if (month < 1 ^ month > 12) {
            expected += "Invalid data month" + System.lineSeparator();
        } else {
            int[] dayOfMonths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (day > dayOfMonths[month - 1]) {
                expected += "Invalid data day" + System.lineSeparator();
            }
        }

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Calendar.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    void testInvalidDataNoNumber(char year, char month, char day) throws IOException {
        String expected = Messages.CALENDAR_INPUT_YEAR + System.lineSeparator();
        expected += "For input string: \"" + year + "\"" + System.lineSeparator();
        String data = year + System.lineSeparator()
                + month + System.lineSeparator()
                + day + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Calendar.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    @AfterEach
    public void systemSetOutDefault() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    void boundary() throws IOException {
        //region year 1_1
        testValidData(1, 1, 1, 1);
        testValidData(2, 1, 1, 2);
        testValidData(4, 1, 2, 1);
        testValidData(5, 1, 2, 2);
        testValidData(4, 1, 3, 1);
        testValidData(5, 1, 3, 2);
        testValidData(7, 1, 4, 1);
        testValidData(1, 1, 4, 2);
        testValidData(2, 1, 5, 1);
        testValidData(3, 1, 5, 2);
        testValidData(5, 1, 6, 1);
        testValidData(6, 1, 6, 2);
        testValidData(7, 1, 7, 1);
        testValidData(1, 1, 7, 2);
        testValidData(3, 1, 8, 1);
        testValidData(4, 1, 8, 2);
        testValidData(6, 1, 9, 1);
        testValidData(7, 1, 9, 2);
        testValidData(1, 1, 10, 1);
        testValidData(2, 1, 10, 2);
        testValidData(4, 1, 11, 1);
        testValidData(5, 1, 11, 2);
        testValidData(6, 1, 12, 1);
        testValidData(7, 1, 12, 2);
        //endregion
        //region year 2_1
        testValidData(2, 2, 1, 1);
        testValidData(3, 2, 1, 2);
        testValidData(5, 2, 2, 1);
        testValidData(6, 2, 2, 2);
        testValidData(5, 2, 3, 1);
        testValidData(6, 2, 3, 2);
        testValidData(1, 2, 4, 1);
        testValidData(2, 2, 4, 2);
        testValidData(3, 2, 5, 1);
        testValidData(4, 2, 5, 2);
        testValidData(6, 2, 6, 1);
        testValidData(7, 2, 6, 2);
        testValidData(1, 2, 7, 1);
        testValidData(2, 2, 7, 2);
        testValidData(4, 2, 8, 1);
        testValidData(5, 2, 8, 2);
        testValidData(7, 2, 9, 1);
        testValidData(1, 2, 9, 2);
        testValidData(2, 2, 10, 1);
        testValidData(3, 2, 10, 2);
        testValidData(5, 2, 11, 1);
        testValidData(6, 2, 11, 2);
        testValidData(7, 2, 12, 1);
        testValidData(1, 2, 12, 2);
        //endregion
        //region Year 3_1
        testValidData(3, 3, 1, 1);
        testValidData(4, 3, 1, 2);
        testValidData(6, 3, 2, 1);
        testValidData(7, 3, 2, 2);
        testValidData(6, 3, 3, 1);
        testValidData(7, 3, 3, 2);
        testValidData(2, 3, 4, 1);
        testValidData(3, 3, 4, 2);
        testValidData(4, 3, 5, 1);
        testValidData(5, 3, 5, 2);
        testValidData(7, 3, 6, 1);
        testValidData(1, 3, 6, 2);
        testValidData(2, 3, 7, 1);
        testValidData(3, 3, 7, 2);
        testValidData(5, 3, 8, 1);
        testValidData(6, 3, 8, 2);
        testValidData(1, 3, 9, 1);
        testValidData(2, 3, 9, 2);
        testValidData(3, 3, 10, 1);
        testValidData(4, 3, 10, 2);
        testValidData(6, 3, 11, 1);
        testValidData(7, 3, 11, 2);
        testValidData(1, 3, 12, 1);
        testValidData(2, 3, 12, 2);
        //endregion
        //region Year 4_1
        testValidData(4, 4, 1, 1);
        testValidData(5, 4, 1, 2);
        testValidData(7, 4, 2, 1);
        testValidData(1, 4, 2, 2);
        testValidData(7, 4, 3, 1);
        testValidData(1, 4, 3, 2);
        testValidData(3, 4, 4, 1);
        testValidData(4, 4, 4, 2);
        testValidData(5, 4, 5, 1);
        testValidData(6, 4, 5, 2);
        testValidData(1, 4, 6, 1);
        testValidData(2, 4, 6, 2);
        testValidData(3, 4, 7, 1);
        testValidData(4, 4, 7, 2);
        testValidData(6, 4, 8, 1);
        testValidData(7, 4, 8, 2);
        testValidData(2, 4, 9, 1);
        testValidData(3, 4, 9, 2);
        testValidData(4, 4, 10, 1);
        testValidData(5, 4, 10, 2);
        testValidData(7, 4, 11, 1);
        testValidData(1, 4, 11, 2);
        testValidData(2, 4, 12, 1);
        testValidData(3, 4, 12, 2);
        //endregion
        //region Year 5_1
        testValidData(5, 5, 1, 1);
        testValidData(6, 5, 1, 2);
        testValidData(1, 5, 2, 1);
        testValidData(2, 5, 2, 2);
        testValidData(1, 5, 3, 1);
        testValidData(2, 5, 3, 2);
        testValidData(4, 5, 4, 1);
        testValidData(5, 5, 4, 2);
        testValidData(6, 5, 5, 1);
        testValidData(7, 5, 5, 2);
        testValidData(2, 5, 6, 1);
        testValidData(3, 5, 6, 2);
        testValidData(4, 5, 7, 1);
        testValidData(5, 5, 7, 2);
        testValidData(7, 5, 8, 1);
        testValidData(1, 5, 8, 2);
        testValidData(3, 5, 9, 1);
        testValidData(4, 5, 9, 2);
        testValidData(5, 5, 10, 1);
        testValidData(6, 5, 10, 2);
        testValidData(1, 5, 11, 1);
        testValidData(2, 5, 11, 2);
        testValidData(3, 5, 12, 1);
        testValidData(4, 5, 12, 2);
        //endregion
        //region Year 6_1
        testValidData(6, 6, 1, 1);
        testValidData(7, 6, 1, 2);
        testValidData(2, 6, 2, 1);
        testValidData(3, 6, 2, 2);
        testValidData(2, 6, 3, 1);
        testValidData(3, 6, 3, 2);
        testValidData(5, 6, 4, 1);
        testValidData(6, 6, 4, 2);
        testValidData(7, 6, 5, 1);
        testValidData(1, 6, 5, 2);
        testValidData(3, 6, 6, 1);
        testValidData(4, 6, 6, 2);
        testValidData(5, 6, 7, 1);
        testValidData(6, 6, 7, 2);
        testValidData(1, 6, 8, 1);
        testValidData(2, 6, 8, 2);
        testValidData(4, 6, 9, 1);
        testValidData(5, 6, 9, 2);
        testValidData(6, 6, 10, 1);
        testValidData(7, 6, 10, 2);
        testValidData(2, 6, 11, 1);
        testValidData(3, 6, 11, 2);
        testValidData(4, 6, 12, 1);
        testValidData(5, 6, 12, 2);
        //endregion
        //region Year 7_1
        testValidData(7, 7, 1, 1);
        testValidData(1, 7, 1, 2);
        testValidData(3, 7, 2, 1);
        testValidData(4, 7, 2, 2);
        testValidData(3, 7, 3, 1);
        testValidData(4, 7, 3, 2);
        testValidData(6, 7, 4, 1);
        testValidData(7, 7, 4, 2);
        testValidData(1, 7, 5, 1);
        testValidData(2, 7, 5, 2);
        testValidData(4, 7, 6, 1);
        testValidData(5, 7, 6, 2);
        testValidData(6, 7, 7, 1);
        testValidData(7, 7, 7, 2);
        testValidData(2, 7, 8, 1);
        testValidData(3, 7, 8, 2);
        testValidData(5, 7, 9, 1);
        testValidData(6, 7, 9, 2);
        testValidData(7, 7, 10, 1);
        testValidData(1, 7, 10, 2);
        testValidData(3, 7, 11, 1);
        testValidData(4, 7, 11, 2);
        testValidData(5, 7, 12, 1);
        testValidData(6, 7, 12, 2);
        //endregion
        //region Year 1_31
        testValidData(3, 1, 1, 31);
        testValidData(2, 1, 1, 30);
        testValidData(3, 1, 2, 28);
        testValidData(2, 1, 2, 27);
        testValidData(6, 1, 3, 31);
        testValidData(5, 1, 3, 30);
        testValidData(1, 1, 4, 30);
        testValidData(7, 1, 4, 29);
        testValidData(4, 1, 5, 31);
        testValidData(3, 1, 5, 30);
        testValidData(6, 1, 6, 30);
        testValidData(5, 1, 6, 29);
        testValidData(2, 1, 7, 31);
        testValidData(1, 1, 7, 30);
        testValidData(5, 1, 8, 31);
        testValidData(4, 1, 8, 30);
        testValidData(7, 1, 9, 30);
        testValidData(6, 1, 9, 29);
        testValidData(3, 1, 10, 31);
        testValidData(2, 1, 10, 30);
        testValidData(5, 1, 11, 30);
        testValidData(4, 1, 11, 29);
        testValidData(1, 1, 12, 31);
        testValidData(7, 1, 12, 30);
        //endregion
        //region Year 2_31
        testValidData(4, 2, 1, 31);
        testValidData(3, 2, 1, 30);
        testValidData(4, 2, 2, 28);
        testValidData(3, 2, 2, 27);
        testValidData(7, 2, 3, 31);
        testValidData(6, 2, 3, 30);
        testValidData(2, 2, 4, 30);
        testValidData(1, 2, 4, 29);
        testValidData(5, 2, 5, 31);
        testValidData(4, 2, 5, 30);
        testValidData(7, 2, 6, 30);
        testValidData(6, 2, 6, 29);
        testValidData(3, 2, 7, 31);
        testValidData(2, 2, 7, 30);
        testValidData(6, 2, 8, 31);
        testValidData(5, 2, 8, 30);
        testValidData(1, 2, 9, 30);
        testValidData(7, 2, 9, 29);
        testValidData(4, 2, 10, 31);
        testValidData(3, 2, 10, 30);
        testValidData(6, 2, 11, 30);
        testValidData(5, 2, 11, 29);
        testValidData(2, 2, 12, 31);
        testValidData(1, 2, 12, 30);
        //endregion
        //region Year 3_31
        testValidData(5, 3, 1, 31);
        testValidData(4, 3, 1, 30);
        testValidData(5, 3, 2, 28);
        testValidData(4, 3, 2, 27);
        testValidData(1, 3, 3, 31);
        testValidData(7, 3, 3, 30);
        testValidData(3, 3, 4, 30);
        testValidData(2, 3, 4, 29);
        testValidData(6, 3, 5, 31);
        testValidData(5, 3, 5, 30);
        testValidData(1, 3, 6, 30);
        testValidData(7, 3, 6, 29);
        testValidData(4, 3, 7, 31);
        testValidData(3, 3, 7, 30);
        testValidData(7, 3, 8, 31);
        testValidData(6, 3, 8, 30);
        testValidData(2, 3, 9, 30);
        testValidData(1, 3, 9, 29);
        testValidData(5, 3, 10, 31);
        testValidData(4, 3, 10, 30);
        testValidData(7, 3, 11, 30);
        testValidData(6, 3, 11, 29);
        testValidData(3, 3, 12, 31);
        testValidData(2, 3, 12, 30);
        //endregion
        //region Year 4_31
        testValidData(6, 4, 1, 31);
        testValidData(5, 4, 1, 30);
        testValidData(6, 4, 2, 28);
        testValidData(5, 4, 2, 27);
        testValidData(2, 4, 3, 31);
        testValidData(1, 4, 3, 30);
        testValidData(4, 4, 4, 30);
        testValidData(3, 4, 4, 29);
        testValidData(7, 4, 5, 31);
        testValidData(6, 4, 5, 30);
        testValidData(2, 4, 6, 30);
        testValidData(1, 4, 6, 29);
        testValidData(5, 4, 7, 31);
        testValidData(4, 4, 7, 30);
        testValidData(1, 4, 8, 31);
        testValidData(7, 4, 8, 30);
        testValidData(3, 4, 9, 30);
        testValidData(2, 4, 9, 29);
        testValidData(6, 4, 10, 31);
        testValidData(5, 4, 10, 30);
        testValidData(1, 4, 11, 30);
        testValidData(7, 4, 11, 29);
        testValidData(4, 4, 12, 31);
        testValidData(3, 4, 12, 30);
        //endregion
        //region Year 5_31
        testValidData(7, 5, 1, 31);
        testValidData(6, 5, 1, 30);
        testValidData(7, 5, 2, 28);
        testValidData(6, 5, 2, 27);
        testValidData(3, 5, 3, 31);
        testValidData(2, 5, 3, 30);
        testValidData(5, 5, 4, 30);
        testValidData(4, 5, 4, 29);
        testValidData(1, 5, 5, 31);
        testValidData(7, 5, 5, 30);
        testValidData(3, 5, 6, 30);
        testValidData(2, 5, 6, 29);
        testValidData(6, 5, 7, 31);
        testValidData(5, 5, 7, 30);
        testValidData(2, 5, 8, 31);
        testValidData(1, 5, 8, 30);
        testValidData(4, 5, 9, 30);
        testValidData(3, 5, 9, 29);
        testValidData(7, 5, 10, 31);
        testValidData(6, 5, 10, 30);
        testValidData(2, 5, 11, 30);
        testValidData(1, 5, 11, 29);
        testValidData(5, 5, 12, 31);
        testValidData(4, 5, 12, 30);
        //endregion
        //region Year 6_31
        testValidData(1, 6, 1, 31);
        testValidData(7, 6, 1, 30);
        testValidData(1, 6, 2, 28);
        testValidData(7, 6, 2, 27);
        testValidData(4, 6, 3, 31);
        testValidData(3, 6, 3, 30);
        testValidData(6, 6, 4, 30);
        testValidData(5, 6, 4, 29);
        testValidData(2, 6, 5, 31);
        testValidData(1, 6, 5, 30);
        testValidData(4, 6, 6, 30);
        testValidData(3, 6, 6, 29);
        testValidData(7, 6, 7, 31);
        testValidData(6, 6, 7, 30);
        testValidData(3, 6, 8, 31);
        testValidData(2, 6, 8, 30);
        testValidData(5, 6, 9, 30);
        testValidData(4, 6, 9, 29);
        testValidData(1, 6, 10, 31);
        testValidData(7, 6, 10, 30);
        testValidData(3, 6, 11, 30);
        testValidData(2, 6, 11, 29);
        testValidData(6, 6, 12, 31);
        testValidData(5, 6, 12, 30);
        //endregion
        //region Year 7_31
        testValidData(2, 7, 1, 31);
        testValidData(1, 7, 1, 30);
        testValidData(2, 7, 2, 28);
        testValidData(1, 7, 2, 27);
        testValidData(5, 7, 3, 31);
        testValidData(4, 7, 3, 30);
        testValidData(7, 7, 4, 30);
        testValidData(6, 7, 4, 29);
        testValidData(3, 7, 5, 31);
        testValidData(2, 7, 5, 30);
        testValidData(5, 7, 6, 30);
        testValidData(4, 7, 6, 29);
        testValidData(1, 7, 7, 31);
        testValidData(7, 7, 7, 30);
        testValidData(4, 7, 8, 31);
        testValidData(3, 7, 8, 30);
        testValidData(6, 7, 9, 30);
        testValidData(5, 7, 9, 29);
        testValidData(2, 7, 10, 31);
        testValidData(1, 7, 10, 30);
        testValidData(4, 7, 11, 30);
        testValidData(3, 7, 11, 29);
        testValidData(7, 7, 12, 31);
        testValidData(6, 7, 12, 30);
        //endregion
        //region Er Year 0_31
        testInvalidDataNumber(0, 0, 32);
        testInvalidDataNumber(0, 1, 32);
        testInvalidDataNumber(0, 2, 29);
        testInvalidDataNumber(0, 2, 28);
        testInvalidDataNumber(0, 3, 32);
        testInvalidDataNumber(0, 4, 30);
        testInvalidDataNumber(0, 5, 32);
        testInvalidDataNumber(0, 6, 31);
        testInvalidDataNumber(0, 7, 32);
        testInvalidDataNumber(0, 8, 32);
        testInvalidDataNumber(0, 9, 31);
        testInvalidDataNumber(0, 10, 32);
        testInvalidDataNumber(0, 11, 31);
        testInvalidDataNumber(0, 12, 32);
        testInvalidDataNumber(0, 13, 32);
        //endregion
        //region Er Year 1_31
        testInvalidDataNumber(1, 0, 32);
        testInvalidDataNumber(1, 1, 32);
        testInvalidDataNumber(1, 2, 29);
        testInvalidDataNumber(1, 3, 32);
        testInvalidDataNumber(1, 4, 31);
        testInvalidDataNumber(1, 5, 32);
        testInvalidDataNumber(1, 6, 31);
        testInvalidDataNumber(1, 7, 32);
        testInvalidDataNumber(1, 8, 32);
        testInvalidDataNumber(1, 9, 31);
        testInvalidDataNumber(1, 10, 32);
        testInvalidDataNumber(1, 11, 31);
        testInvalidDataNumber(1, 12, 32);
        testInvalidDataNumber(1, 13, 32);
        //endregion
        //region Er Year 2_31
        testInvalidDataNumber(2, 0, 32);
        testInvalidDataNumber(2, 1, 32);
        testInvalidDataNumber(2, 2, 29);
        testInvalidDataNumber(2, 3, 32);
        testInvalidDataNumber(2, 4, 31);
        testInvalidDataNumber(2, 5, 32);
        testInvalidDataNumber(2, 6, 31);
        testInvalidDataNumber(2, 7, 32);
        testInvalidDataNumber(2, 8, 32);
        testInvalidDataNumber(2, 9, 31);
        testInvalidDataNumber(2, 10, 32);
        testInvalidDataNumber(2, 11, 31);
        testInvalidDataNumber(2, 12, 32);
        testInvalidDataNumber(2, 13, 32);
        //endregion
        //region Er Year 3_31
        testInvalidDataNumber(3, 0, 32);
        testInvalidDataNumber(3, 1, 32);
        testInvalidDataNumber(3, 2, 29);
        testInvalidDataNumber(3, 3, 32);
        testInvalidDataNumber(3, 4, 31);
        testInvalidDataNumber(3, 5, 32);
        testInvalidDataNumber(3, 6, 31);
        testInvalidDataNumber(3, 7, 32);
        testInvalidDataNumber(3, 8, 32);
        testInvalidDataNumber(3, 9, 31);
        testInvalidDataNumber(3, 10, 32);
        testInvalidDataNumber(3, 11, 31);
        testInvalidDataNumber(3, 12, 32);
        testInvalidDataNumber(3, 13, 32);
        //endregion
        //region Er Year 4_31
        testInvalidDataNumber(4, 0, 32);
        testInvalidDataNumber(4, 1, 32);
        testInvalidDataNumber(4, 2, 29);
        testInvalidDataNumber(4, 3, 32);
        testInvalidDataNumber(4, 4, 31);
        testInvalidDataNumber(4, 5, 32);
        testInvalidDataNumber(4, 6, 31);
        testInvalidDataNumber(4, 7, 32);
        testInvalidDataNumber(4, 8, 32);
        testInvalidDataNumber(4, 9, 31);
        testInvalidDataNumber(4, 10, 32);
        testInvalidDataNumber(4, 11, 31);
        testInvalidDataNumber(4, 12, 32);
        testInvalidDataNumber(4, 13, 32);
        //endregion
        //region Er Year 5_31
        testInvalidDataNumber(5, 0, 32);
        testInvalidDataNumber(5, 1, 32);
        testInvalidDataNumber(5, 2, 29);
        testInvalidDataNumber(5, 3, 32);
        testInvalidDataNumber(5, 4, 31);
        testInvalidDataNumber(5, 5, 32);
        testInvalidDataNumber(5, 6, 31);
        testInvalidDataNumber(5, 7, 32);
        testInvalidDataNumber(5, 8, 32);
        testInvalidDataNumber(5, 9, 31);
        testInvalidDataNumber(5, 10, 32);
        testInvalidDataNumber(5, 11, 31);
        testInvalidDataNumber(5, 12, 32);
        testInvalidDataNumber(5, 13, 32);
        //endregion
        //region Er Year 6_31
        testInvalidDataNumber(6, 0, 32);
        testInvalidDataNumber(6, 1, 32);
        testInvalidDataNumber(6, 2, 29);
        testInvalidDataNumber(6, 3, 32);
        testInvalidDataNumber(6, 4, 31);
        testInvalidDataNumber(6, 5, 32);
        testInvalidDataNumber(6, 6, 31);
        testInvalidDataNumber(6, 7, 32);
        testInvalidDataNumber(6, 8, 32);
        testInvalidDataNumber(6, 9, 31);
        testInvalidDataNumber(6, 10, 32);
        testInvalidDataNumber(6, 11, 31);
        testInvalidDataNumber(6, 12, 32);
        testInvalidDataNumber(6, 13, 32);
        //endregion
        //region Er Year 7_31
        testInvalidDataNumber(7, 0, 32);
        testInvalidDataNumber(7, 1, 32);
        testInvalidDataNumber(7, 2, 29);
        testInvalidDataNumber(7, 3, 32);
        testInvalidDataNumber(7, 4, 31);
        testInvalidDataNumber(7, 5, 32);
        testInvalidDataNumber(7, 6, 31);
        testInvalidDataNumber(7, 7, 32);
        testInvalidDataNumber(7, 8, 32);
        testInvalidDataNumber(7, 9, 31);
        testInvalidDataNumber(7, 10, 32);
        testInvalidDataNumber(7, 11, 31);
        testInvalidDataNumber(7, 12, 32);
        testInvalidDataNumber(7, 13, 32);
        //endregion
        //region Er Year 8_31
        testInvalidDataNumber(8, 0, 32);
        testInvalidDataNumber(8, 1, 32);
        testInvalidDataNumber(8, 2, 29);
        testInvalidDataNumber(8, 3, 32);
        testInvalidDataNumber(8, 4, 31);
        testInvalidDataNumber(8, 5, 32);
        testInvalidDataNumber(8, 6, 31);
        testInvalidDataNumber(8, 7, 32);
        testInvalidDataNumber(8, 8, 32);
        testInvalidDataNumber(8, 9, 31);
        testInvalidDataNumber(8, 10, 32);
        testInvalidDataNumber(8, 11, 31);
        testInvalidDataNumber(8, 12, 32);
        testInvalidDataNumber(8, 13, 32);
        //endregion
        //region Er Year 0-8 Month 0-13 day 0
        testInvalidDataNumber(0, 0, 0);
        testInvalidDataNumber(0, 1, 0);
        testInvalidDataNumber(0, 2, 0);
        testInvalidDataNumber(0, 3, 0);
        testInvalidDataNumber(0, 4, 0);
        testInvalidDataNumber(0, 5, 0);
        testInvalidDataNumber(0, 6, 0);
        testInvalidDataNumber(0, 7, 0);
        testInvalidDataNumber(0, 8, 0);
        testInvalidDataNumber(0, 9, 0);
        testInvalidDataNumber(0, 10, 0);
        testInvalidDataNumber(0, 11, 0);
        testInvalidDataNumber(0, 12, 0);
        testInvalidDataNumber(0, 13, 0);
        testInvalidDataNumber(1, 0, 0);
        testInvalidDataNumber(1, 1, 0);
        testInvalidDataNumber(1, 2, 0);
        testInvalidDataNumber(1, 3, 0);
        testInvalidDataNumber(1, 4, 0);
        testInvalidDataNumber(1, 5, 0);
        testInvalidDataNumber(1, 6, 0);
        testInvalidDataNumber(1, 7, 0);
        testInvalidDataNumber(1, 8, 0);
        testInvalidDataNumber(1, 9, 0);
        testInvalidDataNumber(1, 10, 0);
        testInvalidDataNumber(1, 11, 0);
        testInvalidDataNumber(1, 12, 0);
        testInvalidDataNumber(1, 13, 0);
        testInvalidDataNumber(2, 0, 0);
        testInvalidDataNumber(2, 1, 0);
        testInvalidDataNumber(2, 2, 0);
        testInvalidDataNumber(2, 3, 0);
        testInvalidDataNumber(2, 4, 0);
        testInvalidDataNumber(2, 5, 0);
        testInvalidDataNumber(2, 6, 0);
        testInvalidDataNumber(2, 7, 0);
        testInvalidDataNumber(2, 8, 0);
        testInvalidDataNumber(2, 9, 0);
        testInvalidDataNumber(2, 10, 0);
        testInvalidDataNumber(2, 11, 0);
        testInvalidDataNumber(2, 12, 0);
        testInvalidDataNumber(2, 13, 0);
        testInvalidDataNumber(3, 0, 0);
        testInvalidDataNumber(3, 1, 0);
        testInvalidDataNumber(3, 2, 0);
        testInvalidDataNumber(3, 3, 0);
        testInvalidDataNumber(3, 4, 0);
        testInvalidDataNumber(3, 5, 0);
        testInvalidDataNumber(3, 6, 0);
        testInvalidDataNumber(3, 7, 0);
        testInvalidDataNumber(3, 8, 0);
        testInvalidDataNumber(3, 9, 0);
        testInvalidDataNumber(3, 10, 0);
        testInvalidDataNumber(3, 11, 0);
        testInvalidDataNumber(3, 12, 0);
        testInvalidDataNumber(3, 13, 0);
        testInvalidDataNumber(4, 0, 0);
        testInvalidDataNumber(4, 1, 0);
        testInvalidDataNumber(4, 2, 0);
        testInvalidDataNumber(4, 3, 0);
        testInvalidDataNumber(4, 4, 0);
        testInvalidDataNumber(4, 5, 0);
        testInvalidDataNumber(4, 6, 0);
        testInvalidDataNumber(4, 7, 0);
        testInvalidDataNumber(4, 8, 0);
        testInvalidDataNumber(4, 9, 0);
        testInvalidDataNumber(4, 10, 0);
        testInvalidDataNumber(4, 11, 0);
        testInvalidDataNumber(4, 12, 0);
        testInvalidDataNumber(4, 13, 0);
        testInvalidDataNumber(5, 0, 0);
        testInvalidDataNumber(5, 1, 0);
        testInvalidDataNumber(5, 2, 0);
        testInvalidDataNumber(5, 3, 0);
        testInvalidDataNumber(5, 4, 0);
        testInvalidDataNumber(5, 5, 0);
        testInvalidDataNumber(5, 6, 0);
        testInvalidDataNumber(5, 7, 0);
        testInvalidDataNumber(5, 8, 0);
        testInvalidDataNumber(5, 9, 0);
        testInvalidDataNumber(5, 10, 0);
        testInvalidDataNumber(5, 11, 0);
        testInvalidDataNumber(5, 12, 0);
        testInvalidDataNumber(5, 13, 0);
        testInvalidDataNumber(6, 0, 0);
        testInvalidDataNumber(6, 1, 0);
        testInvalidDataNumber(6, 2, 0);
        testInvalidDataNumber(6, 3, 0);
        testInvalidDataNumber(6, 4, 0);
        testInvalidDataNumber(6, 5, 0);
        testInvalidDataNumber(6, 6, 0);
        testInvalidDataNumber(6, 7, 0);
        testInvalidDataNumber(6, 8, 0);
        testInvalidDataNumber(6, 9, 0);
        testInvalidDataNumber(6, 10, 0);
        testInvalidDataNumber(6, 11, 0);
        testInvalidDataNumber(6, 12, 0);
        testInvalidDataNumber(6, 13, 0);
        testInvalidDataNumber(7, 0, 0);
        testInvalidDataNumber(7, 1, 0);
        testInvalidDataNumber(7, 2, 0);
        testInvalidDataNumber(7, 3, 0);
        testInvalidDataNumber(7, 4, 0);
        testInvalidDataNumber(7, 5, 0);
        testInvalidDataNumber(7, 6, 0);
        testInvalidDataNumber(7, 7, 0);
        testInvalidDataNumber(7, 8, 0);
        testInvalidDataNumber(7, 9, 0);
        testInvalidDataNumber(7, 10, 0);
        testInvalidDataNumber(7, 11, 0);
        testInvalidDataNumber(7, 12, 0);
        testInvalidDataNumber(7, 13, 0);
        testInvalidDataNumber(8, 0, 0);
        testInvalidDataNumber(8, 1, 0);
        testInvalidDataNumber(8, 2, 0);
        testInvalidDataNumber(8, 3, 0);
        testInvalidDataNumber(8, 4, 0);
        testInvalidDataNumber(8, 5, 0);
        testInvalidDataNumber(8, 6, 0);
        testInvalidDataNumber(8, 7, 0);
        testInvalidDataNumber(8, 8, 0);
        testInvalidDataNumber(8, 9, 0);
        testInvalidDataNumber(8, 10, 0);
        testInvalidDataNumber(8, 11, 0);
        testInvalidDataNumber(8, 12, 0);
        testInvalidDataNumber(8, 13, 0);
        //endregion
    }

    @Test
    void equivalence() throws IOException {
        //region Boundary 0_1
        testInvalidDataNumber(0, 1, 1);
        testInvalidDataNumber(0, 1, 2);
        testInvalidDataNumber(0, 1, 3);
        testInvalidDataNumber(0, 1, 4);
        testInvalidDataNumber(0, 1, 5);
        testInvalidDataNumber(0, 1, 6);
        testInvalidDataNumber(0, 1, 7);
        testInvalidDataNumber(0, 1, 8);

        testInvalidDataNumber(0, 2, 1);
        testInvalidDataNumber(0, 2, 2);
        testInvalidDataNumber(0, 2, 3);
        testInvalidDataNumber(0, 2, 4);
        testInvalidDataNumber(0, 2, 5);
        testInvalidDataNumber(0, 2, 6);
        testInvalidDataNumber(0, 2, 7);
        testInvalidDataNumber(0, 2, 8);

        testInvalidDataNumber(0, 3, 1);
        testInvalidDataNumber(0, 3, 2);
        testInvalidDataNumber(0, 3, 3);
        testInvalidDataNumber(0, 3, 4);
        testInvalidDataNumber(0, 3, 5);
        testInvalidDataNumber(0, 3, 6);
        testInvalidDataNumber(0, 3, 7);
        testInvalidDataNumber(0, 3, 8);

        testInvalidDataNumber(0, 4, 1);
        testInvalidDataNumber(0, 4, 2);
        testInvalidDataNumber(0, 4, 3);
        testInvalidDataNumber(0, 4, 4);
        testInvalidDataNumber(0, 4, 5);
        testInvalidDataNumber(0, 4, 6);
        testInvalidDataNumber(0, 4, 7);
        testInvalidDataNumber(0, 4, 8);

        testInvalidDataNumber(0, 5, 1);
        testInvalidDataNumber(0, 5, 2);
        testInvalidDataNumber(0, 5, 3);
        testInvalidDataNumber(0, 5, 4);
        testInvalidDataNumber(0, 5, 5);
        testInvalidDataNumber(0, 5, 6);
        testInvalidDataNumber(0, 5, 7);
        testInvalidDataNumber(0, 5, 8);


        testInvalidDataNumber(0, 6, 1);
        testInvalidDataNumber(0, 6, 2);
        testInvalidDataNumber(0, 6, 3);
        testInvalidDataNumber(0, 6, 4);
        testInvalidDataNumber(0, 6, 5);
        testInvalidDataNumber(0, 6, 6);
        testInvalidDataNumber(0, 6, 7);
        testInvalidDataNumber(0, 6, 8);


        testInvalidDataNumber(0, 7, 1);
        testInvalidDataNumber(0, 7, 2);
        testInvalidDataNumber(0, 7, 3);
        testInvalidDataNumber(0, 7, 4);
        testInvalidDataNumber(0, 7, 5);
        testInvalidDataNumber(0, 7, 6);
        testInvalidDataNumber(0, 7, 7);
        testInvalidDataNumber(0, 7, 8);


        testInvalidDataNumber(0, 8, 1);
        testInvalidDataNumber(0, 8, 2);
        testInvalidDataNumber(0, 8, 3);
        testInvalidDataNumber(0, 8, 4);
        testInvalidDataNumber(0, 8, 5);
        testInvalidDataNumber(0, 8, 6);
        testInvalidDataNumber(0, 8, 7);
        testInvalidDataNumber(0, 8, 8);


        testInvalidDataNumber(0, 9, 1);
        testInvalidDataNumber(0, 9, 2);
        testInvalidDataNumber(0, 9, 3);
        testInvalidDataNumber(0, 9, 4);
        testInvalidDataNumber(0, 9, 5);
        testInvalidDataNumber(0, 9, 6);
        testInvalidDataNumber(0, 9, 7);
        testInvalidDataNumber(0, 9, 8);


        testInvalidDataNumber(0, 10, 1);
        testInvalidDataNumber(0, 10, 2);
        testInvalidDataNumber(0, 10, 3);
        testInvalidDataNumber(0, 10, 4);
        testInvalidDataNumber(0, 10, 5);
        testInvalidDataNumber(0, 10, 6);
        testInvalidDataNumber(0, 10, 7);
        testInvalidDataNumber(0, 10, 8);


        testInvalidDataNumber(0, 11, 1);
        testInvalidDataNumber(0, 11, 2);
        testInvalidDataNumber(0, 11, 3);
        testInvalidDataNumber(0, 11, 4);
        testInvalidDataNumber(0, 11, 5);
        testInvalidDataNumber(0, 11, 6);
        testInvalidDataNumber(0, 11, 7);
        testInvalidDataNumber(0, 11, 8);


        testInvalidDataNumber(0, 12, 1);
        testInvalidDataNumber(0, 12, 2);
        testInvalidDataNumber(0, 12, 3);
        testInvalidDataNumber(0, 12, 4);
        testInvalidDataNumber(0, 12, 5);
        testInvalidDataNumber(0, 12, 6);
        testInvalidDataNumber(0, 12, 7);
        testInvalidDataNumber(0, 12, 8);
        //endregion
        //region Boundary 1_1
        testValidData(1, 1, 1, 1);
        testValidData(2, 1, 1, 2);
        testValidData(3, 1, 1, 3);
        testValidData(4, 1, 1, 4);
        testValidData(5, 1, 1, 5);
        testValidData(6, 1, 1, 6);
        testValidData(7, 1, 1, 7);
        testValidData(1, 1, 1, 8);

        testValidData(4, 1, 2, 1);
        testValidData(5, 1, 2, 2);
        testValidData(6, 1, 2, 3);
        testValidData(7, 1, 2, 4);
        testValidData(1, 1, 2, 5);
        testValidData(2, 1, 2, 6);
        testValidData(3, 1, 2, 7);
        testValidData(4, 1, 2, 8);

        testValidData(4, 1, 3, 1);
        testValidData(5, 1, 3, 2);
        testValidData(6, 1, 3, 3);
        testValidData(7, 1, 3, 4);
        testValidData(1, 1, 3, 5);
        testValidData(2, 1, 3, 6);
        testValidData(3, 1, 3, 7);
        testValidData(4, 1, 3, 8);

        testValidData(7, 1, 4, 1);
        testValidData(1, 1, 4, 2);
        testValidData(2, 1, 4, 3);
        testValidData(3, 1, 4, 4);
        testValidData(4, 1, 4, 5);
        testValidData(5, 1, 4, 6);
        testValidData(6, 1, 4, 7);
        testValidData(7, 1, 4, 8);

        testValidData(2, 1, 5, 1);
        testValidData(3, 1, 5, 2);
        testValidData(4, 1, 5, 3);
        testValidData(5, 1, 5, 4);
        testValidData(6, 1, 5, 5);
        testValidData(7, 1, 5, 6);
        testValidData(1, 1, 5, 7);
        testValidData(2, 1, 5, 8);


        testValidData(5, 1, 6, 1);
        testValidData(6, 1, 6, 2);
        testValidData(7, 1, 6, 3);
        testValidData(1, 1, 6, 4);
        testValidData(2, 1, 6, 5);
        testValidData(3, 1, 6, 6);
        testValidData(4, 1, 6, 7);
        testValidData(5, 1, 6, 8);


        testValidData(7, 1, 7, 1);
        testValidData(1, 1, 7, 2);
        testValidData(2, 1, 7, 3);
        testValidData(3, 1, 7, 4);
        testValidData(4, 1, 7, 5);
        testValidData(5, 1, 7, 6);
        testValidData(6, 1, 7, 7);
        testValidData(7, 1, 7, 8);


        testValidData(3, 1, 8, 1);
        testValidData(4, 1, 8, 2);
        testValidData(5, 1, 8, 3);
        testValidData(6, 1, 8, 4);
        testValidData(7, 1, 8, 5);
        testValidData(1, 1, 8, 6);
        testValidData(2, 1, 8, 7);
        testValidData(3, 1, 8, 8);


        testValidData(6, 1, 9, 1);
        testValidData(7, 1, 9, 2);
        testValidData(1, 1, 9, 3);
        testValidData(2, 1, 9, 4);
        testValidData(3, 1, 9, 5);
        testValidData(4, 1, 9, 6);
        testValidData(5, 1, 9, 7);
        testValidData(6, 1, 9, 8);


        testValidData(1, 1, 10, 1);
        testValidData(2, 1, 10, 2);
        testValidData(3, 1, 10, 3);
        testValidData(4, 1, 10, 4);
        testValidData(5, 1, 10, 5);
        testValidData(6, 1, 10, 6);
        testValidData(7, 1, 10, 7);
        testValidData(1, 1, 10, 8);


        testValidData(4, 1, 11, 1);
        testValidData(5, 1, 11, 2);
        testValidData(6, 1, 11, 3);
        testValidData(7, 1, 11, 4);
        testValidData(1, 1, 11, 5);
        testValidData(2, 1, 11, 6);
        testValidData(3, 1, 11, 7);
        testValidData(4, 1, 11, 8);


        testValidData(6, 1, 12, 1);
        testValidData(7, 1, 12, 2);
        testValidData(1, 1, 12, 3);
        testValidData(2, 1, 12, 4);
        testValidData(3, 1, 12, 5);
        testValidData(4, 1, 12, 6);
        testValidData(5, 1, 12, 7);
        testValidData(6, 1, 12, 8);
        //endregion
        //region Boundary 2_1
        testValidData(2, 2, 1, 1);
        testValidData(3, 2, 1, 2);
        testValidData(4, 2, 1, 3);
        testValidData(5, 2, 1, 4);
        testValidData(6, 2, 1, 5);
        testValidData(7, 2, 1, 6);
        testValidData(1, 2, 1, 7);
        testValidData(2, 2, 1, 8);


        testValidData(5, 2, 2, 1);
        testValidData(6, 2, 2, 2);
        testValidData(7, 2, 2, 3);
        testValidData(1, 2, 2, 4);
        testValidData(2, 2, 2, 5);
        testValidData(3, 2, 2, 6);
        testValidData(4, 2, 2, 7);
        testValidData(5, 2, 2, 8);


        testValidData(5, 2, 3, 1);
        testValidData(6, 2, 3, 2);
        testValidData(7, 2, 3, 3);
        testValidData(1, 2, 3, 4);
        testValidData(2, 2, 3, 5);
        testValidData(3, 2, 3, 6);
        testValidData(4, 2, 3, 7);
        testValidData(5, 2, 3, 8);


        testValidData(1, 2, 4, 1);
        testValidData(2, 2, 4, 2);
        testValidData(3, 2, 4, 3);
        testValidData(4, 2, 4, 4);
        testValidData(5, 2, 4, 5);
        testValidData(6, 2, 4, 6);
        testValidData(7, 2, 4, 7);
        testValidData(1, 2, 4, 8);


        testValidData(3, 2, 5, 1);
        testValidData(4, 2, 5, 2);
        testValidData(5, 2, 5, 3);
        testValidData(6, 2, 5, 4);
        testValidData(7, 2, 5, 5);
        testValidData(1, 2, 5, 6);
        testValidData(2, 2, 5, 7);
        testValidData(3, 2, 5, 8);


        testValidData(6, 2, 6, 1);
        testValidData(7, 2, 6, 2);
        testValidData(1, 2, 6, 3);
        testValidData(2, 2, 6, 4);
        testValidData(3, 2, 6, 5);
        testValidData(4, 2, 6, 6);
        testValidData(5, 2, 6, 7);
        testValidData(6, 2, 6, 8);


        testValidData(1, 2, 7, 1);
        testValidData(2, 2, 7, 2);
        testValidData(3, 2, 7, 3);
        testValidData(4, 2, 7, 4);
        testValidData(5, 2, 7, 5);
        testValidData(6, 2, 7, 6);
        testValidData(7, 2, 7, 7);
        testValidData(1, 2, 7, 8);

        testValidData(4, 2, 8, 1);
        testValidData(5, 2, 8, 2);
        testValidData(6, 2, 8, 3);
        testValidData(7, 2, 8, 4);
        testValidData(1, 2, 8, 5);
        testValidData(2, 2, 8, 6);
        testValidData(3, 2, 8, 7);
        testValidData(4, 2, 8, 8);

        testValidData(7, 2, 9, 1);
        testValidData(1, 2, 9, 2);
        testValidData(2, 2, 9, 3);
        testValidData(3, 2, 9, 4);
        testValidData(4, 2, 9, 5);
        testValidData(5, 2, 9, 6);
        testValidData(6, 2, 9, 7);
        testValidData(7, 2, 9, 8);

        testValidData(2, 2, 10, 1);
        testValidData(3, 2, 10, 2);
        testValidData(4, 2, 10, 3);
        testValidData(5, 2, 10, 4);
        testValidData(6, 2, 10, 5);
        testValidData(7, 2, 10, 6);
        testValidData(1, 2, 10, 7);
        testValidData(2, 2, 10, 8);


        testValidData(5, 2, 11, 1);
        testValidData(6, 2, 11, 2);
        testValidData(7, 2, 11, 3);
        testValidData(1, 2, 11, 4);
        testValidData(2, 2, 11, 5);
        testValidData(3, 2, 11, 6);
        testValidData(4, 2, 11, 7);
        testValidData(5, 2, 11, 8);

        testValidData(7, 2, 12, 1);
        testValidData(1, 2, 12, 2);
        testValidData(2, 2, 12, 3);
        testValidData(3, 2, 12, 4);
        testValidData(4, 2, 12, 5);
        testValidData(5, 2, 12, 6);
        testValidData(6, 2, 12, 7);
        testValidData(7, 2, 12, 8);
        //endregion
        //region Boundary 3_1
        testValidData(3, 3, 1, 1);
        testValidData(4, 3, 1, 2);
        testValidData(5, 3, 1, 3);
        testValidData(6, 3, 1, 4);
        testValidData(7, 3, 1, 5);
        testValidData(1, 3, 1, 6);
        testValidData(2, 3, 1, 7);
        testValidData(3, 3, 1, 8);


        testValidData(6, 3, 2, 1);
        testValidData(7, 3, 2, 2);
        testValidData(1, 3, 2, 3);
        testValidData(2, 3, 2, 4);
        testValidData(3, 3, 2, 5);
        testValidData(4, 3, 2, 6);
        testValidData(5, 3, 2, 7);
        testValidData(6, 3, 2, 8);


        testValidData(6, 3, 3, 1);
        testValidData(7, 3, 3, 2);
        testValidData(1, 3, 3, 3);
        testValidData(2, 3, 3, 4);
        testValidData(3, 3, 3, 5);
        testValidData(4, 3, 3, 6);
        testValidData(5, 3, 3, 7);
        testValidData(6, 3, 3, 8);


        testValidData(2, 3, 4, 1);
        testValidData(3, 3, 4, 2);
        testValidData(4, 3, 4, 3);
        testValidData(5, 3, 4, 4);
        testValidData(6, 3, 4, 5);
        testValidData(7, 3, 4, 6);
        testValidData(1, 3, 4, 7);
        testValidData(2, 3, 4, 8);


        testValidData(4, 3, 5, 1);
        testValidData(5, 3, 5, 2);
        testValidData(6, 3, 5, 3);
        testValidData(7, 3, 5, 4);
        testValidData(1, 3, 5, 5);
        testValidData(2, 3, 5, 6);
        testValidData(3, 3, 5, 7);
        testValidData(4, 3, 5, 8);


        testValidData(7, 3, 6, 1);
        testValidData(1, 3, 6, 2);
        testValidData(2, 3, 6, 3);
        testValidData(3, 3, 6, 4);
        testValidData(4, 3, 6, 5);
        testValidData(5, 3, 6, 6);
        testValidData(6, 3, 6, 7);
        testValidData(7, 3, 6, 8);


        testValidData(2, 3, 7, 1);
        testValidData(3, 3, 7, 2);
        testValidData(4, 3, 7, 3);
        testValidData(5, 3, 7, 4);
        testValidData(6, 3, 7, 5);
        testValidData(7, 3, 7, 6);
        testValidData(1, 3, 7, 7);
        testValidData(2, 3, 7, 8);

        testValidData(5, 3, 8, 1);
        testValidData(6, 3, 8, 2);
        testValidData(7, 3, 8, 3);
        testValidData(1, 3, 8, 4);
        testValidData(2, 3, 8, 5);
        testValidData(3, 3, 8, 6);
        testValidData(4, 3, 8, 7);
        testValidData(5, 3, 8, 8);

        testValidData(1, 3, 9, 1);
        testValidData(2, 3, 9, 2);
        testValidData(3, 3, 9, 3);
        testValidData(4, 3, 9, 4);
        testValidData(5, 3, 9, 5);
        testValidData(6, 3, 9, 6);
        testValidData(7, 3, 9, 7);
        testValidData(1, 3, 9, 8);

        testValidData(3, 3, 10, 1);
        testValidData(4, 3, 10, 2);
        testValidData(5, 3, 10, 3);
        testValidData(6, 3, 10, 4);
        testValidData(7, 3, 10, 5);
        testValidData(1, 3, 10, 6);
        testValidData(2, 3, 10, 7);
        testValidData(3, 3, 10, 8);


        testValidData(6, 3, 11, 1);
        testValidData(7, 3, 11, 2);
        testValidData(1, 3, 11, 3);
        testValidData(2, 3, 11, 4);
        testValidData(3, 3, 11, 5);
        testValidData(4, 3, 11, 6);
        testValidData(5, 3, 11, 7);
        testValidData(6, 3, 11, 8);

        testValidData(1, 3, 12, 1);
        testValidData(2, 3, 12, 2);
        testValidData(3, 3, 12, 3);
        testValidData(4, 3, 12, 4);
        testValidData(5, 3, 12, 5);
        testValidData(6, 3, 12, 6);
        testValidData(7, 3, 12, 7);
        testValidData(1, 3, 12, 8);
        //endregion
        //region Boundary 4_1
        testValidData(4, 4, 1, 1);
        testValidData(5, 4, 1, 2);
        testValidData(6, 4, 1, 3);
        testValidData(7, 4, 1, 4);
        testValidData(1, 4, 1, 5);
        testValidData(2, 4, 1, 6);
        testValidData(3, 4, 1, 7);
        testValidData(4, 4, 1, 8);

        testValidData(7, 4, 2, 1);
        testValidData(1, 4, 2, 2);
        testValidData(2, 4, 2, 3);
        testValidData(3, 4, 2, 4);
        testValidData(4, 4, 2, 5);
        testValidData(5, 4, 2, 6);
        testValidData(6, 4, 2, 7);
        testValidData(7, 4, 2, 8);

        testValidData(7, 4, 3, 1);
        testValidData(1, 4, 3, 2);
        testValidData(2, 4, 3, 3);
        testValidData(3, 4, 3, 4);
        testValidData(4, 4, 3, 5);
        testValidData(5, 4, 3, 6);
        testValidData(6, 4, 3, 7);
        testValidData(7, 4, 3, 8);

        testValidData(3, 4, 4, 1);
        testValidData(4, 4, 4, 2);
        testValidData(5, 4, 4, 3);
        testValidData(6, 4, 4, 4);
        testValidData(7, 4, 4, 5);
        testValidData(1, 4, 4, 6);
        testValidData(2, 4, 4, 7);
        testValidData(3, 4, 4, 8);

        testValidData(5, 4, 5, 1);
        testValidData(6, 4, 5, 2);
        testValidData(7, 4, 5, 3);
        testValidData(1, 4, 5, 4);
        testValidData(2, 4, 5, 5);
        testValidData(3, 4, 5, 6);
        testValidData(4, 4, 5, 7);
        testValidData(5, 4, 5, 8);

        testValidData(1, 4, 6, 1);
        testValidData(2, 4, 6, 2);
        testValidData(3, 4, 6, 3);
        testValidData(4, 4, 6, 4);
        testValidData(5, 4, 6, 5);
        testValidData(6, 4, 6, 6);
        testValidData(7, 4, 6, 7);
        testValidData(1, 4, 6, 8);

        testValidData(3, 4, 7, 1);
        testValidData(4, 4, 7, 2);
        testValidData(5, 4, 7, 3);
        testValidData(6, 4, 7, 4);
        testValidData(7, 4, 7, 5);
        testValidData(1, 4, 7, 6);
        testValidData(2, 4, 7, 7);
        testValidData(3, 4, 7, 8);

        testValidData(6, 4, 8, 1);
        testValidData(7, 4, 8, 2);
        testValidData(1, 4, 8, 3);
        testValidData(2, 4, 8, 4);
        testValidData(3, 4, 8, 5);
        testValidData(4, 4, 8, 6);
        testValidData(5, 4, 8, 7);
        testValidData(6, 4, 8, 8);

        testValidData(2, 4, 9, 1);
        testValidData(3, 4, 9, 2);
        testValidData(4, 4, 9, 3);
        testValidData(5, 4, 9, 4);
        testValidData(6, 4, 9, 5);
        testValidData(7, 4, 9, 6);
        testValidData(1, 4, 9, 7);
        testValidData(2, 4, 9, 8);

        testValidData(4, 4, 10, 1);
        testValidData(5, 4, 10, 2);
        testValidData(6, 4, 10, 3);
        testValidData(7, 4, 10, 4);
        testValidData(1, 4, 10, 5);
        testValidData(2, 4, 10, 6);
        testValidData(3, 4, 10, 7);
        testValidData(4, 4, 10, 8);

        testValidData(7, 4, 11, 1);
        testValidData(1, 4, 11, 2);
        testValidData(2, 4, 11, 3);
        testValidData(3, 4, 11, 4);
        testValidData(4, 4, 11, 5);
        testValidData(5, 4, 11, 6);
        testValidData(6, 4, 11, 7);
        testValidData(7, 4, 11, 8);

        testValidData(2, 4, 12, 1);
        testValidData(3, 4, 12, 2);
        testValidData(4, 4, 12, 3);
        testValidData(5, 4, 12, 4);
        testValidData(6, 4, 12, 5);
        testValidData(7, 4, 12, 6);
        testValidData(1, 4, 12, 7);
        testValidData(2, 4, 12, 8);
        //endregion
        //region Boundary 5_1
        testValidData(5, 5, 1, 1);
        testValidData(6, 5, 1, 2);
        testValidData(7, 5, 1, 3);
        testValidData(1, 5, 1, 4);
        testValidData(2, 5, 1, 5);
        testValidData(3, 5, 1, 6);
        testValidData(4, 5, 1, 7);
        testValidData(5, 5, 1, 8);

        testValidData(1, 5, 2, 1);
        testValidData(2, 5, 2, 2);
        testValidData(3, 5, 2, 3);
        testValidData(4, 5, 2, 4);
        testValidData(5, 5, 2, 5);
        testValidData(6, 5, 2, 6);
        testValidData(7, 5, 2, 7);
        testValidData(1, 5, 2, 8);

        testValidData(1, 5, 3, 1);
        testValidData(2, 5, 3, 2);
        testValidData(3, 5, 3, 3);
        testValidData(4, 5, 3, 4);
        testValidData(5, 5, 3, 5);
        testValidData(6, 5, 3, 6);
        testValidData(7, 5, 3, 7);
        testValidData(1, 5, 3, 8);

        testValidData(4, 5, 4, 1);
        testValidData(5, 5, 4, 2);
        testValidData(6, 5, 4, 3);
        testValidData(7, 5, 4, 4);
        testValidData(1, 5, 4, 5);
        testValidData(2, 5, 4, 6);
        testValidData(3, 5, 4, 7);
        testValidData(4, 5, 4, 8);

        testValidData(6, 5, 5, 1);
        testValidData(7, 5, 5, 2);
        testValidData(1, 5, 5, 3);
        testValidData(2, 5, 5, 4);
        testValidData(3, 5, 5, 5);
        testValidData(4, 5, 5, 6);
        testValidData(5, 5, 5, 7);
        testValidData(6, 5, 5, 8);

        testValidData(2, 5, 6, 1);
        testValidData(3, 5, 6, 2);
        testValidData(4, 5, 6, 3);
        testValidData(5, 5, 6, 4);
        testValidData(6, 5, 6, 5);
        testValidData(7, 5, 6, 6);
        testValidData(1, 5, 6, 7);
        testValidData(2, 5, 6, 8);

        testValidData(4, 5, 7, 1);
        testValidData(5, 5, 7, 2);
        testValidData(6, 5, 7, 3);
        testValidData(7, 5, 7, 4);
        testValidData(1, 5, 7, 5);
        testValidData(2, 5, 7, 6);
        testValidData(3, 5, 7, 7);
        testValidData(4, 5, 7, 8);

        testValidData(7, 5, 8, 1);
        testValidData(1, 5, 8, 2);
        testValidData(2, 5, 8, 3);
        testValidData(3, 5, 8, 4);
        testValidData(4, 5, 8, 5);
        testValidData(5, 5, 8, 6);
        testValidData(6, 5, 8, 7);
        testValidData(7, 5, 8, 8);

        testValidData(3, 5, 9, 1);
        testValidData(4, 5, 9, 2);
        testValidData(5, 5, 9, 3);
        testValidData(6, 5, 9, 4);
        testValidData(7, 5, 9, 5);
        testValidData(1, 5, 9, 6);
        testValidData(2, 5, 9, 7);
        testValidData(3, 5, 9, 8);

        testValidData(5, 5, 10, 1);
        testValidData(6, 5, 10, 2);
        testValidData(7, 5, 10, 3);
        testValidData(1, 5, 10, 4);
        testValidData(2, 5, 10, 5);
        testValidData(3, 5, 10, 6);
        testValidData(4, 5, 10, 7);
        testValidData(5, 5, 10, 8);

        testValidData(1, 5, 11, 1);
        testValidData(2, 5, 11, 2);
        testValidData(3, 5, 11, 3);
        testValidData(4, 5, 11, 4);
        testValidData(5, 5, 11, 5);
        testValidData(6, 5, 11, 6);
        testValidData(7, 5, 11, 7);
        testValidData(1, 5, 11, 8);

        testValidData(3, 5, 12, 1);
        testValidData(4, 5, 12, 2);
        testValidData(5, 5, 12, 3);
        testValidData(6, 5, 12, 4);
        testValidData(7, 5, 12, 5);
        testValidData(1, 5, 12, 6);
        testValidData(2, 5, 12, 7);
        testValidData(3, 5, 12, 8);
        //endregion
        //region Boundary 6_1
        testValidData(6, 6, 1, 1);
        testValidData(7, 6, 1, 2);
        testValidData(1, 6, 1, 3);
        testValidData(2, 6, 1, 4);
        testValidData(3, 6, 1, 5);
        testValidData(4, 6, 1, 6);
        testValidData(5, 6, 1, 7);
        testValidData(6, 6, 1, 8);

        testValidData(2, 6, 2, 1);
        testValidData(3, 6, 2, 2);
        testValidData(4, 6, 2, 3);
        testValidData(5, 6, 2, 4);
        testValidData(6, 6, 2, 5);
        testValidData(7, 6, 2, 6);
        testValidData(1, 6, 2, 7);
        testValidData(2, 6, 2, 8);

        testValidData(2, 6, 3, 1);
        testValidData(3, 6, 3, 2);
        testValidData(4, 6, 3, 3);
        testValidData(5, 6, 3, 4);
        testValidData(6, 6, 3, 5);
        testValidData(7, 6, 3, 6);
        testValidData(1, 6, 3, 7);
        testValidData(2, 6, 3, 8);

        testValidData(5, 6, 4, 1);
        testValidData(6, 6, 4, 2);
        testValidData(7, 6, 4, 3);
        testValidData(1, 6, 4, 4);
        testValidData(2, 6, 4, 5);
        testValidData(3, 6, 4, 6);
        testValidData(4, 6, 4, 7);
        testValidData(5, 6, 4, 8);

        testValidData(7, 6, 5, 1);
        testValidData(1, 6, 5, 2);
        testValidData(2, 6, 5, 3);
        testValidData(3, 6, 5, 4);
        testValidData(4, 6, 5, 5);
        testValidData(5, 6, 5, 6);
        testValidData(6, 6, 5, 7);
        testValidData(7, 6, 5, 8);

        testValidData(3, 6, 6, 1);
        testValidData(4, 6, 6, 2);
        testValidData(5, 6, 6, 3);
        testValidData(6, 6, 6, 4);
        testValidData(7, 6, 6, 5);
        testValidData(1, 6, 6, 6);
        testValidData(2, 6, 6, 7);
        testValidData(3, 6, 6, 8);

        testValidData(5, 6, 7, 1);
        testValidData(6, 6, 7, 2);
        testValidData(7, 6, 7, 3);
        testValidData(1, 6, 7, 4);
        testValidData(2, 6, 7, 5);
        testValidData(3, 6, 7, 6);
        testValidData(4, 6, 7, 7);
        testValidData(5, 6, 7, 8);

        testValidData(1, 6, 8, 1);
        testValidData(2, 6, 8, 2);
        testValidData(3, 6, 8, 3);
        testValidData(4, 6, 8, 4);
        testValidData(5, 6, 8, 5);
        testValidData(6, 6, 8, 6);
        testValidData(7, 6, 8, 7);
        testValidData(1, 6, 8, 8);

        testValidData(4, 6, 9, 1);
        testValidData(5, 6, 9, 2);
        testValidData(6, 6, 9, 3);
        testValidData(7, 6, 9, 4);
        testValidData(1, 6, 9, 5);
        testValidData(2, 6, 9, 6);
        testValidData(3, 6, 9, 7);
        testValidData(4, 6, 9, 8);

        testValidData(6, 6, 10, 1);
        testValidData(7, 6, 10, 2);
        testValidData(1, 6, 10, 3);
        testValidData(2, 6, 10, 4);
        testValidData(3, 6, 10, 5);
        testValidData(4, 6, 10, 6);
        testValidData(5, 6, 10, 7);
        testValidData(6, 6, 10, 8);

        testValidData(2, 6, 11, 1);
        testValidData(3, 6, 11, 2);
        testValidData(4, 6, 11, 3);
        testValidData(5, 6, 11, 4);
        testValidData(6, 6, 11, 5);
        testValidData(7, 6, 11, 6);
        testValidData(1, 6, 11, 7);
        testValidData(2, 6, 11, 8);

        testValidData(4, 6, 12, 1);
        testValidData(5, 6, 12, 2);
        testValidData(6, 6, 12, 3);
        testValidData(7, 6, 12, 4);
        testValidData(1, 6, 12, 5);
        testValidData(2, 6, 12, 6);
        testValidData(3, 6, 12, 7);
        testValidData(4, 6, 12, 8);
        //endregion
        //region Boundary 7_1
        testValidData(7, 7, 1, 1);
        testValidData(1, 7, 1, 2);
        testValidData(2, 7, 1, 3);
        testValidData(3, 7, 1, 4);
        testValidData(4, 7, 1, 5);
        testValidData(5, 7, 1, 6);
        testValidData(6, 7, 1, 7);
        testValidData(7, 7, 1, 8);

        testValidData(3, 7, 2, 1);
        testValidData(4, 7, 2, 2);
        testValidData(5, 7, 2, 3);
        testValidData(6, 7, 2, 4);
        testValidData(7, 7, 2, 5);
        testValidData(1, 7, 2, 6);
        testValidData(2, 7, 2, 7);
        testValidData(3, 7, 2, 8);

        testValidData(3, 7, 3, 1);
        testValidData(4, 7, 3, 2);
        testValidData(5, 7, 3, 3);
        testValidData(6, 7, 3, 4);
        testValidData(7, 7, 3, 5);
        testValidData(1, 7, 3, 6);
        testValidData(2, 7, 3, 7);
        testValidData(3, 7, 3, 8);

        testValidData(6, 7, 4, 1);
        testValidData(7, 7, 4, 2);
        testValidData(1, 7, 4, 3);
        testValidData(2, 7, 4, 4);
        testValidData(3, 7, 4, 5);
        testValidData(4, 7, 4, 6);
        testValidData(5, 7, 4, 7);
        testValidData(6, 7, 4, 8);

        testValidData(1, 7, 5, 1);
        testValidData(2, 7, 5, 2);
        testValidData(3, 7, 5, 3);
        testValidData(4, 7, 5, 4);
        testValidData(5, 7, 5, 5);
        testValidData(6, 7, 5, 6);
        testValidData(7, 7, 5, 7);
        testValidData(1, 7, 5, 8);

        testValidData(4, 7, 6, 1);
        testValidData(5, 7, 6, 2);
        testValidData(6, 7, 6, 3);
        testValidData(7, 7, 6, 4);
        testValidData(1, 7, 6, 5);
        testValidData(2, 7, 6, 6);
        testValidData(3, 7, 6, 7);
        testValidData(4, 7, 6, 8);

        testValidData(6, 7, 7, 1);
        testValidData(7, 7, 7, 2);
        testValidData(1, 7, 7, 3);
        testValidData(2, 7, 7, 4);
        testValidData(3, 7, 7, 5);
        testValidData(4, 7, 7, 6);
        testValidData(5, 7, 7, 7);
        testValidData(6, 7, 7, 8);

        testValidData(2, 7, 8, 1);
        testValidData(3, 7, 8, 2);
        testValidData(4, 7, 8, 3);
        testValidData(5, 7, 8, 4);
        testValidData(6, 7, 8, 5);
        testValidData(7, 7, 8, 6);
        testValidData(1, 7, 8, 7);
        testValidData(2, 7, 8, 8);

        testValidData(5, 7, 9, 1);
        testValidData(6, 7, 9, 2);
        testValidData(7, 7, 9, 3);
        testValidData(1, 7, 9, 4);
        testValidData(2, 7, 9, 5);
        testValidData(3, 7, 9, 6);
        testValidData(4, 7, 9, 7);
        testValidData(5, 7, 9, 8);

        testValidData(7, 7, 10, 1);
        testValidData(1, 7, 10, 2);
        testValidData(2, 7, 10, 3);
        testValidData(3, 7, 10, 4);
        testValidData(4, 7, 10, 5);
        testValidData(5, 7, 10, 6);
        testValidData(6, 7, 10, 7);
        testValidData(7, 7, 10, 8);

        testValidData(3, 7, 11, 1);
        testValidData(4, 7, 11, 2);
        testValidData(5, 7, 11, 3);
        testValidData(6, 7, 11, 4);
        testValidData(7, 7, 11, 5);
        testValidData(1, 7, 11, 6);
        testValidData(2, 7, 11, 7);
        testValidData(3, 7, 11, 8);

        testValidData(5, 7, 12, 1);
        testValidData(6, 7, 12, 2);
        testValidData(7, 7, 12, 3);
        testValidData(1, 7, 12, 4);
        testValidData(2, 7, 12, 5);
        testValidData(3, 7, 12, 6);
        testValidData(4, 7, 12, 7);
        testValidData(5, 7, 12, 8);
        //endregion
        //region Boundary 1_31
        testValidData(2, 1, 1, 30);
        testValidData(1, 1, 1, 29);
        testValidData(7, 1, 1, 28);
        testValidData(6, 1, 1, 27);
        testValidData(5, 1, 1, 26);
        testValidData(4, 1, 1, 25);
        testValidData(3, 1, 1, 24);
        testValidData(2, 1, 1, 23);

        testValidData(2, 1, 2, 27);
        testValidData(1, 1, 2, 26);
        testValidData(7, 1, 2, 25);
        testValidData(6, 1, 2, 24);
        testValidData(5, 1, 2, 23);
        testValidData(4, 1, 2, 22);
        testValidData(3, 1, 2, 21);
        testValidData(2, 1, 2, 20);

        testValidData(5, 1, 3, 30);
        testValidData(4, 1, 3, 29);
        testValidData(3, 1, 3, 28);
        testValidData(2, 1, 3, 27);
        testValidData(1, 1, 3, 26);
        testValidData(7, 1, 3, 25);
        testValidData(6, 1, 3, 24);
        testValidData(5, 1, 3, 23);

        testValidData(7, 1, 4, 29);
        testValidData(6, 1, 4, 28);
        testValidData(5, 1, 4, 27);
        testValidData(4, 1, 4, 26);
        testValidData(3, 1, 4, 25);
        testValidData(2, 1, 4, 24);
        testValidData(1, 1, 4, 23);
        testValidData(7, 1, 4, 22);

        testValidData(3, 1, 5, 30);
        testValidData(2, 1, 5, 29);
        testValidData(1, 1, 5, 28);
        testValidData(7, 1, 5, 27);
        testValidData(6, 1, 5, 26);
        testValidData(5, 1, 5, 25);
        testValidData(4, 1, 5, 24);
        testValidData(3, 1, 5, 23);

        testValidData(5, 1, 6, 29);
        testValidData(4, 1, 6, 28);
        testValidData(3, 1, 6, 27);
        testValidData(2, 1, 6, 26);
        testValidData(1, 1, 6, 25);
        testValidData(7, 1, 6, 24);
        testValidData(6, 1, 6, 23);
        testValidData(5, 1, 6, 22);

        testValidData(1, 1, 7, 30);
        testValidData(7, 1, 7, 29);
        testValidData(6, 1, 7, 28);
        testValidData(5, 1, 7, 27);
        testValidData(4, 1, 7, 26);
        testValidData(3, 1, 7, 25);
        testValidData(2, 1, 7, 24);
        testValidData(1, 1, 7, 23);

        testValidData(4, 1, 8, 30);
        testValidData(3, 1, 8, 29);
        testValidData(2, 1, 8, 28);
        testValidData(1, 1, 8, 27);
        testValidData(7, 1, 8, 26);
        testValidData(6, 1, 8, 25);
        testValidData(5, 1, 8, 24);
        testValidData(4, 1, 8, 23);

        testValidData(6, 1, 9, 29);
        testValidData(5, 1, 9, 28);
        testValidData(4, 1, 9, 27);
        testValidData(3, 1, 9, 26);
        testValidData(2, 1, 9, 25);
        testValidData(1, 1, 9, 24);
        testValidData(7, 1, 9, 23);
        testValidData(6, 1, 9, 22);

        testValidData(2, 1, 10, 30);
        testValidData(1, 1, 10, 29);
        testValidData(7, 1, 10, 28);
        testValidData(6, 1, 10, 27);
        testValidData(5, 1, 10, 26);
        testValidData(4, 1, 10, 25);
        testValidData(3, 1, 10, 24);
        testValidData(2, 1, 10, 23);

        testValidData(4, 1, 11, 29);
        testValidData(3, 1, 11, 28);
        testValidData(2, 1, 11, 27);
        testValidData(1, 1, 11, 26);
        testValidData(7, 1, 11, 25);
        testValidData(6, 1, 11, 24);
        testValidData(5, 1, 11, 23);
        testValidData(4, 1, 11, 22);

        testValidData(7, 1, 12, 30);
        testValidData(6, 1, 12, 29);
        testValidData(5, 1, 12, 28);
        testValidData(4, 1, 12, 27);
        testValidData(3, 1, 12, 26);
        testValidData(2, 1, 12, 25);
        testValidData(1, 1, 12, 24);
        testValidData(7, 1, 12, 23);
        //endregion
        //region Boundary 2_31
        testValidData(3, 2, 1, 30);
        testValidData(2, 2, 1, 29);
        testValidData(1, 2, 1, 28);
        testValidData(7, 2, 1, 27);
        testValidData(6, 2, 1, 26);
        testValidData(5, 2, 1, 25);
        testValidData(4, 2, 1, 24);
        testValidData(3, 2, 1, 23);

        testValidData(3, 2, 2, 27);
        testValidData(2, 2, 2, 26);
        testValidData(1, 2, 2, 25);
        testValidData(7, 2, 2, 24);
        testValidData(6, 2, 2, 23);
        testValidData(5, 2, 2, 22);
        testValidData(4, 2, 2, 21);
        testValidData(3, 2, 2, 20);

        testValidData(6, 2, 3, 30);
        testValidData(5, 2, 3, 29);
        testValidData(4, 2, 3, 28);
        testValidData(3, 2, 3, 27);
        testValidData(2, 2, 3, 26);
        testValidData(1, 2, 3, 25);
        testValidData(7, 2, 3, 24);
        testValidData(6, 2, 3, 23);

        testValidData(1, 2, 4, 29);
        testValidData(7, 2, 4, 28);
        testValidData(6, 2, 4, 27);
        testValidData(5, 2, 4, 26);
        testValidData(4, 2, 4, 25);
        testValidData(3, 2, 4, 24);
        testValidData(2, 2, 4, 23);
        testValidData(1, 2, 4, 22);

        testValidData(4, 2, 5, 30);
        testValidData(3, 2, 5, 29);
        testValidData(2, 2, 5, 28);
        testValidData(1, 2, 5, 27);
        testValidData(7, 2, 5, 26);
        testValidData(6, 2, 5, 25);
        testValidData(5, 2, 5, 24);
        testValidData(4, 2, 5, 23);

        testValidData(6, 2, 6, 29);
        testValidData(5, 2, 6, 28);
        testValidData(4, 2, 6, 27);
        testValidData(3, 2, 6, 26);
        testValidData(2, 2, 6, 25);
        testValidData(1, 2, 6, 24);
        testValidData(7, 2, 6, 23);
        testValidData(6, 2, 6, 22);

        testValidData(2, 2, 7, 30);
        testValidData(1, 2, 7, 29);
        testValidData(7, 2, 7, 28);
        testValidData(6, 2, 7, 27);
        testValidData(5, 2, 7, 26);
        testValidData(4, 2, 7, 25);
        testValidData(3, 2, 7, 24);
        testValidData(2, 2, 7, 23);

        testValidData(5, 2, 8, 30);
        testValidData(4, 2, 8, 29);
        testValidData(3, 2, 8, 28);
        testValidData(2, 2, 8, 27);
        testValidData(1, 2, 8, 26);
        testValidData(7, 2, 8, 25);
        testValidData(6, 2, 8, 24);
        testValidData(5, 2, 8, 23);

        testValidData(7, 2, 9, 29);
        testValidData(6, 2, 9, 28);
        testValidData(5, 2, 9, 27);
        testValidData(4, 2, 9, 26);
        testValidData(3, 2, 9, 25);
        testValidData(2, 2, 9, 24);
        testValidData(1, 2, 9, 23);
        testValidData(7, 2, 9, 22);

        testValidData(3, 2, 10, 30);
        testValidData(2, 2, 10, 29);
        testValidData(1, 2, 10, 28);
        testValidData(7, 2, 10, 27);
        testValidData(6, 2, 10, 26);
        testValidData(5, 2, 10, 25);
        testValidData(4, 2, 10, 24);
        testValidData(3, 2, 10, 23);

        testValidData(5, 2, 11, 29);
        testValidData(4, 2, 11, 28);
        testValidData(3, 2, 11, 27);
        testValidData(2, 2, 11, 26);
        testValidData(1, 2, 11, 25);
        testValidData(7, 2, 11, 24);
        testValidData(6, 2, 11, 23);
        testValidData(5, 2, 11, 22);

        testValidData(1, 2, 12, 30);
        testValidData(7, 2, 12, 29);
        testValidData(6, 2, 12, 28);
        testValidData(5, 2, 12, 27);
        testValidData(4, 2, 12, 26);
        testValidData(3, 2, 12, 25);
        testValidData(2, 2, 12, 24);
        testValidData(1, 2, 12, 23);
        //endregion
        //region Boundary 3_31
        testValidData(4, 3, 1, 30);
        testValidData(3, 3, 1, 29);
        testValidData(2, 3, 1, 28);
        testValidData(1, 3, 1, 27);
        testValidData(7, 3, 1, 26);
        testValidData(6, 3, 1, 25);
        testValidData(5, 3, 1, 24);
        testValidData(4, 3, 1, 23);

        testValidData(4, 3, 2, 27);
        testValidData(3, 3, 2, 26);
        testValidData(2, 3, 2, 25);
        testValidData(1, 3, 2, 24);
        testValidData(7, 3, 2, 23);
        testValidData(6, 3, 2, 22);
        testValidData(5, 3, 2, 21);
        testValidData(4, 3, 2, 20);

        testValidData(7, 3, 3, 30);
        testValidData(6, 3, 3, 29);
        testValidData(5, 3, 3, 28);
        testValidData(4, 3, 3, 27);
        testValidData(3, 3, 3, 26);
        testValidData(2, 3, 3, 25);
        testValidData(1, 3, 3, 24);
        testValidData(7, 3, 3, 23);

        testValidData(2, 3, 4, 29);
        testValidData(1, 3, 4, 28);
        testValidData(7, 3, 4, 27);
        testValidData(6, 3, 4, 26);
        testValidData(5, 3, 4, 25);
        testValidData(4, 3, 4, 24);
        testValidData(3, 3, 4, 23);
        testValidData(2, 3, 4, 22);

        testValidData(5, 3, 5, 30);
        testValidData(4, 3, 5, 29);
        testValidData(3, 3, 5, 28);
        testValidData(2, 3, 5, 27);
        testValidData(1, 3, 5, 26);
        testValidData(7, 3, 5, 25);
        testValidData(6, 3, 5, 24);
        testValidData(5, 3, 5, 23);

        testValidData(7, 3, 6, 29);
        testValidData(6, 3, 6, 28);
        testValidData(5, 3, 6, 27);
        testValidData(4, 3, 6, 26);
        testValidData(3, 3, 6, 25);
        testValidData(2, 3, 6, 24);
        testValidData(1, 3, 6, 23);
        testValidData(7, 3, 6, 22);

        testValidData(3, 3, 7, 30);
        testValidData(2, 3, 7, 29);
        testValidData(1, 3, 7, 28);
        testValidData(7, 3, 7, 27);
        testValidData(6, 3, 7, 26);
        testValidData(5, 3, 7, 25);
        testValidData(4, 3, 7, 24);
        testValidData(3, 3, 7, 23);

        testValidData(6, 3, 8, 30);
        testValidData(5, 3, 8, 29);
        testValidData(4, 3, 8, 28);
        testValidData(3, 3, 8, 27);
        testValidData(2, 3, 8, 26);
        testValidData(1, 3, 8, 25);
        testValidData(7, 3, 8, 24);
        testValidData(6, 3, 8, 23);

        testValidData(1, 3, 9, 29);
        testValidData(7, 3, 9, 28);
        testValidData(6, 3, 9, 27);
        testValidData(5, 3, 9, 26);
        testValidData(4, 3, 9, 25);
        testValidData(3, 3, 9, 24);
        testValidData(2, 3, 9, 23);
        testValidData(1, 3, 9, 22);

        testValidData(4, 3, 10, 30);
        testValidData(3, 3, 10, 29);
        testValidData(2, 3, 10, 28);
        testValidData(1, 3, 10, 27);
        testValidData(7, 3, 10, 26);
        testValidData(6, 3, 10, 25);
        testValidData(5, 3, 10, 24);
        testValidData(4, 3, 10, 23);

        testValidData(6, 3, 11, 29);
        testValidData(5, 3, 11, 28);
        testValidData(4, 3, 11, 27);
        testValidData(3, 3, 11, 26);
        testValidData(2, 3, 11, 25);
        testValidData(1, 3, 11, 24);
        testValidData(7, 3, 11, 23);
        testValidData(6, 3, 11, 22);

        testValidData(2, 3, 12, 30);
        testValidData(1, 3, 12, 29);
        testValidData(7, 3, 12, 28);
        testValidData(6, 3, 12, 27);
        testValidData(5, 3, 12, 26);
        testValidData(4, 3, 12, 25);
        testValidData(3, 3, 12, 24);
        testValidData(2, 3, 12, 23);
        //endregion-
        //region Boundary 4_31
        testValidData(5, 4, 1, 30);
        testValidData(4, 4, 1, 29);
        testValidData(3, 4, 1, 28);
        testValidData(2, 4, 1, 27);
        testValidData(1, 4, 1, 26);
        testValidData(7, 4, 1, 25);
        testValidData(6, 4, 1, 24);
        testValidData(5, 4, 1, 23);

        testValidData(5, 4, 2, 27);
        testValidData(4, 4, 2, 26);
        testValidData(3, 4, 2, 25);
        testValidData(2, 4, 2, 24);
        testValidData(1, 4, 2, 23);
        testValidData(7, 4, 2, 22);
        testValidData(6, 4, 2, 21);
        testValidData(5, 4, 2, 20);

        testValidData(1, 4, 3, 30);
        testValidData(7, 4, 3, 29);
        testValidData(6, 4, 3, 28);
        testValidData(5, 4, 3, 27);
        testValidData(4, 4, 3, 26);
        testValidData(3, 4, 3, 25);
        testValidData(2, 4, 3, 24);
        testValidData(1, 4, 3, 23);

        testValidData(3, 4, 4, 29);
        testValidData(2, 4, 4, 28);
        testValidData(1, 4, 4, 27);
        testValidData(7, 4, 4, 26);
        testValidData(6, 4, 4, 25);
        testValidData(5, 4, 4, 24);
        testValidData(4, 4, 4, 23);
        testValidData(3, 4, 4, 22);

        testValidData(6, 4, 5, 30);
        testValidData(5, 4, 5, 29);
        testValidData(4, 4, 5, 28);
        testValidData(3, 4, 5, 27);
        testValidData(2, 4, 5, 26);
        testValidData(1, 4, 5, 25);
        testValidData(7, 4, 5, 24);
        testValidData(6, 4, 5, 23);

        testValidData(1, 4, 6, 29);
        testValidData(7, 4, 6, 28);
        testValidData(6, 4, 6, 27);
        testValidData(5, 4, 6, 26);
        testValidData(4, 4, 6, 25);
        testValidData(3, 4, 6, 24);
        testValidData(2, 4, 6, 23);
        testValidData(1, 4, 6, 22);

        testValidData(4, 4, 7, 30);
        testValidData(3, 4, 7, 29);
        testValidData(2, 4, 7, 28);
        testValidData(1, 4, 7, 27);
        testValidData(7, 4, 7, 26);
        testValidData(6, 4, 7, 25);
        testValidData(5, 4, 7, 24);
        testValidData(4, 4, 7, 23);

        testValidData(7, 4, 8, 30);
        testValidData(6, 4, 8, 29);
        testValidData(5, 4, 8, 28);
        testValidData(4, 4, 8, 27);
        testValidData(3, 4, 8, 26);
        testValidData(2, 4, 8, 25);
        testValidData(1, 4, 8, 24);
        testValidData(7, 4, 8, 23);

        testValidData(2, 4, 9, 29);
        testValidData(1, 4, 9, 28);
        testValidData(7, 4, 9, 27);
        testValidData(6, 4, 9, 26);
        testValidData(5, 4, 9, 25);
        testValidData(4, 4, 9, 24);
        testValidData(3, 4, 9, 23);
        testValidData(2, 4, 9, 22);

        testValidData(5, 4, 10, 30);
        testValidData(4, 4, 10, 29);
        testValidData(3, 4, 10, 28);
        testValidData(2, 4, 10, 27);
        testValidData(1, 4, 10, 26);
        testValidData(7, 4, 10, 25);
        testValidData(6, 4, 10, 24);
        testValidData(5, 4, 10, 23);

        testValidData(7, 4, 11, 29);
        testValidData(6, 4, 11, 28);
        testValidData(5, 4, 11, 27);
        testValidData(4, 4, 11, 26);
        testValidData(3, 4, 11, 25);
        testValidData(2, 4, 11, 24);
        testValidData(1, 4, 11, 23);
        testValidData(7, 4, 11, 22);

        testValidData(3, 4, 12, 30);
        testValidData(2, 4, 12, 29);
        testValidData(1, 4, 12, 28);
        testValidData(7, 4, 12, 27);
        testValidData(6, 4, 12, 26);
        testValidData(5, 4, 12, 25);
        testValidData(4, 4, 12, 24);
        testValidData(3, 4, 12, 23);
        //endregion-
        //region Boundary 5_31
        testValidData(6, 5, 1, 30);
        testValidData(5, 5, 1, 29);
        testValidData(4, 5, 1, 28);
        testValidData(3, 5, 1, 27);
        testValidData(2, 5, 1, 26);
        testValidData(1, 5, 1, 25);
        testValidData(7, 5, 1, 24);
        testValidData(6, 5, 1, 23);

        testValidData(6, 5, 2, 27);
        testValidData(5, 5, 2, 26);
        testValidData(4, 5, 2, 25);
        testValidData(3, 5, 2, 24);
        testValidData(2, 5, 2, 23);
        testValidData(1, 5, 2, 22);
        testValidData(7, 5, 2, 21);
        testValidData(6, 5, 2, 20);

        testValidData(2, 5, 3, 30);
        testValidData(1, 5, 3, 29);
        testValidData(7, 5, 3, 28);
        testValidData(6, 5, 3, 27);
        testValidData(5, 5, 3, 26);
        testValidData(4, 5, 3, 25);
        testValidData(3, 5, 3, 24);
        testValidData(2, 5, 3, 23);

        testValidData(4, 5, 4, 29);
        testValidData(3, 5, 4, 28);
        testValidData(2, 5, 4, 27);
        testValidData(1, 5, 4, 26);
        testValidData(7, 5, 4, 25);
        testValidData(6, 5, 4, 24);
        testValidData(5, 5, 4, 23);
        testValidData(4, 5, 4, 22);

        testValidData(7, 5, 5, 30);
        testValidData(6, 5, 5, 29);
        testValidData(5, 5, 5, 28);
        testValidData(4, 5, 5, 27);
        testValidData(3, 5, 5, 26);
        testValidData(2, 5, 5, 25);
        testValidData(1, 5, 5, 24);
        testValidData(7, 5, 5, 23);

        testValidData(2, 5, 6, 29);
        testValidData(1, 5, 6, 28);
        testValidData(7, 5, 6, 27);
        testValidData(6, 5, 6, 26);
        testValidData(5, 5, 6, 25);
        testValidData(4, 5, 6, 24);
        testValidData(3, 5, 6, 23);
        testValidData(2, 5, 6, 22);

        testValidData(5, 5, 7, 30);
        testValidData(4, 5, 7, 29);
        testValidData(3, 5, 7, 28);
        testValidData(2, 5, 7, 27);
        testValidData(1, 5, 7, 26);
        testValidData(7, 5, 7, 25);
        testValidData(6, 5, 7, 24);
        testValidData(5, 5, 7, 23);

        testValidData(1, 5, 8, 30);
        testValidData(7, 5, 8, 29);
        testValidData(6, 5, 8, 28);
        testValidData(5, 5, 8, 27);
        testValidData(4, 5, 8, 26);
        testValidData(3, 5, 8, 25);
        testValidData(2, 5, 8, 24);
        testValidData(1, 5, 8, 23);

        testValidData(3, 5, 9, 29);
        testValidData(2, 5, 9, 28);
        testValidData(1, 5, 9, 27);
        testValidData(7, 5, 9, 26);
        testValidData(6, 5, 9, 25);
        testValidData(5, 5, 9, 24);
        testValidData(4, 5, 9, 23);
        testValidData(3, 5, 9, 22);

        testValidData(6, 5, 10, 30);
        testValidData(5, 5, 10, 29);
        testValidData(4, 5, 10, 28);
        testValidData(3, 5, 10, 27);
        testValidData(2, 5, 10, 26);
        testValidData(1, 5, 10, 25);
        testValidData(7, 5, 10, 24);
        testValidData(6, 5, 10, 23);

        testValidData(1, 5, 11, 29);
        testValidData(7, 5, 11, 28);
        testValidData(6, 5, 11, 27);
        testValidData(5, 5, 11, 26);
        testValidData(4, 5, 11, 25);
        testValidData(3, 5, 11, 24);
        testValidData(2, 5, 11, 23);
        testValidData(1, 5, 11, 22);

        testValidData(4, 5, 12, 30);
        testValidData(3, 5, 12, 29);
        testValidData(2, 5, 12, 28);
        testValidData(1, 5, 12, 27);
        testValidData(7, 5, 12, 26);
        testValidData(6, 5, 12, 25);
        testValidData(5, 5, 12, 24);
        testValidData(4, 5, 12, 23);
        //endregion-
        //region Boundary 6_31
        testValidData(7, 6, 1, 30);
        testValidData(6, 6, 1, 29);
        testValidData(5, 6, 1, 28);
        testValidData(4, 6, 1, 27);
        testValidData(3, 6, 1, 26);
        testValidData(2, 6, 1, 25);
        testValidData(1, 6, 1, 24);
        testValidData(7, 6, 1, 23);

        testValidData(7, 6, 2, 27);
        testValidData(6, 6, 2, 26);
        testValidData(5, 6, 2, 25);
        testValidData(4, 6, 2, 24);
        testValidData(3, 6, 2, 23);
        testValidData(2, 6, 2, 22);
        testValidData(1, 6, 2, 21);
        testValidData(7, 6, 2, 20);

        testValidData(3, 6, 3, 30);
        testValidData(2, 6, 3, 29);
        testValidData(1, 6, 3, 28);
        testValidData(7, 6, 3, 27);
        testValidData(6, 6, 3, 26);
        testValidData(5, 6, 3, 25);
        testValidData(4, 6, 3, 24);
        testValidData(3, 6, 3, 23);

        testValidData(5, 6, 4, 29);
        testValidData(4, 6, 4, 28);
        testValidData(3, 6, 4, 27);
        testValidData(2, 6, 4, 26);
        testValidData(1, 6, 4, 25);
        testValidData(7, 6, 4, 24);
        testValidData(6, 6, 4, 23);
        testValidData(5, 6, 4, 22);

        testValidData(1, 6, 5, 30);
        testValidData(7, 6, 5, 29);
        testValidData(6, 6, 5, 28);
        testValidData(5, 6, 5, 27);
        testValidData(4, 6, 5, 26);
        testValidData(3, 6, 5, 25);
        testValidData(2, 6, 5, 24);
        testValidData(1, 6, 5, 23);

        testValidData(3, 6, 6, 29);
        testValidData(2, 6, 6, 28);
        testValidData(1, 6, 6, 27);
        testValidData(7, 6, 6, 26);
        testValidData(6, 6, 6, 25);
        testValidData(5, 6, 6, 24);
        testValidData(4, 6, 6, 23);
        testValidData(3, 6, 6, 22);

        testValidData(6, 6, 7, 30);
        testValidData(5, 6, 7, 29);
        testValidData(4, 6, 7, 28);
        testValidData(3, 6, 7, 27);
        testValidData(2, 6, 7, 26);
        testValidData(1, 6, 7, 25);
        testValidData(7, 6, 7, 24);
        testValidData(6, 6, 7, 23);

        testValidData(2, 6, 8, 30);
        testValidData(1, 6, 8, 29);
        testValidData(7, 6, 8, 28);
        testValidData(6, 6, 8, 27);
        testValidData(5, 6, 8, 26);
        testValidData(4, 6, 8, 25);
        testValidData(3, 6, 8, 24);
        testValidData(2, 6, 8, 23);

        testValidData(4, 6, 9, 29);
        testValidData(3, 6, 9, 28);
        testValidData(2, 6, 9, 27);
        testValidData(1, 6, 9, 26);
        testValidData(7, 6, 9, 25);
        testValidData(6, 6, 9, 24);
        testValidData(5, 6, 9, 23);
        testValidData(4, 6, 9, 22);

        testValidData(7, 6, 10, 30);
        testValidData(6, 6, 10, 29);
        testValidData(5, 6, 10, 28);
        testValidData(4, 6, 10, 27);
        testValidData(3, 6, 10, 26);
        testValidData(2, 6, 10, 25);
        testValidData(1, 6, 10, 24);
        testValidData(7, 6, 10, 23);

        testValidData(2, 6, 11, 29);
        testValidData(1, 6, 11, 28);
        testValidData(7, 6, 11, 27);
        testValidData(6, 6, 11, 26);
        testValidData(5, 6, 11, 25);
        testValidData(4, 6, 11, 24);
        testValidData(3, 6, 11, 23);
        testValidData(2, 6, 11, 22);

        testValidData(5, 6, 12, 30);
        testValidData(4, 6, 12, 29);
        testValidData(3, 6, 12, 28);
        testValidData(2, 6, 12, 27);
        testValidData(1, 6, 12, 26);
        testValidData(7, 6, 12, 25);
        testValidData(6, 6, 12, 24);
        testValidData(5, 6, 12, 23);
        //endregion-
        //region Boundary 7_31
        testValidData(1, 7, 1, 30);
        testValidData(7, 7, 1, 29);
        testValidData(6, 7, 1, 28);
        testValidData(5, 7, 1, 27);
        testValidData(4, 7, 1, 26);
        testValidData(3, 7, 1, 25);
        testValidData(2, 7, 1, 24);
        testValidData(1, 7, 1, 23);

        testValidData(1, 7, 2, 27);
        testValidData(7, 7, 2, 26);
        testValidData(6, 7, 2, 25);
        testValidData(5, 7, 2, 24);
        testValidData(4, 7, 2, 23);
        testValidData(3, 7, 2, 22);
        testValidData(2, 7, 2, 21);
        testValidData(1, 7, 2, 20);

        testValidData(4, 7, 3, 30);
        testValidData(3, 7, 3, 29);
        testValidData(2, 7, 3, 28);
        testValidData(1, 7, 3, 27);
        testValidData(7, 7, 3, 26);
        testValidData(6, 7, 3, 25);
        testValidData(5, 7, 3, 24);
        testValidData(4, 7, 3, 23);

        testValidData(6, 7, 4, 29);
        testValidData(5, 7, 4, 28);
        testValidData(4, 7, 4, 27);
        testValidData(3, 7, 4, 26);
        testValidData(2, 7, 4, 25);
        testValidData(1, 7, 4, 24);
        testValidData(7, 7, 4, 23);
        testValidData(6, 7, 4, 22);

        testValidData(2, 7, 5, 30);
        testValidData(1, 7, 5, 29);
        testValidData(7, 7, 5, 28);
        testValidData(6, 7, 5, 27);
        testValidData(5, 7, 5, 26);
        testValidData(4, 7, 5, 25);
        testValidData(3, 7, 5, 24);
        testValidData(2, 7, 5, 23);

        testValidData(4, 7, 6, 29);
        testValidData(3, 7, 6, 28);
        testValidData(2, 7, 6, 27);
        testValidData(1, 7, 6, 26);
        testValidData(7, 7, 6, 25);
        testValidData(6, 7, 6, 24);
        testValidData(5, 7, 6, 23);
        testValidData(4, 7, 6, 22);

        testValidData(7, 7, 7, 30);
        testValidData(6, 7, 7, 29);
        testValidData(5, 7, 7, 28);
        testValidData(4, 7, 7, 27);
        testValidData(3, 7, 7, 26);
        testValidData(2, 7, 7, 25);
        testValidData(1, 7, 7, 24);
        testValidData(7, 7, 7, 23);

        testValidData(3, 7, 8, 30);
        testValidData(2, 7, 8, 29);
        testValidData(1, 7, 8, 28);
        testValidData(7, 7, 8, 27);
        testValidData(6, 7, 8, 26);
        testValidData(5, 7, 8, 25);
        testValidData(4, 7, 8, 24);
        testValidData(3, 7, 8, 23);

        testValidData(5, 7, 9, 29);
        testValidData(4, 7, 9, 28);
        testValidData(3, 7, 9, 27);
        testValidData(2, 7, 9, 26);
        testValidData(1, 7, 9, 25);
        testValidData(7, 7, 9, 24);
        testValidData(6, 7, 9, 23);
        testValidData(5, 7, 9, 22);

        testValidData(1, 7, 10, 30);
        testValidData(7, 7, 10, 29);
        testValidData(6, 7, 10, 28);
        testValidData(5, 7, 10, 27);
        testValidData(4, 7, 10, 26);
        testValidData(3, 7, 10, 25);
        testValidData(2, 7, 10, 24);
        testValidData(1, 7, 10, 23);

        testValidData(3, 7, 11, 29);
        testValidData(2, 7, 11, 28);
        testValidData(1, 7, 11, 27);
        testValidData(7, 7, 11, 26);
        testValidData(6, 7, 11, 25);
        testValidData(5, 7, 11, 24);
        testValidData(4, 7, 11, 23);
        testValidData(3, 7, 11, 22);

        testValidData(6, 7, 12, 30);
        testValidData(5, 7, 12, 29);
        testValidData(4, 7, 12, 28);
        testValidData(3, 7, 12, 27);
        testValidData(2, 7, 12, 26);
        testValidData(1, 7, 12, 25);
        testValidData(7, 7, 12, 24);
        testValidData(6, 7, 12, 23);
        //endregion-
        //region Boundary 8_31
        testInvalidDataNumber(8, 1, 30);
        testInvalidDataNumber(8, 1, 29);
        testInvalidDataNumber(8, 1, 28);
        testInvalidDataNumber(8, 1, 27);
        testInvalidDataNumber(8, 1, 26);
        testInvalidDataNumber(8, 1, 25);
        testInvalidDataNumber(8, 1, 24);
        testInvalidDataNumber(8, 1, 23);

        testInvalidDataNumber(8, 2, 27);
        testInvalidDataNumber(8, 2, 26);
        testInvalidDataNumber(8, 2, 25);
        testInvalidDataNumber(8, 2, 24);
        testInvalidDataNumber(8, 2, 23);
        testInvalidDataNumber(8, 2, 22);
        testInvalidDataNumber(8, 2, 21);
        testInvalidDataNumber(8, 2, 20);

        testInvalidDataNumber(8, 3, 30);
        testInvalidDataNumber(8, 3, 29);
        testInvalidDataNumber(8, 3, 28);
        testInvalidDataNumber(8, 3, 27);
        testInvalidDataNumber(8, 3, 26);
        testInvalidDataNumber(8, 3, 25);
        testInvalidDataNumber(8, 3, 24);
        testInvalidDataNumber(8, 3, 23);

        testInvalidDataNumber(8, 4, 29);
        testInvalidDataNumber(8, 4, 28);
        testInvalidDataNumber(8, 4, 27);
        testInvalidDataNumber(8, 4, 26);
        testInvalidDataNumber(8, 4, 25);
        testInvalidDataNumber(8, 4, 24);
        testInvalidDataNumber(8, 4, 23);
        testInvalidDataNumber(8, 4, 22);

        testInvalidDataNumber(8, 5, 30);
        testInvalidDataNumber(8, 5, 29);
        testInvalidDataNumber(8, 5, 28);
        testInvalidDataNumber(8, 5, 27);
        testInvalidDataNumber(8, 5, 26);
        testInvalidDataNumber(8, 5, 25);
        testInvalidDataNumber(8, 5, 24);
        testInvalidDataNumber(8, 5, 23);

        testInvalidDataNumber(8, 6, 29);
        testInvalidDataNumber(8, 6, 28);
        testInvalidDataNumber(8, 6, 27);
        testInvalidDataNumber(8, 6, 26);
        testInvalidDataNumber(8, 6, 25);
        testInvalidDataNumber(8, 6, 24);
        testInvalidDataNumber(8, 6, 23);
        testInvalidDataNumber(8, 6, 22);

        testInvalidDataNumber(8, 7, 30);
        testInvalidDataNumber(8, 7, 29);
        testInvalidDataNumber(8, 7, 28);
        testInvalidDataNumber(8, 7, 27);
        testInvalidDataNumber(8, 7, 26);
        testInvalidDataNumber(8, 7, 25);
        testInvalidDataNumber(8, 7, 24);
        testInvalidDataNumber(8, 7, 23);

        testInvalidDataNumber(8, 8, 30);
        testInvalidDataNumber(8, 8, 29);
        testInvalidDataNumber(8, 8, 28);
        testInvalidDataNumber(8, 8, 27);
        testInvalidDataNumber(8, 8, 26);
        testInvalidDataNumber(8, 8, 25);
        testInvalidDataNumber(8, 8, 24);
        testInvalidDataNumber(8, 8, 23);

        testInvalidDataNumber(8, 9, 29);
        testInvalidDataNumber(8, 9, 28);
        testInvalidDataNumber(8, 9, 27);
        testInvalidDataNumber(8, 9, 26);
        testInvalidDataNumber(8, 9, 25);
        testInvalidDataNumber(8, 9, 24);
        testInvalidDataNumber(8, 9, 23);
        testInvalidDataNumber(8, 9, 22);

        testInvalidDataNumber(8, 10, 30);
        testInvalidDataNumber(8, 10, 29);
        testInvalidDataNumber(8, 10, 28);
        testInvalidDataNumber(8, 10, 27);
        testInvalidDataNumber(8, 10, 26);
        testInvalidDataNumber(8, 10, 25);
        testInvalidDataNumber(8, 10, 24);
        testInvalidDataNumber(8, 10, 23);

        testInvalidDataNumber(8, 11, 29);
        testInvalidDataNumber(8, 11, 28);
        testInvalidDataNumber(8, 11, 27);
        testInvalidDataNumber(8, 11, 26);
        testInvalidDataNumber(8, 11, 25);
        testInvalidDataNumber(8, 11, 24);
        testInvalidDataNumber(8, 11, 23);
        testInvalidDataNumber(8, 11, 22);

        testInvalidDataNumber(8, 12, 30);
        testInvalidDataNumber(8, 12, 29);
        testInvalidDataNumber(8, 12, 28);
        testInvalidDataNumber(8, 12, 27);
        testInvalidDataNumber(8, 12, 26);
        testInvalidDataNumber(8, 12, 25);
        testInvalidDataNumber(8, 12, 24);
        testInvalidDataNumber(8, 12, 23);
        //endregion
        //region In class
        testValidData(7, 1, 10, 14);
        testValidData(1, 2, 10, 14);
        testValidData(2, 3, 10, 14);
        testValidData(3, 4, 10, 14);
        testValidData(4, 5, 10, 14);
        testValidData(5, 6, 10, 14);
        testValidData(6, 7, 10, 14);

        testValidData(4, 1, 10, 11);
        testValidData(5, 2, 10, 11);
        testValidData(6, 3, 10, 11);
        testValidData(7, 4, 10, 11);
        testValidData(1, 5, 10, 11);
        testValidData(2, 6, 10, 11);
        testValidData(3, 7, 10, 11);

        testValidData(6, 1, 11, 17);
        testValidData(7, 2, 11, 17);
        testValidData(1, 3, 11, 17);
        testValidData(2, 4, 11, 17);
        testValidData(3, 5, 11, 17);
        testValidData(4, 6, 11, 17);
        testValidData(5, 7, 11, 17);

        testValidData(3, 1, 8, 22);
        testValidData(4, 2, 8, 22);
        testValidData(5, 3, 8, 22);
        testValidData(6, 4, 8, 22);
        testValidData(7, 5, 8, 22);
        testValidData(1, 6, 8, 22);
        testValidData(2, 7, 8, 22);

        testValidData(5, 1, 11, 16);
        testValidData(6, 2, 11, 16);
        testValidData(7, 3, 11, 16);
        testValidData(1, 4, 11, 16);
        testValidData(2, 5, 11, 16);
        testValidData(3, 6, 11, 16);
        testValidData(4, 7, 11, 16);

        testValidData(3, 1, 7, 25);
        testValidData(4, 2, 7, 25);
        testValidData(5, 3, 7, 25);
        testValidData(6, 4, 7, 25);
        testValidData(7, 5, 7, 25);
        testValidData(1, 6, 7, 25);
        testValidData(2, 7, 7, 25);

        testValidData(4, 1, 4, 19);
        testValidData(5, 2, 4, 19);
        testValidData(6, 3, 4, 19);
        testValidData(7, 4, 4, 19);
        testValidData(1, 5, 4, 19);
        testValidData(2, 6, 4, 19);
        testValidData(3, 7, 4, 19);

        testValidData(6, 1, 2, 17);
        testValidData(7, 2, 2, 17);
        testValidData(1, 3, 2, 17);
        testValidData(2, 4, 2, 17);
        testValidData(3, 5, 2, 17);
        testValidData(4, 6, 2, 17);
        testValidData(5, 7, 2, 17);

        testValidData(3, 1, 6, 13);
        testValidData(4, 2, 6, 13);
        testValidData(5, 3, 6, 13);
        testValidData(6, 4, 6, 13);
        testValidData(7, 5, 6, 13);
        testValidData(1, 6, 6, 13);
        testValidData(2, 7, 6, 13);

        testValidData(5, 1, 11, 16);
        testValidData(6, 2, 11, 16);
        testValidData(7, 3, 11, 16);
        testValidData(1, 4, 11, 16);
        testValidData(2, 5, 11, 16);
        testValidData(3, 6, 11, 16);
        testValidData(4, 7, 11, 16);

        testValidData(4, 1, 3, 8);
        testValidData(5, 2, 3, 8);
        testValidData(6, 3, 3, 8);
        testValidData(7, 4, 3, 8);
        testValidData(1, 5, 3, 8);
        testValidData(2, 6, 3, 8);
        testValidData(3, 7, 3, 8);
        //endregion
    }

    @Test
    void special_meanings() throws IOException {
        testInvalidDataNumber(5, 80, 32);
        testInvalidDataNumber(1, 15, 20);
        testInvalidDataNumber(5, 14, 35);
        testInvalidDataNumber(12, 12, 2);
    }

    @Test
    void realization() throws IOException {
        testInvalidDataNoNumber('*', 'w', 'r');
        testInvalidDataNoNumber('#', '2', '1');
        testInvalidDataNoNumber('m', 'g', 'k');
        testInvalidDataNoNumber('h', '^', '(');
        testInvalidDataNoNumber('+', '-', '&');
    }
}