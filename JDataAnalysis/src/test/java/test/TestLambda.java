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
package test;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.RidgeRegression;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class TestLambda {
    public static void main(String[] args) {
        String folder = "src/main/resources/week4/quiz2/";
        JDataAnalysis JDA_kc_house_data = new JDataAnalysis(folder + "kc_house_data.csv");
        double l2 = 1.5e-5;
        RidgeRegression model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 1), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e-5;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e-3;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e-1;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e+1;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e+3;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e+5;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
        
        l2 = 1.5e+20;
        model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        model.getCoefficients();
    }
}
