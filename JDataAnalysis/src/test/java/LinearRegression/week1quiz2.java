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

import org.gitia.jdataanalysis.JDataAnalysis;
import org.gitia.jdataanalysis.SimpleLinearRegression;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week1quiz2 {

    public static void main(String[] args) {
        JDataAnalysis jda = new JDataAnalysis();
        JDataAnalysis jdaTrain = new JDataAnalysis();
        JDataAnalysis jdaBedRooms = new JDataAnalysis();
        JDataAnalysis jdaTest = new JDataAnalysis();

        System.out.println("House Data:");
        jda.open("G:\\coursera\\Machine Learning - Regression\\week 1 - Simple Linear Regression\\QUIZ\\kc_house_data.csv");
        System.out.println("House Train Data:");
        jdaTrain.open("G:\\coursera\\Machine Learning - Regression\\week 1 - Simple Linear Regression\\QUIZ\\kc_house_train_data.csv");
        System.out.println("House Test Data:");
        jdaBedRooms.open("G:\\coursera\\Machine Learning - Regression\\week 1 - Simple Linear Regression\\QUIZ\\kc_house_train_data.csv");
        jdaTest.open("G:\\coursera\\Machine Learning - Regression\\week 1 - Simple Linear Regression\\QUIZ\\kc_house_test_data.csv");
        
        System.out.println("");
        System.out.println("Train Data");
        SimpleLinearRegression modelsqft = jdaTrain.simpleLinearRegression("sqft_living", "price");
        System.out.println("Coefficients:");
        modelsqft.getCoefficients();
        System.out.println("Q1 - Prediction x = 2650, y(x): " + modelsqft.prediction(2650));
        
        System.out.println("Q2 - RSS for Training Data");
        System.out.println(modelsqft.getRSS(jdaTrain.getFeature("sqft_living"), jdaTrain.getFeature("price")));
        System.out.println("\nQ3 - Sqft of house of $800,000.");
        System.out.println(modelsqft.inverseRegressionPredictions(800000));
        
        
        System.out.println("");
        System.out.println("Trian Data BEDROOMS");
        SimpleLinearRegression modelBedRooms = jdaBedRooms.simpleLinearRegression("bedrooms", "price");
        System.out.println("Coefficients bedrooms model:");
        modelBedRooms.getCoefficients();
        
        System.out.println("\nQ4");
        System.out.println("RSS for TestData with sqft model");
        System.out.println(modelsqft.getRSS(jdaTest.getFeature("sqft_living"), jdaTest.getFeature("price")));
        System.out.println("RSS for TestData with bedrooms model");
        System.out.println(modelBedRooms.getRSS(jdaTest.getFeature("bedrooms"), jdaTest.getFeature("price")));
       
    }

}
