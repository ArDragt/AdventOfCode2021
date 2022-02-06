package d9;

import java.util.ArrayList;
import java.util.List;

class LowPointAnalyzer {
    private List<HeightMapData> heightData = new ArrayList<>();
    private List<HeightPoint> lowPoints = new ArrayList<>();
    int maxRow;
    int maxCol;

    void setMaxRow() {
        maxRow = heightData.size() - 1;
    }
    void setMaxCol() {
        maxCol = heightData.get(0).data.length() - 1;
    }

    void addHeightData(HeightMapData rowData) {
        heightData.add(rowData);
    }

    HeightMapData getHeightPointData(int rowNumber) {
        return heightData.stream()
                .filter(e -> e.rowNumber == rowNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
    }

    int getPrevRowValue(int currentRow, int currentColumn) {
        HeightMapData previousRow = heightData.stream()
                .filter(e -> e.rowNumber == currentRow - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
        return Character.getNumericValue(previousRow.data.charAt(currentColumn));
    }

    int getNextRowValue(int currentRow, int currentColumn) {
        HeightMapData previousRow = heightData.stream()
                .filter(e -> e.rowNumber == currentRow + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
        return Character.getNumericValue(previousRow.data.charAt(currentColumn));
    }

    int getPointValue(int row, int column) {
        HeightMapData targetRow = heightData.stream()
                .filter(e -> e.rowNumber == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found"));
        return Character.getNumericValue(targetRow.data.charAt(column));
    }

    int getPrevColValue(HeightMapData currentRow, int currentColumn) {
        return Character.getNumericValue(currentRow.data.charAt(currentColumn - 1));
    }

    int getNextColValue(HeightMapData currentRow, int currentColumn) {
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
            for (int colNumber=0;colNumber<row.data.length();colNumber++) {
                boolean isLowPoint = checkLowPoint(row, Character.getNumericValue(row.data.charAt(colNumber)),
                        colNumber);
                if (isLowPoint) {
                    addLowPoint(new HeightPoint(rowIndex, colNumber,
                            Character.getNumericValue(row.data.charAt(colNumber))));
                }
            }
            rowIndex++;
        }
    }

    int getRiskLevel() {
        return lowPoints.stream()
                .map(e -> e.value)
                .reduce(0, (a,b) -> a+b+1 );

    }

    private void addLowPoint(HeightPoint lowPoint) {lowPoints.add(lowPoint);}

    List<HeightPoint> getLowPoints() {return lowPoints;}

    HeightPoint getLowPoint(int index) {return lowPoints.get(index);}

}
