package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
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
        
        JDataAnalysis JDA_kc_house_data = new JDataAnalysis(folder + "kc_house_data.csv");
        JDataAnalysis JDA_train_valid_shuffled_data = new JDataAnalysis(folder + "wk3_kc_house_train_valid_shuffled.csv");

        System.out.println("\nQuiz Question: What’s the learned value for the coefficient of feature power_1?");
        double l2 = 1.5e-5;
        RidgeRegression model = new RidgeRegression(l2);
        System.out.println("l2:\t" + model.getL2Penalty());
        model.fit(JDA_kc_house_data.getPolynomial("sqft_living", 2), JDA_kc_house_data.getFeature("price"));
        System.out.println("\n---- Q1: Coeficientes, See power_1 coefficient ---");
        model.getCoefficients();

        System.out.println("\nEntrenamos cada uno de los sets");

        JDataAnalysis JDASet1 = new JDataAnalysis(folder + "wk3_kc_house_set_1_data.csv");
        JDataAnalysis JDASet2 = new JDataAnalysis(folder + "wk3_kc_house_set_2_data.csv");
        JDataAnalysis JDASet3 = new JDataAnalysis(folder + "wk3_kc_house_set_3_data.csv");
        JDataAnalysis JDASet4 = new JDataAnalysis(folder + "wk3_kc_house_set_4_data.csv");

        l2 = 1e-9;
        RidgeRegression modelSet1 = new RidgeRegression(l2);
        RidgeRegression modelSet2 = new RidgeRegression(l2);
        RidgeRegression modelSet3 = new RidgeRegression(l2);
        RidgeRegression modelSet4 = new RidgeRegression(l2);

        System.out.println("\nQuiz Question: For the models learned in each of \n"
                + "these training sets, what are the smallest and largest values\n"
                + "you learned for the coefficient of feature power_1?\n");
        System.out.println("Set 1");
        modelSet1.fit(JDASet1.getPolynomial("sqft_living", 2), JDASet1.getFeature("price"));
        modelSet1.getCoefficients();
        System.out.println("Set 2");
        modelSet2.fit(JDASet2.getPolynomial("sqft_living", 2), JDASet2.getFeature("price"));
        modelSet2.getCoefficients();
        System.out.println("Set 3");
        modelSet3.fit(JDASet3.getPolynomial("sqft_living", 2), JDASet3.getFeature("price"));
        modelSet3.getCoefficients();
        System.out.println("Set 4");
        modelSet4.fit(JDASet4.getPolynomial("sqft_living", 2), JDASet4.getFeature("price"));
        modelSet4.getCoefficients();

        System.out.println("\nEntrenamos cada uno de los sets");

        System.out.println("\nQUIZ QUESTION: For the models learned with \n"
                + "regularization in each of these training sets, what are the \n"
                + "smallest and largest values you learned for the coefficient \n"
                + "of feature power_1?\n");

        l2 = 1.23e2;
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

        CrossValidation crossValidation = new CrossValidation();
        System.out.println("e: 1.2e5");
        crossValidation.kFoldCrossValidation(10, 1.2e5, JDA_train_valid_shuffled_data.getPolynomial("sqft_living", 2), JDA_train_valid_shuffled_data.getFeature("price"));
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
    }
}
