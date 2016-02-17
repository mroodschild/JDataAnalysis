
import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.MultipleLinearRegression;
import com.gitia.jdataanalysis.Util;

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
/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week3quiz2 {

    public static void main(String[] args) {
        System.out.println("Open Data");
        
        JDataAnalysis poly1_data = new JDataAnalysis();
        poly1_data.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "poly1_data.csv");
        JDataAnalysis train_poly = new JDataAnalysis();
        train_poly.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_train_data_poly.csv");

        System.out.println("\nModel 1");
        MultipleLinearRegression model1 = new MultipleLinearRegression(
                poly1_data.getFeatures("power_1"), poly1_data.getFeature("price"));
        model1.getCoefficients();
        double[] model1_predict = model1.predictOutcome(poly1_data.getFeatures("power_1"));

        System.out.println("\nModel 2");
        MultipleLinearRegression model2 = new MultipleLinearRegression(
                train_poly.getFeatures("power_1", "power_2"), train_poly.getFeature("price"));
        model2.getCoefficients();
        
        double[] model2_predict = model2.predictOutcome(poly1_data.getFeatures("power_1", "power_2"));

        System.out.println("\nModel 3");
        MultipleLinearRegression model3 = new MultipleLinearRegression(
                train_poly.getFeatures("power_1", "power_2", "power_3"), train_poly.getFeature("price"));
        model3.getCoefficients();
        
        double[] model3_predict = model3.predictOutcome(poly1_data.getFeatures(
                "power_1", "power_2", "power_3"));

        System.out.println("\nModel 4");
        MultipleLinearRegression model4 = new MultipleLinearRegression(
                train_poly.getFeatures("power_1", "power_2", "power_3", "power_4"), train_poly.getFeature("price"));
        model4.getCoefficients();
        
        double[] model4_predict = model4.predictOutcome(poly1_data.getFeatures("power_1", "power_2", "power_3", "power_4"));

        System.out.println("\nModel 15");
        MultipleLinearRegression model15 = new MultipleLinearRegression(
                train_poly.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"
                ), train_poly.getFeature("price"));
        model15.getCoefficients();
        double[] model15_predict = model15.predictOutcome(poly1_data.getFeatures(
                "power_1", "power_2", "power_3", "power_4", "power_5",
                "power_6", "power_7", "power_8", "power_9", "power_10",
                "power_11", "power_12", "power_13", "power_14", "power_15"
        ));

//        Util.mostrarVector("model1 \t model2 \t model3 \t model4 \t model15"
//                , model1_predict, model2_predict, 
//                  model3_predict, model4_predict, model15_predict);

//        JDataAnalysis set_1 = new JDataAnalysis();
//        set_1.open("G:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "wk3_kc_house_set_1_data.csv");
//        JDataAnalysis set_2 = new JDataAnalysis();
//        set_2.open("G:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "wk3_kc_house_set_2_data.csv");
//        JDataAnalysis set_3 = new JDataAnalysis();
//        set_3.open("G:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "wk3_kc_house_set_3_data.csv");
//        JDataAnalysis set_4 = new JDataAnalysis();
//        set_4.open("G:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "wk3_kc_house_set_4_data.csv");
//        
//        System.out.println("\nModel set_1");
//        MultipleLinearRegression model_set1 = new MultipleLinearRegression(
//                set_1.getFeatures("power_15"), set_1.getFeature("price"));
//        model_set1.getCoefficients();
//        double[] model_set1_predict = model_set1.predictOutcome(set_1.getFeatures("power_15"));
//        
//        System.out.println("\nModel set_2");
//        MultipleLinearRegression model_set2 = new MultipleLinearRegression(
//                set_2.getFeatures("power_15"), set_2.getFeature("price"));
//        model_set2.getCoefficients();
//        double[] model_set2_predict = model_set1.predictOutcome(set_1.getFeatures("power_15"));
//        
//        System.out.println("\nModel set_3");
//        MultipleLinearRegression model_set3 = new MultipleLinearRegression(
//                set_3.getFeatures("power_15"), set_3.getFeature("price"));
//        model_set3.getCoefficients();
//        double[] model_set3_predict = model_set1.predictOutcome(set_1.getFeatures("power_15"));
//        
//        System.out.println("\nModel set_4");
//        MultipleLinearRegression model_set4 = new MultipleLinearRegression(
//                set_4.getFeatures("power_15"), set_4.getFeature("price"));
//        model_set4.getCoefficients();
//        double[] model_set4_predict = model_set1.predictOutcome(set_1.getFeatures("power_15"));
    }
}
