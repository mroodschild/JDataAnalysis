package LinearRegression;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegression;
import com.gitia.jdataanalysis.MultipleLinearRegressionGD;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week2quiz2 {

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

        //Q2
        System.out.println("Model 1");
        MultipleLinearRegression model1 = new MultipleLinearRegression(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long"),
                train.getFeature("price"));
        model1.getCoefficients();
        System.out.println("Model 2");
        MultipleLinearRegression model2 = new MultipleLinearRegression(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms"),
                train.getFeature("price"));
        model2.getCoefficients();
        System.out.println("Model 3");
        MultipleLinearRegression model3 = new MultipleLinearRegression(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms",
                "bedrooms_squared",
                "log_sqft_living",
                "lat_plus_long"),
                train.getFeature("price"));
        model3.getCoefficients();

        System.out.println("RSS 1: " + model1.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long"),
                test.getFeature("price")));
        System.out.println("RSS 2: " + model2.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms"),
                test.getFeature("price")));
        System.out.println("RSS 3: " + model3.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms",
                "bedrooms_squared",
                "log_sqft_living",
                "lat_plus_long"),
                test.getFeature("price")));

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////");

        //Q2
        System.out.println("Model 1");
        MultipleLinearRegressionGD model1B = new MultipleLinearRegressionGD(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long"),
                train.getFeature("price"),0.01,0.01);
        model1.getCoefficients();
        System.out.println("Model 2");
        MultipleLinearRegressionGD model2B = new MultipleLinearRegressionGD(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms"),
                train.getFeature("price"),0.01,0.01);
        model2.getCoefficients();
        System.out.println("Model 3");
        MultipleLinearRegressionGD model3B = new MultipleLinearRegressionGD(train.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms",
                "bedrooms_squared",
                "log_sqft_living",
                "lat_plus_long"),
                train.getFeature("price"),0.01,0.01);
        model3.getCoefficients();

        System.out.println("RSS 1: " + model1B.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long"),
                test.getFeature("price")));
        System.out.println("RSS 2: " + model2B.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms"),
                test.getFeature("price")));
        System.out.println("RSS 3: " + model3B.getRSS(test.getFeatures(
                "sqft_living",
                "bedrooms",
                "bathrooms",
                "lat",
                "long",
                "bed_bath_rooms",
                "bedrooms_squared",
                "log_sqft_living",
                "lat_plus_long"),
                test.getFeature("price")));
    }
}
