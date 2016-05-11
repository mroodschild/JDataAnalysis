/*
 * Copyright 2016 Matías Roodschild <mroodschild@gmail.com>.
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
