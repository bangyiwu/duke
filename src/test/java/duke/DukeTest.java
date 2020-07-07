package duke;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testCase1() throws IOException, ParseException {
        final String testString = "event /at 2019-12-01";
        provideInput(testString);

        new Duke("java/duke/schedule").run();

        assertEquals("Aight new task for you: [E][x]   (at: Dec 1 2019) Now you got 3 task(s) waiting man", getOutput());
    }
}

