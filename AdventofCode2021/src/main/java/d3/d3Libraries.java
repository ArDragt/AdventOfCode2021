/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3;

/**
 *
 * @author ca-adragt
 */

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


class DiagnosticTracker {
    List<BitTracker> positionTracker;
    private List<String> measurementData;

    public DiagnosticTracker() {
        positionTracker = new ArrayList<>();
        measurementData = new ArrayList<>();
    }
    
    public void addBitTracker(int position, BitTracker tracker) {
        positionTracker.add(position, tracker);
    }
    
    public void addInitialData(List<String> data) {
        measurementData = data;
    }
    
    public int getRowCount() {
        return measurementData.size();
    }
    
    public void filterRows(int position, char matchValue) {
        List<String> filteredRows = measurementData.stream()
                .filter(e -> e.charAt(position) == matchValue)
                .collect(Collectors.toList());
        measurementData = filteredRows;
    }
    
    public List<String> getData() {
        return measurementData;
    }

}


class BitTracker {
    private int zeros = 0;
    private int ones = 0;

    public void incrZeros() {
        zeros++;
    }
    
    public void incrOnes() {
        ones++;
    }
    
    public int getZeros() {
        return zeros;
    }
    
    public int getOnes() {
        return ones;
    }
    
    public String getMostCommon() {
        if (ones > zeros) {
            return "1";
        }
        else if (zeros > ones) {
            return "0";
        }
        else {
            return "";
        }
    }
    
    public String getLeastCommon() {
        if (ones < zeros) {
            return "1";
        }
        else if (zeros < ones) {
            return "0";
        }
        else {
            return "";
        }
    }
}
