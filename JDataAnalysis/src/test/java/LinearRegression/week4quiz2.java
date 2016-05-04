package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegression;
import com.gitia.jdataanalysis.RidgeRegression;
import com.gitia.jdataanalysis.data.CrossValidation;

/*
 * Copyright 2016 Roodschild, Matias <mroodschild@gmail.com>.
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
/**
 *
 * @author Roodschild, Matias <mroodschild@gmail.com>
 */
public class week4quiz2 {

    public static void main(String[] args) {
        //establezco la dirección relativa
        String folder = "src/main/resources/week4/quiz2/";
        JDataAnalysis kc_house_data = new JDataAnalysis(folder + "kc_house_data.csv");
        JDataAnalysis train_valid_shuffled_data = new JDataAnalysis(folder + "wk3_kc_house_train_valid_shuffled.csv");
        //JDataAnalysis test_valid_shuffled_data = new JDataAnalysis(folder + "wk3_kc_house_test_data.csv");

        //double poly15_data[][] = all_data.getPolynomial("sqft_living", 15);
        double poly15_train_valid_shuffled_data[][] = 
                train_valid_shuffled_data.getPolynomial("sqft_living", 15);
        //double poly15_test[][] = test_valid_shuffled_data.getPolynomial("sqft_living", 15);

        System.out.println("\n4. Quiz Question: What’s the learned value for the coefficient of feature power_1?");
        double l2_small_penalty = 1.5e-5;
        RidgeRegression model = new RidgeRegression(l2_small_penalty);
        model.fit(kc_house_data.getPolynomial("sqft_living", 15), kc_house_data.getFeature("price"));
        System.out.println("\n---- Q1: Coeficientes ---");
        model.getCoefficients();

        System.out.println("\n Polynomial Regression");
        MultipleLinearRegression polynomial_regression = new MultipleLinearRegression(kc_house_data.getPolynomial("sqft_living", 15), kc_house_data.getFeature("price"));
        polynomial_regression.getCoefficients();

        System.out.println("\nEntrenamos cada uno de los sets");

        String set1 = folder + "wk3_kc_house_set_1_data.csv";
        String set2 = folder + "wk3_kc_house_set_2_data.csv";
        String set3 = folder + "wk3_kc_house_set_3_data.csv";
        String set4 = folder + "wk3_kc_house_set_4_data.csv";
        JDataAnalysis set1_data = new JDataAnalysis(set1);
        JDataAnalysis set2_data = new JDataAnalysis(set2);
        JDataAnalysis set3_data = new JDataAnalysis(set3);
        JDataAnalysis set4_data = new JDataAnalysis(set4);
        double poly15_set1[][] = set1_data.getPolynomial("sqft_living", 15);
        double poly15_set2[][] = set2_data.getPolynomial("sqft_living", 15);
        double poly15_set3[][] = set3_data.getPolynomial("sqft_living", 15);
        double poly15_set4[][] = set4_data.getPolynomial("sqft_living", 15);
        l2_small_penalty = 1e-9;
        RidgeRegression model_set1 = new RidgeRegression(l2_small_penalty);
        RidgeRegression model_set2 = new RidgeRegression(l2_small_penalty);
        RidgeRegression model_set3 = new RidgeRegression(l2_small_penalty);
        RidgeRegression model_set4 = new RidgeRegression(l2_small_penalty);

        System.out.println("\nQuiz Question: For the models learned in each of \n"
                + "these training sets, what are the smallest and largest values\n"
                + "you learned for the coefficient of feature power_1?\n");
        System.out.println("Set 1");
        model_set1.fit(poly15_set1, set1_data.getFeature("price"));
        model_set1.getCoefficients();
        System.out.println("Set 2");
        model_set2.fit(poly15_set2, set2_data.getFeature("price"));
        model_set2.getCoefficients();
        System.out.println("Set 3");
        model_set3.fit(poly15_set3, set3_data.getFeature("price"));
        model_set3.getCoefficients();
        System.out.println("Set 4");
        model_set4.fit(poly15_set4, set4_data.getFeature("price"));
        model_set4.getCoefficients();

        System.out.println("\nEntrenamos cada uno de los sets");

        double l2_large_penalty = 1.23e2;
        //Util.mostrarMatriz(a, "poly_sqft_living");
        model_set1 = new RidgeRegression(l2_large_penalty);
        model_set2 = new RidgeRegression(l2_large_penalty);
        model_set3 = new RidgeRegression(l2_large_penalty);
        model_set4 = new RidgeRegression(l2_large_penalty);

        System.out.println("\nQUIZ QUESTION: For the models learned with \n"
                + "regularization in each of these training sets, what are the \n"
                + "smallest and largest values you learned for the coefficient \n"
                + "of feature power_1?\n");
        System.out.println("Set 1");
        model_set1.fit(poly15_set1, set1_data.getFeature("price"));
        model_set1.getCoefficients();
        System.out.println("Set 2");
        model_set2.fit(poly15_set2, set2_data.getFeature("price"));
        model_set2.getCoefficients();
        System.out.println("Set 3");
        model_set3.fit(poly15_set3, set3_data.getFeature("price"));
        model_set3.getCoefficients();
        System.out.println("Set 4");
        model_set4.fit(poly15_set4, set4_data.getFeature("price"));
        model_set4.getCoefficients();

        CrossValidation crossValidation = new CrossValidation();
        System.out.println("e: 1.2e5");
        crossValidation.kFoldCrossValidation(10, 1.2e5, poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^3");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 3), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^3.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 3.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^4");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 4), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^4.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 4.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^5.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 5.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^6");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 6), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^6.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 6.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^7");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 7), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^7.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 7.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^8");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 8), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^8.5");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 8.5), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
//        System.out.println("e: 10^9");
//        crossValidation.kFoldCrossValidation(10, Math.pow(10, 9), poly15_train_valid_shuffled_data, train_valid_shuffled_data.getFeature("price"));
    }
}
