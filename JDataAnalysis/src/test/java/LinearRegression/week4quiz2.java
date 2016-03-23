package LinearRegression;

import com.gitia.jdataanalysis.JDataAnalysis;
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
        String set1 = folder + "wk3_kc_house_set_1_data.csv";
        
        /*3. Let us revisit the 15th-order polynomial model using 
        the 'sqft_living' input. Generate polynomial features up to 
        degree 15 using `polynomial_sframe()` and fit a model with 
        these features. When fitting the model, use an L2 penalty 
        of 1.5e-5:*/
        double l2_small_penalty = 1.5e-5;
        JDataAnalysis jda = new JDataAnalysis(set1);
        double poly_sqft[][] = jda.getPolynomial("sqft_living", 15);
        RidgeRegression model = new RidgeRegression();
        //Util.mostrarMatriz(a, "poly_sqft_living");

    }
}
