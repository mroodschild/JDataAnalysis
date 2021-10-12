/*
 * The MIT License
 *
 * Copyright 2017 
 *   Matías Roodschild <mroodschild@gmail.com>.
 *   Jorge Gotay Sardiñas <jgotay57@gmail.com>.
 *   Adrian Will <adrian.will.01@gmail.com>.
 *   Sebastián Rodriguez <sebastian.rodriguez@gitia.org>.
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
package org.gitia.jdataanalysis.data.stats;

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
