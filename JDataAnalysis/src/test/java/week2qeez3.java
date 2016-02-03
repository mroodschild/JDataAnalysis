/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegression;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week2qeez3 {

    public static void main(String[] args) {
        JDataAnalysis data = new JDataAnalysis();
        JDataAnalysis train = new JDataAnalysis();
        JDataAnalysis test = new JDataAnalysis();
        System.out.println("-- Data --");
        data.open("D:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_data.csv");
        System.out.println("-- Train --");
        train.open("D:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_train_data.csv");
        System.out.println("-- Test --");
        test.open("D:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_test_data.csv");

        System.out.println("Q1 mean 4 new Columns");
        System.out.println("Mean (bedrooms_squared): " + test.mean("bedrooms_squared"));
        System.out.println("Mean (bed_bath_rooms): " + test.mean("bed_bath_rooms"));
        System.out.println("Mean (log_sqft_living): " + test.mean("log_sqft_living"));
        System.out.println("Mean (lat_plus_long): " + test.mean("lat_plus_long"));

        //Util.print(train.getFeatures("bedrooms_squared", "bed_bath_rooms", "log_sqft_living", "lat_plus_long"), "Datos Extraidos");
        MultipleLinearRegression mlr = new MultipleLinearRegression(train.getFeatures("bedrooms_squared", "bed_bath_rooms", "log_sqft_living", "lat_plus_long"), train.getFeature("price"));
        mlr.getCoefficients();
    }
}
