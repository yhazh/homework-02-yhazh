package com.softserveinc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Task01Test {

    @DisplayName("Test task #01")
    @Test 
    void test() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Task01.main(null);

        String expected = "Average annual temperature: 10.125\n" +
                "Month with the lowest temperature in a year: I\n" +
                "The month with the highest temperatures in a year: VII";

        // assertion
        String output = bos.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty() || output.isBlank(), "Output should not be blank or empty");
        String actual = Arrays.stream(output.split("\\n")).map(String::trim).collect(Collectors.joining("\n"));
        assertEquals(expected, actual);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
