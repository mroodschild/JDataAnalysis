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
package test;

import com.gitia.jdataanalysis.JDataAnalysis;
import com.gitia.jdataanalysis.Util;

/**
 *
 * @author Roodschild, Matias <mroodschild@gmail.com>
 */
public class TestPolynomial {

    public static void main(String[] args) {
        String folder = "src/main/resources/week4/quiz2/";
        String kc_house_data = folder + "kc_house_data.csv";
        JDataAnalysis jda = new JDataAnalysis(kc_house_data);
        double[][] sqft = jda.getPolynomial("sqft_living", 3);
        Util.mostrarMatriz(sqft, "SQFT");
    }
}
