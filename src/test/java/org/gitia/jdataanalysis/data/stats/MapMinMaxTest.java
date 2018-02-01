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
import static org.junit.Assert.*;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class MapMinMaxTest {

    public MapMinMaxTest() {
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
     * Test of fit method, of class MapMinMax.
     */
    @Test
    public void testFit_SimpleMatrix() {
        System.out.println("fit");
        SimpleMatrix x = new SimpleMatrix(3, 3, true,
                7, 5, 3.0,
                4, 2, 6,
                1, 8, 9);
        MapMinMax instance = new MapMinMax();
        instance.fit(x);
        x.print();
        System.out.println("Min");
        instance.min.print();
        System.out.println("Max");
        instance.max.print();
        double[] minExpected = {1, 2, 3};
        double[] maxExpected = {7, 8, 9};
        assertArrayEquals(minExpected, instance.min.getDDRM().data, 0);
        assertArrayEquals(maxExpected, instance.max.getDDRM().data, 0);
    }

    /**
     * Test of fit method, of class MapMinMax.
     */
    @Test
    public void testFit_3args() {
        System.out.println("testFit_3args");
        int numberInput = 0;
        int min = 0;
        int max = 3;
        MapMinMax instance = new MapMinMax();
        instance.fit(numberInput, min, max);
    }

    /**
     * Test of eval method, of class MapMinMax.
     */
    @Test
    public void testEval() {
        System.out.println("testEval");
        SimpleMatrix x = new SimpleMatrix(3, 3, true,
                0, -10, 0,
                4, 0, 1.0,
                10, 10, 5);
        MapMinMax instance = new MapMinMax();
        instance.fit(x);
        SimpleMatrix result = instance.eval(x);
    }

    /**
     * Test of reverse method, of class MapMinMax.
     */
    @Test
    public void testReverse() {
        System.out.println("testReverse");
        SimpleMatrix x = new SimpleMatrix(3, 3, true,
                0, -10, 0.0,
                4, 0, 1,
                10, 10, 5);
        MapMinMax instance = new MapMinMax();
        instance.fit(x);
        x = instance.eval(x);
        SimpleMatrix result = instance.reverse(x);
    }

}
