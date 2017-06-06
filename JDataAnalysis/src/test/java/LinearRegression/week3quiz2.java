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
import com.gitia.jdataanalysis.MultipleLinearRegression;
import com.gitia.jdataanalysis.Util;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class week3quiz2 {

    public static void main(String[] args) {
        System.out.println("Open Data");

//        JDataAnalysis poly1_data = new JDataAnalysis();
//        poly1_data.open("D:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "poly1_data.csv");
//        JDataAnalysis train_poly = new JDataAnalysis();
//        train_poly.open("D:\\coursera\\Machine Learning - Regression\\"
//                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
//                + "wk3_kc_house_train_data_poly.csv");
//
//        System.out.println("\nModel 1");
//        MultipleLinearRegression model1 = new MultipleLinearRegression(
//                poly1_data.getFeatures("power_1"), poly1_data.getFeature("price"));
//        model1.getCoefficients();
//        double[] model1_predict = model1.predictOutcome(poly1_data.getFeatures("power_1"));
//
//        System.out.println("\nModel 2");
//        MultipleLinearRegression model2 = new MultipleLinearRegression(
//                train_poly.getFeatures("power_1", "power_2"), train_poly.getFeature("price"));
//        model2.getCoefficients();
//        
//        double[] model2_predict = model2.predictOutcome(poly1_data.getFeatures("power_1", "power_2"));
//
//        System.out.println("\nModel 3");
//        MultipleLinearRegression model3 = new MultipleLinearRegression(
//                train_poly.getFeatures("power_1", "power_2", "power_3"), train_poly.getFeature("price"));
//        model3.getCoefficients();
//        
//        double[] model3_predict = model3.predictOutcome(poly1_data.getFeatures(
//                "power_1", "power_2", "power_3"));
//
//        System.out.println("\nModel 4");
//        MultipleLinearRegression model4 = new MultipleLinearRegression(
//                train_poly.getFeatures("power_1", "power_2", "power_3", "power_4"), train_poly.getFeature("price"));
//        model4.getCoefficients();
//        
//        double[] model4_predict = model4.predictOutcome(poly1_data.getFeatures("power_1", "power_2", "power_3", "power_4"));
//
//        System.out.println("\nModel 15");
//        MultipleLinearRegression model15 = new MultipleLinearRegression(
//                train_poly.getFeatures(
//                        "power_1", "power_2", "power_3", "power_4", "power_5",
//                        "power_6", "power_7", "power_8", "power_9", "power_10",
//                        "power_11", "power_12", "power_13", "power_14", "power_15"
//                ), train_poly.getFeature("price"));
//        model15.getCoefficients();
//        double[] model15_predict = model15.predictOutcome(poly1_data.getFeatures(
//                "power_1", "power_2", "power_3", "power_4", "power_5",
//                "power_6", "power_7", "power_8", "power_9", "power_10",
//                "power_11", "power_12", "power_13", "power_14", "power_15"
//        ));
//        Util.mostrarVector("model1 \t model2 \t model3 \t model4 \t model15"
//                , model1_predict, model2_predict, 
//                  model3_predict, model4_predict, model15_predict);
        JDataAnalysis set_1 = new JDataAnalysis();
        set_1.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_set_1_data.csv");
        JDataAnalysis set_2 = new JDataAnalysis();
        set_2.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_set_2_data.csv");
        JDataAnalysis set_3 = new JDataAnalysis();
        set_3.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_set_3_data.csv");
        JDataAnalysis set_4 = new JDataAnalysis();
        set_4.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_set_4_data.csv");

        System.out.println("\nModel set_1");
        MultipleLinearRegression model_set1 = new MultipleLinearRegression(
                set_1.getFeatures("power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                set_1.getFeature("price"));
        model_set1.getCoefficients();
        double[] model_set1_predict = model_set1.predictOutcome(set_1.getFeatures(
                "power_1", "power_2", "power_3", "power_4", "power_5",
                "power_6", "power_7", "power_8", "power_9", "power_10",
                "power_11", "power_12", "power_13", "power_14", "power_15"));

        System.out.println("\nModel set_2");
        MultipleLinearRegression model_set2 = new MultipleLinearRegression(
                set_2.getFeatures("power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                set_2.getFeature("price"));
        model_set2.getCoefficients();
        double[] model_set2_predict = model_set2.predictOutcome(set_2.getFeatures(
                "power_1", "power_2", "power_3", "power_4", "power_5",
                "power_6", "power_7", "power_8", "power_9", "power_10",
                "power_11", "power_12", "power_13", "power_14", "power_15"));

        System.out.println("\nModel set_3");
        MultipleLinearRegression model_set3 = new MultipleLinearRegression(
                set_3.getFeatures("power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                set_3.getFeature("price"));
        model_set3.getCoefficients();
        double[] model_set3_predict = model_set3.predictOutcome(set_3.getFeatures(
                "power_1", "power_2", "power_3", "power_4", "power_5",
                "power_6", "power_7", "power_8", "power_9", "power_10",
                "power_11", "power_12", "power_13", "power_14", "power_15"));

        System.out.println("\nModel set_4");
        MultipleLinearRegression model_set4 = new MultipleLinearRegression(
                set_4.getFeatures("power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                set_4.getFeature("price"));
        model_set4.getCoefficients();
        double[] model_set4_predict = model_set4.predictOutcome(set_4.getFeatures(
                "power_1", "power_2", "power_3", "power_4", "power_5",
                "power_6", "power_7", "power_8", "power_9", "power_10",
                "power_11", "power_12", "power_13", "power_14", "power_15"));
//        Util.mostrarVector(model_set1_predict, "model 1");
//        Util.mostrarVector(model_set2_predict, "model 2");
//        Util.mostrarVector(model_set3_predict, "model 3");
//        Util.mostrarVector(model_set4_predict, "model 4");
        JDataAnalysis data_train = new JDataAnalysis();
        System.out.println("Train Data");
        data_train.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_train_data_poly.csv");
        JDataAnalysis data_test = new JDataAnalysis();
        System.out.println("Test Data");
        data_test.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_test_data.csv");
        JDataAnalysis data_val = new JDataAnalysis();
        System.out.println("Val Data");
        data_val.open("D:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "wk3_kc_house_valid_data.csv");

        double[] RSS_valid = new double[16];
        RSS_valid[0] = 0;
        System.out.println("\n\n-------------  Degree 1  -------------");
        MultipleLinearRegression degree1 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1"),
                data_train.getFeature("price"));
        degree1.getCoefficients();
        RSS_valid[1] = degree1.getRSS(
                data_val.getFeatures(
                        "power_1"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 2  -------------");
        MultipleLinearRegression degree2 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2"),
                data_train.getFeature("price"));
        degree2.getCoefficients();
        RSS_valid[2] = degree2.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 3  -------------");
        MultipleLinearRegression degree3 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3"),
                data_train.getFeature("price"));
        degree3.getCoefficients();
        RSS_valid[3] = degree3.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 4  -------------");
        MultipleLinearRegression degree4 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4"),
                data_train.getFeature("price"));
        degree4.getCoefficients();
        RSS_valid[4] = degree4.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 5  -------------");
        MultipleLinearRegression degree5 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5"),
                data_train.getFeature("price"));
        degree5.getCoefficients();
        RSS_valid[5] = degree5.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 6  -------------");
        MultipleLinearRegression degree6 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6"),
                data_train.getFeature("price"));
        degree6.getCoefficients();
        RSS_valid[6] = degree6.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 7  -------------");
        MultipleLinearRegression degree7 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7"),
                data_train.getFeature("price"));
        degree7.getCoefficients();
        RSS_valid[7] = degree7.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 8  -------------");
        MultipleLinearRegression degree8 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8"),
                data_train.getFeature("price"));
        degree8.getCoefficients();
        RSS_valid[8] = degree8.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 9  -------------");
        MultipleLinearRegression degree9 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9"),
                data_train.getFeature("price"));
        degree9.getCoefficients();
        RSS_valid[9] = degree9.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 10  -------------");
        MultipleLinearRegression degree10 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10"),
                data_train.getFeature("price"));
        degree10.getCoefficients();
        RSS_valid[10] = degree10.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 11  -------------");
        MultipleLinearRegression degree11 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11"),
                data_train.getFeature("price"));
        degree11.getCoefficients();
        RSS_valid[11] = degree11.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 12  -------------");
        MultipleLinearRegression degree12 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12"),
                data_train.getFeature("price"));
        degree12.getCoefficients();
        RSS_valid[12] = degree12.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 13  -------------");
        MultipleLinearRegression degree13 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13"),
                data_train.getFeature("price"));
        degree13.getCoefficients();
        RSS_valid[13] = degree13.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 14  -------------");
        MultipleLinearRegression degree14 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14"),
                data_train.getFeature("price"));
        degree14.getCoefficients();
        RSS_valid[14] = degree14.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14"),
                data_val.getFeature("price"));
        System.out.println("\n\n-------------  Degree 15  -------------");
        MultipleLinearRegression degree15 = new MultipleLinearRegression(
                data_train.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                data_train.getFeature("price"));
        degree15.getCoefficients();
        RSS_valid[15] = degree15.getRSS(
                data_val.getFeatures(
                        "power_1", "power_2", "power_3", "power_4", "power_5",
                        "power_6", "power_7", "power_8", "power_9", "power_10",
                        "power_11", "power_12", "power_13", "power_14", "power_15"),
                data_val.getFeature("price"));

        Util.mostrarVector(RSS_valid, "RSS Valid");

        double RSS_test = degree5.getRSS(data_test.getFeatures("power_1", "power_2", "power_3", "power_4", "power_5"), data_test.getFeature("price"));
        System.out.println("Test RSS: \t" + RSS_test);
    }
}
