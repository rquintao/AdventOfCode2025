package org.rquintao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    private Day4() {
    }

    public static void first() throws IOException {

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        try (InputStream inputStream = Day4.class.getResourceAsStream("/input4.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int numberOfRolls = 0;
            List<List<Character>> grid = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                grid.add(line.chars()
                        .mapToObj(c -> (char) c)
                        .toList());
            }

            for (int i = 0; i < grid.size(); i++) {
                int validNeighbors = 0;
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) != '@') {
                        continue;
                    }
                    for (int[] d : directions) {
                        int nr = i + d[0];   // neighbor row
                        int nc = j + d[1];   // neighbor col

                        // Check bounds
                        if (nr >= 0 && nr < grid.size() &&
                                nc >= 0 && nc < grid.get(nr).size()) {

                            char neighbor = grid.get(nr).get(nc);
                            if (neighbor == '@') {
                                validNeighbors++;
                            }
                        }
                    }
                    if (validNeighbors < 4) {
                        numberOfRolls++;
                    }
                    validNeighbors = 0;
                }
            }

            IO.println("The result is: " + numberOfRolls);
        } catch (IOException e) {
            IO.println("Error: " + e.getMessage());
        }
    }
}
