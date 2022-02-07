package com.softserveinc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.print.DocFlavor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Task03Test {

    PrintStream originalOut;
    InputStream originalIn;

    ByteArrayOutputStream bos;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        originalIn = System.in;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    @DisplayName("Test task #03")
    @ParameterizedTest
    @CsvSource({"12,  75, 10,  9, 23, 0"})
    void test(String n1, String n2, String n3, String n4, String n5, String zero) {
        String[] numbers = {n1, n2, n3, n4, n5, zero};
        String input = String.join("\n", numbers);
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);

        // action
        Task03.main(null);

        StringBuilder arrow = new StringBuilder();
        arrow.append(">>> ".repeat(numbers.length));

        String expected = String.format(arrow.toString() +
                "Minimum number is %d", Arrays.stream(numbers).mapToInt(Integer::valueOf).takeWhile(n -> n!=0).min().getAsInt());

        // assertion
        String output = bos.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty() || output.isBlank(), "Output should not be blank or empty");
        String actual = Arrays.stream(output.split("\\n")).map(String::trim).collect(Collectors.joining("\n"));
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);

        bos = null;
    }
}
