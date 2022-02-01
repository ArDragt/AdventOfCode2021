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
public class d6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // How many "days" to process
        int days = 256;
        
        
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d6input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        AgeTracker ageTracker = new AgeTracker();
        
        // Get and set initial data
        String[] inputDataValues = inputData.get(0).split(",");
        for (String value : inputDataValues) {
            ageTracker.setInitialData(Integer.valueOf(value));
        }
        
        // Process "days"
        for (int i = 0; i < days; i++) {
            ageTracker.doDay();
        }
        
        // Output
        System.out.println(ageTracker.countFish());
    }
    
}


class AgeTracker {
    private long zero, one, two, three, four, five, six, seven, eight = 0;
    private long temp0, temp1, temp2, temp3, temp4, temp5, temp6,
            temp7, temp8 = 0;
    
    public void setInitialData(int age) {
        switch (age) {
            case 1:
                one++;
                break;
            case 2:
                two++;
                break;
            case 3:
                three++;
                break;
            case 4:
                four++;
                break;
            case 5:
                five++;
                break;
            case 6:
                six++;
                break;
            case 7:
                seven++;
                break;
            case 8:
                eight++;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void doDay() {
        temp8 = zero;
        temp7 = eight;
        temp6 = zero + seven;
        temp5 = six;
        temp4 = five;
        temp3 = four;
        temp2 = three;
        temp1 = two;
        temp0 = one;

        eight = temp8;
        seven = temp7;
        six = temp6;
        five = temp5;
        four = temp4;
        three = temp3;
        two = temp2;
        one = temp1;
        zero = temp0;
    }
    
    public long countFish() {
        return zero + one + two + three + four + five + six + seven + eight;
    }
}