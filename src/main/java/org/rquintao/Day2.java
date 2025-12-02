package org.rquintao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Day2 {

    private static final char RIGHT = 'R';

    private Day2() {
    }

    public static void first() throws IOException {
        try (InputStream inputStream = Day2.class.getResourceAsStream("/input2.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ranges = line.split(",");
                List<Long> problemList = new ArrayList<>();
                for (int i = 0; i < ranges.length; i++) {
                    String[] rangeComponents = ranges[i].split("-");
                    long from = Long.parseLong(rangeComponents[0]);
                    long to = Long.parseLong(rangeComponents[1]);
                    for (long k = from; k <= to; k++) {
                        String numberAsString = String.valueOf(k);
                        if (numberAsString.length() % 2 == 0) {
                            int firstNumber = Integer.parseInt(numberAsString.substring(0, (numberAsString.length() / 2)));
                            int secondNumber = Integer.parseInt(numberAsString.substring(numberAsString.length() / 2));
                            if (firstNumber == secondNumber) {
                                problemList.add(k);
                            }
                        }
                    }
                }
                IO.println(problemList);
                AtomicLong sum = new AtomicLong();
                problemList.forEach(sum::addAndGet);
                IO.println(sum);
            }
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }

    }

    public static void second() throws IOException {
        try (InputStream inputStream = Day2.class.getResourceAsStream("/input2.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ranges = line.split(",");
                Set<Long> problemList = new HashSet<>();
                for (int i = 0; i < ranges.length; i++) {
                    String[] rangeComponents = ranges[i].split("-");
                    long from = Long.parseLong(rangeComponents[0]);
                    long to = Long.parseLong(rangeComponents[1]);
                    for (long k = from; k <= to; k++) {
                        List<List<String>> splitNumbers = getSplitNumbers(k);
                        for (int iter = 1; iter < splitNumbers.size(); iter++) {
                            String comparator = splitNumbers.get(iter).get(0);
                            if (splitNumbers.get(iter).stream().allMatch(number -> number.equals(comparator))) {
                                problemList.add(k);
                            }
                        }
                    }
                }
                IO.println(problemList);
                AtomicLong sum = new AtomicLong();
                problemList.forEach(sum::addAndGet);
                IO.println(sum);
            }
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }

    }

    private static List<List<String>> getSplitNumbers(long k) {
        List<List<String>> splittedNumbers = new ArrayList<>();
        String numberAsString = String.valueOf(k);
        int stringLength = numberAsString.length();
        for (int parts = 1; parts <= stringLength; parts++) {
            if (stringLength % parts != 0) continue;
            int size = stringLength / parts;

            List<String> split = new ArrayList<>();
            for (int l = 0; l < stringLength; l += size) {
                split.add(numberAsString.substring(l, l + size));
            }
            splittedNumbers.add(split);
        }
        return splittedNumbers;
    }
}
