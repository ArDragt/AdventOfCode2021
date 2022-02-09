package d10;

import com.ad.adventofcode2021.InputDataReader;

import java.util.List;

public class D10P1 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d10input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        ChunkParser parser = new ChunkParser();

        // Hold total value
        int syntaxErrorScore = 0;

        for (String row : inputData) {
            syntaxErrorScore = syntaxErrorScore + parser.checkCorruptRow(row).errorScore;
        }
        System.out.println(syntaxErrorScore);
    }
}
