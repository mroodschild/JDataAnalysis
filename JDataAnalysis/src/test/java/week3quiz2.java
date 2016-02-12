
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
        JDataAnalysis sales = new JDataAnalysis();
        sales.open("G:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "kc_house_data.csv");
        JDataAnalysis poly1_data = new JDataAnalysis();
        poly1_data.open("G:\\coursera\\Machine Learning - Regression\\"
                + "week 3 - Assessing Performance\\06 - Programming assignment\\"
                + "poly1_data.csv");
        MultipleLinearRegression model1 = new MultipleLinearRegression(
                poly1_data.getFeatures("power_1"), poly1_data.getFeature("price"));
        model1.getCoefficients();
        double[] model1_predict = model1.predictOutcome(poly1_data.getFeatures("power_1"));
        Util.mostrarVector(model1_predict, "predic model1");
    }
}
