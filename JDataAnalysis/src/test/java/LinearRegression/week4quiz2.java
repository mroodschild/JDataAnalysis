package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.RidgeRegression;
import com.gitia.jdataanalysis.Util;

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
        double l2_small_penalty = 1.5e-5;
        JDataAnalysis jda = new JDataAnalysis(
                "D:\\coursera\\Machine Learning - Regression\\"
                + "week 4 - Ridge Regression\\quiz 2\\wk3_kc_house_set_1_data.csv"
        );
        double poly_sqft[][] = jda.getPolynomial("sqft_living", 15);
        RidgeRegression model = new RidgeRegression();
        //Util.mostrarMatriz(a, "poly_sqft_living");

    }
}
