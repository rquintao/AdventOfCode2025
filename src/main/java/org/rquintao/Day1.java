package org.rquintao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day1 {

    private static final char RIGHT = 'R';

    public static void first() throws IOException {
        try (InputStream inputStream = Day1.class.getResourceAsStream("/input1.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int currentNumber = 50;
            int passwordCount = 0;
            int currentCommand;
            while ((line = br.readLine()) != null) {
                currentCommand = Integer.parseInt(line.substring(1));
                currentCommand %= 100;
                if (RIGHT == line.charAt(0)) {
                    currentNumber += currentCommand;
                    if (currentNumber >= 100) {
                        currentNumber = Math.abs(100 - currentNumber);
                    }
                } else {
                    currentNumber -= currentCommand;
                    if (currentNumber < 0) {
                        currentNumber = 100 + currentNumber;
                    }
                }

                if (currentNumber == 0) {
                    passwordCount++;
                }
            }
            IO.println("Password Count: " + passwordCount);
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }

    }
}
