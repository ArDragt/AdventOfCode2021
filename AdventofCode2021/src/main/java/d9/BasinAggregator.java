package d9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BasinAggregator {
    List<Basin> basins = new ArrayList<>();

    void addBasin(Basin basin) {
        basins.add(basin);
    }

    int getLargestBasinSizes(int basinCount) {
        List<Integer> basinSizes = new ArrayList<>();
        for (Basin basin : basins) {
            int basinSize = basin.confirmed.size();
            basinSizes.add(basinSize);
        }
        basinSizes.sort(Comparator.reverseOrder());
        int outputVal = 1;
        for (int i=0;i<basinCount;i++) {
            outputVal = outputVal*basinSizes.get(i);
        }
        return outputVal;
    }
}
