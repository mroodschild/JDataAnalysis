/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegressionGD;

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
        data.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_data.csv");
        System.out.println("-- Train --");
        train.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_train_data.csv");
        System.out.println("-- Test --");
        test.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\06 - Quiz 2\\kc_house_test_data.csv");

        System.out.println("--------------------------------------------------");
        MultipleLinearRegressionGD model1 = new MultipleLinearRegressionGD();
                model1.regression_gradient_descent(train.getFeatures("sqft_living"), train.getFeature("price"), 7e-12, 2.5e7,  -47000, 1);
        System.out.println("\nQ1: \tWhat is the value of the weight for sqft_living from your gradient descent predicting house prices (model 1)? Round your answer to 1 decimal place.");
        double price1 = model1.predictOutcome(test.getFeatures("sqft_living"))[0];
        System.out.println("$"+price1);
        model1.getCoefficients();
        System.out.println("--------------------------------------------------");

        
        System.out.println("\nQ2: \tWhat is the predicted price for the 1st house in the TEST data set for model 1 (round to nearest dollar)?");
        double rss_model1 = model1.getRSS(test.getFeatures("sqft_living"), test.getFeature("price"));
        System.out.println("rss model1 = " + rss_model1);
        System.out.println("--------------------------------------------------");
        
        
        System.out.println("\nQ3: \tWhat is the predicted price for the 1st house in the TEST data set for model 2 (round to nearest dollar)?");
        MultipleLinearRegressionGD model2 = new MultipleLinearRegressionGD();
                model2.regression_gradient_descent(
                        train.getFeatures("sqft_living", "sqft_living15"), 
                        train.getFeature("price"), 7e-12, 2.5e7, -10000, 1, 1);
        double price2 = model2.predictOutcome(test.getFeatures("sqft_living", "sqft_living15"))[0];
        System.out.println("$"+price2);
        System.out.println("--------------------------------------------------");
        
        double rss_model2 = model2.getRSS(
                test.getFeatures("sqft_living", "sqft_living15"),
                test.getFeature("price"));
        
        System.out.println("\nQ4: \tWhich estimate was closer to the true price for the 1st house on the TEST data set, model 1 or model 2?");
        System.out.println("Real Value: $" + test.getFeature("price")[0] + "\t Model1: $" + price1 + "\t Model2: $" + price2);

        System.out.println("\nQ5: \tWhich model (1 or 2) has lowest RSS on all of the TEST data?");
        System.out.println("RSS1: " + rss_model1 + " RSS2: " + rss_model2);
        
        MultipleLinearRegressionGD model3 = new MultipleLinearRegressionGD(
                train.getFeatures("sqft_living"),
                train.getFeature("price"),
                4e-12,
                1e09);
        double price3 = model3.predictOutcome(test.getFeatures("sqft_living"))[0];
        System.out.println("$"+price3);
        model3.getCoefficients();
        
        
    }
}
