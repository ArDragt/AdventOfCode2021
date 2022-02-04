package d9;

import com.ad.adventofcode2021.InputDataReader;
import java.util.List;

class D9P1 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d9input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        LowPointAnalyzer analyzer = new LowPointAnalyzer();
        for (int i=0;i< inputData.size();i++) {
            HeightMapData rowData = new HeightMapData(i, inputData.get(i));
            analyzer.addHeightData(rowData);
        }
        analyzer.findLowPoints();
        System.out.println(analyzer.getRiskLevel());
    }

}
