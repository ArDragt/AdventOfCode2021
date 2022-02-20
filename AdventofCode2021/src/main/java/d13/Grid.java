package d13;

import java.util.ArrayList;
import java.util.List;

class Grid {
    List<Dot> dotList = new ArrayList<>();

    void addDot(Dot dot) {
        dotList.add(dot);
    }

    int countDots() {
        return dotList.size();
    }

    private int getMaxColumn() {
        return dotList.stream()
                .map(e -> e.x)
                .max(Integer::compare).get();
    }

    private int getMaxRow() {
        return dotList.stream()
                .map(e -> e.y)
                .max(Integer::compare).get();
    }

    private boolean checkDotAtLocation(int column, int row) {
        return dotList.stream()
                .anyMatch(e -> e.x == column && e.y == row);
    }

    void printDots() {
        int maxColumn = getMaxColumn();
        int maxRow = getMaxRow();

        for (int row=0;row<=maxRow;row++) {
            for (int column=0;column<=maxColumn;column++) {
                if (checkDotAtLocation(column, row)) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println("\r");
        }
    }
}
