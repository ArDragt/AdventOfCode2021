package d13;

import com.ad.adventofcode2021.InputDataReader;

import java.util.List;

public class D13P2 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d13input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        Grid grid = new Grid();

        // Populate initial grid with dots
        for (String row : inputData) {
            String[] rowParts = row.split(",");
            Dot dot = new Dot(Integer.parseInt(rowParts[0]), Integer.parseInt(rowParts[1]));
            grid.addDot(dot);
        }

        // Fold along the axes according to fold instructions
        for (FoldInstruction fi : FoldInstruction.values()) {
            Grid foldedGrid = new Grid();
            for (Dot dot : grid.dotList) {
                Dot foldedDot;
                if (fi.axis == 'x') {
                    try {
                        foldedDot = dot.foldX(fi.position);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                } else {
                    try {
                        foldedDot = dot.foldY(fi.position);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
                if (foldedGrid.dotList.stream()
                        .noneMatch(e -> e.x == foldedDot.x && e.y == foldedDot.y)) {
                    foldedGrid.addDot(foldedDot);
                }
            }
            grid = foldedGrid;
            grid.printDots();
            System.out.println("\n");
            System.out.println("\n");
        }
    }
}
