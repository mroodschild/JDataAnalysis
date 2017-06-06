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
import com.gitia.jdataanalysis.RidgeRegression;
import com.gitia.jdataanalysis.Util;

/**
 *
 * @author Roodschild, Matias <mroodschild@gmail.com>
 */
public class week4quiz2b {

    public static void main(String[] args) {
        //establezco la dirección relativa
        String folder = "src/main/resources/week4/quiz2/";

        System.out.println("\n---- Q2: Quiz Question: For the models learned in each of "
                + "these training sets, what are \nthe smallest and largest values"
                + "you learned for the coefficient of feature power_1? ----\n");

        System.out.println("\nEntrenamos cada uno de los sets");

        JDataAnalysis JDASet1 = new JDataAnalysis(folder + "wk3_kc_house_set_1_data.csv", false);
        JDataAnalysis JDASet2 = new JDataAnalysis(folder + "wk3_kc_house_set_2_data.csv", false);
        JDataAnalysis JDASet3 = new JDataAnalysis(folder + "wk3_kc_house_set_3_data.csv", false);
        JDataAnalysis JDASet4 = new JDataAnalysis(folder + "wk3_kc_house_set_4_data.csv", false);

        double l2 = 1e-9;
        System.out.println("l2_small_penalty: " + l2);
        RidgeRegression modelSet1 = new RidgeRegression(l2);
        RidgeRegression modelSet2 = new RidgeRegression(l2);
        RidgeRegression modelSet3 = new RidgeRegression(l2);
        RidgeRegression modelSet4 = new RidgeRegression(l2);

        System.out.println("\n------- Fit -------\n");
        System.out.println("Set 1");
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 2), JDASet1.getFeature("price"), true);

        System.out.println("Set 2");
        modelSet2.fit(JDASet2.getPolynomial("sqft_living", 2), JDASet2.getFeature("price"), true);

        System.out.println("Set 3");
        modelSet3.fit(JDASet3.getPolynomial("sqft_living", 2), JDASet3.getFeature("price"), true);

        System.out.println("Set 4");
        modelSet4.fit(JDASet4.getPolynomial("sqft_living", 2), JDASet4.getFeature("price"), true);

        System.out.println("\n------- Coefficients -------\n");

        System.out.println("Set 1");
        modelSet1.getCoefficients();

        System.out.println("Set 2");
        modelSet2.getCoefficients();

        System.out.println("Set 3");
        modelSet3.getCoefficients();

        System.out.println("Set 4");
        modelSet4.getCoefficients();

        System.out.println("Set 1");
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 1), JDASet1.getFeature("price"), true);
        double[] poly1 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 1));
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 2), JDASet1.getFeature("price"), true);
        double[] poly2 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 2));
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 3), JDASet1.getFeature("price"), true);
        double[] poly3 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 3));
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 4), JDASet1.getFeature("price"), true);
        double[] poly4 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 4));
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 5), JDASet1.getFeature("price"), true);
        double[] poly5 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 5));
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 6), JDASet1.getFeature("price"), true);
        double[] poly6 = modelSet1.predictOutcome(JDASet1.getPolynomial("sqft_living", 6));
//Util.mostrarVector(predict_set1, "Predict 1");

        String[] title = {"Poly 1", "Poly 2", "Poly 3", "Poly 4", "Poly 5", "Poly 6"};
        Util.show(title, poly1, poly2, poly3, poly4, poly5, poly6);
        
        modelSet1.getCoefficients();
    }
}
