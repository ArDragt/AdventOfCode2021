package d10;

import java.util.ArrayList;
import java.util.List;


class ChunkInfo {
    private List<ChunkAnalysis> rows = new ArrayList<>();

    void addRow(ChunkAnalysis row) {
        rows.add(row);}

    List<ChunkAnalysis> getRows() {return rows;}
}
