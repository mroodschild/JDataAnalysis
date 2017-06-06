/*
 * The MIT License
 *
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegressionGD;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week2quiz3 {

    public static void main(String[] args) {
        JDataAnalysis data = new JDataAnalysis();
        JDataAnalysis train = new JDataAnalysis();
        JDataAnalysis test = new JDataAnalysis();
        System.out.println("-- Data --");
        data.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\08 - Quiz 3\\kc_house_data.csv");
        System.out.println("-- Train --");
        train.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\08 - Quiz 3\\kc_house_train_data.csv");
        System.out.println("-- Test --");
        test.open("G:\\coursera\\Machine Learning - Regression\\week 2 - Multiple Regression\\08 - Quiz 3\\kc_house_test_data.csv");

        System.out.println("----------------------Q1----------------------------");

        System.out.println("\nQ1: \tWhat is the value of the weight for sqft_living from your gradient descent predicting house prices (model 1)? Round your answer to 1 decimal place.");

        MultipleLinearRegressionGD model1 = new MultipleLinearRegressionGD();
        model1.regression_gradient_descent(
                train.getFeatures("sqft_living"),
                train.getFeature("price"),
                7e-12,//step_size
                2.5e7,//tolerance
                -47000, 1);//initial weights

        System.out.println("Coefficients Model 1");
        model1.getCoefficients();
        
        System.out.println("");
        System.out.println("--------------------------------------------------");
        System.out.println("------------------------Q2--------------------------");

        System.out.println("\nQ2: \tWhat is the predicted price for the 1st house in the TEST data set for model 1 (round to nearest dollar)?");

        double price1 = model1.predictOutcome(test.getFeatures("sqft_living"))[0];
        System.out.println("Price model 1: $" + price1);

        System.out.println("");
        System.out.println("--------------------------------------------------");
        System.out.println("------------------------Q3--------------------------");
        System.out.println("\nQ3: \tWhat is the predicted price for the 1st house in the TEST data set for model 2 (round to nearest dollar)?");

        MultipleLinearRegressionGD model2 = new MultipleLinearRegressionGD();
        model2.regression_gradient_descent(
                train.getFeatures("sqft_living", "sqft_living15"),
                train.getFeature("price"),
                4e-12, //step_size
                1e9, //tolerance
                -100000, 1, 1); //initial weights

        System.out.println("Coefficients Model 2");
        model2.getCoefficients();

        double price2 = model2.predictOutcome(test.getFeatures("sqft_living", "sqft_living15"))[0];
        System.out.println("Price model 2: $" + price2);
        System.out.println("Actual Price House: $" + test.getFeature("price")[0]);
        System.out.println("");
        System.out.println("--------------------------------------------------");
        System.out.println("------------------------Q4--------------------------");

        System.out.println("\nQ4: \tWhich estimate was closer to the true price for the 1st house on the TEST data set, model 1 or model 2?");
        System.out.println("Actual Price House: $" + test.getFeature("price")[0] + "\t Model1: $" + price1 + "\t Model2: $" + price2);

        System.out.println("--------------------------------------------------");
        System.out.println("------------------------Q5--------------------------");
        System.out.println("\nQ5: \tWhich model (1 or 2) has lowest RSS on all of the TEST data?");
        System.out.println("");
        double rss_model1 = model1.getRSS(test.getFeatures("sqft_living"), test.getFeature("price"));
        System.out.println("rss model1 = " + rss_model1);
        double rss_model2 = model2.getRSS(test.getFeatures("sqft_living", "sqft_living15"), test.getFeature("price"));
        System.out.println("rss model1 = " + rss_model2);
        System.out.println("");
        System.out.println("Model 1 RSS: " + rss_model1 + " Model 2 RSS2: " + rss_model2);

    }
}
