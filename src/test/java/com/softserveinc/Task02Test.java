package com.softserveinc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Task02Test {

    @DisplayName("Test task #02")
    @ParameterizedTest
    @CsvSource({"0, 10, 1"})
    void test(double t1, double t2, double dt) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String args = String.format("%g\n%g\n%g\n", t1, t2, dt);
        ByteArrayInputStream bis = new ByteArrayInputStream(args.getBytes());
        System.setOut(new PrintStream(bos));
        System.setIn(bis);

        // action
        Task02.main(null);

        String expected = "t1 >>> " +
                "t2 >>> " +
                "dt >>> " +
                "-----------------\n" +
                "C       F\n" +
                "-----------------\n" +
                "0.00    32.00\n" +
                "1.00    33.80\n" +
                "2.00    35.60\n" +
                "3.00    37.40\n" +
                "4.00    39.20\n" +
                "5.00    41.00\n" +
                "6.00    42.80\n" +
                "7.00    44.60\n" +
                "8.00    46.40\n" +
                "9.00    48.20\n" +
                "10.00    50.00\n" +
                "-----------------";

        // assertion
        String output = bos.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty() || output.isBlank(), "Output should not be blank or empty");
        String actual = Arrays.stream(output.split("\\n")).map(String::trim).collect(Collectors.joining("\n"));
        assertEquals(expected, actual);

        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
