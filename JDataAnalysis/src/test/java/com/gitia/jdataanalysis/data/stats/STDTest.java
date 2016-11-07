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
package com.gitia.jdataanalysis.data.stats;

import org.ejml.simple.SimpleMatrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matias
 */
public class STDTest {

    public STDTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
     * Test of fit method, of class STD.
     */
    @Test
    public void testFit_SimpleMatrix() {
        System.out.println("fit_SimpleMatrix");
        double[][] x = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12},
            {13, 14, 15}};
        SimpleMatrix input = new SimpleMatrix(x);
        STD instance = new STD();
        instance.fit(input);
        System.out.println("Mean");
        instance.getMean().print();
        System.out.println("STD");
        instance.getStandardDeviation().print();
    }

    /**
     * Test of fit method, of class STD.
     */
    @Test
    public void testFit_SimpleMatrix_Jorge() {
        System.out.println("fit_SimpleMatrix JORGE");
        double[][] x = {
            {10, 2, 3, 1},
            {4, 15, 3, 2},
            {3, 5, 12, 2}
        };
        SimpleMatrix input = new SimpleMatrix(x);
        STD instance = new STD();
        instance.fit(input);
        System.out.println("Mean");
        instance.getMean().print();
        System.out.println("STD");
        instance.getStandardDeviation().print();
        SimpleMatrix aux = instance.eval(input);
        instance.eval(input).print();
        instance.reverse(aux).print();
    }

    /**
     * Test of eval method, of class STD.
     */
    @Test
    public void testEval_SimpleMatrix() {
        System.out.println("Test Eval Matrix");
        double[][] x = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12},
            {13, 14, 15}};
        SimpleMatrix input = new SimpleMatrix(x);
        STD instance = new STD();
        instance.fit(input);

        instance.eval(input).print();

    }

    /**
     * Test of eval method, of class STD.
     */
    @Test
    public void testReverse_SimpleMatrix() {
        System.out.println("Test Reverse Matrix");
        double[][] x = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12},
            {13, 14, 15}};
        SimpleMatrix input = new SimpleMatrix(x);
        STD instance = new STD();
        instance.fit(input);
        SimpleMatrix a = instance.eval(input);
        instance.reverse(a).print();
    }

}
