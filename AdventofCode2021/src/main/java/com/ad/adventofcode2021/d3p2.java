/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ad.adventofcode2021;

import java.util.List;

/**
 *
 * @author ca-adragt
 */
public class d3p2 {

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
        
        DiagnosticTracker o2Tracker = new DiagnosticTracker();
        DiagnosticTracker co2Tracker = new DiagnosticTracker();
        o2Tracker.addInitialData(inputData);
        co2Tracker.addInitialData(inputData);
        
        // Add bit position trackers for each column
        for (int i = 0;i < 12;i++) {
            /**
            * First deal with oxygen data
            */
            List<String> currentO2Data = o2Tracker.getData();
            BitTracker o2BitTracker = new BitTracker();
            
            // If only one entry left in the data, then skip
            if (currentO2Data.size() > 1) {
                for (String readings : currentO2Data) {
                    if (readings.charAt(i) == '0') {
                        o2BitTracker.incrZeros();
                    }
                    else {
                        o2BitTracker.incrOnes();
                    }
                }
                String mostCommonBit = o2BitTracker.getMostCommon();
                if (mostCommonBit.equals("1") || mostCommonBit.isBlank()) {
                    o2Tracker.filterRows(i, '1');
                }
                else if (mostCommonBit.equals("0")) {
                    o2Tracker.filterRows(i, '0');
                }
            }
            
            /**
            * Next deal with CO2 data
            */
            List<String> currentCo2Data = co2Tracker.getData();
            BitTracker co2BitTracker = new BitTracker();
            
            // If only one entry left in the data, then skip
            if (currentCo2Data.size() > 1) {
                for (String readings : currentCo2Data) {
                    if (readings.charAt(i) == '0') {
                        co2BitTracker.incrZeros();
                    }
                    else {
                        co2BitTracker.incrOnes();
                    }
                }
                String leastCommonBit = co2BitTracker.getLeastCommon();
                if (leastCommonBit.equals("0") || leastCommonBit.isBlank()) {
                    co2Tracker.filterRows(i, '0');
                }
                else if (leastCommonBit.equals("1")) {
                    co2Tracker.filterRows(i, '1');
                }
            }
            
        }
        
        // Done reducing data. Get and print results
        String o2Binary = o2Tracker.getData().stream()
                .reduce("", String::concat);
        String co2Binary = co2Tracker.getData().stream()
                .reduce("", String::concat);
        
        System.out.println(Integer.parseInt(o2Binary, 2) * 
                Integer.parseInt(co2Binary, 2));
        
    }
    
}
