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

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class LabelEncoderTest {

    public LabelEncoderTest() {
    }

    /**
     * Test of getClasses method, of class LabelEncoder.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        String[] palabras = {"hola", "mundo"};
        LabelEncoder<String> instance = new LabelEncoder(palabras);
        List expResult = Arrays.asList(palabras);
        List result = instance.getClasses();
        assertEquals(expResult, result);
        System.out.println("" + instance.encode("hola"));
        System.out.println("" + instance.encode("mundo"));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumClasses method, of class LabelEncoder.
     */
    @Test
    public void testGetNumClasses() {
        System.out.println("getNumClasses");
        String[] palabras = {"hola", "mundo"};
        LabelEncoder<String> instance = new LabelEncoder(palabras);
        int expResult = 2;
        int result = instance.getNumClasses();
        assertEquals(expResult, result);
        System.out.println("numero de clases:\t" + result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class LabelEncoder.
     */
    @Test
    public void testEncode() {
        System.out.println("encode");
        String[] palabras = {"hola", "mundo"};
        LabelEncoder<String> instance = new LabelEncoder(palabras);
        int expResult = 1;
        int result = instance.encode("mundo");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of decode method, of class LabelEncoder.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        int index = 1;
        String[] palabras = {"hola", "mundo"};
        LabelEncoder<String> instance = new LabelEncoder(palabras);
        String expResult = "mundo";
        String result = instance.decode(index);
        assertEquals(expResult, result);
        System.out.println("Esperdado: mundo, Resultado: " + result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
