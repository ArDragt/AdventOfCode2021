package d11;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Octopi {
    private List<Octopus> octopusList;

    Octopi() {
        this.octopusList = new ArrayList<>();
    }

    void addOctopus(Octopus octopus) {
        octopusList.add(octopus);
    }

    List<Octopus> getOctopusList() {return octopusList;}

    void incrementAll() {
        for (Octopus octopus : octopusList) {
            octopus.incrementValue();
        }
    }

    boolean checkFlashed() {
        long flashCount = octopusList.stream()
                .filter(Octopus::getFlashedState)
                .count();
        return flashCount > 0;
    }

    boolean checkFlashedNotProcessed() {
        long flashCount = octopusList.stream()
                .filter(e -> e.getFlashedState() && !e.getFlashProcessedState())
                .count();
        return flashCount > 0;
    }

    long getFlashedProcessedCount() {
        return octopusList.stream()
                .filter(Octopus::getFlashProcessedState)
                .count();
    }

    void clearFlashProcessed() {
        for (Octopus octopus : octopusList) {
            octopus.clearFlashProcessed();
        }
    }

    private Octopus getOctopus(String direction, int row, int col) {
        int rowDelta = 0;
        int colDelta = 0;

        switch (direction) {
            case "left":
                colDelta = -1;
                break;
            case "upleft":
                rowDelta = -1;
                colDelta = -1;
                break;
            case "up":
                rowDelta = -1;
                break;
            case "upright":
                rowDelta = -1;
                colDelta = 1;
                break;
            case "right":
                colDelta = 1;
                break;
            case "downright":
                rowDelta = 1;
                colDelta = 1;
                break;
            case "down":
                rowDelta = 1;
                break;
            case "downleft":
                rowDelta = 1;
                colDelta = -1;
        }
        int finalRowDelta = rowDelta;
        int finalColDelta = colDelta;
        Octopus closeOctopus = octopusList.stream()
                .filter(e -> e.row == row + finalRowDelta && e.col == col + finalColDelta)
                .findFirst()
                .orElseThrow();
        return closeOctopus;
    }

    void processFlashed() {
        List<Octopus> flashedOctopi = octopusList.stream()
                .filter(Octopus::getFlashedState)
                .toList();

        List<String> directions = List.of("left", "upleft", "up", "upright", "right", "downright", "down", "downleft");

        for (Octopus octopus : flashedOctopi) {

            // Increment value for all locations surrounding the current "flashed" location
            for (String direction : directions) {
                try {
                    Octopus adjacent = getOctopus(direction, octopus.row, octopus.col);
                    if (!adjacent.getFlashedState() && !adjacent.getFlashProcessedState()) {
                        adjacent.incrementValue();
                    }
                } catch (NoSuchElementException e) {
                }
            }
            octopus.clearFlashed();
            octopus.setFlashProcessed();
        }
    }
}
