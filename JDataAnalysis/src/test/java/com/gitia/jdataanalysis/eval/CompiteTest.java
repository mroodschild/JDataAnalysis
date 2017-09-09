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
package com.gitia.jdataanalysis.eval;

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
public class CompiteTest {

    public CompiteTest() {
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
     * Test of eval method, of class Compite.
     */
    @Test
    public void testEval() {
        System.out.println("eval");
        SimpleMatrix matrix = new SimpleMatrix(5, 3, true, 
                1, 2, 3, 3, 2, 1, 1, 3, 2, 1, 2, 3, 1, 2, 3);
        SimpleMatrix expResult = new SimpleMatrix(5, 3, true, 
                0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1);
        SimpleMatrix result = Compite.eval(matrix);
        assertArrayEquals(expResult.getMatrix().getData(), result.getMatrix().getData(), 0);
    }

}
