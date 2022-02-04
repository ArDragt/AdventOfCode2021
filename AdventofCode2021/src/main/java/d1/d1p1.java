/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package d1;

/**
 *
 * @author ca-adragt
 */

import com.ad.adventofcode2021.InputDataReader;

import java.util.List;

public class d1p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d1input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        int increaseCounter = 0;
        
        // Look for increasing cases, increment counter when true
        for (int i = 0;i < inputData.size(); i++) {
            if (i == 0) {
            }
            else {
                int prevValue = Integer.valueOf(inputData.get(i-1));
                int curValue = Integer.valueOf(inputData.get(i));
                if (curValue > prevValue) {
                    increaseCounter++;
                }
            }
        }
        System.out.println(increaseCounter);
    }
}
