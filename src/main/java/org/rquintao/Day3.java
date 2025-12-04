package org.rquintao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    private Day3() {
    }

    public static void first() throws IOException {
        try (InputStream inputStream = Day3.class.getResourceAsStream("/input3.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int firstNumber = 0;
            int secondNumber = 0;
            List<Integer> jolts = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    int currentNumber = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (currentNumber > firstNumber && i < line.length() - 1) {
                        firstNumber = currentNumber;
                        secondNumber = 0;
                    } else if (currentNumber > secondNumber) {
                        secondNumber = currentNumber;
                    }
                }
                jolts.add(Integer.valueOf(String.valueOf(firstNumber) + String.valueOf(secondNumber)));
                firstNumber = 0;
                secondNumber = 0;
            }
            IO.println("The result is: " + jolts.stream().reduce(0, Integer::sum));
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }
    }

    public static void second() throws IOException {
        try (InputStream inputStream = Day3.class.getResourceAsStream("/input3.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Integer[] numbers = new Integer[12];
            List<Long> jolts = new ArrayList<>();
            int stillNeedToBeFound = 11;
            int lastFoundIndex = -1;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < 12; i++) {
                    int candidate = 0;
                    for (int j = lastFoundIndex + 1; j < line.length() - stillNeedToBeFound; j++) {
                        if (Integer.parseInt(String.valueOf(line.charAt(j))) > candidate) {
                            candidate = Integer.parseInt(String.valueOf(line.charAt(j)));
                            lastFoundIndex = j;
                        }
                    }
                    numbers[i] = candidate;
                    stillNeedToBeFound--;
                }
                StringBuilder sb = new StringBuilder();
                for (Integer number : numbers) {
                    sb.append(number);
                }
                IO.println("Current jolt " + sb.toString());
                jolts.add(Long.valueOf(sb.toString()));
                stillNeedToBeFound = 11;
                lastFoundIndex = -1;
            }
            IO.println("The result is: " + jolts.stream().reduce(0L, Long::sum));
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }
    }
}
