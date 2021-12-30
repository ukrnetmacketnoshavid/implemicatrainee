package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwapTest {
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();

    @BeforeAll
    static void systemSetOut() throws IOException {
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

    void testValidData(int number1, int number2) throws IOException {
        String data = number1 + System.lineSeparator()
                + number2 + System.lineSeparator();
        String expected = Messages.SWAP_INPUT_NUMBER1 + System.lineSeparator();
        expected += Messages.SWAP_INPUT_NUMBER2 + System.lineSeparator();
        expected += number2 + " " + number1 + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Swap.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    void testInvalidData(char number1, char number2) throws IOException {
        String expected = Messages.SWAP_INPUT_NUMBER1 + System.lineSeparator();
        expected += Messages.ALL_MESSAGE_ERROR + System.lineSeparator();
        String data = number1 + System.lineSeparator()
                + number2 + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Swap.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    @Test
    void Boundary_values() throws IOException {
        testValidData(2147483647, 2147483647);
        testValidData(2147483646, 2147483646);
        testValidData(2147483645, 2147483645);
        testValidData(2147483647, 0);
        testValidData(2147483646, 0);
        testValidData(2147483645, 0);
    }

    @Test
    void Equivalence_classes() throws IOException {
        testValidData(5000, 8000);
        testValidData(10000, 40000);
        testValidData(124214213, 12421643);
        testValidData(845624324, 85462434);
        testValidData(623423, 6573);
        testValidData(213, 663451324);
        testValidData(236734, 13213);
        testValidData(134326, 65735132);
        testValidData(23436, 856835);
        testValidData(65725, 1234235);
        testValidData(5235416, 3254327);
        testValidData(324326, 6575763);
        testValidData(5634512, 645874562);
        testValidData(212356, 7654632);
        testValidData(32563425, 12321421);
        testValidData(6735632, 75672432);
        testValidData(346437, 234326436);
        testValidData(5475745, 78462);
        testValidData(124214, 534634);
        testValidData(10000, 76978);
        testValidData(3241231, 547568);
        testValidData(23423, 45745);
        testValidData(1321, 43643);
        testValidData(54724, 123123);
        testValidData(9656132, 23455647);
        testValidData(85623452, 657241);
        testValidData(32462, 673456234);
    }

    @Test
    void Subject_space() throws IOException {
        testValidData(1, 5);
        testValidData(8, 12);
        testValidData(15, 19);
        testValidData(22, 26);
        testValidData(29, 33);
        testValidData(36, 40);
        testValidData(43, 47);
        testValidData(50, 54);
        testValidData(57, 61);
        testValidData(64, 68);
        testValidData(71, 75);
        testValidData(78, 82);
        testValidData(85, 89);
        testValidData(92, 96);
        testValidData(99, 103);
        testValidData(106, 110);
        testValidData(113, 117);
        testValidData(120, 124);
        testValidData(127, 131);
        testValidData(134, 138);
        testValidData(141, 145);
        testValidData(148, 152);
        testValidData(155, 159);
        testValidData(162, 166);
        testValidData(169, 173);
        testValidData(176, 180);
        testValidData(183, 187);
        testValidData(190, 194);
        testValidData(197, 201);
        testValidData(204, 208);
        testValidData(211, 215);
        testValidData(218, 222);
        testValidData(225, 229);
        testValidData(232, 236);
        testValidData(239, 243);
        testValidData(246, 250);
        testValidData(253, 257);
        testValidData(260, 264);
        testValidData(267, 271);
        testValidData(274, 278);
        testValidData(281, 285);
        testValidData(288, 292);
        testValidData(295, 299);
        testValidData(302, 306);
        testValidData(309, 313);
        testValidData(316, 320);
        testValidData(323, 327);
        testValidData(330, 334);
        testValidData(337, 341);
        testValidData(344, 348);
        testValidData(351, 355);
        testValidData(358, 362);
        testValidData(365, 369);
        testValidData(372, 376);
        testValidData(379, 383);
        testValidData(386, 390);
        testValidData(393, 397);
        testValidData(400, 404);
        testValidData(407, 411);
        testValidData(414, 418);
        testValidData(421, 425);
        testValidData(428, 432);
        testValidData(435, 439);
        testValidData(442, 446);
        testValidData(449, 453);
        testValidData(456, 460);
        testValidData(463, 467);
        testValidData(470, 474);
        testValidData(477, 481);
        testValidData(484, 488);
        testValidData(491, 495);
        testValidData(498, 502);
        testValidData(505, 509);
        testValidData(512, 516);
        testValidData(519, 523);
        testValidData(526, 530);
        testValidData(533, 537);
        testValidData(540, 544);
        testValidData(547, 551);
        testValidData(554, 558);
    }

    @Test
    void Random_arg() throws IOException {
        testValidData(1031680, 1148902719);
        testValidData(493427200, 590064577);
        testValidData(620787200, 483804607);
        testValidData(2065101824, 670097281);
        testValidData(972326400, 374837311);
        testValidData(1696551936, 1489902719);
        testValidData(1943734272, 1528804607);
        testValidData(590512128, 559902719);
    }

    @Test
    void realization() throws IOException {
        testInvalidData('%', 's');
        testInvalidData('l', '0');
    }

    @Test
    void Other_special() throws IOException {
        testValidData(3, 5);
        testValidData(7, 2);
        testValidData(11, 17);
        testValidData(17, 23);
        testValidData(29, 31);
        testValidData(31, 7);
        testValidData(2, 5);
        testValidData(7, 11);
        testValidData(13, 11);
        testValidData(23, 29);
        testValidData(1, 1);
        testValidData(2, 1);
        testValidData(3, 2);
        testValidData(5, 3);
        testValidData(8, 5);
        testValidData(13, 8);
        testValidData(21, 13);
        testValidData(34, 21);
        testValidData(55, 34);
        testValidData(89, 55);
        testValidData(13, 21);
        testValidData(4, 13);
        testValidData(666, 777);
        testValidData(21, 666);
        testValidData(13, 777);
        testValidData(666, 4);
    }
}

