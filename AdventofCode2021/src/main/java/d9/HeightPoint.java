package d9;

public class HeightPoint {
    final int row;
    final int column;
    final int value;

    HeightPoint(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    boolean checkLeft(HeightMapData heightMapData) {
        if (column == 0) {return false;}
        else {
            int leftVal = Character.getNumericValue(heightMapData.data.charAt(column - 1));
            if (leftVal > value && leftVal != 9) {return true;}
            else {return  false;}
        }
    }

    boolean checkRight(HeightMapData heightMapData, int maxCol) {
        if (column == maxCol) {return false;}
        else {
            int rightVal = Character.getNumericValue(heightMapData.data.charAt(column + 1));
            if (rightVal > value && rightVal != 9) {return true;}
            else {return  false;}
        }
    }

    boolean checkUp(LowPointAnalyzer lowPointAnalyzer) {
        if (row == 0) {return false;}
        else {
            int upVal = lowPointAnalyzer.getPrevRowValue(row, column);
            if (upVal > value && upVal != 9) {return true;}
            else {return  false;}
        }
    }

    boolean checkDown(LowPointAnalyzer lowPointAnalyzer, int maxRow) {
        if (row == maxRow) {return false;}
        else {
            int downVal = lowPointAnalyzer.getNextRowValue(row, column);
            if (downVal > value && downVal != 9) {return true;}
            else {return  false;}
        }
    }
}
