/*
 * The MIT License
 *
 * Copyright 2018 Matías Roodschild <mroodschild@gmail.com>.
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
package org.gitia.jdataanalysis.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class OneHotTest {

    OneHot onehot;
    String[] words = {"hola", "mundo", "matias", "hola", "matias"};

    public OneHotTest() {
    }

    @Before
    public void setUp() {
        onehot = new OneHot(words);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNumberOfClasses method, of class OneHot.
     */
    @Test
    public void testGetNumberOfClasses() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testGetNumberOfClasses()");
        int expResult = 3;
        int result = onehot.getNumberOfClasses();
        assertEquals(expResult, result);
    }

    /**
     * Test of encode method, of class OneHot.
     */
    @Test
    public void testEncode_double() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testEncode_double()");
        double label = 1;
        OneHot instance = onehot;
        double[] expResult = {0, 1, 0};
        double[] result = instance.encode(label);
//        System.out.println("\nEsperado");
//        for (int i = 0; i < expResult.length; i++) {
//            System.out.printf("%.1f ",expResult[i]);
//        }
//        
//        System.out.println("\nResultado");
//        for (int i = 0; i < result.length; i++) {
//            System.out.printf("%.1f ",result[i]);
//        }
//        System.out.println("\n tag:\t"+instance.tag(label));
        assertArrayEquals(expResult, result, 0);
    }

    /**
     * Test of encode method, of class OneHot.
     */
    @Test
    public void testEncode_String() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testEncode_String()");
        String label = "mundo";
        OneHot instance = onehot;
        double[] expResult = {0, 1, 0};
        double[] result = instance.encode(label);
        assertArrayEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of encode method, of class OneHot.
     */
    @Test
    public void testEncode_StringArr() {
        System.out.println("encode");
        String[] tags = {"hola", "mundo", "matias", "mundo"};
        OneHot instance = onehot;
        double[] expResult = {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0};
        double[] result = instance.encode(tags);
        assertArrayEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of decode method, of class OneHot.
     */
    @Test
    public void testDecode() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testDecode()");
        double[] oneHot = {0, 0, 1};
        OneHot instance = onehot;
        double expResult = 2;
        double result = instance.decode(oneHot);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of tag method, of class OneHot.
     */
    @Test
    public void testTag_doubleArr() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testTag_doubleArr()");
        double[] oneHot = {0, 1, 0};
        OneHot instance = onehot;
        String expResult = "mundo";
        String result = instance.tag(oneHot);
        //System.out.println("RESULT\t" + result);
        assertEquals(expResult, result);

    }

    /**
     * Test of tag method, of class OneHot.
     */
    //@Ignore
    @Test
    public void testTag_double() {
        System.out.println("org.gitia.jdataanalysis.data.OneHotTest.testTag_double()");
        double code = 0;
        OneHot instance = onehot;
        String expResult = "hola";
        String result = instance.tag(code);
        assertEquals(expResult, result);
    }

}
