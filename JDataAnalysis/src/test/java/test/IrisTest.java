/*
 * Copyright 2016 matias.
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

/**
 *
 * @author matias
 */
public class IrisTest {
    public static void main(String[] args) {
        String folder = "src/main/resources/week4/quiz2/";

        JDataAnalysis JDASet1 = new JDataAnalysis(folder + "wk3_kc_house_set_1_data.csv", true);
        
        
        JDataAnalysis jdaIrisIn = new JDataAnalysis("src/main/resources/iris/iris-input.csv", true,true);
    }
}
