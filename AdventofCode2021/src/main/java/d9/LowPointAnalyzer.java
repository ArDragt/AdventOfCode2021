package d9;

import java.util.ArrayList;
import java.util.List;

class LowPointAnalyzer {
    private List<HeightMapData> heightData = new ArrayList<>();
    private List<LowPoint> lowPoints = new ArrayList<>();

    void addHeightData(HeightMapData rowData) {
        heightData.add(rowData);
    }

    private int getPrevRowValue(int currentRow, int currentColumn) {
        HeightMapData previousRow = heightData.stream()
                .filter(e -> e.rowNumber == currentRow - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
        return Character.getNumericValue(previousRow.data.charAt(currentColumn));
    }

    private int getNextRowValue(int currentRow, int currentColumn) {
        HeightMapData previousRow = heightData.stream()
                .filter(e -> e.rowNumber == currentRow + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
        return Character.getNumericValue(previousRow.data.charAt(currentColumn));
    }

    private int getPrevColValue(HeightMapData currentRow, int currentColumn) {
        return Character.getNumericValue(currentRow.data.charAt(currentColumn - 1));
    }

    private int getNextColValue(HeightMapData currentRow, int currentColumn) {
        return Character.getNumericValue(currentRow.data.charAt(currentColumn + 1));
    }

    private boolean checkLowPoint(HeightMapData currentRow, int currentValue, int colNum) {
        int rowNum = currentRow.rowNumber;
        int maxRow = heightData.size() - 1;
        int maxCol = currentRow.data.length() - 1;

        if (rowNum == 0 && colNum == 0) {
            if (getNextColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        } else if (colNum == maxCol && rowNum == maxRow) {
            if (getPrevColValue(currentRow, colNum) > currentValue &&
                    getPrevRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        }
        else if (rowNum == 0 && colNum == maxCol) {
            if (getPrevColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        } else if (colNum == 0 && rowNum == maxRow) {
            if (getNextColValue(currentRow, colNum) > currentValue &&
                    getPrevRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        }
        else if (colNum == 0) {
            if (getNextColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(rowNum, colNum) > currentValue &&
                    getPrevRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        } else if (rowNum == 0) {
            if (getNextColValue(currentRow, colNum) > currentValue &&
                    getPrevColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        } else if (colNum == maxCol) {
            if (getPrevRowValue(rowNum, colNum) > currentValue &&
                    getPrevColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(rowNum, colNum) > currentValue
            ) {return true;} else {return false;}
        } else if (rowNum == maxRow) {
            if (getPrevRowValue(rowNum, colNum) > currentValue &&
                    getPrevColValue(currentRow, colNum) > currentValue &&
                    getNextColValue(currentRow, colNum) > currentValue
            ) {return true;} else {return false;}
        } else {
            if (getPrevColValue(currentRow, colNum) > currentValue &&
                    getPrevRowValue(currentRow.rowNumber, colNum) > currentValue &&
                    getNextColValue(currentRow, colNum) > currentValue &&
                    getNextRowValue(currentRow.rowNumber, colNum) > currentValue
            ) {return true;} else {return false;}
        }

    }

    void findLowPoints() {
        int rowIndex = 0;
        for (HeightMapData row : heightData) {
            for (int columNumber=0;columNumber<row.data.length();columNumber++) {
                boolean isLowPoint = checkLowPoint(row, Character.getNumericValue(row.data.charAt(columNumber)),
                        columNumber);
                if (isLowPoint) {
                    addLowPoint(new LowPoint(rowIndex, columNumber,
                            Character.getNumericValue(row.data.charAt(columNumber))));
                }
                rowIndex++;
            }
        }
    }

    int getRiskLevel() {
        return lowPoints.stream()
                .map(e -> e.value)
                .reduce(0, (a,b) -> a+b+1 );

    }

    private void addLowPoint(LowPoint lowPoint) {
        lowPoints.add(lowPoint);
    }

    private class LowPoint {
        private final int row;
        private final int column;
        private final int value;

        private LowPoint(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

}
