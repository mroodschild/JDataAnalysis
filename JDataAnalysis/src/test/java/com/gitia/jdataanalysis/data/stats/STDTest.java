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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

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
    public void testFit() {
        System.out.println("fit");
        double[] x = {4, 5, 6, 7};
        double expMean = 5.5;
        double expSTD = 1.290994449;
        STD instance = new STD();
        instance.fit(x);
        assertEquals(expSTD, instance.getStandardDeviation(), 0.000000001);
        assertEquals(expMean, instance.getMean(), 0.0);
        System.out.println("Mean:\t" + instance.getMean() + "\tSTD:\t" + instance.getStandardDeviation());
    }

    /**
     * Test of eval method, of class STD.
     */
    @Test
    public void testEval() {
        System.out.println("eval");
        double x = 6.0;
        STD instance = new STD(2, 5);
        double expResult = 0.8;
        double result = instance.eval(x);
        assertEquals(expResult, result, 0.0);
        System.out.println(result);
    }

}