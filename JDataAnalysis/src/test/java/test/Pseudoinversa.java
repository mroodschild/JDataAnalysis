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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Pseudoinversa {

    public static void main(String[] args) {
        SimpleMatrix A = new SimpleMatrix(8, 6, true,
                64, 2, 3, 61, 60, 6,
                9, 55, 54, 12, 13, 51,
                17, 47, 46, 20, 21, 43,
                40, 26, 27, 37, 36, 30,
                32, 34, 35, 29, 28, 38,
                41, 23, 22, 44, 45, 19,
                49, 15, 14, 52, 53, 11,
                8, 58, 59, 5, 4, 62);
        SimpleMatrix b = new SimpleMatrix(8, 1, true, 260, 260, 260, 260, 260, 260, 260, 260);
        System.out.println("A");
        A.print();
        System.out.println("B");
        b.print();
        System.out.println("x = A.pseudoInverse().mult(b);");
        SimpleMatrix x = A.pseudoInverse().mult(b);
        x.print("%.4f");
        System.out.println("y = A.solve(b);");
        SimpleMatrix y = A.solve(b);
        y.print();
        System.out.println("Norm(x):\t"+x.normF());
        System.out.println("Norm(y):\t"+y.normF());
        
        
        A.pseudoInverse().print("%.4f");
        //A.mult(A.pseudoInverse()).print("%.4f");
        A.pseudoInverse().mult(A).print("%.4f");
        
        
    }
}
