package d11;

import com.ad.adventofcode2021.InputDataReader;

import java.util.ArrayList;
import java.util.List;

public class D11P1 {
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

        // Simulate 100 steps

        for (int step=0;step<100;step++) {
            octopi.incrementAll();
            if (octopi.checkFlashed()) {
                do {
                    octopi.processFlashed();
                } while (octopi.checkFlashedNotProcessed());
            }
            octopi.clearFlashProcessed();
        }

        long totalFlashCount = octopi.getOctopusList().stream()
                .map(Octopus::getFlashCount)
                .reduce((long) 0, Long::sum);

        System.out.println(totalFlashCount);
    }
}
