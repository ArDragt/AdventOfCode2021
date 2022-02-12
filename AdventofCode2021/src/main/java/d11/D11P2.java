package d11;

import com.ad.adventofcode2021.InputDataReader;

import java.util.List;

public class D11P2 {
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d11input";

        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();

        Octopi octopi = new Octopi();

        // Create octopus objects and populate octopi
        for (int row=0;row< inputData.size();row++) {
            String rowValues = inputData.get(row);
            for (int col=0;col<rowValues.length();col++) {
                int value = Character.getNumericValue(rowValues.charAt(col));
                Octopus octopus = new Octopus(row, col, value);
                octopi.addOctopus(octopus);
            }
        }

        // Loop until all flash simultaneously
        boolean allFlashed = false;
        int loopCounter = 0;

        while (!allFlashed) {
            loopCounter++;
            octopi.incrementAll();
            if (octopi.checkFlashed()) {
                do {
                    octopi.processFlashed();
                } while (octopi.checkFlashedNotProcessed());
            }
            if (octopi.getFlashedProcessedCount() == 100) {
                allFlashed = true;
            }
            octopi.clearFlashProcessed();
        }
        System.out.println(loopCounter);
    }
}
