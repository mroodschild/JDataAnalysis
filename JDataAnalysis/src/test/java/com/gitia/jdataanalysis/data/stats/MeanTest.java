/*
 * Copyright 2016 Matías Roodschild <mroodschild@gmail.com>.
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
import static org.junit.Assert.*;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class MeanTest {

    public MeanTest() {
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
     * Test of mean method, of class Mean.
     */
    @Test
    public void testMean_doubleArr() {
        System.out.println("testMean_doubleArr");
        double[] datos = {4, 5, 6, 7};
        double expResult = 5.5;
        double result = Mean.mean(datos);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of mean method, of class Mean.
     */
    @Test
    public void testMean_SimpleMatrix() {
        System.out.println("mean");
        double data[][] = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        SimpleMatrix datos = new SimpleMatrix(data);
        SimpleMatrix expResult = new SimpleMatrix(1, 3, false, 4, 5, 6);
        SimpleMatrix result = Mean.mean(datos);
        assertArrayEquals(expResult.getMatrix().getData(), result.getMatrix().getData(), 0);
    }

}
