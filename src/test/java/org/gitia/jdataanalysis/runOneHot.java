/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gitia.jdataanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gitia.jdataanalysis.data.OneHot;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class runOneHot {

    public static void main(String[] args) {
        String[] stringLabels = {"Sunday", "Monday", "Tuestay","Sunday", "Monday", "Tuestay"};
        
        OneHot oneHotEncoder = new OneHot(stringLabels);
        List<String> datos = Arrays.asList("Sunday", "Monday", "Tuestay", "Sunday", "Monday", "Tuestay");
        List<double[]> predictions = new ArrayList<>();
        for (String dato : datos) {
            double[] oneHot = oneHotEncoder.encode(dato);
            predictions.add(oneHot);
            print(oneHotEncoder.decode(oneHot), oneHot);
        }
        for (double[] predicton : predictions) {
            double classLabel = oneHotEncoder.decode(predicton);
            String label = oneHotEncoder.tag(predicton);
            System.out.printf("classLabel: %.0f %s\n", classLabel, label);
        }
    }
    
    public static void print(double[] oneHot){
        for (int i = 0; i < oneHot.length; i++) {
            System.out.printf("%.0f ", oneHot[i]);
        }
        System.out.println("");
    }

    public static void print(double classNumber, double[] oneHot) {
        System.out.printf("%.0f: ", classNumber);
        for (int i = 0; i < oneHot.length; i++) {
            System.out.printf("%.0f ", oneHot[i]);
        }
        System.out.println("");
    }
}
