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
import com.gitia.jdataanalysis.data.CrossValidation;

/**
 *
 * @author Roodschild, Matias <mroodschild@gmail.com>
 */
public class week4quiz2 {

    public static void main(String[] args) {
        //establezco la dirección relativa
        String folder = "src/main/resources/week4/quiz2/";

        ////////////////////////////// Q1 //////////////////////////////
        System.out.println("\nQuiz Question: What’s the learned value for the coefficient of feature power_1?");
        //open file
        JDataAnalysis JDA_kc_house_data = new JDataAnalysis(folder + "kc_house_data.csv", false);
        double l2 = 1.5e-5;
        RidgeRegression model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        System.out.println("\n---- Q1: Coeficientes, See power_1 coefficient ----");
        model.getCoefficients();

        System.out.println("\n---- Q2: Quiz Question: For the models learned in each of "
                + "these training sets, what are \nthe smallest and largest values"
                + "you learned for the coefficient of feature power_1? ----\n");

        System.out.println("\nEntrenamos cada uno de los sets");

        JDataAnalysis JDASet1 = new JDataAnalysis(folder + "wk3_kc_house_set_1_data.csv", false);
        JDataAnalysis JDASet2 = new JDataAnalysis(folder + "wk3_kc_house_set_2_data.csv", false);
        JDataAnalysis JDASet3 = new JDataAnalysis(folder + "wk3_kc_house_set_3_data.csv", false);
        JDataAnalysis JDASet4 = new JDataAnalysis(folder + "wk3_kc_house_set_4_data.csv", false);

        l2 = 1e-9;
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

        System.out.println("\n---- Q3: QUIZ QUESTION: For the models learned with "
                + "regularization in each of these \ntraining sets, what are the "
                + "smallest and largest values you learned for the coefficient \n"
                + "of feature power_1? ----\n");

        l2 = 1.23e2;
        System.out.println("l2_large_penalty: " + l2);
        System.out.println("Set 1");
        modelSet1.setL2Penalty(l2);
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 2), JDASet1.getFeature("price"));
        modelSet1.getCoefficients();

        System.out.println("Set 2");
        modelSet2.setL2Penalty(l2);
        modelSet2.fit(JDASet2.getPolynomial("sqft_living", 2), JDASet2.getFeature("price"));
        modelSet2.getCoefficients();

        System.out.println("Set 3");
        modelSet3.setL2Penalty(l2);
        modelSet3.fit(JDASet3.getPolynomial("sqft_living", 2), JDASet3.getFeature("price"));
        modelSet3.getCoefficients();

        System.out.println("Set 4");
        modelSet4.setL2Penalty(l2);
        modelSet4.fit(JDASet4.getPolynomial("sqft_living", 2), JDASet4.getFeature("price"));
        modelSet4.getCoefficients();

        JDataAnalysis JDA_train_valid_shuffled_data = new JDataAnalysis(folder + "wk3_kc_house_train_valid_shuffled.csv", false);

        CrossValidation crossValidation = new CrossValidation();
        System.out.println("e: 10^3");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 3), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^3.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 3.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^4");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 4), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^4.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 4.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^5.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 5.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^6");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 6), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^6.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 6.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^7");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 7), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^7.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 7.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^8");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 8), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^8.5");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 8.5), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        System.out.println("e: 10^9");
        crossValidation.kFoldCrossValidation(10, Math.pow(10, 9), JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
        
        System.out.println("\n---- Q7: Using the best L2 penalty found above, train a model using all \n"
                + "training data. Which of the following ranges contains the RSS on the TEST \n"
                + "data of the model you learn with this L2 penalty? ----\n");
        JDataAnalysis JDA_test_data = new JDataAnalysis(folder + "wk3_kc_house_test_data.csv", false);
        RidgeRegression model_allData = new RidgeRegression(100);
        model_allData.fit(
                JDA_test_data.getPolynomial("sqft_living", 2), 
                JDA_test_data.getFeature("price"), 
                true);
        
    }
}
