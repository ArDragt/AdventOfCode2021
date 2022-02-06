package d9;

import com.ad.adventofcode2021.InputDataReader;

import java.util.ArrayList;
import java.util.List;

public class D9P2 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d9input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        LowPointAnalyzer analyzer = new LowPointAnalyzer();
        for (int i = 0; i < inputData.size(); i++) {
            HeightMapData rowData = new HeightMapData(i, inputData.get(i));
            analyzer.addHeightData(rowData);
        }
        // Set max row and column values in the analyzer
        analyzer.setMaxRow();
        analyzer.setMaxCol();

        // Start by finding low points
        analyzer.findLowPoints();

        // Add each low point to a basin and all of those to an aggregator
        BasinAggregator basinAggregator = new BasinAggregator();
        List<HeightPoint> basinNewPoints = new ArrayList<>();

        for (HeightPoint lowPoint : analyzer.getLowPoints()) {
            Basin basin = new Basin();
            basin.addToNeedsEval(lowPoint);
            basinAggregator.addBasin(basin);
        }

        // Now work on filling in each basin with other height points
        for (Basin basin : basinAggregator.basins) {
            boolean basinComplete = false;
            while (!basinComplete) {
                for (int i = 0; i < basin.needsEval.size(); i++) {
                    HeightPoint heightPoint = basin.getNeedsEvalPoint(i);
                    HeightMapData thisRow = analyzer.getHeightPointData(heightPoint.row);

                    // Check value to left
                    boolean checkLeft = heightPoint.checkLeft(thisRow);
                    if (checkLeft) {
                        int pointValue = analyzer.getPointValue(heightPoint.row, heightPoint.column - 1);
                        HeightPoint newHeightPoint = new HeightPoint(heightPoint.row, heightPoint.column - 1,
                                pointValue);
                        basinNewPoints.add(newHeightPoint);
                    }

                    // Check value to right
                    boolean checkRight = heightPoint.checkRight(thisRow, analyzer.maxCol);
                    if (checkRight) {
                        int pointValue = analyzer.getPointValue(heightPoint.row, heightPoint.column + 1);
                        HeightPoint newHeightPoint = new HeightPoint(heightPoint.row, heightPoint.column + 1,
                                pointValue);
                        basinNewPoints.add(newHeightPoint);
                    }

                    // Check value above
                    boolean checkUp = heightPoint.checkUp(analyzer);
                    if (checkUp) {
                        int pointValue = analyzer.getPointValue(heightPoint.row - 1, heightPoint.column);
                        HeightPoint newHeightPoint = new HeightPoint(heightPoint.row - 1, heightPoint.column,
                                pointValue);
                        basinNewPoints.add(newHeightPoint);
                    }

                    // Check value above
                    boolean checkDown = heightPoint.checkDown(analyzer, analyzer.maxRow);
                    if (checkDown) {
                        int pointValue = analyzer.getPointValue(heightPoint.row + 1, heightPoint.column);
                        HeightPoint newHeightPoint = new HeightPoint(heightPoint.row + 1, heightPoint.column,
                                pointValue);
                        basinNewPoints.add(newHeightPoint);
                    }

                    // Point has been evaluated and is part of basin, add to Evaluated
                    if (!basin.checkHavePoint(heightPoint)) {
                        basin.addToConfirmed(heightPoint);
                    }
                }

                // Clear the needs eval list. New points to eval come from basinNewPoints
                basin.clearNeedsEval();
                for (HeightPoint heightPoint : basinNewPoints) {basin.addToNeedsEval(heightPoint);}

                basinNewPoints.clear();
                if (basin.needsEval.size() == 0) {
                    basinComplete = true;
                }
            }
        }
        // Get value of three largest basin sizes multiplied by each other
        System.out.println(basinAggregator.getLargestBasinSizes(3));
    }
}
