/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package d7;

import com.ad.adventofcode2021.InputDataReader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca-adragt
 */
public class d7p2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d7input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        String inputDataString = reader.readTextLine();
        String[] inputDataParts = inputDataString.split(",");
        List<Integer> inputData = new ArrayList<>();
        for (String entry : inputDataParts) {
            inputData.add(Integer.valueOf(entry));
        }
        
        CrabCostCalculator costCalculator = new CrabCostCalculator(inputData);
        for (int position : inputData) {
            costCalculator.calcIncrMoveCost(position);
        }
        System.out.println(costCalculator.getLowestCost());
    }
    
}
