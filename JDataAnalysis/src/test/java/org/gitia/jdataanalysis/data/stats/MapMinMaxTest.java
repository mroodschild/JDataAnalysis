/*
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
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
package org.gitia.jdataanalysis.data.stats;

import org.gitia.jdataanalysis.data.stats.MapMinMax;
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
                7, 5, 3,
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
        assertArrayEquals(minExpected, instance.min.getMatrix().data, 0);
        assertArrayEquals(maxExpected, instance.max.getMatrix().data, 0);
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
                4, 0, 1,
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
                0, -10, 0,
                4, 0, 1,
                10, 10, 5);
        MapMinMax instance = new MapMinMax();
        instance.fit(x);
        x = instance.eval(x);
        SimpleMatrix result = instance.reverse(x);
    }

}
