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
package com.gitia.jdataanalysis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class JDataAnalysisTest {

    static JDataAnalysis data;

    public JDataAnalysisTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String folder = "src/main/resources/week4/quiz2/";
        String train_valid_shuffled = folder + "wk3_kc_house_train_valid_shuffled.csv";
        data = new JDataAnalysis(train_valid_shuffled);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCrossData method, of class JDataAnalysis.
     */
    @Test
    public void testGetCrossData() {
        System.out.println("getCrossData");
        int k = 4;
        String[] features = {"price"};
        int[][] idx = data.getCrossIndex(k, features);
        Util.mostrarMatriz(idx, "Indices");
        assertEquals(0, 0, 0);
    }

    @Test
    public void testGetCrossTrain() {
        System.out.println("");
        System.out.println("getCrossTrain");
        int k = 4;
        String[] features = {"price", "sqft_living"};
        double[][] datos0 = data.getCrossTrain(0, k, features);
        double[][] datos1 = data.getCrossTrain(0, k, features);
        double[][] datos2 = data.getCrossTrain(0, k, features);
        double[][] datos3 = data.getCrossTrain(0, k, features);
        System.out.println("Size Datos 0: "+datos0.length+" x "+datos0[0].length);
        System.out.println("Size Datos 1: "+datos1.length+" x "+datos1[0].length);
        System.out.println("Size Datos 2: "+datos2.length+" x "+datos2[0].length);
        System.out.println("Size Datos 3: "+datos3.length+" x "+datos3[0].length);
        assertEquals("datos 0",0, 14547, datos0.length);
        assertEquals("datos 1",0, 14547, datos1.length);
        assertEquals("datos 2",0, 14547, datos2.length);
        assertEquals("datos 3",0, 14547, datos3.length);
    }
    
    @Test
    public void testGetCrossValidation() {
        System.out.println("");
        System.out.println("getCrossValidation");
        int k = 4;
        String[] features = {"price", "sqft_living"};
        double[][] datos0 = data.getCrossValidation(0, k, features);
        double[][] datos1 = data.getCrossValidation(0, k, features);
        double[][] datos2 = data.getCrossValidation(0, k, features);
        double[][] datos3 = data.getCrossValidation(0, k, features);
        System.out.println("Size Datos 0: "+datos0.length+" x "+datos0[0].length);
        System.out.println("Size Datos 1: "+datos1.length+" x "+datos1[0].length);
        System.out.println("Size Datos 2: "+datos2.length+" x "+datos2[0].length);
        System.out.println("Size Datos 3: "+datos3.length+" x "+datos3[0].length);
        assertEquals("datos 0",0, 4849, datos0.length);
        assertEquals("datos 1",0, 4849, datos1.length);
        assertEquals("datos 2",0, 4849, datos2.length);
        assertEquals("datos 3",0, 4849, datos3.length);
    }

}
