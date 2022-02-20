package d13;

import com.ad.adventofcode2021.InputDataReader;

import java.util.List;

public class D13P1 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d13input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        Grid grid = new Grid();
        Grid foldedGrid = new Grid();

        for (String row : inputData) {
            String[] rowParts = row.split(",");
            Dot dot = new Dot(Integer.parseInt(rowParts[0]), Integer.parseInt(rowParts[1]));
            grid.addDot(dot);
        }

        // Fold along an X axis
        int xFoldValue = 655;

        for (Dot dot : grid.dotList) {
            Dot foldedDot = dot.foldX(xFoldValue);
            if (foldedGrid.dotList.stream()
                    .noneMatch(e -> e.x == foldedDot.x && e.y == foldedDot.y)) {
                foldedGrid.addDot(foldedDot);
            }
        }

        System.out.println(foldedGrid.countDots());
    }
}
