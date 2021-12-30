package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GCDTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void setSystemOut() throws IOException {
        OUT_CONTENT.close();
        ERR_CONTENT.close();
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    @AfterEach
    public void setSystemOutDefault() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    void testValidData(String expectedResult, int number1, int number2, int number3, int number4) throws IOException {
        String data = number1 + System.lineSeparator()
                + number2 + System.lineSeparator()
                + number3 + System.lineSeparator()
                + number4 + System.lineSeparator();
        String expected = Messages.GCD_INPUT_NUMBER1 + System.lineSeparator();
        expected += Messages.GCD_INPUT_NUMBER2 + System.lineSeparator();
        expected += Messages.GCD_INPUT_NUMBER3 + System.lineSeparator();
        expected += Messages.GCD_INPUT_NUMBER4 + System.lineSeparator();
        expected += expectedResult + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            GCD.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    void testInvalidData(char number1, char number2, char number3, char number4) throws IOException {
        String expected = Messages.GCD_INPUT_NUMBER1 + System.lineSeparator();
        expected += "For input string: \"" + number1 + "\"" + System.lineSeparator();
        String data = number1 + System.lineSeparator()
                + number2 + System.lineSeparator()
                + number3 + System.lineSeparator()
                + number4 + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            GCD.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    @Test
    void Boundary_values() throws IOException {
        //region MAX MIN
        testValidData("1", Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData(String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testValidData(String.valueOf(Math.abs(Integer.MIN_VALUE)), Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE, Integer.MAX_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        testValidData("2", Integer.MAX_VALUE - 1, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        testValidData(String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        testValidData(String.valueOf(Integer.MAX_VALUE), Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        testValidData("2", Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE);
        testValidData("2", Integer.MAX_VALUE - 1, Integer.MIN_VALUE, Integer.MAX_VALUE - 1, Integer.MIN_VALUE);
        testValidData(String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE, Integer.MIN_VALUE + 1, Integer.MAX_VALUE, Integer.MIN_VALUE + 1);
        testValidData(String.valueOf(Integer.MAX_VALUE), Integer.MAX_VALUE, Integer.MIN_VALUE + 1, Integer.MAX_VALUE, Integer.MIN_VALUE + 1);
        testValidData("1", Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE, Integer.MIN_VALUE + 1);
        testValidData(String.valueOf(Integer.MAX_VALUE - 1), Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE + 1);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        //endregion
        //region Subject area
        testValidData("1", Integer.MAX_VALUE, 6, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 5, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 7, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 6, 6, 8);
        testValidData("1", Integer.MAX_VALUE, 6, 8, 8);
        testValidData("1", Integer.MAX_VALUE, 6, 7, 7);
        testValidData("1", Integer.MAX_VALUE, 6, 7, 9);
        testValidData("1", Integer.MAX_VALUE - 1, 6, 7, 8);
        testValidData("1", Integer.MIN_VALUE, 6, 7, 8);

        testValidData("1", Integer.MAX_VALUE, 4123, 54634, 2135);
        testValidData("1", Integer.MAX_VALUE, 4123, 54634, 2134);
        testValidData("1", Integer.MAX_VALUE, 4123, 54634, 2136);
        testValidData("1", Integer.MAX_VALUE - 1, 4123, 54634, 2135);
        testValidData("1", Integer.MIN_VALUE, 4123, 54634, 2135);
        testValidData("1", Integer.MAX_VALUE, 4122, 54634, 2135);
        testValidData("1", Integer.MAX_VALUE, 4124, 54634, 2135);
        testValidData("1", Integer.MAX_VALUE, 4123, 54633, 2135);
        testValidData("1", Integer.MAX_VALUE, 4123, 54635, 2135);


        testValidData("1", Integer.MAX_VALUE, 654, 124, 64345);
        testValidData("1", Integer.MAX_VALUE, 654, 124, 64344);
        testValidData("1", Integer.MAX_VALUE, 654, 124, 64346);
        testValidData("1", Integer.MAX_VALUE - 1, 654, 124, 64345);
        testValidData("1", Integer.MIN_VALUE, 654, 124, 64345);
        testValidData("1", Integer.MAX_VALUE, 653, 124, 64345);
        testValidData("1", Integer.MAX_VALUE, 655, 124, 64345);
        testValidData("1", Integer.MAX_VALUE, 654, 123, 64345);
        testValidData("1", Integer.MAX_VALUE, 654, 125, 64345);


        testValidData("1", Integer.MAX_VALUE, 8723, 455, 643532);
        testValidData("1", Integer.MAX_VALUE, 8723, 455, 643531);
        testValidData("1", Integer.MAX_VALUE, 8723, 455, 643533);
        testValidData("1", Integer.MAX_VALUE - 1, 8723, 455, 643532);
        testValidData("1", Integer.MIN_VALUE, 8723, 455, 643532);
        testValidData("1", Integer.MAX_VALUE, 8722, 455, 643532);
        testValidData("1", Integer.MAX_VALUE, 8724, 455, 643532);
        testValidData("1", Integer.MAX_VALUE, 8723, 454, 643532);
        testValidData("1", Integer.MAX_VALUE, 8723, 456, 643532);


        testValidData("1", Integer.MAX_VALUE, 8723, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 8723, 7, 9);
        testValidData("1", Integer.MAX_VALUE, 8723, 7, 7);
        testValidData("1", Integer.MIN_VALUE, 8723, 7, 8);
        testValidData("1", Integer.MAX_VALUE - 1, 8723, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 8722, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 8724, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 8723, 6, 8);
        testValidData("1", Integer.MAX_VALUE, 8723, 8, 8);

        testValidData("1", Integer.MAX_VALUE, 289713, 6435, 5123);
        testValidData("1", Integer.MAX_VALUE - 1, 289713, 6435, 5123);
        testValidData("1", Integer.MIN_VALUE, 289713, 6435, 5123);
        testValidData("1", Integer.MAX_VALUE, 289712, 6435, 5123);
        testValidData("1", Integer.MAX_VALUE, 289714, 6435, 5123);
        testValidData("1", Integer.MAX_VALUE, 289713, 6434, 5123);
        testValidData("1", Integer.MAX_VALUE, 289713, 6436, 5123);
        testValidData("1", Integer.MAX_VALUE, 289713, 6435, 5122);
        testValidData("1", Integer.MAX_VALUE, 289713, 6435, 5124);

        testValidData("1", Integer.MAX_VALUE, 8324, 6433, 897);
        testValidData("1", Integer.MIN_VALUE, 8324, 6433, 897);
        testValidData("1", Integer.MAX_VALUE - 1, 8324, 6433, 897);
        testValidData("1", Integer.MAX_VALUE, 8323, 6433, 897);
        testValidData("1", Integer.MAX_VALUE, 8325, 6433, 897);
        testValidData("1", Integer.MAX_VALUE, 8324, 6434, 897);
        testValidData("1", Integer.MAX_VALUE, 8324, 6432, 897);
        testValidData("1", Integer.MAX_VALUE, 8324, 6433, 896);
        testValidData("1", Integer.MAX_VALUE, 8324, 6433, 898);

        testValidData("1", Integer.MAX_VALUE, 643, 7, 8);
        testValidData("1", Integer.MAX_VALUE - 1, 643, 7, 8);
        testValidData("1", Integer.MIN_VALUE, 643, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 644, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 642, 7, 8);
        testValidData("1", Integer.MAX_VALUE, 643, 8, 8);
        testValidData("1", Integer.MAX_VALUE, 643, 6, 8);
        testValidData("1", Integer.MAX_VALUE, 643, 7, 7);
        testValidData("1", Integer.MAX_VALUE, 643, 7, 9);


        testValidData("1", Integer.MAX_VALUE, 940, 6342, 643);
        testValidData("1", Integer.MIN_VALUE, 940, 6342, 643);
        testValidData("1", Integer.MAX_VALUE - 1, 940, 6342, 643);
        testValidData("1", Integer.MAX_VALUE, 939, 6342, 643);
        testValidData("1", Integer.MAX_VALUE, 941, 6342, 643);
        testValidData("1", Integer.MAX_VALUE, 940, 6341, 643);
        testValidData("1", Integer.MAX_VALUE, 940, 6343, 643);
        testValidData("1", Integer.MAX_VALUE, 940, 6342, 642);
        testValidData("1", Integer.MAX_VALUE, 940, 6342, 644);
        //endregion
        //region Random
        testValidData("1", Integer.MIN_VALUE, 3123, 512, 2);
        testValidData("1", Integer.MIN_VALUE, 3123, 512, 1);
        testValidData("1", Integer.MIN_VALUE, 3123, 512, 3);
        testValidData("1", Integer.MAX_VALUE, 3123, 512, 2);
        testValidData("1", Integer.MIN_VALUE + 1, 3123, 512, 2);
        testValidData("2", Integer.MIN_VALUE, 3122, 512, 2);
        testValidData("2", Integer.MIN_VALUE, 3124, 512, 2);
        testValidData("1", Integer.MIN_VALUE, 3123, 511, 2);
        testValidData("1", Integer.MIN_VALUE, 3123, 513, 2);

        testValidData("1", Integer.MAX_VALUE, 5231, 2, 5221);
        testValidData("1", Integer.MAX_VALUE, 5231, 2, 5220);
        testValidData("1", Integer.MAX_VALUE, 5231, 2, 5222);
        testValidData("1", Integer.MAX_VALUE, 5231, 3, 5221);
        testValidData("1", Integer.MIN_VALUE + 1, 5231, 2, 5221);
        testValidData("1", Integer.MIN_VALUE, 5230, 2, 5221);
        testValidData("1", Integer.MIN_VALUE, 5232, 2, 5221);
        testValidData("1", Integer.MIN_VALUE, 5231, 1, 5221);

        testValidData("1", Integer.MIN_VALUE, 745, 321, 532);
        testValidData("1", Integer.MIN_VALUE, 745, 321, 531);
        testValidData("1", Integer.MIN_VALUE, 745, 321, 533);
        testValidData("1", Integer.MAX_VALUE, 745, 321, 532);
        testValidData("1", Integer.MIN_VALUE + 1, 745, 321, 532);
        testValidData("1", Integer.MIN_VALUE, 744, 321, 532);
        testValidData("1", Integer.MIN_VALUE, 746, 321, 532);
        testValidData("1", Integer.MIN_VALUE, 745, 320, 532);
        testValidData("1", Integer.MIN_VALUE, 745, 322, 532);

        testValidData("1", Integer.MIN_VALUE, 634, 123, 6);
        testValidData("1", Integer.MAX_VALUE, 634, 123, 6);
        testValidData("1", Integer.MIN_VALUE + 1, 634, 123, 6);
        testValidData("1", Integer.MIN_VALUE, 633, 123, 6);
        testValidData("1", Integer.MIN_VALUE, 635, 123, 6);
        testValidData("2", Integer.MIN_VALUE, 634, 122, 6);
        testValidData("2", Integer.MIN_VALUE, 634, 124, 6);
        testValidData("1", Integer.MIN_VALUE, 634, 123, 7);
        testValidData("1", Integer.MIN_VALUE, 634, 123, 5);

        testValidData("1", Integer.MIN_VALUE, 1253, 634, 42);
        testValidData("1", Integer.MAX_VALUE, 1253, 634, 42);
        testValidData("1", Integer.MIN_VALUE + 1, 1253, 634, 42);
        testValidData("2", Integer.MIN_VALUE, 1252, 634, 42);
        testValidData("2", Integer.MIN_VALUE, 1254, 634, 42);
        testValidData("1", Integer.MIN_VALUE, 1253, 633, 42);
        testValidData("1", Integer.MIN_VALUE, 1253, 635, 42);
        testValidData("1", Integer.MIN_VALUE, 1253, 634, 41);
        testValidData("1", Integer.MIN_VALUE, 1253, 634, 43);

        testValidData("1", Integer.MAX_VALUE, 854, 7546, 754);
        testValidData("1", Integer.MAX_VALUE, 854, 7546, 753);
        testValidData("1", Integer.MAX_VALUE, 854, 7546, 755);
        testValidData("2", Integer.MAX_VALUE - 1, 854, 7546, 754);
        testValidData("2", Integer.MIN_VALUE, 854, 7546, 754);
        testValidData("1", Integer.MAX_VALUE, 853, 7546, 754);
        testValidData("1", Integer.MAX_VALUE, 855, 7546, 754);
        testValidData("1", Integer.MAX_VALUE, 854, 7545, 754);
        testValidData("1", Integer.MAX_VALUE, 854, 7547, 754);

        testValidData("1", Integer.MAX_VALUE, 2356, 532, 234);
        testValidData("2", Integer.MAX_VALUE - 1, 2356, 532, 234);
        testValidData("2", Integer.MIN_VALUE, 2356, 532, 234);
        testValidData("1", Integer.MAX_VALUE, 2355, 532, 234);
        testValidData("1", Integer.MAX_VALUE, 2357, 532, 234);
        testValidData("1", Integer.MAX_VALUE, 2356, 531, 234);
        testValidData("1", Integer.MAX_VALUE, 2356, 533, 234);
        testValidData("1", Integer.MAX_VALUE, 2356, 532, 233);
        testValidData("1", Integer.MAX_VALUE, 2356, 532, 235);
        //endregion
        //region Other
        testValidData("1", Integer.MAX_VALUE, 13, 4, 21);
        testValidData("1", Integer.MAX_VALUE - 1, 13, 4, 21);
        testValidData("1", Integer.MIN_VALUE, 13, 4, 21);
        testValidData("1", Integer.MAX_VALUE, 12, 4, 21);
        testValidData("1", Integer.MAX_VALUE, 14, 4, 21);
        testValidData("1", Integer.MAX_VALUE, 13, 3, 21);
        testValidData("1", Integer.MAX_VALUE, 13, 5, 21);
        testValidData("1", Integer.MAX_VALUE, 13, 4, 22);
        testValidData("1", Integer.MAX_VALUE, 13, 4, 20);

        testValidData("1", Integer.MAX_VALUE, 13654, 666123, 121);
        testValidData("1", Integer.MAX_VALUE - 1, 13654, 666123, 121);
        testValidData("1", Integer.MIN_VALUE, 13654, 666123, 121);
        testValidData("1", Integer.MAX_VALUE, 13653, 666123, 121);
        testValidData("1", Integer.MAX_VALUE, 13655, 666123, 121);
        testValidData("1", Integer.MAX_VALUE, 13654, 666122, 121);
        testValidData("1", Integer.MAX_VALUE, 13654, 666124, 121);
        testValidData("1", Integer.MAX_VALUE, 13654, 666123, 122);
        testValidData("1", Integer.MAX_VALUE, 13654, 666123, 123);

        testValidData("1", Integer.MAX_VALUE, 353, 808, 971657391);
        testValidData("1", Integer.MAX_VALUE - 1, 353, 808, 971657391);
        testValidData("1", Integer.MIN_VALUE, 353, 808, 971657391);
        testValidData("1", Integer.MAX_VALUE, 352, 808, 971657391);
        testValidData("1", Integer.MAX_VALUE, 354, 808, 971657391);
        testValidData("1", Integer.MAX_VALUE, 353, 807, 971657391);
        testValidData("1", Integer.MAX_VALUE, 353, 809, 971657391);
        testValidData("1", Integer.MAX_VALUE, 353, 808, 971657390);
        testValidData("1", Integer.MAX_VALUE, 353, 808, 971657392);

        testValidData("1", Integer.MIN_VALUE, 973757384, 777, 13);
        testValidData("1", Integer.MAX_VALUE, 973757384, 777, 13);
        testValidData("1", Integer.MIN_VALUE + 1, 973757384, 777, 13);
        testValidData("1", Integer.MIN_VALUE, 973757383, 777, 13);
        testValidData("1", Integer.MIN_VALUE, 973757385, 777, 13);
        testValidData("1", Integer.MIN_VALUE, 973757384, 776, 13);
        testValidData("1", Integer.MIN_VALUE, 973757384, 778, 13);
        testValidData("1", Integer.MIN_VALUE, 973757384, 777, 12);
        testValidData("1", Integer.MIN_VALUE, 973757384, 777, 14);

        testValidData("4", Integer.MIN_VALUE, 4, 897384, 26532);
        testValidData("1", Integer.MAX_VALUE, 4, 897384, 26532);
        testValidData("1", Integer.MIN_VALUE + 1, 4, 897384, 26532);
        testValidData("1", Integer.MIN_VALUE, 3, 897384, 26532);
        testValidData("1", Integer.MIN_VALUE, 5, 897384, 26532);
        testValidData("1", Integer.MIN_VALUE, 4, 897383, 26532);
        testValidData("1", Integer.MIN_VALUE, 4, 897385, 26532);
        testValidData("1", Integer.MIN_VALUE, 4, 897384, 26531);
        testValidData("1", Integer.MIN_VALUE, 4, 897384, 26533);
        //endregion
    }

    @Test
    void Equivalence_classes() throws IOException {

        testValidData("3", 15, 18, 21, 24);
        testValidData("3", Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 3);
        testValidData("1", Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 3);
        testValidData("1", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE - 1, 3);
        testValidData("1", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3);
        testValidData("1", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2);
        testValidData("1", Integer.MAX_VALUE - 2, Integer.MAX_VALUE, Integer.MAX_VALUE, 2);
        testValidData("1", Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 2, Integer.MAX_VALUE, 2);
        testValidData("1", Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 2, 2);

        testValidData("3", Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, -3);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, -3);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, -3);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, -3);
        testValidData("2", Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, -2);
        testValidData("1", Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, -1);
        testValidData("1", Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 3, -3);


        testValidData("4", 8, 32, 16, 4);
        testValidData("4", Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 4);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE, Integer.MIN_VALUE);
        testValidData("1", Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE);

        testValidData("4", Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3, 4);
        testValidData("1", Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3);
        testValidData("1", Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3);
        testValidData("1", Integer.MAX_VALUE - 4, Integer.MAX_VALUE - 4, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3);
        testValidData("4", 48, 52, 56, 60);

        testValidData("6324123", 2023719360, 2023719360, 2023719360, 6324123);
        testValidData("1", 2023719359, 2023719360, 2023719360, 6324123);
        testValidData("1", 2023719359, 2023719359, 2023719360, 6324123);
        testValidData("1", 2023719359, 2023719359, 2023719359, 6324123);
        testValidData("1", 2023719361, 2023719361, 2023719360, 6324123);
        testValidData("1", 2023719361, 2023719360, 2023719360, 6324123);
        testValidData("1", 2023719361, 2023719361, 2023719361, 6324123);
        testValidData("1", 2023719361, 2023719361, 2023719361, 6324122);
        testValidData("1", 2023719361, 2023719361, 2023719361, 6324121);

        testValidData("6324123", -2023719360, -2023719360, -2023719360, -6324123);
        testValidData("1", -2023719359, -2023719360, -2023719360, -6324123);
        testValidData("1", -2023719359, -2023719359, -2023719360, -6324123);
        testValidData("1", -2023719359, -2023719359, -2023719359, -6324123);
        testValidData("1", -2023719361, -2023719361, -2023719360, -6324123);
        testValidData("1", -2023719361, -2023719360, -2023719360, -6324123);
        testValidData("1", -2023719361, -2023719361, -2023719361, -6324123);
        testValidData("1", -2023719361, -2023719361, -2023719361, -6324122);
        testValidData("1", -2023719361, -2023719361, -2023719361, -6324121);

        testValidData("743224123", 1486448246, 1486448246, 1486448246, 743224123);
        testValidData("1", 1486448245, 1486448246, 1486448246, 743224123);
        testValidData("1", 1486448247, 1486448246, 1486448246, 743224123);
        testValidData("1", 1486448247, 1486448245, 1486448246, 743224123);
        testValidData("1", 1486448245, 1486448247, 1486448246, 743224123);
        testValidData("1", 1486448247, 1486448245, 1486448245, 743224122);
        testValidData("1", 1486448245, 1486448247, 1486448247, 743224121);

        testValidData("743224123", -1486448246, -1486448246, -1486448246, -743224123);
        testValidData("1", -1486448245, -1486448246, -1486448246, -743224123);
        testValidData("1", -1486448247, -1486448246, -1486448246, -743224123);
        testValidData("1", -1486448247, -1486448245, -1486448246, -743224123);
        testValidData("1", -1486448245, -1486448247, -1486448246, -743224123);
        testValidData("1", -1486448247, -1486448245, -1486448245, -743224122);
        testValidData("1", -1486448245, -1486448247, -1486448247, -743224121);


    }

    @Test
    void Subject_space() throws IOException {
        testValidData("1", 745, 6532, 3, 4);
        testValidData("1", 63, 125, 3, 2);
        testValidData("2", 2, 1234, 6, 8);
        testValidData("1", 1, 162, 10, 15);
        testValidData("2", 8, 1326, 64, 16);
        testValidData("1", 6, 1643, 2, 1);
        testValidData("1", 6743, 1235, 54632, 74);
        testValidData("1", 1276, 7634, 873, 523);
        testValidData("1", 6432, 1235, 15, 865);
        testValidData("1", 7344, 845, 8213, 324);
        testValidData("1", 83213, 6232, 9873, 8237);
        testValidData("1", 754, 12365, 54213, 7344);
        testValidData("1", 123, 743, 7452, 8452);
        testValidData("1", 546, 7693, 13743, 78421);
        testValidData("1", 5861239, 123124213, 2, 65234);
        testValidData("1", 856724323, 1232145, -53274, 63232);
        testValidData("1", -623443, 743, -98736, 12211);
    }

    @Test
    void realization() throws IOException {
        testValidData("6324123", 6324123, 6324123, 6324123, 6324123);
        testValidData("743224123", 743224123, 743224123, 743224123, 743224123);
        testValidData("2", 2, 2, 2, 2);
        testValidData("364", 364, 364, 364, 364);
        testValidData("123", 123, 123, 123, 123);
        testValidData("12", 12, 12, 12, 12);
        testInvalidData('s', '2', '2', '%');
        testInvalidData('y', '#', 'h', '@');
        testInvalidData('t', '9', '$', '6');
    }

    @Test
    void Random() throws IOException {
        testValidData("1", -2041423744, 1304389989, -1253129982, -305865243);
        testValidData("1", -1405823479, -1971120108, -214672306, -173783696);
        testValidData("1", 1922404929, 874306663, 174492012, 1934110992);
        testValidData("1", 691381486, 1474615224, 502320174, -385035079);
        testValidData("1", -1436037794, -639528428, -1042957849, 168368413);
        testValidData("1", -967608310, 1047686912, 331745637, -736199572);
        testValidData("1", -1575604063, -1242174656, 632933201, -1188986692);
        testValidData("1", -751164780, 1949533004, -2136521775, 1486854838);
        testValidData("1", 1740626321, -158348700, 1224518815, -1000688105);
        testValidData("1", -1846406748, -203267777, 215838288, -2116207951);
        testValidData("1", 804255791, -1710847936, -1842826849, -1852994784);
        testValidData("1", -1356074937, -578451951, -24116817, 456634063);
        testValidData("1", 276309383, -1997509157, 827650044, -919039503);
        testValidData("2", 1472033598, -1357835124, -1962096154, -1574182388);
        testValidData("1", -418887351, 475020747, -943072576, 1365598126);
        testValidData("3", 695301333, -251501751, 1720318590, 2092230921);
        testValidData("2", -77284166, -1618588720, -508408978, -1371070918);
        testValidData("1", 2085549272, 968965969, 820514780, -1095567105);
        testValidData("2", -1965159534, -908103316, -1431831958, -1323154708);
        testValidData("1", 772851385, -2064012501, 1612308204, -651762214);
        testValidData("1", 1407197640, -1420855956, -1078646203, -1134999095);
        testValidData("1", 1932426512, 1611749593, 212040388, -1818585934);
        testValidData("1", -410089234, -576597552, 514542634, -449178835);
        testValidData("2", -598861484, -1535451232, 1749268546, 1605997550);
        testValidData("1", -218506880, 939039584, 1685684310, -1961474933);
        testValidData("1", -571892446, 1901838988, -293627972, 1208064993);
        testValidData("1", 1539583347, 618858573, -1095044094, -512057828);
        testValidData("1", -31269123, 18021234, 1829198946, -2038372279);
        testValidData("1", -1301942487, -534342628, -1807409433, 1763872595);
    }

    @Test
    void Other() throws IOException {
        testValidData("1", 13, 8, 21, 666);
        testValidData("1", 9, 18, 21, 23);
        testValidData("1", 13, 26, 29, 31);
        testValidData("1", 19, 38, 41, 43);
        testValidData("1", 17, 34, 37, 39);
        testValidData("1", 11, 22, 25, 27);
        testValidData("1", 20, 40, 43, 45);
        testValidData("1", 21, 42, 45, 47);
        testValidData("1", 23, 46, 49, 51);
        testValidData("1", 13, 777, 21, 4);
        testValidData("1", 29, 58, 61, 63);
        testValidData("1", 24, 48, 51, 53);
        testValidData("1", 5, 10, 13, 15);
        testValidData("1", 2, 4, 7, 9);
        testValidData("1", 15, 30, 33, 35);
        testValidData("1", 13, 4, 21, 8);
        testValidData("1", 25, 50, 53, 55);
        testValidData("1", 12, 24, 27, 29);
        testValidData("1", 3, 6, 9, 11);
        testValidData("1", 16, 32, 35, 37);
        testValidData("1", 4, 8, 11, 13);
        testValidData("1", 27, 54, 57, 59);
        testValidData("1", 7, 14, 17, 19);
        testValidData("1", 6, 12, 15, 17);
        testValidData("1", 14, 28, 31, 33);
        testValidData("1", 26, 52, 55, 57);
        testValidData("1", 10, 20, 23, 25);
        testValidData("1", 1, 2, 5, 7);
        testValidData("1", 8, 16, 19, 21);
        testValidData("1", 22, 44, 47, 49);
        testValidData("1", 28, 56, 59, 61);
        testValidData("1", 18, 36, 39, 41);
    }

}