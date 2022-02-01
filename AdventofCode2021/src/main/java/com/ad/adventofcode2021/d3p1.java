/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ad.adventofcode2021;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca-adragt
 */
public class d3p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d3input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        //Set up "bit trackers" for each of the bit positions
        // and add to the overall diagnostics tracker
        DiagnosticTracker tracker = new DiagnosticTracker();
        for (int i = 0; i < 12; i++) {
            tracker.addBitTracker(i, new BitTracker());
        }
        
        // Loop through input data, one row at a time
        for (String readings : inputData) {
            for (int bit = 0; bit < readings.length(); bit++) {
                if (readings.charAt(bit) == '0') {
                    tracker.positionTracker.get(bit).incrZeros();
                }
                else {
                    tracker.positionTracker.get(bit).incrOnes();
                }
            }
        }
        
        //Have results at this point
        List<String> mcBits = new ArrayList<>();
        List<String> lcBits = new ArrayList<>();
        for (BitTracker bt : tracker.positionTracker) {
            mcBits.add(bt.getMostCommon());
            lcBits.add(bt.getLeastCommon());
        }
        String mcBinary = mcBits.stream()
                .reduce("", String::concat);
        String lcBinary = lcBits.stream()
                .reduce("", String::concat);
        
        System.out.println(Integer.parseInt(mcBinary, 2) * 
                Integer.parseInt(lcBinary, 2));
    }
}
