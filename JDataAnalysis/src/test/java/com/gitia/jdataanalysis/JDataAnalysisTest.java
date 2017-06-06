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
