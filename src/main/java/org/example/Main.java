package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;


class Main {
    public static void main (String[] args) throws IOException {
        File fileInput = new File("input.txt");
        File fileOutput = new File("output.txt");
        Scanner scanner = new Scanner(fileInput, StandardCharsets.UTF_8);
        String strInput = scanner.useDelimiter("\\A").next();
        List<String> expression = ExpressionParser.parse(strInput);
        PrintWriter out = new PrintWriter(fileOutput);
        out.println(ExpressionCalculator.calculate(expression));
        out.close();
    }
}