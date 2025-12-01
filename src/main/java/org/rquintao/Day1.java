package org.rquintao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class Day1 {

    private static final char RIGHT = 'R';

    private Day1() {
    }

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
                        currentNumber = abs(100 - currentNumber);
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

    public static void second() throws IOException {
        try (InputStream inputStream = Day1.class.getResourceAsStream("/input1.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int currentNumber = 50;
            int numberOnEntry;
            int passwordCount = 0;
            int currentCommand;
            while ((line = br.readLine()) != null) {
                currentCommand = Integer.parseInt(line.substring(1));
                numberOnEntry = currentNumber;
                if (RIGHT == line.charAt(0)) {
                    currentNumber += currentCommand;
                    if (currentNumber >= 100) {
                        passwordCount = getPassWordCountForRightMove(numberOnEntry, passwordCount, currentCommand);
                        currentNumber = currentNumber % 100;
                    }
                } else {
                    currentNumber -= currentCommand;
                    if (currentNumber <= 0) {
                        passwordCount = getPasswordCountForLeftMove(numberOnEntry, passwordCount, currentCommand);
                        currentNumber = 100 + (currentNumber % 100 == 0 ? -100 : currentNumber % 100);
                    }
                }
            }
            IO.println("Password Count: " + passwordCount);
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }
    }

    private static int getPasswordCountForLeftMove(int numberOnEntry, int passwordCount, int currentCommand) {
        if (numberOnEntry == 0) {
            passwordCount += currentCommand / 100;
        } else {
            passwordCount += (abs((numberOnEntry - currentCommand)) / 100) + 1;
        }
        return passwordCount;
    }

    private static int getPassWordCountForRightMove(int numberOnEntry, int passwordCount, int currentCommand) {
        if (numberOnEntry == 0) {
            passwordCount += currentCommand / 100;
        } else {
            passwordCount += (numberOnEntry + currentCommand) / 100;
        }
        return passwordCount;
    }
}
