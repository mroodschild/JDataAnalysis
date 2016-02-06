/*
 * Copyright 2016 @author Matías Roodschild <mroodschild@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
