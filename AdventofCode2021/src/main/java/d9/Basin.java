package d9;

import java.util.ArrayList;
import java.util.List;

class Basin {
    List<HeightPoint> needsEval = new ArrayList<>();
    List<HeightPoint> confirmed = new ArrayList<>();

    void addToNeedsEval(HeightPoint heightPoint) {needsEval.add(heightPoint);}

    HeightPoint getNeedsEvalPoint(int index) {return needsEval.get(index);}

    void clearNeedsEval() {needsEval.clear();}

    boolean checkHavePoint(HeightPoint heightPoint) {
        long existingCount = confirmed.stream()
                .filter(e -> e.row == heightPoint.row && e.column == heightPoint.column)
                .count();
        return existingCount > 0;
    }

    void addToConfirmed(HeightPoint heightPoint) {
        confirmed.add(heightPoint);
    }
}
