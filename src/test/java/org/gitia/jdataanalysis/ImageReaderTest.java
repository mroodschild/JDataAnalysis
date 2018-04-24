/*
 * The MIT License
 *
 * Copyright 2018 orteg.
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
package org.gitia.jdataanalysis;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author orteg
 */
public class ImageReaderTest {
    
    public ImageReaderTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getImageFromArray method, of class ImageReader.
     */
//    @Ignore
//    @Test
//    public void testGetImageFromArray() throws Exception {
//        System.out.println("getImageFromArray");
//        double[] pixels = null;
//        int width = 0;
//        int height = 0;
//        ImageReader instance = new ImageReader();
//        BufferedImage expResult = null;
//        BufferedImage result = instance.getImageFromArray(pixels, width, height);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of open method, of class ImageReader.
     */
    @Ignore
    public void testOpen() {
        System.out.println("open");
        String url = "";
        ImageReader instance = new ImageReader();
        double[] expResult = null;
        //double[] result = instance.open(url);
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ImageReader.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Image img = null;
        ImageReader instance = new ImageReader();
        instance.save(img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
