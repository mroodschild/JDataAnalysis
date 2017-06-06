/*
 * The MIT License
 *
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
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
package test;

import java.util.Random;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class TestDiagMatrix {

    public static void main(String[] args) {
        double alpha = 0.25;
        double[] a = new double[4];
        SimpleMatrix H = SimpleMatrix.random(5, 3, 2, 4, new Random());
        H.print();
        SimpleMatrix Diag = SimpleMatrix.identity(H.numCols()).scale(alpha);
        Diag.print();
        H.transpose().print();
        H.transpose().mult(H).print();
        H.transpose().mult(H).plus(Diag).print();
        
    }

}
