package d10;

import com.ad.adventofcode2021.InputDataReader;

import java.util.ArrayList;
import java.util.List;

public class D10P2 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d10input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        ChunkParser parser = new ChunkParser();
        ChunkInfo chunkInfo = new ChunkInfo();

        // Hold total value
        int syntaxErrorScore = 0;

        for (String row : inputData) {
            ChunkAnalysis chunkAnalysis = parser.checkCorruptRow(row);
            if (!chunkAnalysis.isCorrupt) {
                chunkInfo.addRow(chunkAnalysis);}
        }

        for (ChunkAnalysis chunkAnalysis : chunkInfo.getRows()) {
            chunkAnalysis.calcCompletionScore();
        }

        List<Long> scoresList = new ArrayList<>();
        scoresList = chunkInfo.getRows().stream()
                        .map(e -> e.getCompletionScore())
                .sorted().toList();

        long middleScore = scoresList.get((scoresList.size() - 1)/2);

        System.out.println(middleScore);

    }
}
