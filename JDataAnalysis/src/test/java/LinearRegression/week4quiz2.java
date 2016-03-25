package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegression;
import com.gitia.jdataanalysis.RidgeRegression;

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
        //indicamos los lugares de donde se tomaran los datos
        String all = folder + "kc_house_data.csv";
        String set1 = folder + "wk3_kc_house_set_1_data.csv";
        String set2 = folder + "wk3_kc_house_set_2_data.csv";
        String set3 = folder + "wk3_kc_house_set_3_data.csv";
        String set4 = folder + "wk3_kc_house_set_4_data.csv";
        String train_valid_shuffled = folder + "wk3_kc_house_train_valid_shuffled.csv";
        String test_valid_shuffled = folder + "wk3_kc_house_test_data.csv";
        
        JDataAnalysis all_data = new JDataAnalysis(all);
        JDataAnalysis set1_data = new JDataAnalysis(set1);
        JDataAnalysis set2_data = new JDataAnalysis(set2);
        JDataAnalysis set3_data = new JDataAnalysis(set3);
        JDataAnalysis set4_data = new JDataAnalysis(set4);
        JDataAnalysis train_valid_shuffled_data = new JDataAnalysis(train_valid_shuffled);
        JDataAnalysis test_valid_shuffled_data = new JDataAnalysis(test_valid_shuffled);
        
        
        double poly15_data[][] = all_data.getPolynomial("sqft_living", 15);
        double poly15_set1[][] = set1_data.getPolynomial("sqft_living", 15);
        double poly15_set2[][] = set2_data.getPolynomial("sqft_living", 15);
        double poly15_set3[][] = set3_data.getPolynomial("sqft_living", 15);
        double poly15_set4[][] = set4_data.getPolynomial("sqft_living", 15);
        double poly15_train[][] = train_valid_shuffled_data.getPolynomial("sqft_living", 15);
        double poly15_test[][] = test_valid_shuffled_data.getPolynomial("sqft_living", 15);

        double l2_small_penalty = 1.5e-5;
        RidgeRegression model = new RidgeRegression(l2_small_penalty, true);
        model.fit(poly15_data, all_data.getFeature("price"));
        System.out.println("\n4. Quiz Question: What’s the learned value for the coefficient of feature power_1?");
        model.getCoefficients();

        System.out.println("\n Polynomial Regression");
        MultipleLinearRegression polynomial_regression = new MultipleLinearRegression(poly15_data, all_data.getFeature("price"));
        polynomial_regression.getCoefficients();

        System.out.println("\nEntrenamos cada uno de los sets");
        l2_small_penalty = 1e-9;
        //Util.mostrarMatriz(a, "poly_sqft_living");
        RidgeRegression model_set1 = new RidgeRegression(l2_small_penalty, true);
        RidgeRegression model_set2 = new RidgeRegression(l2_small_penalty, true);
        RidgeRegression model_set3 = new RidgeRegression(l2_small_penalty, true);
        RidgeRegression model_set4 = new RidgeRegression(l2_small_penalty, true);
        
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
        l2_small_penalty = 1.23e2;
        //Util.mostrarMatriz(a, "poly_sqft_living");
        model_set1 = new RidgeRegression(l2_small_penalty, true);
        model_set2 = new RidgeRegression(l2_small_penalty, true);
        model_set3 = new RidgeRegression(l2_small_penalty, true);
        model_set4 = new RidgeRegression(l2_small_penalty, true);
        
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
        

    }
}
